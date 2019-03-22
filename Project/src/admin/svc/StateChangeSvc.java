package admin.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class StateChangeSvc {
	public void stateChange(int od_num, int pro_code, String state) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		int check = 0;

		check = adminDAO.stateChange(od_num, pro_code, state);

		if (check > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

	}
}
