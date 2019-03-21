package admin.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;


public class RefundSvc {
	public void refundSvc() {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		adminDAO.refund()
		
		
		close(con);
		
	}
}
