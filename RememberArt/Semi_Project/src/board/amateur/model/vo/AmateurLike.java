package board.amateur.model.vo;

public class AmateurLike {
	private int event_no;
	private String user_id;
	private int event_count;
	public AmateurLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public AmateurLike(int event_count) {
		super();
		this.event_count = event_count;
	}


	public AmateurLike(int event_no, int event_count) {
		super();
		this.event_no = event_no;
		this.event_count = event_count;
	}

	public AmateurLike(int event_no, String user_id) {
		super();
		this.event_no = event_no;
		this.user_id = user_id;
	}

	public AmateurLike(int event_no, String user_id, int event_count) {
		super();
		this.event_no = event_no;
		this.user_id = user_id;
		this.event_count = event_count;
	}

	public int getEvent_no() {
		return event_no;
	}

	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getEvent_count() {
		return event_count;
	}

	public void setEvent_count(int event_count) {
		this.event_count = event_count;
	}

	@Override
	public String toString() {
		return "AmateurLike [event_no=" + event_no + ", user_id=" + user_id + ", event_count=" + event_count + "]";
	}
	
	
}
