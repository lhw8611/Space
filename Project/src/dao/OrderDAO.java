package dao;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.CartProViewBean;
import vo.MemberBean;
import vo.OrOdProViewBean;
import vo.OrderBean;
import vo.OrderListBean;
import vo.PointBean;
import vo.ProductBean;
import vo.QtyBean;

public class OrderDAO {
	DataSource ds;
	Connection con;
	private static OrderDAO orderDAO;

	private OrderDAO() {
	}

	public static OrderDAO getInstance() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO();
		}
		return orderDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 오더 폼 관련=======================================================
	// 구매자 정보
	public MemberBean purchaserInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_id=?";
		MemberBean membean = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				membean = new MemberBean();
				membean.setMem_id(rs.getString("mem_id"));
				membean.setMem_pass(rs.getString("mem_pass"));
				membean.setMem_name(rs.getString("mem_name"));
				membean.setMem_add(rs.getString("mem_add"));
				membean.setMem_email(rs.getString("mem_email"));
				membean.setMem_grade(rs.getString("mem_grade"));
				membean.setMem_tel(rs.getString("mem_tel"));
				membean.setMem_date(rs.getDate("mem_date"));
				membean.setMem_zip(rs.getString("mem_zip"));
				membean.setMem_add2(rs.getString("mem_add2"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return membean;

	}

	// 구매할 상품 정보
	public ProductBean productsInfo(int pro_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBean probean = new ProductBean();
		String sql = "select * from product where pro_code=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				probean.setPro_code(rs.getInt("pro_code"));
				probean.setPro_name(rs.getString("pro_name"));
				probean.setPro_price(rs.getInt("pro_price"));
				probean.setPro_category(rs.getString("pro_category"));
				probean.setPro_content(rs.getString("pro_content"));
				probean.setPro_image(rs.getString("pro_image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return probean;
	}

	// 장바구니에 상품이 있나 확인 (없으면 true , 있으면 false)
	public boolean checkCart(ProductBean probean, String id) {
//		ArrayList<CartProViewBean> cartList = new ArrayList<CartProViewBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isNewCart = true;

		String sql = "select * from cart_result where mem_id=? AND pro_code=?";
//		System.out.println("checkCart DAO에서 slq문 : "+sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, probean.getPro_code());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				isNewCart = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return isNewCart;

	}

	// 로그인 상태 CartAdd
	public int addCart(boolean isNewCart, ProductBean probean, String id) {
		System.out.println("addCart DAO 진입");
		PreparedStatement pstmt = null;
		int checkCartInsert = 0;
		String sql = null;
		// 장바구니에 해당 상품 X
		if (isNewCart) {
			sql = "insert into cart values (null, '" + id + "', '" + probean.getPro_code() + "', 1)";
			System.out.println("sql : " + sql);
			// 장바구니에 해당 상품 있을때
		} else {
			sql = "update cart_result set cart_qty=cart_qty+1 where mem_id='" + id + "' AND pro_code ='"
					+ probean.getPro_code() + "'";
			System.out.println("sql : " + sql);
		}
		try {
			pstmt = con.prepareStatement(sql);
			checkCartInsert = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return checkCartInsert;

	}

	// 장바구니 삭제
	public int deleteCart(int cart_num) {
		PreparedStatement pstmt = null;
		int deleteCartCheck = 0;
		String sql = "delete from cart where cart_num=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			deleteCartCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCartCheck;
	}

	public QtyBean productqty(int pro_code) { // 재고수 계산
		System.out.println("[4]OrderDAO.productqty");
		QtyBean qtybean = null;
		ArrayList<QtyBean> qtybeanlist = new ArrayList<QtyBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from qty where pro_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				qtybean = new QtyBean();
				qtybean.setQty_num(rs.getInt("qty_num"));
				qtybean.setPro_code(rs.getInt("pro_code"));
				qtybean.setQty_qty(rs.getInt("qty_qty"));
				qtybean.setQty_inout(rs.getString("qty_inout"));
				qtybean.setQty_date(rs.getDate("qty_date"));
				qtybean.setQty_note(rs.getString("qty_note"));
				qtybean.setResult_qty(0);
				qtybeanlist.add(qtybean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return qtybean;
	}

	// 장바구니 리스트
	public ArrayList<CartProViewBean> cartListForm(String id) {
		CartProViewBean cartProViewBean = null;
		ArrayList<CartProViewBean> cartList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from cart_result where mem_id=?";

		try {
			// 커서 이동을 위한 옵션
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cartList = new ArrayList<CartProViewBean>();
				// 커서를 맨 처음행으로 이동
				rs.beforeFirst();
				while (rs.next()) {
					cartProViewBean = new CartProViewBean();
					cartProViewBean.setCart_num(rs.getInt("cart_num"));
					cartProViewBean.setPro_code(rs.getInt("pro_code"));
					cartProViewBean.setMem_id(rs.getString("mem_id"));
					cartProViewBean.setPro_name(rs.getString("pro_name"));
					cartProViewBean.setPro_price(rs.getInt("pro_price"));
					cartProViewBean.setPro_image(rs.getString("pro_image"));
					cartProViewBean.setCart_qty(rs.getInt("cart_qty"));
					cartList.add(cartProViewBean);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return cartList;
	}

	// 수량변경
	public int qtyChange(int cart_num, int cart_qty) {
//		System.out.println("id : " +id);
//		System.out.println("pro_code : " + pro_code);
//		System.out.println("cart_qty : " +cart_qty);
		PreparedStatement pstmt = null;
		int qtyChangeCheck = 0;
		String sql = "update cart set cart_qty=? where cart_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_qty);
			pstmt.setInt(2, cart_num);
			qtyChangeCheck = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return qtyChangeCheck;
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

	public ArrayList<OrderListBean> OrderList(int pro_code, int qty) { // 하나 주문DAO
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderListBean orderbean = null;
		ArrayList<OrderListBean> orderlistbean = new ArrayList<OrderListBean>();
		try {
			pstmt = con.prepareStatement("select * from product where pro_code=?");
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderbean = new OrderListBean();
				orderbean.setPro_code(rs.getInt("pro_code"));
				orderbean.setPro_name(rs.getString("pro_name"));
				orderbean.setPro_price(rs.getInt("pro_price"));
				orderbean.setPro_category(rs.getString("pro_category"));
				orderbean.setPro_content(rs.getString("pro_content"));
				orderbean.setPro_image(rs.getString("pro_image"));
				orderbean.setOd_qty(qty);
				orderlistbean.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderlistbean;
	}

	public int MaxPointDAO(String mem_id) { // 사용할 수 있는 포인트 조회
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int MaxPoint = 0;
		try {
			pstmt = con.prepareStatement("select * from point where mem_id=?");
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 마지막행의 갖고있는 포인트 사용을 위한 반복문
				MaxPoint = rs.getInt("po_total");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("OrderDAO.MaxPointDAO error");
		} finally {
			close(rs);
			close(pstmt);
		}

		return MaxPoint;
	}

	public ArrayList<OrderListBean> productsInfoDAO(ArrayList<Integer> pro_codes, ArrayList<Integer> pro_qty) { // 구매폼->구매액션
																												// 상품정보
																												// 저장
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderListBean orderbean = null;
		ArrayList<OrderListBean> orderlistbean = new ArrayList<OrderListBean>();
		try {
			for (int i = 0; i < pro_codes.size(); i++) {
				pstmt = con.prepareStatement("select * from product where pro_code=?");
				pstmt.setInt(1, pro_codes.get(i));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					orderbean = new OrderListBean();
					orderbean.setPro_code(rs.getInt("pro_code"));
					orderbean.setPro_name(rs.getString("pro_name"));
					orderbean.setPro_price(rs.getInt("pro_price"));
					orderbean.setPro_category(rs.getString("pro_category"));
					orderbean.setPro_content(rs.getString("pro_content"));
					orderbean.setPro_image(rs.getString("pro_image"));
					orderbean.setPro_name(rs.getString("pro_name"));
					orderbean.setOd_qty(pro_qty.get(i));
					orderlistbean.add(orderbean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return orderlistbean;
	}

	public int order_qty(ArrayList<OrderListBean> orderlistbean) { // 주문할때 재고수 뺀거 insert
		System.out.println("order_qty dao");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		try {
			for (int i = 0; i < orderlistbean.size(); i++) {
				pstmt = con
						.prepareStatement("select max(qty_modifyCount) as qty_modifyCount from qty where pro_code=?");
				pstmt.setInt(1, orderlistbean.get(i).getPro_code());
				rs = pstmt.executeQuery();
				int modifycount = 0;
				if (rs.next()) {
					modifycount = rs.getInt("qty_modifyCount");
				}
				close(pstmt);
				pstmt = con.prepareStatement("insert into qty values(null, ?, ?, 'out', now(), ?, 'sell')");
				pstmt.setInt(1, orderlistbean.get(i).getPro_code());
				pstmt.setInt(2, orderlistbean.get(i).getOd_qty());
				pstmt.setInt(3, modifycount + 1);
				insertCount = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}

	public int order_add(MemberBean membean, OrderBean orderbean) { // ordercomplete orders insert
		System.out.println("[4]order_add dao");
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			pstmt = con.prepareStatement("insert into orders values(null, now(), 'wait', ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, orderbean.getOr_pay());
			pstmt.setInt(2, orderbean.getOr_point());
			pstmt.setString(3, orderbean.getOr_request());
			pstmt.setString(4, orderbean.getOr_getname());
			pstmt.setString(5, orderbean.getOr_getadd());
			pstmt.setString(6, orderbean.getOr_gettel());
			pstmt.setString(7, membean.getMem_id());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}

	public ArrayList<Integer> order_detail_add(OrderBean orderbean, ArrayList<OrderListBean> orderlistbean) { // ordercomplete
																												// order_detail_insert
		System.out.println("[4]order_detail_add dao");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> arryInsertCount = new ArrayList<Integer>();
		int order_num = 0;
		try {
			pstmt = con.prepareStatement("select max(or_num) as or_num from orders");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				order_num = rs.getInt("or_num");
				orderbean.setOr_num(order_num);
			}
			close(rs);
			close(pstmt);
			for (int i = 0; i < orderlistbean.size(); i++) {
				pstmt = con.prepareStatement("insert into order_detail values(?, ?, ?, ?)");
				pstmt.setInt(1, order_num);
				pstmt.setInt(2, orderlistbean.get(i).getPro_code());
				pstmt.setInt(3, orderlistbean.get(i).getOd_qty());
				pstmt.setString(4, "wait");
				arryInsertCount.add(pstmt.executeUpdate());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return arryInsertCount;
	}

	public ArrayList<OrOdProViewBean> OrderSimpleList(int page, int limit, String id, String keyWord) { // 주문조회
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrOdProViewBean> orodproviewbean = null;
		OrOdProViewBean temp = null;
		String sql = "select * from orodproview where mem_id=? order by or_date desc, or_num desc limit ?, ?";
		String sql2 = "select * from orodproview where mem_id=? and pro_name like ? order by or_date desc, or_num desc limit ?, ?";

		int startrow = (page - 1) * 5;
		try {
			if (keyWord == null || keyWord.trim().equals("")) {
				System.out.println("키워드 널");
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, id);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, limit);
			} else {
				System.out.println("키워드 널아님");
				pstmt = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, id);
				pstmt.setString(2,"%"+ keyWord + "%");
				pstmt.setInt(3, startrow);
				pstmt.setInt(4, limit);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orodproviewbean = new ArrayList<OrOdProViewBean>();
				rs.beforeFirst();
				while (rs.next()) {
					temp = new OrOdProViewBean();
					temp.setOr_num(rs.getInt("or_num")); // orders 주문번호
					temp.setOr_date(rs.getDate("or_date")); // 주문날짜
					temp.setOr_state(rs.getString("or_state")); // 주문현황
					temp.setOr_pay(rs.getString("or_pay")); // 결제방식
					temp.setOr_point(rs.getInt("or_point")); // 포인트 사용
					temp.setOr_request(rs.getString("or_request")); // 요청사항
					temp.setOr_getname(rs.getString("or_getname"));// 받는사람 이름
					temp.setOr_getadd(rs.getString("or_getadd"));// 받는사람 주소
					temp.setOr_gettel(rs.getString("or_gettel"));// 받는사람 번호
					temp.setMem_id(rs.getString("mem_id")); // 주문자 이름

					temp.setOd_num(rs.getInt("od_num"));// order_detail주문번호
					temp.setPro_code(rs.getInt("pro_code")); // 상품코드
					temp.setOd_qty(rs.getInt("od_qty")); // 상품주문갯수

					temp.setPro_name(rs.getString("pro_name"));// 상품이름
					temp.setPro_price(rs.getInt("pro_price")); // 상품가격
					temp.setPro_category(rs.getString("pro_category")); // 상품 카테고리
					temp.setPro_content(rs.getString("pro_content"));// 상품 내용
					temp.setPro_image(rs.getString("pro_image")); // 상품이미지
					temp.setPro_show(rs.getString("pro_show"));// 상품 보이기 숨기기
					temp.setPro_date(rs.getDate("pro_date"));// 상품 등록날짜
					temp.setPro_count(rs.getInt("pro_count")); // 상품 조회수
					orodproviewbean.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return orodproviewbean;
	}

	public ArrayList<OrOdProViewBean> OrderDetailList(int od_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrOdProViewBean> orodproviewbean = null;
		OrOdProViewBean temp = null;
		try {
			pstmt = con.prepareStatement("select * from orodproview where od_num=?", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, od_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orodproviewbean = new ArrayList<OrOdProViewBean>();
				rs.beforeFirst();
				while (rs.next()) {
					temp = new OrOdProViewBean();
					temp.setOr_num(rs.getInt("or_num")); // orders 주문번호
					temp.setOr_date(rs.getDate("or_date")); // 주문날짜
					temp.setOr_state(rs.getString("or_state")); // 주문현황
					temp.setOr_pay(rs.getString("or_pay")); // 결제방식
					temp.setOr_point(rs.getInt("or_point")); // 포인트 사용
					temp.setOr_request(rs.getString("or_request")); // 요청사항
					temp.setOr_getname(rs.getString("or_getname"));// 받는사람 이름
					temp.setOr_getadd(rs.getString("or_getadd"));// 받는사람 주소
					temp.setOr_gettel(rs.getString("or_gettel"));// 받는사람 번호
					temp.setMem_id(rs.getString("mem_id")); // 주문자 이름

					temp.setOd_num(rs.getInt("od_num"));// order_detail주문번호
					temp.setPro_code(rs.getInt("pro_code")); // 상품코드
					temp.setOd_qty(rs.getInt("od_qty")); // 상품주문갯수
					temp.setOd_state(rs.getString("od_state"));// 주문현황

					temp.setPro_name(rs.getString("pro_name"));// 상품이름
					temp.setPro_price(rs.getInt("pro_price")); // 상품가격
					temp.setPro_category(rs.getString("pro_category")); // 상품 카테고리
					temp.setPro_content(rs.getString("pro_content"));// 상품 내용
					temp.setPro_image(rs.getString("pro_image")); // 상품이미지
					temp.setPro_show(rs.getString("pro_show"));// 상품 보이기 숨기기
					temp.setPro_date(rs.getDate("pro_date"));// 상품 등록날짜
					temp.setPro_count(rs.getInt("pro_count")); // 상품 조회수
					orodproviewbean.add(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return orodproviewbean;
	}

	public int[] OrderDelete(int or_num) {// 주문삭제
		PreparedStatement pstmt = null;
		int[] insertCount = new int[2];
		try {
			pstmt = con.prepareStatement("delete from orders where or_num=?");
			pstmt.setInt(1, or_num);
			insertCount[0] = pstmt.executeUpdate();
			close(pstmt);
			pstmt = con.prepareStatement("delete from order_detail where od_num=?");
			pstmt.setInt(1, or_num);
			insertCount[1] = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}

	public int getListCount(String id, String keyWord) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		try {
			if (keyWord == null || keyWord.trim().equals("") ) {
				pstmt = con.prepareStatement("select count(*) from orodproview where mem_id=?");
				pstmt.setString(1, id);
			} else {
				pstmt = con.prepareStatement("select count(*) from orodproview where mem_id=? and pro_name=?");
				pstmt.setString(1, id);
				pstmt.setString(2, keyWord);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	/*
	 * public int save_point(PointBean pointbean) { //주문으로 포인트저장 PreparedStatement
	 * pstmt = null; ResultSet rs = null; int insertCount = 0; try {
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally {
	 * 
	 * } return insertCount; }
	 */
}
