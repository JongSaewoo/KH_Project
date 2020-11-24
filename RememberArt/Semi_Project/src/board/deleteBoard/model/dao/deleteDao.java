package board.deleteBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class deleteDao {


	public int deleteBoard(Connection conn, int deleteNo, String queryA, String queryB) {
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		String query = queryA+" WHERE "+queryB+"=?";		
		System.out.println(query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteNo);
				
			deleteResult = pstmt.executeUpdate();

				if(deleteResult>0) {
					System.out.println("delete가 성공적으로 이루어졌습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return deleteResult;
	}

	public int deleteFile(Connection conn, int deleteNo) {
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		String query = "DELETE FROM EVENT_FILE WHERE EVENT_NO=?";			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteNo);
				
			deleteResult = pstmt.executeUpdate();

				if(deleteResult>0) {
					System.out.println("delete file이 성공적으로 이루어졌습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return deleteResult;
	}

	public int deleteLike(Connection conn, int deleteNo) {
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		String query = "DELETE FROM EVENT_LIKE WHERE EVENT_NO=?";			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteNo);
				
			deleteResult = pstmt.executeUpdate();

				if(deleteResult>0) {
					System.out.println("delete like가 성공적으로 이루어졌습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return deleteResult;
		}

	public int deleteReply(Connection conn, int deleteNo) {
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		String query = "DELETE FROM EVENT_REPLY WHERE EVENT_NO=?";			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteNo);
				
			deleteResult = pstmt.executeUpdate();

				if(deleteResult>0) {
					System.out.println("delete REPLY이 성공적으로 이루어졌습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return deleteResult;
	}

}
