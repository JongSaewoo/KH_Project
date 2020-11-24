package board.inquiry.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.inquiry.model.vo.Inquiry;

public class InquiryDao {

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) FROM QUESTION";
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

	public ArrayList<Inquiry> selectList(Connection conn, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Inquiry in = new Inquiry();
		ArrayList<Inquiry> list = new ArrayList<>();
//		list.add(in);
		String query = "SELECT * FROM Q_VIEW WHERE RNUM BETWEEN ? AND ?";
		int startRow = (currentPage -1)*limit +1;
		int endRow = startRow + limit -1;
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				in = new Inquiry(rset.getInt("rnum"),
									  rset.getString("user_id"),
									  rset.getDate("q_date"),
									  rset.getString("q_cate"),
									  rset.getString("qeustion"),
									  rset.getString("q_yn"),
									  rset.getString("question_title"));
									 
				list.add(in);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertQuestion(Connection conn, Inquiry in) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO QUESTION VALUES(Q_SEQ.NEXTVAL,?,SYSDATE,?,?,'N',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, in.getUser_id());
			pstmt.setString(2, in.getQ_cate());
			pstmt.setString(3, in.getQuestion_title());
			pstmt.setString(4, in.getQuestion());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}



}
