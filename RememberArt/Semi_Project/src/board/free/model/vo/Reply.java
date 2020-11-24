package board.free.model.vo;

import java.sql.Date;

public class Reply {

	private int free_r_no;
	private int free_no;
	private String user_id;
	private String free_r_content;
	private Date free_r_date;
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Reply(int free_no, String user_id, String free_r_content) {
		super();
		this.free_no = free_no;
		this.user_id = user_id;
		this.free_r_content = free_r_content;
	}

	public Reply(int free_r_no, int free_no, String user_id, String free_r_content, Date free_r_date) {
		super();
		this.free_r_no = free_r_no;
		this.free_no = free_no;
		this.user_id = user_id;
		this.free_r_content = free_r_content;
		this.free_r_date = free_r_date;
	}
	public int getFree_r_no() {
		return free_r_no;
	}
	public void setFree_r_no(int free_r_no) {
		this.free_r_no = free_r_no;
	}
	public int getFree_no() {
		return free_no;
	}
	public void setFree_no(int free_no) {
		this.free_no = free_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFree_r_content() {
		return free_r_content;
	}
	public void setFree_r_content(String free_r_content) {
		this.free_r_content = free_r_content;
	}
	public Date getFree_r_date() {
		return free_r_date;
	}
	public void setFree_r_date(Date free_r_date) {
		this.free_r_date = free_r_date;
	}
	@Override
	public String toString() {
		return "Reply [free_r_no=" + free_r_no + ", free_no=" + free_no + ", user_id=" + user_id + ", free_r_content="
				+ free_r_content + ", free_r_date=" + free_r_date + "]";
	}
	
	
}
