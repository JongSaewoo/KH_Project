package board.free.model.vo;

import java.sql.Date;

public class Free {

	private int free_no;
	private String user_id;
	private Date free_date;
	private String free_title;
	private String free_content;
	private int free_hit;
	private String free_picture;
	public Free() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Free(String user_id, String free_title, String free_content) {
		super();
		this.user_id = user_id;
		this.free_title = free_title;
		this.free_content = free_content;
	}
	

	

	public Free(String user_id, String free_title, String free_content, String free_picture) {
		super();
		this.user_id = user_id;
		this.free_title = free_title;
		this.free_content = free_content;
		this.free_picture = free_picture;
	}

	public Free(int free_no, String user_id, Date free_date, String free_title, String free_content, int free_hit,
			String free_picture) {
		super();
		this.free_no = free_no;
		this.user_id = user_id;
		this.free_date = free_date;
		this.free_title = free_title;
		this.free_content = free_content;
		this.free_hit = free_hit;
		this.free_picture = free_picture;
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
	public Date getFree_date() {
		return free_date;
	}
	public void setFree_date(Date free_date) {
		this.free_date = free_date;
	}
	public String getFree_title() {
		return free_title;
	}
	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}
	public String getFree_content() {
		return free_content;
	}
	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}
	public int getFree_hit() {
		return free_hit;
	}
	public void setFree_hit(int free_hit) {
		this.free_hit = free_hit;
	}
	public String getFree_picture() {
		return free_picture;
	}
	public void setFree_picture(String free_picture) {
		this.free_picture = free_picture;
	}
	@Override
	public String toString() {
		return "Free [free_no=" + free_no + ", user_id=" + user_id + ", free_date=" + free_date + ", free_title="
				+ free_title + ", free_content=" + free_content + ", free_hit=" + free_hit + ", free_picture="
				+ free_picture + "]";
	}
	
	
}
