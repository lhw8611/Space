package admin.svc;

import java.sql.Connection;
import java.util.HashMap;

import dao.AdminDAO;

import static db.jdbcUtil.*;


public class SalesManagementSvc {
	public HashMap<Integer, Integer> SalesManagement() {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map =  adminDAO.salesManagement();
		
		close(con);
		return map;
	}
}
