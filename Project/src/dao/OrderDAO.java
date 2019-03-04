package dao;

import java.sql.Connection;

import javax.sql.DataSource;

public class OrderDAO {
	DataSource ds;
	Connection con;
	private static OrderDAO orderDAO;
	
	private OrderDAO() {
	}
	
	public static OrderDAO getInstance() {
		if(orderDAO == null) {
			orderDAO = new OrderDAO();
		}
		return orderDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
}

