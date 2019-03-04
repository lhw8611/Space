package dao;
import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.MemberBean;
import vo.ProductBean;


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
		MemberBean membean = new MemberBean();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
//				membean.setMem_id(rs.getString("mem_id"));
				membean.setMem_name(rs.getString("mem_name"));
				membean.setMem_tel(rs.getString("mem_tel"));
				membean.setMem_add(rs.getString("mem_add"));
				membean.setMem_add2(rs.getString("mem_add2"));
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
}

