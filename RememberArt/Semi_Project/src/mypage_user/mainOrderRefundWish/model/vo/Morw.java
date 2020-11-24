package mypage_user.mainOrderRefundWish.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Morw implements Serializable {

	
	private static final long serialVersionUID = -5807071061366059782L;
	

	private String orderNo;
	private String aFile;
	private String paintName;
	private int paintPrice;
	private Date orderDate;
	private String orderStatus;
	private String userId;
	private String artistName;
	private int basketNo;
	private int paintNo;
	private String message;
	//환불계좌 등록
	private String refundReason;
	private String accountName;
	private String accountNo;
	private String bank;
	private String buyYN;
	
	
	public Morw() {
		super();
	}
	
	

	//메인,오더
	public Morw(String orderNo, int paintNo, String aFile, String paintName, String artistName, int paintPrice, Date orderDate, String orderStatus) {
		super();
		this.orderNo = orderNo;
		this.paintNo = paintNo;
		this.aFile = aFile;
		this.paintName = paintName;
		this.artistName = artistName;
		this.paintPrice = paintPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}


	
	//리펀
	public Morw(String orderNo, int paintNo, String aFile, String paintName, String artistName, int paintPrice, String orderStatus) {
		super();
		this.orderNo = orderNo;
		this.paintNo = paintNo;
		this.aFile = aFile;
		this.paintName = paintName;
		this.artistName = artistName;
		this.paintPrice = paintPrice;
		this.orderStatus = orderStatus;
	}
	
	
	

	//위시리스트
	public Morw(int basketNo, int paintNo, String paintName, String artistName,int paintPrice, String buyYN) {
		super();
		this.basketNo = basketNo;
		this.paintNo = paintNo;
		this.paintName = paintName;
		this.artistName = artistName;
		this.paintPrice = paintPrice;
		this.buyYN = buyYN;
	}
	
	
	

	public Morw(String orderNo, int paintNo, String userId, String message, String paintName,  String artistName) {
		super();
		this.orderNo = orderNo;
		this.paintNo = paintNo;
		this.userId = userId;
		this.message = message;
		this.paintName = paintName;
		this.artistName = artistName;
	}
	
	
	public Morw(String orderNo, int paintNo,  String message, String paintName,  String artistName) {
		super();
		this.orderNo = orderNo;
		this.paintNo = paintNo;
		this.message = message;
		this.paintName = paintName;
		this.artistName = artistName;
	}

	
	


	public Morw(String orderNo, int paintNo, String userId,  String refundReason, String accountName, String accountNo,
			String bank) {
		super();
		this.orderNo = orderNo;
		this.paintNo = paintNo;
		this.userId = userId;
		this.refundReason = refundReason;
		this.accountName = accountName;
		this.accountNo = accountNo;
		this.bank = bank;
	}



	public Morw(String orderNo, String aFile, String paintName, int paintPrice, Date orderDate, String orderStatus,
			String userId, String artistName, int basketNo, int paintNo, String message, String refundReason,
			String accountName, String accountNo, String bank) {
		super();
		this.orderNo = orderNo;
		this.aFile = aFile;
		this.paintName = paintName;
		this.paintPrice = paintPrice;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.userId = userId;
		this.artistName = artistName;
		this.basketNo = basketNo;
		this.paintNo = paintNo;
		this.message = message;
		this.refundReason = refundReason;
		this.accountName = accountName;
		this.accountNo = accountNo;
		this.bank = bank;
	}


	

	public String getBuyYN() {
		return buyYN;
	}



	public void setBuyYN(String buyYN) {
		this.buyYN = buyYN;
	}



	public String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public String getaFile() {
		return aFile;
	}



	public void setaFile(String aFile) {
		this.aFile = aFile;
	}



	public String getPaintName() {
		return paintName;
	}



	public void setPaintName(String paintName) {
		this.paintName = paintName;
	}



	public int getPaintPrice() {
		return paintPrice;
	}



	public void setPaintPrice(int paintPrice) {
		this.paintPrice = paintPrice;
	}



	public Date getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getArtistName() {
		return artistName;
	}



	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}



	public int getBasketNo() {
		return basketNo;
	}



	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}



	public int getPaintNo() {
		return paintNo;
	}



	public void setPaintNo(int paintNo) {
		this.paintNo = paintNo;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getRefundReason() {
		return refundReason;
	}



	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}



	public String getAccountName() {
		return accountName;
	}



	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	public String getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}



	public String getBank() {
		return bank;
	}



	public void setBank(String bank) {
		this.bank = bank;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Morw [orderNo=" + orderNo + ", aFile=" + aFile + ", paintName=" + paintName + ", paintPrice="
				+ paintPrice + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", userId=" + userId
				+ ", artistName=" + artistName + ", basketNo=" + basketNo + ", paintNo=" + paintNo + ", message="
				+ message + ", refundReason=" + refundReason + ", accountName=" + accountName + ", accountNo="
				+ accountNo + ", bank=" + bank + ", buyYN=" + buyYN + "]";
	}



	
	

	


	






}
