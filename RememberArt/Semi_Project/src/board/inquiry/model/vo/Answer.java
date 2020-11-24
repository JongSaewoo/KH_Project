package board.inquiry.model.vo;

public class Answer {

	private String q_no;
	private String a_date;
	private String answer;
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(String q_no, String a_date, String answer) {
		super();
		this.q_no = q_no;
		this.a_date = a_date;
		this.answer = answer;
	}
	public String getQ_no() {
		return q_no;
	}
	public void setQ_no(String q_no) {
		this.q_no = q_no;
	}
	public String getA_date() {
		return a_date;
	}
	public void setA_date(String a_date) {
		this.a_date = a_date;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Answer [q_no=" + q_no + ", a_date=" + a_date + ", answer=" + answer + "]";
	}
	
	
	
	
}
