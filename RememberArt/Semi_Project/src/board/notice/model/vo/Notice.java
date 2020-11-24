package board.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3917860409744056936L;
	
	private int noti_no; 			//게시판 번호
	private String noti_title;		//게시판 제목
	private String noti_cate; 		//게시판 구분
	private Date noti_date;			//게시일
	private String notice;			//게시글 내용
	
	public Notice() {
		int noti_no; 			//게시판 번호
		String noti_title;		//게시판 제목
		String noti_cate; 		//게시판 구분
		Date noti_date;			//게시일
		String notice;			//게시글 내용
	}

	//게시글 작성 시 필요한 생성자(사용자는 게시판 제목과 게시글 내용만 작성)
	public Notice(String noti_title, String notice) {
		super();
		this.noti_title = noti_title;
		this.notice = notice;
	}

	//게시글 상세보기 시 필요한 생성자(전체를 불러옴)
	public Notice(int noti_no, String noti_title, String noti_cate, Date noti_date, String notice) {
		super();
		this.noti_no = noti_no;
		this.noti_title = noti_title;
		this.noti_cate = noti_cate;
		this.noti_date = noti_date;
		this.notice = notice;
	}

	public int getNoti_no() {
		return noti_no;
	}

	public void setNoti_no(int noti_no) {
		this.noti_no = noti_no;
	}

	public String getNoti_title() {
		return noti_title;
	}

	public void setNoti_title(String noti_title) {
		this.noti_title = noti_title;
	}

	public String getNoti_cate() {
		return noti_cate;
	}

	public void setNoti_cate(String noti_cate) {
		this.noti_cate = noti_cate;
	}

	public Date getNoti_date() {
		return noti_date;
	}

	public void setNoti_date(Date noti_date) {
		this.noti_date = noti_date;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Override
	public String toString() {
		return "Notice [noti_no=" + noti_no + ", noti_title=" + noti_title + ", noti_cate=" + noti_cate + ", noti_date="
				+ noti_date + ", notice=" + notice + "]";
	}
	
	
	
	
	
}
