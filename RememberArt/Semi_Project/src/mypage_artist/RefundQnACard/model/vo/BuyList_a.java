package mypage_artist.RefundQnACard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BuyList_a implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3474347789221743732L;
	
    /* 구매 목록 */
	private String order_no;		// 주문번호
	private String user_id;			// 주문자 아이디 
 	private String order_status;	// 주문 처리 상태
	private Date refund_date;		// 환불 날짜
	private Date order_date;		// 주문 날짜
	
	/* 상품 */
	private int paint_no;			// 작품 번호
	private String paint_price;		// 작품 가격
	private String artist_name;		// 작가 이름
	private String paint_name;		// 작품 이름
	
	
	public BuyList_a() {
		super();
	}
	
	


	public BuyList_a(String order_no, String user_id, String order_status, int paint_no, String paint_price,
			String artist_name, String paint_name) {
		super();
		this.order_no = order_no;
		this.user_id = user_id;
		this.order_status = order_status;
		this.paint_no = paint_no;
		this.paint_price = paint_price;
		this.artist_name = artist_name;
		this.paint_name = paint_name;
	}




	public BuyList_a(String order_no, String user_id, String order_status, Date refund_date, Date order_date,
			int paint_no, String paint_price, String artist_name, String paint_name) {
		super();
		this.order_no = order_no;
		this.user_id = user_id;
		this.order_status = order_status;
		this.refund_date = refund_date;
		this.order_date = order_date;
		this.paint_no = paint_no;
		this.paint_price = paint_price;
		this.artist_name = artist_name;
		this.paint_name = paint_name;
	}




	public String getOrder_no() {
		return order_no;
	}




	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}




	public String getUser_id() {
		return user_id;
	}




	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}




	public String getOrder_status() {
		return order_status;
	}




	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}




	public Date getRefund_date() {
		return refund_date;
	}




	public void setRefund_date(Date refund_date) {
		this.refund_date = refund_date;
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




	public String getPaint_price() {
		return paint_price;
	}




	public void setPaint_price(String paint_price) {
		this.paint_price = paint_price;
	}




	public String getArtist_name() {
		return artist_name;
	}




	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}




	public String getPaint_name() {
		return paint_name;
	}




	public void setPaint_name(String paint_name) {
		this.paint_name = paint_name;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "BuyList_a [order_no=" + order_no + ", user_id=" + user_id + ", order_status=" + order_status
				+ ", refund_date=" + refund_date + ", order_date=" + order_date + ", paint_no=" + paint_no
				+ ", paint_price=" + paint_price + ", artist_name=" + artist_name + ", paint_name=" + paint_name + "]";
	}


	
	
	
	
	
	
	

}
