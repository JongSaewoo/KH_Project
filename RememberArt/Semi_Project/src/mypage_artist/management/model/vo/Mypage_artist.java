package mypage_artist.management.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Mypage_artist implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1796526628500158181L;
	
	
	private int paint_no;					//작품번호
	private String paint_name;		//작품이름
	private int paint_price;				//작품가격
	private String category;			//작품카테고리
	private String artist_name;		//작가이름
	private String paint_int;			//작품소개
	private String paint_mdate;		//작품제작년도
	private String user_id;				//사용자 아이디
	
	private int afile_no;					//첨부사진번호
	private String afile;					//첨부파일
	
	private int order_no;					//주문 번호
	private Date order_date;				//주문 날짜
	private String order_name;			//입금자
	private String pay_type;				//결제 방법
	private String order_phone;		//입금자연락처
	
	private String rec_name;				//수령인
	private String rec_add;				//배송지
	private String rec_message;		//판매자에게 전할 말
	
	private Date ship_date	;				//배송일
	private String order_status;		//처리상태
	
	public Mypage_artist() {}
	
	public Mypage_artist(String user_id) {
		super();
		this.user_id = user_id;
	}
	
	public Mypage_artist(int paint_no, String rec_name, String rec_add, String rec_message) {
		super();
		this.rec_name = rec_name;
		this.rec_add = rec_add;
		this.rec_message = rec_message;
	}

	public Mypage_artist(String order_name, String pay_type, String order_phone) {
		super();
		this.order_name = order_name;
		this.pay_type = pay_type;
		this.order_phone = order_phone;
	}

	
	public Mypage_artist(int order_no,String afile, String paint_name, String artist_name,  Date order_date,
			Date ship_date) {
		super();
		this.paint_name = paint_name;
		this.artist_name = artist_name;
		this.afile = afile;
		this.order_no = order_no;
		this.order_date = order_date;
		this.ship_date = ship_date;
	}

	public Mypage_artist(int order_no, String afile, String paint_name, String artist_name,   Date order_date,
			Date ship_date, String order_status) {
		super();
		this.paint_name = paint_name;
		this.artist_name = artist_name;
		this.afile = afile;
		this.order_no = order_no;
		this.order_date = order_date;
		this.ship_date = ship_date;
		this.order_status = order_status;
	}

	public Mypage_artist(int paint_no, String afile, String paint_name, String artist_name, int paint_price ) {
		super();
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.paint_price = paint_price;
		this.artist_name = artist_name;
		this.afile = afile;
	}

	public Mypage_artist(int paint_no, int order_no, String afile, String paint_name, String artist_name, int paint_price, 
			String order_status) {
		super();
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.paint_price = paint_price;
		this.artist_name = artist_name;
		this.afile = afile;
		this.order_no = order_no;
		this.order_status = order_status;
	}
	public Mypage_artist(int order_no, String afile, String paint_name, String artist_name, int paint_price, 
			String order_status) {
		super();
		this.paint_name = paint_name;
		this.paint_price = paint_price;
		this.artist_name = artist_name;
		this.afile = afile;
		this.order_no = order_no;
		this.order_status = order_status;
	}

	public Mypage_artist(int order_no, String afile, String paint_int,   Date order_date, Date ship_date, String order_status) {
		super();
		this.paint_int = paint_int;
		this.afile = afile;
		this.order_no = order_no;
		this.order_date = order_date;
		this.ship_date = ship_date;
		this.order_status=order_status;
	}

	public Mypage_artist(int paint_no, String afile,String paint_int, int paint_price ) {
		super();
		this.paint_no = paint_no;
		this.afile = afile;
		this.paint_int = paint_int;
		this.paint_price = paint_price;
	}

	public Mypage_artist(int paint_no, String paint_name, int patint_price, String category, String artist_name,
			String paint_int, String paint_mdate, String user_id, int afile_no, String afile, int order_no,
			String order_name, String pay_type, String order_phone, String rec_name, String rec_add,
			String rec_message, Date ship_date, String order_status) {
		super();
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.paint_price = patint_price;
		this.category = category;
		this.artist_name = artist_name;
		this.paint_int = paint_int;
		this.paint_mdate = paint_mdate;
		this.user_id = user_id;
		this.afile_no = afile_no;
		this.afile = afile;
		this.order_no = order_no;
		this.order_name = order_name;
		this.pay_type = pay_type;
		this.order_phone = order_phone;
		this.rec_name = rec_name;
		this.rec_add = rec_add;
		this.rec_message = rec_message;
		this.ship_date = ship_date;
		this.order_status = order_status;
	}
	
	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public int getPaint_no() {
		return paint_no;
	}
	public void setPaint_no(int paint_no) {
		this.paint_no = paint_no;
	}
	public String getPaint_name() {
		return paint_name;
	}
	public void setPaint_name(String paint_name) {
		this.paint_name = paint_name;
	}
	public int getPaint_price() {
		return paint_price;
	}
	public void setPaint_price(int paint_price) {
		this.paint_price = paint_price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getPaint_int() {
		return paint_int;
	}
	public void setPaint_int(String paint_int) {
		this.paint_int = paint_int;
	}
	public String getPaint_mdate() {
		return paint_mdate;
	}
	public void setPaint_mdate(String paint_mdate) {
		this.paint_mdate = paint_mdate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getAfile_no() {
		return afile_no;
	}
	public void setAfile_no(int afile_no) {
		this.afile_no = afile_no;
	}
	public String getAfile() {
		return afile;
	}
	public void setAfile(String afile) {
		this.afile = afile;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
	}
	public String getRec_add() {
		return rec_add;
	}
	public void setRec_add(String rec_add) {
		this.rec_add = rec_add;
	}
	public String getRec_message() {
		return rec_message;
	}
	public void setRec_message(String rec_message) {
		this.rec_message = rec_message;
	}
	public Date getShip_date() {
		return ship_date;
	}
	public void setShip_date(Date ship_date) {
		this.ship_date = ship_date;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	@Override
	public String toString() {
		return "Mypage_artist [paint_no=" + paint_no + ", paint_name=" + paint_name + ", paint_price=" + paint_price
				+ ", category=" + category + ", artist_name=" + artist_name + ", paint_int=" + paint_int
				+ ", paint_mdate=" + paint_mdate + ", user_id=" + user_id + ", afile_no=" + afile_no + ", afile="
				+ afile + ", order_no=" + order_no + ", order_name=" + order_name + ", pay_type=" + pay_type
				+ ", order_phone=" + order_phone + ", rec_name=" + rec_name + ", rec_add=" + rec_add
				+ ", rec_message=" + rec_message + ", ship_date=" + ship_date + ", order_status=" + order_status + "]";
	}
	
}
