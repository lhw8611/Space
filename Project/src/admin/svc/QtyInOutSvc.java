package admin.svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.AdminDAO;
import vo.QtyProViewBean;

public class QtyInOutSvc {
	public void QtyInSvc (int pro_code, String inout,int qty,String note) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		int qty_modifyCount = 0;
		int inoutCheck =0;
		
		qty_modifyCount=adminDAO.qtyProView(pro_code);
		System.out.println("카운터 : "+qty_modifyCount);
		inoutCheck = adminDAO.qtyInOutRegister(pro_code,inout,qty,note,qty_modifyCount);
		
		
		if(inoutCheck>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
	}

}
