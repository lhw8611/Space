package dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import vo.Member;

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

	public int JoinCheckDAO(Member member) {
		System.out.println("[4]JoinCheckDAO");
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {

			pstmt = con.prepareStatement("insert into member values(?, ?, ?, ?, ?, ?, ?, now())");
			System.out.println("[4]member.id 값 : " + member.getMem_id());
			System.out.println("[4]member.getMem_name 값 : " + member.getMem_name());
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pass());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_add());
			pstmt.setString(5, member.getMem_email());
			pstmt.setString(6, member.getMem_grade());
			pstmt.setString(7, member.getMem_tel());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[4]JoinCheckDAO 에러");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;
	}

	public boolean LoginCheckDAO(Member member) {
		System.out.println("[4]LoginCheckDAO");
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
}
