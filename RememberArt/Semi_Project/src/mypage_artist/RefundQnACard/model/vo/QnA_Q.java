package mypage_artist.RefundQnACard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class QnA_Q implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1484156563491809052L;
	
	private int rownum;
	private int pq_no;			// 질문 번호
	private String pquestion;	// 질문 내용
	private String pq_date;		// 질문 날짜
	private int paint_no;		// 작품 번호
	private String user_id;		// 작성자 아이디
	private String pq_yn;		// 처리 상태
	private String paint_name;	// 작품 이름
	
	
	
	public QnA_Q() {
		super();
	}



	public QnA_Q(int pq_no, String pquestion, String pq_date, int paint_no, String user_id, String pq_yn,
			String paint_name) {
		super();
		this.pq_no = pq_no;
		this.pquestion = pquestion;
		this.pq_date = pq_date;
		this.paint_no = paint_no;
		this.user_id = user_id;
		this.pq_yn = pq_yn;
		this.paint_name = paint_name;
	}



	public int getRownum() {
		return rownum;
	}



	public void setRownum(int rownum) {
		this.rownum = rownum;
	}



	public int getPq_no() {
		return pq_no;
	}



	public void setPq_no(int pq_no) {
		this.pq_no = pq_no;
	}



	public String getPquestion() {
		return pquestion;
	}



	public void setPquestion(String pquestion) {
		this.pquestion = pquestion;
	}



	public String getPq_date() {
		return pq_date;
	}



	public void setPq_date(String pq_date) {
		this.pq_date = pq_date;
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



	public String getPq_yn() {
		return pq_yn;
	}



	public void setPq_yn(String pq_yn) {
		this.pq_yn = pq_yn;
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
		return "QnA_Q [rownum=" + rownum + ", pq_no=" + pq_no + ", pquestion=" + pquestion + ", pq_date=" + pq_date
				+ ", paint_no=" + paint_no + ", user_id=" + user_id + ", pq_yn=" + pq_yn + ", paint_name=" + paint_name
				+ "]";
	}
	
	

}
