package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.PointBean;
public class PointAddSvc {
	public int pointAddSvc(PointBean pointbean) {
		Connection con = getConnection();
		MemberDAO memberdao = MemberDAO.getInstance();
		memberdao.setConnection(con);
		int updateCount = memberdao.pointAddDAO(pointbean);
		
		if(updateCount > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return updateCount;
	}
}
