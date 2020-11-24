package product.model.vo;

public class product {
	
	private int paint_no;		//작품번호
	private String paint_name ;	//작품이름
	private int patint_price ;	//작품가격
	private String category ;	//작품카테고리
	private String artist_name ;	//작가이름
	private String paint_int ;	//작품소개
	private String paint_mdate ;	//작품제작년도
	private int size_no ;		//사이즈번호
	private int width ;			//사이즈 가로크기
	private int height;			//사이즈 세로크기
	private String auc_yn ;   	//경매여부
	
	private int afile_no;		//첨부사진번호
	private String afile;		//첨부파일
	private String filepath; 	//사진경로
	
	private String tag_name; 	//테그명
	private String pId;			//판매자 아이디
	
	
	public product() {
		
	}


	public product(int paint_no, String paint_name, int patint_price,  int size_no,String artist_name, String auc_yn) {
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.artist_name = artist_name;
		this.size_no = size_no;
		this.auc_yn = auc_yn;
	}


	public product(String paint_name, int patint_price) {
		super();
		this.paint_name = paint_name;
		this.patint_price = patint_price;
	}


	public product(int size_no, int width, int height) {
		this.size_no = size_no;
		this.width = width;
		this.height = height;
	}

	

	public product(int paint_no, String paint_name, String artist_name) {
		super();
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.artist_name = artist_name;
	}


	public product(int paint_no, String paint_name, int patint_price, String category, String artist_name,
			String paint_int, String paint_mdate, int size_no) {
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.category = category;
		this.artist_name = artist_name;
		this.paint_int = paint_int;
		this.paint_mdate = paint_mdate;
		this.size_no = size_no;
	}


	public product(int paint_no) {
		this.paint_no = paint_no;
	}


	public product(int paint_no, String tag_name) {
		this.paint_no = paint_no;
		this.tag_name = tag_name;
	}


	public product(String paint_name, String category, int patint_price) {
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.category = category;
	}


	public product(String paint_name, String artist_name, String category, String paint_mdate, int patint_price,String paint_int,
					String bWriter,int size) {
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.category = category;
		this.artist_name = artist_name;
		this.paint_mdate = paint_mdate;
		this.paint_int = paint_int;
		this.pId = bWriter;
		this.size_no = size;
	}


	public product(int paint_no, String paint_name, int patint_price, int size_no,String artist_name) {
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.size_no = size_no;
		this.artist_name = artist_name;
	}

	public product(int paint_no, String paint_name, int patint_price, String category, String artist_name,
			String paint_int, String paint_mdate, int size_no, int width, int height, String auc_yn, int afile_no,
			String afile) {
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.category = category;
		this.artist_name = artist_name;
		this.paint_int = paint_int;
		this.paint_mdate = paint_mdate;
		this.size_no = size_no;
		this.width = width;
		this.height = height;
		this.auc_yn = auc_yn;
		this.afile_no = afile_no;
		this.afile = afile;
	}


	public product(int paint_no, String paint_name, int patint_price, String category, String artist_name,
			String paint_int, String paint_mdate, String auc_yn) {
		this.paint_no = paint_no;
		this.paint_name = paint_name;
		this.patint_price = patint_price;
		this.category = category;
		this.artist_name = artist_name;
		this.paint_int = paint_int;
		this.paint_mdate = paint_mdate;
		this.auc_yn = auc_yn;
	}

	
	public String getpId() {
		return pId;
	}


	public void setpId(String pId) {
		this.pId = pId;
	}


	public String getTag_name() {
		return tag_name;
	}


	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}


	public int getPaint_no() {
		return paint_no;
	}

	public void setPaint_no(int paint_no) {
		this.paint_no = paint_no;
	}

	public String getPaint_name() {
		return paint_name;
	}

	public void setPaint_name(String paint_name) {
		this.paint_name = paint_name;
	}

	public int getPatint_price() {
		return patint_price;
	}

	public void setPatint_price(int patint_price) {
		this.patint_price = patint_price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getPaint_int() {
		return paint_int;
	}

	public void setPaint_int(String paint_int) {
		this.paint_int = paint_int;
	}

	public String getPaint_mdate() {
		return paint_mdate;
	}

	public void setPaint_mdate(String paint_mdate) {
		this.paint_mdate = paint_mdate;
	}

	public int getSize_no() {
		return size_no;
	}

	public void setSize_no(int size_no) {
		this.size_no = size_no;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getAuc_yn() {
		return auc_yn;
	}

	public void setAuc_yn(String auc_yn) {
		this.auc_yn = auc_yn;
	}

	public int getAfile_no() {
		return afile_no;
	}

	public void setAfile_no(int afile_no) {
		this.afile_no = afile_no;
	}

	public String getAfile() {
		return afile;
	}

	public void setAfile(String afile) {
		this.afile = afile;
	}


	@Override
	public String toString() {
		return "product [paint_no=" + paint_no + ", paint_name=" + paint_name + ", patint_price=" + patint_price
				+ ", category=" + category + ", artist_name=" + artist_name + ", paint_int=" + paint_int
				+ ", paint_mdate=" + paint_mdate + ", size_no=" + size_no + ", width=" + width + ", height=" + height
				+ ", auc_yn=" + auc_yn + ", afile_no=" + afile_no + ", afile=" + afile + ", tag_name=" + tag_name + "]";
	}


	
	
	
	
	
	

}
