package product.model.vo;

import java.util.Date;

public class Attachment {
	
	private int afile_no =0;			//파일번호
	private int paint_no =0;			//파일이 등록된 상품 번호
	private String savefileName =" ";	//저장 파일명
	private String filePath =" ";		//파일의 저장경로	
	private int fileLevel =0;			//대표이미지(0)와 일반이미지(1)
	
	public Attachment() {
	}
	
	public Attachment(int paint_no, String savefileName) {
		this.paint_no = paint_no;
		this.savefileName = savefileName;
	}

	public Attachment(int afile_no, int paint_no, String savefileName, String filePath, int fileLevel) {
		this.afile_no = afile_no;
		this.paint_no = paint_no;
		this.savefileName = savefileName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}


	public Attachment(int paint_no, String savefileName, String filePath, int fileLevel) {
		this.paint_no = paint_no;
		this.savefileName = savefileName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}


	
	
	public String getSavefileName() {
		return savefileName;
	}


	public void setSavefileName(String savefileName) {
		this.savefileName = savefileName;
	}


	public int getAfile_no() {
		return afile_no;
	}
	public void setAfile_no(int afile_no) {
		this.afile_no = afile_no;
	}
	public int getPaint_no() {
		return paint_no;
	}
	public void setPaint_no(int paint_no) {
		this.paint_no = paint_no;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}


	@Override
	public String toString() {
		return "Attachment [afile_no=" + afile_no + ", paint_no=" + paint_no + ", savefileName=" + savefileName
				+ ", filePath=" + filePath + ", fileLevel=" + fileLevel + "]";
	}

	


	
}
