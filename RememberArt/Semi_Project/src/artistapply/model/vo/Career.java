package artistapply.model.vo;

import java.io.Serializable;

public class Career implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8372460515843095133L;
	private String user_id;
	private String myCareer; //경력 명
	private String myCareerTerm; //경력 기간
	
	
	public Career() {
		super();
	}


	public Career(String myCareer, String myCareerTerm) {
		super();
		this.myCareer = myCareer;
		this.myCareerTerm = myCareerTerm;
	}


	public Career(String user_id, String myCareer, String myCareerTerm) {
		super();
		this.user_id = user_id;
		this.myCareer = myCareer;
		this.myCareerTerm = myCareerTerm;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getMyCareer() {
		return myCareer;
	}


	public void setMyCareer(String myCareer) {
		this.myCareer = myCareer;
	}


	public String getMyCareerTerm() {
		return myCareerTerm;
	}


	public void setMyCareerTerm(String myCareerTerm) {
		this.myCareerTerm = myCareerTerm;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Career [user_id=" + user_id + ", myCareer=" + myCareer + ", myCareerTerm=" + myCareerTerm + "]";
	}


	
	
	

	
}
