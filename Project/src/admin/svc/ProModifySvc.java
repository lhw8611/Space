package admin.svc;

import vo.ProductBean;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class ProModifySvc {

	public void proModifySvc(ProductBean probean) {
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);

		int updateCheck = 0;
		updateCheck = adminDAO.ProModify(probean);
		if (updateCheck > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

	}
}
