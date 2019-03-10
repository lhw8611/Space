package dao;
import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.CartProViewBean;
import vo.MemberBean;
import vo.OrderBean;
import vo.OrderDetailBean;
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
	
	
	//장바구니에 상품이 있나 확인 (없으면 true , 있으면 false)
	public boolean checkCart(ProductBean probean, String id) {
//		ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		boolean isNewCart = true;
		
		String sql = "select * from cart_result where mem_id=? AND pro_code=?";
//		System.out.println("checkCart DAO에서 slq문 : "+sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, probean.getPro_code());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isNewCart = false;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return isNewCart;
		
	}
	
	
	
	//로그인 상태 CartAdd
	public int addCart(boolean isNewCart, ProductBean probean, String id) {
		System.out.println("addCart DAO 진입");
		PreparedStatement pstmt = null;
		int checkCartInsert = 0;
		String sql = null;
		//장바구니에 해당 상품 X
		if(isNewCart) {
			sql = "insert into cart values (null, '"+id+"', '"+probean.getPro_code()+"', 1)";
			System.out.println("sql : " +sql);
		//장바구니에 해당 상품 있을때
		}else {
			sql = "update cart_result set cart_qty=cart_qty+1 where mem_id='"+id+"' AND pro_code ='"+probean.getPro_code()+"'";
			System.out.println("sql : " +sql);
		}
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
	
	//장바구니 리스트 
	public ArrayList<CartProViewBean> cartListForm(String id) {
		CartProViewBean cartProViewBean = null;
		ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from cart_result where mem_id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
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
			close(rs);
			close(pstmt);
		}
		return cartList;
	}
	
	public int order_insert(String mem_id, OrderBean odbean) { //id에 대한 주문내역 만들기
		PreparedStatement pstmt = null;
		String sql = "insert into orders values(null, now(), 'wait', ?, ?, ?, ?, ?, ?, ?)";
		int updateCount = 0;
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "temp cash");//결제방법
			pstmt.setInt(2, odbean.getOr_point());//포인트
			pstmt.setString(3, odbean.getOr_request());//요청사항
			pstmt.setString(4, odbean.getOr_getname());//받는사람 이름
			pstmt.setString(5, odbean.getOr_getadd());//받는사람 주소
			pstmt.setString(6, odbean.getOr_gettel());//받는사람 전화
			pstmt.setString(7, mem_id);//구매자 아이디
			updateCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public OrderDetailBean ordercode(OrderBean odbean, OrderDetailBean oddbean) { //마지막행의 주문번호 즉 방금 주문해서 추가된 주문코드
		PreparedStatement pstmt = null;
		String sql = "select * from orders";
		ResultSet rs = null;
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				oddbean.setOd_num(rs.getInt("or_num"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return oddbean;
	}

	
	
	public int detail_insert(OrderDetailBean oddbean) {
		PreparedStatement pstmt = null;
		String sql = "insert into order_detail values(?, ?, ?)";
		int updateCount = 0;
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oddbean.getOd_num());//주문코드
			pstmt.setInt(2, oddbean.getPro_code());//상품코드
			pstmt.setInt(3, oddbean.getOd_qty());//주문갯수
			updateCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int qty_insert(OrderDetailBean oddbean, QtyBean qtybean) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qty where pro_code=?";
		int qtyUpdate=0;
		int qty_qty=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oddbean.getPro_code());
			rs = pstmt.executeQuery();
			while(rs.next()) {
			qty_qty = rs.getInt("qty_qty");
			}
			close(pstmt);
			sql = "insert into qty values(null, ?, ?, 'out', now(), 'sell')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oddbean.getPro_code());//상품코드
			pstmt.setInt(2, qty_qty-oddbean.getOd_qty());//재고수
			qtyUpdate = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return qtyUpdate;
	}
}

