package board.notice.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import static common.JDBCTemplate.*;
import board.notice.model.vo.Notice;

public class NoticeDao {

	//공통 특성인 Properties 사용부분 생성자로 명시
//	Properties prop = new Properties();
//	public NoticeDao() {
//		String fileName = NoticeDao.class.getResource("/sql/driver.properties").getPath();
//		try {
//			prop.load(new FileReader(fileName));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public int insertNotice(Connection conn, Notice noticeBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO BOARD VALUES(NOTICE_SEQ.NEXTVAL,?,'1',SYSDATE,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeBoard.getNoti_title());
			pstmt.setString(2, noticeBoard.getNotice());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} 
		return result;
		
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) FROM BOARD";
		int listCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}

	//selectList_선택한 범위만큼 게시판 리스트를 띄우는 메소드
	//추후 선택한 범위에 대한 최소/최대 매개변수를 넘길 예정
	public ArrayList<Notice> selectList(Connection conn, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Notice n = new Notice();
		ArrayList<Notice> list = new ArrayList<>();
//		list.add(n);
		String query = "SELECT * FROM NOTICE_VIEW WHERE RNUM BETWEEN ? AND ?";
		int startRow = (currentPage -1)*limit +1;
		int endRow = startRow + limit -1;
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				n = new Notice(rset.getInt("NOTI_NO"),
									  rset.getString("NOTI_TITLE"),
									  rset.getString("NOTI_CATE"),
									  rset.getDate("NOTI_DATE"),
									  rset.getString("NOTICE"));
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Notice selectNotice(Connection conn, int noti_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		
		String query = "SELECT * FROM NOTICE_VIEW WHERE NOTI_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noti_no);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice(rset.getInt("NOTI_NO"),
							   rset.getString("noti_title"),
							   rset.getString("noti_cate"),
							   rset.getDate("noti_date"),
							   rset.getString("notice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return n;
	}

}
