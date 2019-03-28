package vo;

import java.util.Date;

public class QtyBean { //'qty'
	private int qty_num;
	private int pro_code;
	private int qty_qty;
	private String qty_inout;
	private Date qty_date;
	private String qty_note;
	private int result_qty;//모든 행 계산결과 입고일때 + 출고일때 -
	
	public int getResult_qty() {
		return result_qty;
	}
	public void setResult_qty(int result_qty) {
		this.result_qty = result_qty;
	}
	public int getQty_num() {
		return qty_num;
	}
	public void setQty_num(int qty_num) {
		this.qty_num = qty_num;
	}
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public int getQty_qty() {
		return qty_qty;
	}
	public void setQty_qty(int qty_qty) {
		this.qty_qty = qty_qty;
	}
	public String getQty_inout() {
		return qty_inout;
	}
	public void setQty_inout(String qty_inout) {
		this.qty_inout = qty_inout;
	}
	public Date getQty_date() {
		return qty_date;
	}
	public void setQty_date(Date qty_date) {
		this.qty_date = qty_date;
	}
	public String getQty_note() {
		return qty_note;
	}
	public void setQty_note(String qty_note) {
		this.qty_note = qty_note;
	}
	
	
}
