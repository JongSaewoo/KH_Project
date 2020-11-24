package board.free.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import board.free.model.vo.Free;
import board.free.model.vo.Reply;

public class FreeDao {

	public int insertBoard(Connection conn, Free f) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO FREE_BOARD VALUES(FREE_SEQ.NEXTVAL,?,SYSDATE,?,0,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, f.getUser_id());
			pstmt.setString(2, f.getFree_content());
			pstmt.setString(3, f.getFree_picture());
			pstmt.setString(4, f.getFree_title());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("[Free게시판 insert확인]"+f);
		return result;
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM FREE_BOARD";
		int listCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		System.out.println("[Free게시판 getListCount확인]:"+listCount);
		return listCount;
	}

	public ArrayList<Free> selectList(Connection conn, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		ArrayList<Free> list = new ArrayList<>();
		String query = "SELECT * FROM FREE_BOARD WHERE FREE_NO BETWEEN ? AND ?";
		
		int startRow = (currentPage -1)*limit+1;
		int endRow = startRow+limit-1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Free f = new Free(rset.getInt("free_no"),
										rset.getString("user_id"),
										rset.getDate("free_date"),
										rset.getString("free_title"),
										rset.getString("free_content"),
										rset.getInt("free_hit"),
										rset.getString("free_picture"));
				list.add(f);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("[Free게시판 selectList확인]:"+list);
		return list;
	}

	public int updateCount(Connection conn, int free_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE FREE_BOARD SET FREE_HIT = FREE_HIT+1 WHERE FREE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, free_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Free selectBoard(Connection conn, int free_no) {

		PreparedStatement pstmt = null;
		ResultSet rset= null;
		Free f = new Free();
		String query = "SELECT * FROM FREE_BOARD WHERE FREE_NO=?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, free_no);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				f = new Free(rset.getInt("free_no"),
						rset.getString("user_id"),
						rset.getDate("free_date"),
						rset.getString("free_title"),
						rset.getString("free_content"),
						rset.getInt("free_hit"),
						rset.getString("free_picture"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return f;
	
	}

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
//		System.out.println("AmateurDao"+r.getEvent_no());
//		System.out.println("AmateurDao"+r.getReply());
//		System.out.println("AmateurDao"+r.getUser_id());
//		
		String query = "INSERT INTO FREE_BOARD_REPLY VALUES(FREE_BOARD_SEQ.NEXTVAL,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, r.getFree_no());
			pstmt.setString(2, r.getUser_id());
			pstmt.setString(3, r.getFree_r_content());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("[댓글 insert]Amateur Dao:"+result);
		return result;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int free_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Reply> rList = null;
		
		String query = "SELECT * FROM FREE_BOARD_REPLY WHERE FREE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, free_no);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<Reply>();
			while(rset.next()) {
				rList.add(new Reply(rset.getInt("FREE_R_NO"),
									rset.getInt("FREE_NO"),
									rset.getString("USER_ID"),
									rset.getString("FREE_R_CONTENT"),
									rset.getDate("FREE_R_DATE")));
			}
			System.out.println("[자유게시판에서 게시글을 클릭했을 때(dao)] event_no 값:"+free_no);
			System.out.println("[자유게시판에서 게시글을 클릭했을 때(dao)] rList 값:"+rList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}






}
