package admin.svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.AdminDAO;

public class ProDeleteSvc {
	public int proDel(int pro_code) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		int check=0;
		check = adminDAO.ProDel(pro_code);
		
		if(check>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return check;
	}
}
