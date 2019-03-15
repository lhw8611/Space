package dao;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.ProductBean;
import vo.QtyProViewBean;

public class AdminDAO {

	DataSource ds;
	Connection con;
	private static AdminDAO adminDAO;

	private AdminDAO() {

	}

	public static AdminDAO getInstance() {
		if (adminDAO == null) {
			adminDAO = new AdminDAO();
		}
		return adminDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// qty + Pro뷰
	public ArrayList<QtyProViewBean> qtyProViewList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qtyproview where qty_modifyCount=0";
		ArrayList<QtyProViewBean> qtyList = null;
		QtyProViewBean qtybean = null;

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qtyList = new ArrayList<QtyProViewBean>();
				rs.beforeFirst();
			}
			while (rs.next()) {
				qtybean = new QtyProViewBean();
				qtybean.setQty_num(rs.getInt("qty_num"));
				qtybean.setPro_code(rs.getInt("Pro_code"));
				qtybean.setQty_qty(rs.getInt("qty_qty"));
				qtybean.setQty_inout(rs.getString("qty_inout"));
				qtybean.setQty_date(rs.getDate("qty_date"));
				qtybean.setQty_note(rs.getString("qty_note"));
				qtybean.setPro_name(rs.getString("pro_name"));
				qtybean.setPro_price(rs.getInt("pro_price"));
				qtybean.setPro_category(rs.getString("pro_category"));
				qtybean.setPro_content(rs.getString("pro_content"));
				qtybean.setPro_image(rs.getString("pro_image"));
				qtybean.setQty_modifycount(rs.getInt("qty_modifycount"));
				qtybean.setPro_show(rs.getString("pro_show"));
				qtyList.add(qtybean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return qtyList;
	}

	// 입출력 리스트
	public ArrayList<QtyProViewBean> qtyProViewInOut(int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qtyproview where pro_code=? AND qty_modifyCount>0";
		ArrayList<QtyProViewBean> qtyInOutList = null;
		QtyProViewBean qtybean = null;

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				qtyInOutList = new ArrayList<QtyProViewBean>();
				rs.beforeFirst();

			}
			while (rs.next()) {
				qtybean = new QtyProViewBean();
				qtybean.setQty_num(rs.getInt("qty_num"));
				qtybean.setPro_code(rs.getInt("Pro_code"));
				qtybean.setQty_qty(rs.getInt("qty_qty"));
				qtybean.setQty_inout(rs.getString("qty_inout"));
				qtybean.setQty_date(rs.getDate("qty_date"));
				qtybean.setQty_note(rs.getString("qty_note"));
				qtybean.setPro_name(rs.getString("pro_name"));
				qtybean.setPro_price(rs.getInt("pro_price"));
				qtybean.setPro_category(rs.getString("pro_category"));
				qtybean.setPro_content(rs.getString("pro_content"));
				qtybean.setPro_image(rs.getString("pro_image"));
				qtybean.setQty_modifycount(rs.getInt("qty_modifycount"));
				qtybean.setPro_show(rs.getString("pro_show"));
				qtyInOutList.add(qtybean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return qtyInOutList;
	}

	// 입출고 등록을 위한 정보 추출
	public int qtyProView(int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qtyproview where pro_code=? order by qty_modifyCount desc";
		int qty_modifyCount = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				qty_modifyCount = rs.getInt("qty_modifyCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return qty_modifyCount;
	}

	// 입출고 입력
	public int qtyInOutRegister(int pro_code, String inout, int qty, String note, int qty_modifyCount) {
		PreparedStatement pstmt = null;
		int inoutCheck = 0;
		String sql = "insert into qty values (null,?,?,?,now(),?,?)";
//		int total = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			pstmt.setInt(2, qty);
			pstmt.setString(3, inout);
			pstmt.setInt(4, qty_modifyCount+1);
			pstmt.setString(5, note);
			

			inoutCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return inoutCheck;
	}
	//입출고 내역 삭제
	public int qtyInOutDelete(int qty_num) {
		PreparedStatement pstmt = null;
		int inoutDeleteCheck = 0;
		String sql = "delete from qty where qty_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qty_num);

			inoutDeleteCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return inoutDeleteCheck;
	}
	
	//상품 Show
	public int ProShow(int pro_code) {
		PreparedStatement pstmt = null;
		int updateCheck = 0;
		String sql = "update product set pro_show='o' where pro_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);

			updateCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCheck;
	}
	//상품 Hide
	public int ProHide(int pro_code) {
		PreparedStatement pstmt = null;
		int updateCheck = 0;
		String sql = "update product set pro_show='x' where pro_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);

			updateCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCheck;
	}
	
	//상품 수정
	public int ProModify(ProductBean probean) {
		PreparedStatement pstmt = null;
		int updateCheck = 0;
		String sql = "update product set pro_name=?, pro_price=?, pro_category=?, pro_content=?, pro_image=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,probean.getPro_name());
			pstmt.setInt(2, probean.getPro_price());
			pstmt.setString(3, probean.getPro_category());
			pstmt.setString(4, probean.getPro_content());
			pstmt.setString(5, probean.getPro_image());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCheck;
		
	}
	
	
	
}
