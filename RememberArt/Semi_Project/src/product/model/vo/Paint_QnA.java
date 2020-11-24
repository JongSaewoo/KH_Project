package product.model.vo;

public class Paint_QnA {
	private int pq_no; 			//질문번호
	private String pqusetion;	//질문내용
	private String pq_date;		//작성일
	private int paint_no;		//작품번호
	private String user_id;		//아이디
	private String pq_yn;		//처리상태
	
	private String panswer;		//답변내용
	private String pa_date;		//답변일자
	public Paint_QnA() {
	}
	
	
	public Paint_QnA(int pq_no, String pqusetion, String pq_date, int paint_no, String user_id, String pq_yn) {
		this.pq_no = pq_no;
		this.pqusetion = pqusetion;
		this.pq_date = pq_date;
		this.paint_no = paint_no;
		this.user_id = user_id;
		this.pq_yn = pq_yn;
	}


	public Paint_QnA(int pq_no, String panswer, String pa_date) {
		this.pq_no = pq_no;
		this.panswer = panswer;
		this.pa_date = pa_date;
	}


	public Paint_QnA(int pq_no, String pqusetion, String pq_date, int paint_no, String user_id, String pq_yn,
			String panswer, String pa_date) {
		this.pq_no = pq_no;
		this.pqusetion = pqusetion;
		this.pq_date = pq_date;
		this.paint_no = paint_no;
		this.user_id = user_id;
		this.pq_yn = pq_yn;
		this.panswer = panswer;
		this.pa_date = pa_date;
	}
	public int getPq_no() {
		return pq_no;
	}
	public void setPq_no(int pq_no) {
		this.pq_no = pq_no;
	}
	public String getPqusetion() {
		return pqusetion;
	}
	public void setPqusetion(String pqusetion) {
		this.pqusetion = pqusetion;
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
	public String getPanswer() {
		return panswer;
	}
	public void setPanswer(String panswer) {
		this.panswer = panswer;
	}
	public String getPa_date() {
		return pa_date;
	}
	public void setPa_date(String pa_date) {
		this.pa_date = pa_date;
	}
	@Override
	public String toString() {
		return "Paint_QnA [pq_no=" + pq_no + ", pqusetion=" + pqusetion + ", pq_date=" + pq_date + ", paint_no="
				+ paint_no + ", user_id=" + user_id + ", pq_yn=" + pq_yn + ", panswer=" + panswer + ", pa_date="
				+ pa_date + "]";
	}
	
	
	

}
