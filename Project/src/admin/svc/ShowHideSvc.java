package admin.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class ShowHideSvc {
	//show
	public void proShow(int pro_code) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		int updateCheck = 0 ;
		updateCheck = adminDAO.ProShow(pro_code);
		if(updateCheck>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}
	
	//hide
	public void proHide(int pro_code) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		int updateCheck = 0 ;
		updateCheck = adminDAO.ProHide(pro_code);
		if(updateCheck>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}
}
