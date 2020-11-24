package artistapply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import artistapply.model.vo.Apply;
import artistapply.model.vo.Career;
import product.model.vo.Attachment;

import static common.JDBCTemplate.*;

public class ApplyDao {

	public int insertApply(Connection conn, Apply apply) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO SELLER VALUES(?, SYSDATE, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, apply.getUser_id());
			pstmt.setString(2, apply.getArtist_int());
			pstmt.setString(3, apply.getArtist_pro());
			pstmt.setString(4, apply.getCar_etc());
			pstmt.setString(5, apply.getCar_file());
			
			result = pstmt.executeUpdate();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int insertCareer(Connection conn, ArrayList<Career> carlist) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO CAREER VALUES(?, ?, ?)";
		
		try {
			
			for(int i = 0 ; i<carlist.size() ; i++) {
				
			Career career = carlist.get(i);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, career.getMyCareer());
			pstmt.setString(2, career.getUser_id());
			pstmt.setString(3, career.getMyCareerTerm());
			
			result += pstmt.executeUpdate();
			
			
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return result;
	}

	public int updateApply(Connection conn, Apply apply) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE SELLER SET USER_ID=?, ARTIST_INT =?, ARTIST_PRO=?, CAR_ETC=?, CAR_FILE=? WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, apply.getUser_id());
			pstmt.setString(2, apply.getArtist_int());
			pstmt.setString(3, apply.getArtist_pro());
			pstmt.setString(4, apply.getCar_etc());
			pstmt.setString(5, apply.getCar_file());
			pstmt.setString(6, apply.getUser_id());
		
			result = pstmt.executeUpdate();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateCareer(Connection conn, ArrayList<Career> carlist) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE CAREER SET CAREER =?, CAR_TERM=? WHERE USER_ID =?";
		
		try {
			
			for(int i = 0 ; i<carlist.size() ; i++) {
				
			Career career = carlist.get(i);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, career.getMyCareer());
			pstmt.setString(2, career.getMyCareerTerm());
			pstmt.setString(3, career.getUser_id());
			
			result += pstmt.executeUpdate();
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return result;
	}
	
	

}
