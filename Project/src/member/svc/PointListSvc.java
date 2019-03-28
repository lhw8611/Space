package member.svc;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.PointBean;
public class PointListSvc {
	public ArrayList<PointBean> pointListSvc(String id) { //포인트조회
		Connection con = getConnection();
		MemberDAO memberdao = MemberDAO.getInstance();
		memberdao.setConnection(con);
		ArrayList<PointBean> arraypointbean = memberdao.pointList(id);
		
		close(con);
		return arraypointbean;
	}
}
