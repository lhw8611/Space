package vo;

import java.util.Date;

public class PointBean { //'point'
	private int po_num;
	private String mem_id;
	private String po_state;
	private int po_point;
	private int po_total;
	private Date po_date;
	
	public int getPo_num() {
		return po_num;
	}
	public void setPo_num(int po_num) {
		this.po_num = po_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getPo_state() {
		return po_state;
	}
	public void setPo_state(String po_state) {
		this.po_state = po_state;
	}
	public int getPo_point() {
		return po_point;
	}
	public void setPo_point(int po_point) {
		this.po_point = po_point;
	}
	public int getPo_total() {
		return po_total;
	}
	public void setPo_total(int po_total) {
		this.po_total = po_total;
	}
	public Date getPo_date() {
		return po_date;
	}
	public void setPo_date(Date po_date) {
		this.po_date = po_date;
	}
	
}
