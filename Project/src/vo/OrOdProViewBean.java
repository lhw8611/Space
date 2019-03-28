package vo;

import java.sql.Date;

public class OrOdProViewBean {
	//orders
	private int or_num;
	private Date or_date;
	private String or_state;
	private String or_pay;
	private int or_point;
	private String or_request;
	private String or_getname;
	private String or_getadd;
	private String or_gettel;
	private String mem_id;
	// order_detail + product
	private int od_num;
	private int pro_code;
	private int od_qty;
	private String pro_name;
	private int pro_price;
	private String pro_category;
	private String pro_content;
	private String pro_image;
	private String pro_show;
	private Date pro_date;
	private int pro_count;
	private String od_state;
	
	public String getOd_state() {
		return od_state;
	}
	public void setOd_state(String od_state) {
		this.od_state = od_state;
	}
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
	public int getOr_point() {
		return or_point;
	}
	public void setOr_point(int or_point) {
		this.or_point = or_point;
	}
	public String getOr_request() {
		return or_request;
	}
	public void setOr_request(String or_request) {
		this.or_request = or_request;
	}
	public String getOr_getname() {
		return or_getname;
	}
	public void setOr_getname(String or_getname) {
		this.or_getname = or_getname;
	}
	public String getOr_getadd() {
		return or_getadd;
	}
	public void setOr_getadd(String or_getadd) {
		this.or_getadd = or_getadd;
	}
	public String getOr_gettel() {
		return or_gettel;
	}
	public void setOr_gettel(String or_gettel) {
		this.or_gettel = or_gettel;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
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
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	public String getPro_category() {
		return pro_category;
	}
	public void setPro_category(String pro_category) {
		this.pro_category = pro_category;
	}
	public String getPro_content() {
		return pro_content;
	}
	public void setPro_content(String pro_content) {
		this.pro_content = pro_content;
	}
	public String getPro_image() {
		return pro_image;
	}
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}
	public String getPro_show() {
		return pro_show;
	}
	public void setPro_show(String pro_show) {
		this.pro_show = pro_show;
	}
	public Date getPro_date() {
		return pro_date;
	}
	public void setPro_date(Date pro_date) {
		this.pro_date = pro_date;
	}
	public int getPro_count() {
		return pro_count;
	}
	public void setPro_count(int pro_count) {
		this.pro_count = pro_count;
	}
	
	
}
