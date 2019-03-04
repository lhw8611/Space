package dao;

import static db.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.QnaBean;

public class qnaDAO {
	DataSource ds;
	Connection con;
	private static qnaDAO qnadao;

	public static qnaDAO getInstance() {
		if (qnadao == null) {
			qnadao = new qnaDAO();
		}
		return qnadao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 글의 개수 구하기
	public int selectListCount() {
		System.out.println("[4]selectListCount");
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select count(*) from qna");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	// 글 목록 보기
	public ArrayList<QnaBean> selectArticleList(int page, int limit) {
		System.out.println("[4]selectArticleList");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QnaBean> arryqna = new ArrayList<QnaBean>();
		QnaBean voqna = null;
		int startrow = (page - 1) * 10; // 읽기 시작할 row 번호
		try {
			pstmt = con.prepareStatement("select * from qna order by qna_num desc limit ?,?");
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				voqna = new QnaBean();
				voqna.setQna_num(rs.getInt("qna_num"));
				voqna.setQna_question(rs.getString("qna_question"));
				voqna.setQna_answer(rs.getString("qna_answer"));
				voqna.setQna_date(rs.getDate("qna_date"));
				arryqna.add(voqna);
			}
		} catch (Exception ex) {
			System.out.println("getBoardList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return arryqna;
	}
	
	//qna 글쓰기 
	public int UserCheckDAO(QnaBean voqna) {
		System.out.println("[4]UserCheckDAO");
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			pstmt = con.prepareStatement("insert into qna values( null, ?, ?, now())");
			pstmt.setString(1, voqna.getQna_question());
			pstmt.setString(2, voqna.getQna_answer());

			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
}
