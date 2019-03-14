package admin.svc;
import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class QtyInOutDeleteSvc {
	public int qtyInOutDelete (int qty_num) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		int inoutDeleteCheck =0;
		inoutDeleteCheck = adminDAO.qtyInOutDelete(qty_num);
		
		if(inoutDeleteCheck>0) {
			commit(con);
		}else {
			rollback(con);
		}
			
		
		close(con);
		
		return inoutDeleteCheck;
	}
}
