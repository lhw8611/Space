package dao;
import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.CartProViewBean;
import vo.MemberBean;
import vo.ProductBean;
import vo.QtyBean;


public class OrderDAO {
	DataSource ds;
	Connection con;
	private static OrderDAO orderDAO;
	
	private OrderDAO() {
	}
	
	public static OrderDAO getInstance() {
		if(orderDAO == null) {
			orderDAO = new OrderDAO();
		}
		return orderDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	//오더 폼 관련=======================================================
	//구매자 정보
	public MemberBean purchaserInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_id=?";
		MemberBean membean = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				membean = new MemberBean();
				membean.setMem_id(rs.getString("mem_id"));
				membean.setMem_pass(rs.getString("mem_pass"));
				membean.setMem_name(rs.getString("mem_name"));
				membean.setMem_add(rs.getString("mem_add"));
				membean.setMem_email(rs.getString("mem_email"));
				membean.setMem_grade(rs.getString("mem_grade"));
				membean.setMem_tel(rs.getString("mem_tel"));
				membean.setMem_zip(rs.getString("mem_tel"));
				membean.setMem_add2(rs.getString("mem_add2"));
				membean.setMem_date(rs.getString("mem_date"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return membean;
		
		
	}
	//구매할 상품 정보
	public ProductBean productsInfo(int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ProductBean probean = new ProductBean();
		String sql = "select * from product where pro_code=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				probean.setPro_name(rs.getString("pro_name"));
				probean.setPro_price(rs.getInt("pro_price"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return probean;
	}
	
	//오더 폼 관련 끝==================================================
	
	
	//장바구니에 상품이 있나 확인 (있으면  true , 없으면 false)
	public ArrayList<CartProViewBean> checkCart(String id) {
		ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		CartProViewBean cartProViewBean = null;
		
		String sql = "select * from cart_result where mem_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cartProViewBean = new CartProViewBean();
				cartProViewBean.setPro_code(rs.getInt("pro_code"));
				cartProViewBean.setMem_id(rs.getString("mem_id"));
				cartProViewBean.setPro_name(rs.getString("pro_name"));
				cartProViewBean.setPro_price(rs.getInt("pro_price"));
				cartProViewBean.setPro_image(rs.getString("pro_image"));
				cartProViewBean.setCart_qty(rs.getInt("cart_qty"));
				cartList.add(cartProViewBean);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return cartList;
		
	}
	
	
	
	//로그인 상태 CartAdd
	public int addCart(ProductBean probean, String id) {
		PreparedStatement pstmt = null;
		int checkCartInsert = 0;
		
		String sql = "insert into cart values (null, '"+id+"', '"+probean.getPro_code()+"', 1)";
		try {
			pstmt =con.prepareStatement(sql);
			checkCartInsert = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return checkCartInsert;
		
	}

	public QtyBean productqty(int pro_code) {
		System.out.println("[4]OrderDAO.productqty");
		QtyBean qtybean = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql = "select * from qty where pro_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qtybean = new QtyBean();
				qtybean.setQty_num(rs.getInt("qty_num"));
				qtybean.setPro_code(rs.getInt("pro_code"));
				qtybean.setQty_qty(rs.getInt("qty_qty"));
				qtybean.setQty_inout(rs.getString("qty_inout"));
				qtybean.setQty_date(rs.getDate("qty_date"));
				qtybean.setQty_note(rs.getString("qty_note"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return qtybean;
	}
	
}

