package dao;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import vo.OrOdProViewBean;
import vo.ProductBean;
import vo.QtyProViewBean;
import vo.SalesBean;

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
		String sql = "select * from qtyproview where pro_code=? AND qty_modifyCount>0 order by qty_num desc";
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
		String sql = "update product set pro_show='o', pro_date=now() where pro_code=?";
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
	//관리자 - 주문내역
	public ArrayList<OrOdProViewBean> admin_orderList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from orodproview";
		ArrayList<OrOdProViewBean> OrderList= new ArrayList<OrOdProViewBean>();
		OrOdProViewBean OrderListBean = null;
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				 OrderListBean = new OrOdProViewBean();
				 OrderListBean.setOr_num(rs.getInt("or_num")); // orders 주문번호
				 OrderListBean.setOr_date(rs.getDate("or_date")); //주문날짜
				 OrderListBean.setOr_state(rs.getString("or_state")); //주문현황
				 OrderListBean.setOr_pay(rs.getString("or_pay")); //결제방식
				 OrderListBean.setOr_point(rs.getInt("or_point")); //포인트 사용
				 OrderListBean.setOr_request(rs.getString("or_request")); //요청사항
				 OrderListBean.setOr_getname(rs.getString("or_getname"));//받는사람 이름
				 OrderListBean.setOr_getadd(rs.getString("or_getadd"));//받는사람 주소
				 OrderListBean.setOr_gettel(rs.getString("or_gettel"));//받는사람 번호
				 OrderListBean.setMem_id(rs.getString("mem_id")); //주문자 이름
		               
				 OrderListBean.setOd_num(rs.getInt("od_num"));//order_detail주문번호
				 OrderListBean.setPro_code(rs.getInt("pro_code")); //상품코드
				 OrderListBean.setOd_qty(rs.getInt("od_qty")); //상품주문갯수
				 OrderListBean.setOd_state(rs.getString("od_state")); //주문상태
		               
				 OrderListBean.setPro_name(rs.getString("pro_name"));//상품이름
				 OrderListBean.setPro_price(rs.getInt("pro_price")); //상품가격
				 OrderListBean.setPro_category(rs.getString("pro_category")); //상품 카테고리
				 OrderListBean.setPro_content(rs.getString("pro_content"));//상품 내용
				 OrderListBean.setPro_image(rs.getString("pro_image")); //상품이미지
				 OrderListBean.setPro_show(rs.getString("pro_show"));//상품 보이기 숨기기
				 OrderListBean.setPro_date(rs.getDate("pro_date"));//상품 등록날짜
				 OrderListBean.setPro_count(rs.getInt("pro_count")); //상품 조회수
				 OrderList.add(OrderListBean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return OrderList;
		
	}

	public int stateChange(int od_num, int pro_code, String state) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String sql = "update order_detail set od_state = ? where od_num=? AND pro_code =?";
		int check = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setInt(2, od_num);
			pstmt.setInt(3, pro_code);
			check = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return check;
	}
	
	//매출관리(아직 덜함)
	public HashMap<Integer, Integer> salesManagement() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SalesBean salesBean = null;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int price = 0;
		int yy = 2019;
		int mm = 1;
		
		
		try {
			for(int i=1; i<=12; i++) {
				
				String date = "'"+yy+"-"+mm+"-01'";
				String date2 = "'"+yy+"-"+mm+"-31'";
				String sql = "select * from ordersimplelist where or_state='wait' and or_date>="+date+" and or_date<="+date2+"";
//				System.out.println(sql);
				pstmt = con.prepareStatement(sql);
				rs =  pstmt.executeQuery();
				
				while(rs.next()) {
					price += rs.getInt("pro_price");
				}
				map.put(i, price);
				
				price=0;
				mm+=1;
			}

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return map;
	}
	
	
	
}
