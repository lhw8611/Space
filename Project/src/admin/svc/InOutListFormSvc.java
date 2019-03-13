package admin.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.QtyProViewBean;

public class InOutListFormSvc {
	public ArrayList<QtyProViewBean> InOutManagement (int pro_code) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		ArrayList<QtyProViewBean> qtyInOutList = new ArrayList<QtyProViewBean>();
		qtyInOutList = adminDAO.qtyProViewInOut(pro_code);
		close(con);
		
		return qtyInOutList;		
		
		}
}
