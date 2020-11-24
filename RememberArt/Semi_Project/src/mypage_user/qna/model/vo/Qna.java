package mypage_user.qna.model.vo;

import java.sql.Date;

public class Qna {
	
	//상품문의 
	private int paintNo; 	// 주문번호
	private String paintName; 	// 상품이름
	private String pQuestion;	// 상품문의
	private Date pQdate;		// 상품문의 날짜
	private String pqYN;		// 답변여부
	
	
	//1:1문의
	private String qCate;		// 1:1 문의내역 카테고리
	private String questionTitle;	// 1:1 질문
	private Date qDate;			// 1:1 문의 날짜
	private String qYN;			// 답변여부
	
	
	public Qna() {
		super();
	}



	//전체
	public Qna(int paintNo, String paintName, String pQuestion, Date pQdate, String pqYN, String qCate,
			String questionTitle, Date qDate, String qYN) {
		super();
		this.paintNo = paintNo;
		this.paintName = paintName;
		this.pQuestion = pQuestion;
		this.pQdate = pQdate;
		this.pqYN = pqYN;
		this.qCate = qCate;
		this.questionTitle = questionTitle;
		this.qDate = qDate;
		this.qYN = qYN;
	}









	//상품문의
	public Qna(int paintNo, String paintName, String pQuestion, Date pQdate, String pqYN) {
		super();
		this.paintNo = paintNo;
		this.paintName = paintName;
		this.pQuestion = pQuestion;
		this.pQdate = pQdate;
		this.pqYN = pqYN;
	}




	//1:1 문의
	public Qna(String qCate, String questionTitle, Date qDate, String qYN) {
		super();
		this.qCate = qCate;
		this.questionTitle = questionTitle;
		this.qDate = qDate;
		this.qYN = qYN;
	}




	public int getPaintNo() {
		return paintNo;
	}



	public void setPaintNo(int paintNo) {
		this.paintNo = paintNo;
	}



	public String getPaintName() {
		return paintName;
	}



	public void setPaintName(String paintName) {
		this.paintName = paintName;
	}








	public String getpQuestion() {
		return pQuestion;
	}








	public void setpQuestion(String pQuestion) {
		this.pQuestion = pQuestion;
	}








	public Date getpQdate() {
		return pQdate;
	}








	public void setpQdate(Date pQdate) {
		this.pQdate = pQdate;
	}








	public String getPqYN() {
		return pqYN;
	}








	public void setPqYN(String pqYN) {
		this.pqYN = pqYN;
	}








	public String getqCate() {
		return qCate;
	}








	public void setqCate(String qCate) {
		this.qCate = qCate;
	}








	public String getQuestionTitle() {
		return questionTitle;
	}








	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}








	public Date getqDate() {
		return qDate;
	}








	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}








	public String getqYN() {
		return qYN;
	}








	public void setqYN(String qYN) {
		this.qYN = qYN;
	}



	@Override
	public String toString() {
		return "Qna [paintNo=" + paintNo + ", paintName=" + paintName + ", pQuestion=" + pQuestion + ", pQdate="
				+ pQdate + ", pqYN=" + pqYN + ", qCate=" + qCate + ", questionTitle=" + questionTitle + ", qDate="
				+ qDate + ", qYN=" + qYN + "]";
	}








	
}

