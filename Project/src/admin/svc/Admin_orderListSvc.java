package admin.svc;

import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;

public class Admin_orderListSvc {
	public ArrayListist<> admin_orderList () {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
	}
}
