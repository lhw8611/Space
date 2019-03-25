package vo;

import java.sql.Date;

public class SalesBean {
	private Date sa_date;
	private int sa_price;
	
	public Date getSa_date() {
		return sa_date;
	}
	public void setSa_date(Date sa_date) {
		this.sa_date = sa_date;
	}
	public int getSa_price() {
		return sa_price;
	}
	public void setSa_price(int sa_price) {
		this.sa_price = sa_price;
	}
}
