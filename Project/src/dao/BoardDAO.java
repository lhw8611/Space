package dao;

import static db.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.NoticeBean;
import vo.ProductBean;
import vo.QtyProViewBean;
import vo.ReviewBean;

public class BoardDAO {
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;

	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 공지 글의 개수 구하기
	public int selectListCount() {
//			System.out.println("selectListCount dao 진입");
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from notice";
		// 무언가 검색했을때
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
//				System.out.println("getListCount 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
//			System.out.println("listCount(글번호) : " + listCount);
		return listCount;
	}
//상품리스트 개수
	public int selectListCount2(String sW) {
//			System.out.println("selectListCount dao 진입");
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from product where pro_show='o' ";
		
		if (sW!=null) {
			sql += " and pro_name like '%" + sW + "%'";
		}
		System.out.println("SQL:"+sql);
		// 무언가 검색했을때
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
//			System.out.println("listCount(글번호) : " + listCount);
		return listCount;
	}

	// 공지사항 글 등록
	public int noticeWrite(NoticeBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;

		try {
			sql = "insert into notice values (null,?,?,now(),?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, article.getNo_title());
			pstmt.setString(2, article.getNo_content());
			pstmt.setInt(3, article.getNo_count());

			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {

			System.out.println("NoticeWrite 에러 ");
			ex.printStackTrace();

		} finally {
			close(rs);
			close(pstmt);

		}
		return insertCount;
	}

	// 상품 상세보기
	public ProductBean productInfo(int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBean productBean = null;
		try {
			pstmt = con.prepareStatement("select * from product where pro_code=?");
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				productBean = new ProductBean();
				productBean.setPro_code(rs.getInt("pro_code"));
				productBean.setPro_name(rs.getString("pro_name"));
				productBean.setPro_price(rs.getInt("pro_price"));
				productBean.setPro_category(rs.getString("pro_category"));
				productBean.setPro_content(rs.getString("pro_content"));
				productBean.setPro_image(rs.getString("pro_image"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO.productInfo error");
		} finally {
			close(rs);
			close(pstmt);
		}
		return productBean;
	}
	
	// notice 목록
	public ArrayList<NoticeBean> selectArticleList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from notice order by no_num desc limit ? , ?";
		ArrayList<NoticeBean> articleList = new ArrayList<NoticeBean>();
		NoticeBean notice = null;
		int startrow = (page - 1) * 10;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				notice = new NoticeBean();
				notice.setNo_num(rs.getInt("no_num"));
				notice.setNo_title(rs.getString("no_title"));
				notice.setNo_content(rs.getString("no_content"));
				notice.setNo_date(rs.getDate("no_date"));
				notice.setNo_count(rs.getInt("no_count"));

				articleList.add(notice);

			}

		} catch (Exception ex) {
			System.out.println("NoticeList 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	// notice 글 상세 보기
	public NoticeBean selectArticle(int no_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeBean noticeBean = null;

		try {
			pstmt = con.prepareStatement("select * from notice where no_num = ?");
			pstmt.setInt(1, no_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				noticeBean = new NoticeBean();
				noticeBean.setNo_num(rs.getInt("no_num"));
				noticeBean.setNo_title(rs.getString("no_title"));
				noticeBean.setNo_content(rs.getString("no_content"));
				noticeBean.setNo_date(rs.getDate("no_date"));
				noticeBean.setNo_count(rs.getInt("no_count"));
			}
		} catch (Exception ex) {
			System.out.println("getDetail에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return noticeBean;
	}

	// notice 조회수
	public int updateReadCount(int no_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update notice set no_count = no_count+1 where no_num=" + no_num;

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate 에러 : ");
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;

	}

	// 상품 등록
	public int productWrite(ProductBean article) {
		System.out.println("상품 등록 DAO");
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;

		try {
			sql = "insert into product values (null,?,?,?,?,?,?,null,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, article.getPro_name());
			pstmt.setInt(2, article.getPro_price());
			pstmt.setString(3, article.getPro_category());
			pstmt.setString(4, article.getPro_content());
			pstmt.setString(5, article.getPro_image());
			pstmt.setString(6, "x");
			pstmt.setInt(7, 0);

			insertCount = pstmt.executeUpdate();
		} catch (Exception ex) {

			System.out.println("NoticeWrite 에러 ");
			ex.printStackTrace();

		} finally {
			close(pstmt);

		}
		return insertCount;
	}

	// 상품등록 - qty-modifyCount 추가
	public int QtyModifyCount() {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int insertCount = 0;
		String sql = "select * from product order by pro_code desc";
		String sql2 = "insert into qty(qty_modifyCount, pro_code) values(0,?);";

		// 새로 등록한 상품의 코드를 추출
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int getPro_code = rs.getInt("pro_code");
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, getPro_code);
				System.out.println(sql2);
				insertCount = pstmt2.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	// 상품리스트
	public ArrayList<ProductBean> selectProductList(int page, int limit, String sort, String sW) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("DAO에서 sort :" + sort);
		/*
		 * String sql =
		 * "select * from product where pro_show='o' order by pro_code desc limit ? , ?"
		 * ;
		 */
		String sql = "select * from product where pro_show='o'";
		//검색일 경우
		if(sW!=null) {
			sql += " and pro_name like '%"+sW+"%' ";
		}
		
		
		// 조회수 순
		if (sort.equals("count")) {
			sql += "order by pro_count desc limit ? , ?";
			// 판매량 순
		} else if (sort.equals("sell")) {
			sql = "SELECT pro_code FROM order_detail GROUP BY pro_code ORDER BY COUNT(*) DESC limit ? , ?";
			// 리뷰 순
		} else if (sort.equals("review")) {
			sql = "";
			// 디폴트 - 등록일 순
		} else {
			sql += "order by pro_date desc limit ? , ?";
		}
		

		System.out.println("sql문 :" + sql);
		ArrayList<ProductBean> articleList = new ArrayList<ProductBean>();
		ProductBean product = null;
		int startrow = (page - 1) * 12;
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);

			rs = pstmt.executeQuery();
			
			

				while (rs.next()) {
					product = new ProductBean();
					product.setPro_code(rs.getInt("pro_code"));
					product.setPro_name(rs.getString("pro_name"));
					product.setPro_price(rs.getInt("pro_price"));
					product.setPro_category(rs.getString("pro_category"));
					product.setPro_content(rs.getString("pro_content"));
					product.setPro_image(rs.getString("pro_image"));
					articleList.add(product);

				}
		} catch (Exception ex) {
			System.out.println("NoticeList 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}


	//리뷰 출력
	public ArrayList<ReviewBean> reviewList(int page, int limit, int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBean reviewBean = null;
		ArrayList<ReviewBean> reviewList = null;
		String sql = "select * from review where pro_code=? order by rev_num desc limit ?,?";
		int startrow = (page - 1) * 5;
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, pro_code);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				reviewList = new ArrayList<ReviewBean>();
				rs.beforeFirst();
				
				
				while(rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setContent(rs.getString("rev_content"));
				reviewBean.setMem_id(rs.getString("mem_id"));
				reviewBean.setPro_code(rs.getInt("pro_code"));
				reviewBean.setRev_date(rs.getDate("rev_date"));
				reviewBean.setRev_img(rs.getString("rev_img"));
				reviewBean.setRev_num(rs.getInt("rev_num"));
				reviewBean.setRev_star(rs.getInt("rev_star"));
				reviewList.add(reviewBean);
				
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("OrderDAO.productInfo error");
		} finally {
			close(rs);
			close(pstmt);
		}
		return reviewList;

	}
	//리뷰 개수 조회
	public int selectListCountReview(int pro_code) {
//		System.out.println("selectListCount dao 진입");
	int listCount = 0;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select count(*) from review where pro_code="+pro_code;
	// 무언가 검색했을때
	try {
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			listCount = rs.getInt(1);
		}
	} catch (Exception ex) {
			System.out.println("getListCount 에러 :");
			ex.printStackTrace();
	} finally {
		close(rs);
		close(pstmt);
	}
//		System.out.println("listCount(글번호) : " + listCount);
	return listCount;
}
	
	// 상품 조회수
	public int proUpdateReadCount(int pro_code) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update product set pro_count = pro_count+1 where pro_code=" + pro_code;

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate 에러 : ");
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;

	}
	//상품 샀는지 비교
	public boolean reviewCheck(String mem_id, int pro_code) {
		System.out.println("리뷰체크 DAO");
		boolean reviewCheck = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from orodproview where mem_id=? and pro_code=?";
//		sql +=  "and od_state='DeleCom'"
//		od_state로 변경 후 사용
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setInt(2,pro_code);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				reviewCheck = true;
			}
		} catch (SQLException ex) {
			System.out.println("리뷰등록 에러 : ");
			ex.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return reviewCheck;
	}
	
	//리뷰 등록
	public int reviewReg(ReviewBean reviewBean) {
		System.out.println("리뷰레지  dao");
		PreparedStatement pstmt = null;
		String sql = "insert into review value(null,?,?,?,now(),?,?)";
		
		int updateCheck = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewBean.getMem_id());
			pstmt.setInt(2,reviewBean.getRev_star());
			pstmt.setString(3, reviewBean.getContent());
			pstmt.setString(4,reviewBean.getRev_img());
			pstmt.setInt(5, reviewBean.getPro_code());
			
			 updateCheck = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("리뷰등록 에러 : ");
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCheck;

		
	}
	
	public int totalQty (int pro_code) {
		System.out.println("남은 qty계산 DAO");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qty where pro_code=? and qty_inout='in'";
		int totalQty = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				totalQty += rs.getInt("qty_qty");
			}
			
			
			System.out.println(totalQty);
			
		} catch (SQLException ex) {
			System.out.println("남은 qty계산 에러 : ");
			ex.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return totalQty;
		
	}
	

}
