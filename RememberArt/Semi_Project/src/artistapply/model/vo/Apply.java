package artistapply.model.vo;

import java.io.Serializable;

public class Apply implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5249675792709279517L;
	private String user_id;		// 회원 아이디
	private String apply_date;	// 신청 날짜
	private String artist_int;	// 작가 자기 소개
	private String car_etc;		// 기타 이력
	private String artist_pro; 	// 작가 사진 파일 이름
	private String car_file;	// 추가 이력 파일 이름
	private	Career career;		// 경력
	
	public Apply() {
		super();
	}


	

	public Apply(String user_id, String artist_int, String artist_pro) {
		this.user_id = user_id;
		this.artist_int = artist_int;
		this.artist_pro = artist_pro;
	}




	public Apply(String user_id, String artist_int, String car_etc,String car_file, String artist_pro) {
		super();
		this.user_id = user_id;
		this.artist_int = artist_int;
		this.car_etc = car_etc;
		this.artist_pro = artist_pro;
		this.car_file = car_file;
	}




	public Apply(String user_id, String artist_int,String artist_pro, String car_etc, String car_file, Career career) {
		super();
		this.user_id = user_id;
		this.artist_int = artist_int;
		this.car_etc = car_etc;
		this.artist_pro = artist_pro;
		this.car_file = car_file;
		this.career = career;
	}
	
	



	public Apply(String user_id, String apply_date, String artist_int, String artist_pro, String car_etc, 
			String car_file) {
		super();
		this.user_id = user_id;
		this.apply_date = apply_date;
		this.artist_int = artist_int;
		this.car_etc = car_etc;
		this.artist_pro = artist_pro;
		this.car_file = car_file;
	}




	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	

	public String getApply_date() {
		return apply_date;
	}




	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}




	public String getArtist_int() {
		return artist_int;
	}

	public void setArtist_int(String artist_int) {
		this.artist_int = artist_int;
	}

	public String getCar_etc() {
		return car_etc;
	}

	public void setCar_etc(String car_etc) {
		this.car_etc = car_etc;
	}

	public String getArtist_pro() {
		return artist_pro;
	}

	public void setArtist_pro(String artist_pro) {
		this.artist_pro = artist_pro;
	}

	public String getCar_file() {
		return car_file;
	}

	public void setCar_file(String car_file) {
		this.car_file = car_file;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "Apply [user_id=" + user_id + ", apply_date=" + apply_date + ", artist_int=" + artist_int + ", car_etc="
				+ car_etc + ", artist_pro=" + artist_pro + ", car_file=" + car_file + ", career=" + career + "]";
	}

	

	
	
	

	
	

}
