package board.updateBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import board.free.model.vo.Free;

public class UpdateDao {

	

	public int insertBoard(Connection conn, Free f, int updateNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE FREE_BOARD SET FREE_CONTENT=?, FREE_PICTURE=?, FREE_TITLE=? WHERE FREE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, f.getFree_content());
			pstmt.setString(2, f.getFree_picture());
			pstmt.setString(3, f.getFree_title());
			pstmt.setInt(4, updateNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("[Free게시판 update확인]"+f);
		return result;
	}

	public int insertBoard(Connection conn, Amateur a,  int updateNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE EVENT SET EVENT=?, EVENT_TITLE=?  WHERE EVENT_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, a.getEvent());
			pstmt.setString(2, a.getEvent_title());
			pstmt.setInt(3, updateNo);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("[아마추어게시판 update확인]"+a);
		
		return result;
	}

	public int insertFile(Connection conn, int updateNo, ArrayList<FileManagement> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE EVENT_FILE SET EVENT_FILE=?, EVENT_PATH=?  WHERE EVENT_NO=?";
		
		try {
			for(int i = 0;i<fileList.size();i++) {
				FileManagement fm = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, fm.getEvent_file());
				pstmt.setString(2, fm.getEvent_path());
				pstmt.setInt(3, updateNo);
							
			}
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("[아마추어게시판 update확인]"+result);
		
		return result;
	}

}
