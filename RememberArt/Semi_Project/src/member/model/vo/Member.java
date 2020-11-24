package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4952932019676617041L;
	
	private String userId; 		//아이디
	private String grade;		//회원등급
	private String userName;	//회원이름
	private String userPwd;		//회원 비밀번호
	private String email;		//이메일
	private String phone;		//핸드폰번호
	private String address;		//주소	
	private String nickname;	//닉네임
	private String accountGrade;//계정등급	
	private int point;			//포인트
	private String deleteYN;	//탈퇴여부
	private String deleteDate;	//탈퇴일
	private int cash;			//캐쉬
	private Date enrollDate;	//가입일
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Member(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	


	public Member(String userId, String userName, String accountGrade) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.accountGrade = accountGrade;
	}



	public Member(String userId, String userName, String userPwd, String email, String phone, String address,
			String nickname) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.nickname = nickname;
	}
	
	

	public Member(String userId, String grade, String userName, String userPwd, String email, String phone,
			String address, String nickname, String accountGrade, int point, String deleteYN, String deleteDate,
			int cash, Date enrollDate) {
		super();
		this.userId = userId;
		this.grade = grade;
		this.userName = userName;
		this.userPwd = userPwd;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.nickname = nickname;
		this.accountGrade = accountGrade;
		this.point = point;
		this.deleteYN = deleteYN;
		this.deleteDate = deleteDate;
		this.cash = cash;
		this.enrollDate = enrollDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAccountGrade() {
		return accountGrade;
	}
	public void setAccountGrade(String accountGrade) {
		this.accountGrade = accountGrade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", grade=" + grade + ", userName=" + userName + ", userPwd=" + userPwd
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", nickname=" + nickname
				+ ", accountGrade=" + accountGrade + ", point=" + point + ", deleteYN=" + deleteYN + ", deleteDate="
				+ deleteDate + ", cash=" + cash + ", enrollDate=" + enrollDate + "]";
	}


}







