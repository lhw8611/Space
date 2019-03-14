package admin.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.QtyProViewBean;


public class QtyManagementFormSvc {
	public ArrayList<QtyProViewBean> qtyManagementForm () {
	Connection con = getConnection();
	AdminDAO adminDAO = AdminDAO.getInstance();
	adminDAO.setConnection(con);
	ArrayList<QtyProViewBean> qtyList = new ArrayList<QtyProViewBean>();
	qtyList = adminDAO.qtyProViewList();
	
	close(con);
	return qtyList;		
	}
}
