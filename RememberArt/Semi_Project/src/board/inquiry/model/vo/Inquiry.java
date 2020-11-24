package board.inquiry.model.vo;

import java.sql.Date;

public class Inquiry {
	private int q_no;
	private String user_id;
	private Date q_date;
	private String q_cate;
	private String question;
	private String q_yn;
	private String question_title;
	public Inquiry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Inquiry(String user_id, String q_cate, String question, String question_title) {
		super();
		this.user_id = user_id;
		this.q_cate = q_cate;
		this.question = question;
		this.question_title = question_title;
	}

	public Inquiry(int q_no, String user_id, Date q_date, String q_cate, String question, String q_yn,
			String question_title) {
		super();
		this.q_no = q_no;
		this.user_id = user_id;
		this.q_date = q_date;
		this.q_cate = q_cate;
		this.question = question;
		this.q_yn = q_yn;
		this.question_title = question_title;
	}
	public int getQ_no() {
		return q_no;
	}
	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getQ_date() {
		return q_date;
	}
	public void setQ_date(Date q_date) {
		this.q_date = q_date;
	}
	public String getQ_cate() {
		return q_cate;
	}
	public void setQ_cate(String q_cate) {
		this.q_cate = q_cate;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQ_yn() {
		return q_yn;
	}
	public void setQ_yn(String q_yn) {
		this.q_yn = q_yn;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	@Override
	public String toString() {
		return "Inquiry [q_no=" + q_no + ", user_id=" + user_id + ", q_date=" + q_date + ", q_cate=" + q_cate
				+ ", question=" + question + ", q_yn=" + q_yn + ", question_title=" + question_title + "]";
	}
		

}
