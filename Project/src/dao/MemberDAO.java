package dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.MemberBean;

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

	public int JoinCheckDAO(MemberBean member) {
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
	
	//멤버 정보폼
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
			if(rs.next()) {
				member = new MemberBean();
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_add(rs.getString("mem_add"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_grade(rs.getString("mem_grade"));
				member.setMem_tel(rs.getString("mem_tel"));
				member.setMem_zip(rs.getString("mem_tel"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_date(rs.getString("mem_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	//멤버 정보수정 액션
	public int modifyDAO(MemberBean member) {
		System.out.println("[4]MemberDAO.modifyDAO");
		PreparedStatement pstmt = null;
		String sql = "update member set mem_pass = ?, mem_name =?, mem_add = ?, "
				+ "mem_email = ?, mem_tel = ?, mem_zip = ?, mem_add2 = ? where mem_id = ?";
		int count=0;
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
		}finally {
			close(pstmt);
		}
		
		return count;
	}

	//관리자 회원정보 목록보기
	public ArrayList<MemberBean> getSelectList() {
		System.out.println("[4]MemberDAO.getSelectList");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberBean> list= new ArrayList<MemberBean>();
		MemberBean member = null;
		try {
			pstmt = con.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberBean();
				//id pass name add email grade tel date zip add2
				member.setMem_id(rs.getString("mem_iD"));
				member.setMem_pass(rs.getString("mem_pass"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_zip(rs.getString("mem_zip"));
				member.setMem_add(rs.getString("mem_add"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_grade(rs.getString("mem_grade"));
				member.setMem_tel(rs.getString("mem_tel"));
				member.setMem_date(rs.getString("mem_date"));
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

	public int UserDeleteDAO(String id) {
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
}
