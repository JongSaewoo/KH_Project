package masterpiece.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import product.model.vo.Attachment;
import product.model.vo.product;

public class MPDao {

	public ArrayList<Amateur> showMasterpiece(Connection conn, int firstPaint, int lastPaint) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Amateur a = null;
		ArrayList<Amateur> amateurMasterpiece = new ArrayList<>();
		
		String query = "SELECT * FROM MASTERPIECE_AMATEUR WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, firstPaint);
			pstmt.setInt(2, lastPaint);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				a = new Amateur(rset.getInt("event_no"),
								rset.getString("event_title"),
								rset.getString("user_id"));
				amateurMasterpiece.add(a);
			}
			System.out.println(amateurMasterpiece);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amateurMasterpiece;
	}

	public ArrayList<FileManagement> showMasterpieceFile(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FileManagement a = null;
		ArrayList<FileManagement> amateurMasterpieceFile = new ArrayList<>();
		
		String query = "SELECT * FROM EVENT_FILE";
		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				a = new FileManagement(rset.getInt("event_no"),
									   rset.getString("event_file"));
				amateurMasterpieceFile.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amateurMasterpieceFile;
	}

	public ArrayList<product> showProMasterpiece(Connection conn, int firstPaint, int lastPaint) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		product p = null;
		ArrayList<product> ProMasterpiece = new ArrayList<>();
		
		String query = "SELECT * FROM MASTERPIECE_PAINT WHERE R BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, firstPaint);
			pstmt.setInt(2, lastPaint);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				p = new product(rset.getInt("paint_no"),
								rset.getString("paint_name"),
								rset.getString("artist_name"));
				ProMasterpiece.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ProMasterpiece;
	}

	public ArrayList<Attachment> showProMasterpieceFile(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment a = null;
		ArrayList<Attachment> ProMasterpieceFile = new ArrayList<>();
		
		String query = "SELECT * FROM PAINT_PHOTO WHERE FILELEVEL=0";
		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				a = new Attachment(rset.getInt("paint_no"),
									   rset.getString("afile"));
				ProMasterpieceFile.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ProMasterpieceFile;
	}

}
