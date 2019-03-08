package vo;

import java.sql.Date;

public class OrderBean {
	private int or_num;
	private Date or_date;
	private String or_state;
	private String or_pay;
	private String mem_id;
	private String or_request;
	
	
	public int getOr_num() {
		return or_num;
	}
	public void setOr_num(int or_num) {
		this.or_num = or_num;
	}
	public Date getOr_date() {
		return or_date;
	}
	public void setOr_date(Date or_date) {
		this.or_date = or_date;
	}
	public String getOr_state() {
		return or_state;
	}
	public void setOr_state(String or_state) {
		this.or_state = or_state;
	}
	public String getOr_pay() {
		return or_pay;
	}
	public void setOr_pay(String or_pay) {
		this.or_pay = or_pay;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getOr_request() {
		return or_request;
	}
	public void setOr_request(String or_request) {
		this.or_request = or_request;
	}
	

}
