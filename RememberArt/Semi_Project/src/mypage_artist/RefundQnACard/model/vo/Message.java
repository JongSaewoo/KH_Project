package mypage_artist.RefundQnACard.model.vo;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623831910300642781L;

	private String order_no;		// 입출고 번호
	private int paint_no;			// 작품 번호
	private String user_id;			// 아이디
	private String message;			// 감동 메세지
	private String paint_name;		// 작품 이름
	private String artist_name;		// 작가 이름
	private String user_name;		// 작성자 이름
	
	
	public Message() {
		super();
	}


	public Message(String order_no, int paint_no, String user_id, String message, String paint_name, String artist_name,
			String user_name) {
		super();
		this.order_no = order_no;
		this.paint_no = paint_no;
		this.user_id = user_id;
		this.message = message;
		this.paint_name = paint_name;
		this.artist_name = artist_name;
		this.user_name = user_name;
	}


	public String getOrder_no() {
		return order_no;
	}


	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}


	public int getPaint_no() {
		return paint_no;
	}


	public void setPaint_no(int paint_no) {
		this.paint_no = paint_no;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPaint_name() {
		return paint_name;
	}


	public void setPaint_name(String paint_name) {
		this.paint_name = paint_name;
	}


	public String getArtist_name() {
		return artist_name;
	}


	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Message [order_no=" + order_no + ", paint_no=" + paint_no + ", user_id=" + user_id + ", message="
				+ message + ", paint_name=" + paint_name + ", artist_name=" + artist_name + ", user_name=" + user_name
				+ "]";
	}


	
	
	
}
