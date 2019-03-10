package vo;

public class OrderDetailBean {
	int od_num;	//주문번호
	int pro_code;	//상품코드
	int od_qty; //구매수량
	public int getOd_num() {
		return od_num;
	}
	public void setOd_num(int od_num) {
		this.od_num = od_num;
	}
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public int getOd_qty() {
		return od_qty;
	}
	public void setOd_qty(int od_qty) {
		this.od_qty = od_qty;
	}
	
	
}
