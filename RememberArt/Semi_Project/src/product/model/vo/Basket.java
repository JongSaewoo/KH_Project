package product.model.vo;

public class Basket {
	private String basket_no;			 //장바구니 번호
	private String paint_name;			 //작품 이름
	private int paint_price;			 //작품 가격
	private String user_id;				 //아이디
	private int paint_no;				 //작품번호
	private String buy_yn;				 //구매여부
	public Basket() {
	}
	public Basket(String basket_no, String paint_name, int paint_price, String user_id, int paint_no, String buy_yn) {
		this.basket_no = basket_no;
		this.paint_name = paint_name;
		this.paint_price = paint_price;
		this.user_id = user_id;
		this.paint_no = paint_no;
		this.buy_yn = buy_yn;
	}
	public String getBasket_no() {
		return basket_no;
	}
	public void setBasket_no(String basket_no) {
		this.basket_no = basket_no;
	}
	public String getPaint_name() {
		return paint_name;
	}
	public void setPaint_name(String paint_name) {
		this.paint_name = paint_name;
	}
	public int getPaint_price() {
		return paint_price;
	}
	public void setPaint_price(int paint_price) {
		this.paint_price = paint_price;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getPaint_no() {
		return paint_no;
	}
	public void setPaint_no(int paint_no) {
		this.paint_no = paint_no;
	}
	public String getBuy_yn() {
		return buy_yn;
	}
	public void setBuy_yn(String buy_yn) {
		this.buy_yn = buy_yn;
	}
	@Override
	public String toString() {
		return "Basket [basket_no=" + basket_no + ", paint_name=" + paint_name + ", paint_price=" + paint_price
				+ ", user_id=" + user_id + ", paint_no=" + paint_no + ", buy_yn=" + buy_yn + "]";
	}
	
	
}
