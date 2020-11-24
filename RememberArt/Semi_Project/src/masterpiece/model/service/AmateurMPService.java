package masterpiece.model.service;

import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import masterpiece.model.dao.MPDao;
import product.model.vo.Attachment;
import product.model.vo.product;

public class AmateurMPService {

	public ArrayList<Amateur> showMasterpiece(int firstPaint, int lastPaint) {
		Connection conn = getConnection();
		
		ArrayList<Amateur> amateurMasterpiece = new MPDao().showMasterpiece(conn,firstPaint, lastPaint);
		
		return amateurMasterpiece;
	}

	public ArrayList<FileManagement> showMasterpiece() {
		Connection conn = getConnection();
		
		ArrayList<FileManagement> amateurFileMasterpiece = new MPDao().showMasterpieceFile(conn);
		
		return amateurFileMasterpiece;
	}

	public ArrayList<product> showProMasterpiece(int firstPaint, int lastPaint) {
		Connection conn = getConnection();
		
		ArrayList<product> paintMasterpiece = new MPDao().showProMasterpiece(conn,firstPaint, lastPaint);
		
		return paintMasterpiece;
	}

	public ArrayList<Attachment> showProMasterpiece() {
		Connection conn = getConnection();
		
		ArrayList<Attachment> paintFileMasterpiece = new MPDao().showProMasterpieceFile(conn);
		
		return paintFileMasterpiece;
	}

}
