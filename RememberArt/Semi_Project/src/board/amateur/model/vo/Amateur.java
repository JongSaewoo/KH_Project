package board.amateur.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Amateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5678502789452786002L;
	
	private int event_no;			//게시판 번호
	private String event_title;		//게시판 제목
	private String user_id;			//게시자 아이디
	private Date event_date;		//작성일
	private String event;			//게시글
	private int hit;				//조회수
	private int event_like;		//좋아요 유무
	public Amateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Amateur(int event_no, String event_title, String user_id) {
		super();
		this.event_no = event_no;
		this.event_title = event_title;
		this.user_id = user_id;
	}



	public Amateur(String event_title, String event) {
		super();
		this.event_title = event_title;
		this.event = event;
	}
 
 

	public Amateur(String event_title, String user_id, String event) {
		super();
		this.event_title = event_title;
		this.user_id = user_id;
		this.event = event;
	}



	public Amateur(int event_no, String event_title, String user_id, Date event_date, String event, int hit,
			int event_like) {
		super();
		this.event_no = event_no;
		this.event_title = event_title;
		this.user_id = user_id;
		this.event_date = event_date;
		this.event = event;
		this.hit = hit;
		this.event_like = event_like;
	}
	public int getEvent_no() {
		return event_no;
	}
	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getEvent_like() {
		return event_like;
	}
	public void setEvent_like(int event_like) {
		this.event_like = event_like;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Amateur [event_no=" + event_no + ", event_title=" + event_title + ", user_id=" + user_id
				+ ", event_date=" + event_date + ", event=" + event + ", hit=" + hit + ", event_like=" + event_like
				+ "]";
	}
		

	
}
