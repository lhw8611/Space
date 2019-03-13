package dao;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

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
	//qty + Pro뷰
	public ArrayList<QtyProViewBean> qtyProView() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qtyproview where qty_modifyCount=0";
		ArrayList<QtyProViewBean> qtyList = null;
		QtyProViewBean qtybean = null;
		
		
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qtyList = new ArrayList<QtyProViewBean>();
				rs.beforeFirst();
			}
			while(rs.next()) {
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
				qtyList.add(qtybean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return qtyList;
	}
	
	//입출력 리스트
	public ArrayList<QtyProViewBean> qtyProViewInOut(int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qtyproview where pro_code=? AND qty_modifyCount>0";
		ArrayList<QtyProViewBean> qtyInOutList = null;
		QtyProViewBean qtybean = null;
		
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qtyInOutList = new ArrayList<QtyProViewBean>();
				rs.beforeFirst();
			}
			while(rs.next()) {	
				qtybean = new QtyProViewBean();
				qtybean.setQty_num(rs.getInt("qty_num"));
				qtybean.setPro_code(rs.getInt("Pro_code"));
				qtybean.setQty_qty(rs.getInt("qty_qty"));
				qtybean.setQty_inout(rs.getString("qty_inout"));
				qtybean.setQty_date(rs.getDate("qty_date"));
				qtybean.setQty_note(rs.getString("qty_note"));
				qtybean.setPro_name	(rs.getString("pro_name"));
				qtybean.setPro_price(rs.getInt("pro_price"));
				qtybean.setPro_category(rs.getString("pro_category"));
				qtybean.setPro_content(rs.getString("pro_content"));
				qtybean.setPro_image(rs.getString("pro_image"));
				qtybean.setQty_modifycount(rs.getInt("qty_modifycount"));
				qtyInOutList.add(qtybean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return qtyInOutList;
	}
	//입출고 입력
	public int qtyInOutRegister(int pro_code,String inout,int qty,String note) {
		PreparedStatement pstmt = null;
		int inoutCheck =0;
		String sql = "insert into qty values (null,?,?,?,now(),?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qty);
			pstmt.setString(2, inout);
			pstmt.setString(3, note);
			pstmt.setInt(4, pro_code);
			
			inoutCheck = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return inoutCheck;
	}
	
}
