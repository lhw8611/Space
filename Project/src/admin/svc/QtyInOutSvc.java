package admin.svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.AdminDAO;

public class QtyInOutSvc {
	public void QtyInSvc (int pro_code, String inout,int qty,String note) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		int inoutCheck =0;
		inoutCheck = adminDAO.qtyInOutRegister(pro_code,inout,qty,note);
		
		if(inoutCheck>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
	}

}
