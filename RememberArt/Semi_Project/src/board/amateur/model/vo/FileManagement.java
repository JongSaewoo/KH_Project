package board.amateur.model.vo;

import java.io.Serializable;

public class FileManagement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3019824339648418966L;

	private int event_no;			//게시판번호
	private String event_file;		//파일명	
	private String event_path;		//파일 경로
	public FileManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FileManagement(int event_no, String event_file) {
		super();
		this.event_no = event_no;
		this.event_file = event_file;
	}

	public FileManagement(int event_no, String event_file, String event_path) {
		super();
		this.event_no = event_no;
		this.event_file = event_file;
		this.event_path = event_path;
	}
	public int getEvent_no() {
		return event_no;
	}
	public void setEvent_no(int event_no) {
		this.event_no = event_no;
	}
	public String getEvent_file() {
		return event_file;
	}
	public void setEvent_file(String event_file) {
		this.event_file = event_file;
	}
	public String getEvent_path() {
		return event_path;
	}
	public void setEvent_path(String event_path) {
		this.event_path = event_path;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "FileManagement [event_no=" + event_no + ", event_file=" + event_file + ", event_path=" + event_path
				+ "]";
	}
	
	
	
	
}
