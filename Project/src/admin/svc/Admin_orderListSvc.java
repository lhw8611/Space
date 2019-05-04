package admin.svc;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import dao.BoardDAO;
import vo.OrOdProViewBean;

public class Admin_orderListSvc {
	public int admin_orderList_count() throws Exception {
		int listCount = 0;
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		listCount = adminDAO.admin_orderList_count();
		close(con);
		return listCount;
	}
	
	public ArrayList<OrOdProViewBean> admin_orderList (int page, int limit) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		ArrayList<OrOdProViewBean> OrderList= new ArrayList<OrOdProViewBean>();
		OrderList = adminDAO.admin_orderList(page, limit);
		
		close(con);
		return OrderList;
	}
}
