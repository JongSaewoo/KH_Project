package board.deleteBoard.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import board.deleteBoard.model.dao.deleteDao;
public class deleteService {

	public int deleteBoard(int deleteNo) {
		Connection conn = getConnection();
		int deleteResult = 0;
		String queryA="DELETE FROM BOARD";
		String queryB="NOTI_NO";
		deleteResult = new deleteDao().deleteBoard(conn, deleteNo, queryA, queryB);
		
		if(deleteResult>0){
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return deleteResult;
		
	}

	public int deleteAmateur(int deleteNo) {
		Connection conn = getConnection();
		int deleteResult = 0;
		String queryA="DELETE FROM EVENT_FILE";
		String queryB="EVENT_NO";
		deleteResult = new deleteDao().deleteBoard(conn, deleteNo, queryA, queryB);
		
		if(deleteResult>0){
			int deleteLike = new deleteDao().deleteLike(conn,deleteNo);
			int deleteReply = new deleteDao().deleteReply(conn,deleteNo);
			int deleteFile = new deleteDao().deleteBoard(conn,deleteNo,"DELETE FROM EVENT","EVENT_NO");
			System.out.println("file="+deleteFile+"/Like="+deleteLike+"/deleteReply="+deleteReply);
			
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return deleteResult;
	}

	public int deleteFree(int deleteNo) {
		Connection conn = getConnection();
		int deleteResult = 0;
		String queryA="DELETE FROM FREE_BOARD_REPLY";
		String queryB="FREE_NO";
		deleteResult = new deleteDao().deleteBoard(conn, deleteNo, queryA, queryB);
		
		deleteResult = new deleteDao().deleteBoard(conn, deleteNo, "DELETE FROM FREE_BOARD", "FREE_NO");
		
		commit(conn);
		
		return deleteResult;
	}

}
