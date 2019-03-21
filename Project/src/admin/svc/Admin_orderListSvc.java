package admin.svc;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.OrOdProViewBean;

public class Admin_orderListSvc {
	public ArrayList<OrOdProViewBean> admin_orderList () {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		ArrayList<OrOdProViewBean> OrderList= new ArrayList<OrOdProViewBean>();
		OrderList = adminDAO.admin_orderList();
		
		close(con);
		return OrderList;
	}
}
