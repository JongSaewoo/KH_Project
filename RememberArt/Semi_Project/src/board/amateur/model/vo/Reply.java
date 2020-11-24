package board.amateur.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Reply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3894681879349578954L;
	
	private int reply_no;		//댓글 번호
	private int event_no;		//게시글 번호
	private String reply;		//댓글 내용
	private Date reply_date;	//작성일
	private String user_id;		//작성자(댓글)
	
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Reply(int event_no, String reply, String user_id) {
		super();
		this.event_no = event_no;
		this.reply = reply;
		this.user_id = user_id;
	}



	public Reply(int reply_no, int event_no, String reply, Date reply_date, String user_id) {
		super();
		this.reply_no = reply_no;
		this.event_no = event_no;
		this.reply = reply;
		this.reply_date = reply_date;
		this.user_id = user_id;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public int getEvent_no() {
		return event_no;
	}

	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	@Override
	public String toString() {
		return "Reply [reply_no=" + reply_no + ", event_no=" + event_no + ", reply=" + reply + ", reply_date="
				+ reply_date + ", user_id=" + user_id + "]";
	}
	
	
	
}
