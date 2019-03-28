package dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.MemberBean;
import vo.PointBean;

public class MemberDAO {
	DataSource ds;
	Connection con;
	private static MemberDAO memberDAO;

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int JoinCheckDAO(MemberBean member) { //회원가입
		System.out.println("[4]MemberDAO.JoinCheckDAO");
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {

			pstmt = con.prepareStatement("insert into member values(?, ?, ?, ?, ?, ?, ?, now(),?,?)");
			System.out.println("[4]member.id 값 : " + member.getMem_id());
			System.out.println("[4]member.getMem_name 값 : " + member.getMem_name());
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pass());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_add());
			pstmt.setString(5, member.getMem_email());
			pstmt.setString(6, member.getMem_grade());
			pstmt.setString(7, member.getMem_tel());
			pstmt.setString(8, member.getMem_zip());
			pstmt.setString(9, member.getMem_add2());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[4]JoinCheckDAO 에러");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}
	
	public int pointAddDAO(PointBean pointbean) { //포인트 추가
		System.out.println("[4]MemberDAO.PointAddDAO");
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			if(pointbean.getPo_state().equals("join")) {
				pointbean.setPo_total(1000);
			}
			pstmt = con.prepareStatement("insert into point(po_num, mem_id, po_state, po_point, po_date, po_total) value(null, ?, ?, ?, now(), ?)");
			pstmt.setString(1, pointbean.getMem_id());
			pstmt.setString(2, pointbean.getPo_state());
			pstmt.setInt(3, pointbean.getPo_point());
			pstmt.setInt(4, pointbean.getPo_total());
			updateCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public ArrayList<PointBean> pointList(String id) { //포인트 조회
		System.out.println("[4]MemberDAO.pointList");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PointBean> arraypointbean = null;
		PointBean pointbean = null;
		try {
			pstmt = con.prepareStatement("select * from point where mem_id=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				arraypointbean = new ArrayList<PointBean>();
				rs.beforeFirst();
				while(rs.next()) {
					pointbean = new PointBean();
					pointbean.setPo_num(rs.getInt("po_num"));
					pointbean.setMem_id(rs.getString("mem_id"));
					pointbean.setPo_state(rs.getString("po_state"));
					pointbean.setPo_point(rs.getInt("po_point"));
					pointbean.setPo_total(rs.getInt("po_total"));
					pointbean.setPo_date(rs.getDate("po_date"));
					arraypointbean.add(pointbean);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return arraypointbean;
	}

	public boolean LoginCheckDAO(MemberBean member) {
		System.out.println("[4]MemberDAO.LoginCheckDAO");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean RightUser = false;
		try {
			pstmt = con.prepareStatement("select * from member where mem_id=? and mem_pass=?");
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pass());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (member.getMem_pass().equals(rs.getString("mem_pass"))) {
					RightUser = true;
				} else {
					RightUser = false;
				}
			}

		} catch (SQLException e) {
			System.out.println("LoginCheckDAO 에러");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return RightUser;
	}

	// 멤버 정보폼
	public MemberBean MemberInfoDAO(String id) {
		System.out.println("[4]MemberDAO.MemberInfoDAO");
		PreparedStatement pstmt = null;
		String sql = "select * from member where mem_id=?";
		ResultSet rs = null;
		MemberBean member = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberBean();
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_add(rs.getString("mem_add"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_grade(rs.getString("mem_grade"));
				member.setMem_tel(rs.getString("mem_tel"));
				member.setMem_zip(rs.getString("mem_zip"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_date(rs.getDate("mem_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	// 멤버 정보수정 액션
	public int modifyDAO(MemberBean member) {
		System.out.println("[4]MemberDAO.modifyDAO");
		PreparedStatement pstmt = null;
		String sql = "update member set mem_pass = ?, mem_name =?, mem_add = ?, "
				+ "mem_email = ?, mem_tel = ?, mem_zip = ?, mem_add2 = ? where mem_id = ?";
		int count = 0;
		System.out.println("[4]sql문 출력 : " + sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMem_pass());
			pstmt.setString(2, member.getMem_name());
			pstmt.setString(3, member.getMem_add());
			pstmt.setString(4, member.getMem_email());
			pstmt.setString(5, member.getMem_tel());
			pstmt.setString(6, member.getMem_zip());
			pstmt.setString(7, member.getMem_add2());
			pstmt.setString(8, member.getMem_id());
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return count;
	}

	// 관리자 회원정보 목록보기
	public ArrayList<MemberBean> getSelectList(int page, int limit, String keyWord) {
		System.out.println("[4]MemberDAO.getSelectList");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		MemberBean member = null;
		int startrow = (page - 1) * 10;
		try {
			if (keyWord == null || keyWord.trim().equals("")) {
				System.out.println("keyword null");
				pstmt = con.prepareStatement("select * from member order by mem_id desc limit ?, ?");
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, limit);
			} else {
				System.out.println("keyword not null");
				pstmt = con.prepareStatement("select * from member where mem_id like ? order by mem_id limit ?, ?");
				pstmt.setString(1, "%" + keyWord + "%");
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, limit);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new MemberBean();
				// id pass name add email grade tel date zip add2
				member.setMem_id(rs.getString("mem_iD"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_zip(rs.getString("mem_zip"));
				member.setMem_add(rs.getString("mem_add"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_grade(rs.getString("mem_grade"));
				member.setMem_tel(rs.getString("mem_tel"));
				member.setMem_date(rs.getDate("mem_date"));
				list.add(member);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return list;
	}

	public int UserDeleteDAO(String id) { // 회원탈퇴
		System.out.println("[4]MemberDAO.UserDeleteDAO");
		PreparedStatement pstmt = null;
		String sql = "delete from member where mem_id=?";
		int deleteCount = 0;
		System.out.println("[4]id값 넘어왔나 확인 : " + id);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			close(pstmt);
		}

		return deleteCount;
	}

	public boolean IdCheck(String id) { // 아이디체크
		System.out.println("[4]IdCheck");
		PreparedStatement pstmt = null;
		String sql = "select * from member where mem_id = ?";
		boolean IdCheckResult = false;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				IdCheckResult = true;
			} else {
				IdCheckResult = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return IdCheckResult;
	}

	public int selectListCount(String keyWord) { // 멤버리스트 행갯수
		System.out.println("[4]MemberDAO.selectListCount");
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (keyWord == null || keyWord.trim().equals("")) {
				pstmt = con.prepareStatement("select count(*) from member");
			} else {
				pstmt = con.prepareStatement("select count(*) from member where mem_id like ?");
				pstmt.setString(1,"%"+ keyWord +"%");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
}
