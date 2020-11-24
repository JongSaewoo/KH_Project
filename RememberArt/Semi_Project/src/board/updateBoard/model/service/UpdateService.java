package board.updateBoard.model.service;

import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import board.free.model.vo.Free;
import board.updateBoard.model.dao.UpdateDao;
public class UpdateService {


	public int insertBoard(Free f, int updateNo) {
		
		Connection conn = getConnection();
		
		int result = new UpdateDao().insertBoard(conn,f, updateNo);
		
		if(result>0) {
			System.out.println("[Free게시판 insert확인]:"+result);
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
		
	}

	public int insertBoard(Amateur a, ArrayList<FileManagement> fileList, int updateNo) {
		Connection conn = getConnection();
		int result = new UpdateDao().insertBoard(conn, a, updateNo);
		if(result>0) {
			int resultFile = new UpdateDao().insertFile(conn, updateNo, fileList);
			System.out.println("[아마추어게시판 insert확인]:"+result);
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
		}

}
