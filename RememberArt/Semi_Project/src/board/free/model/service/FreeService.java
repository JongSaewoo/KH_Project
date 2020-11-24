package board.free.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.amateur.model.dao.AmateurDao;
import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import board.free.model.dao.FreeDao;
import board.free.model.vo.Free;
import board.free.model.vo.Reply;

public class FreeService {

	public int insertBoard(Free f) {
		Connection conn = getConnection();
		FreeDao fDao = new FreeDao();
		
		int result = fDao.insertBoard(conn,f);
		
		if(result>0) {
			System.out.println("[Free게시판 insert확인]:"+result);
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
		
	}

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new FreeDao().getListCount(conn);
		close(conn);
		if(listCount>0) {
			System.out.println("[Free게시판 insert확인]:"+listCount);
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return listCount;
	}

	public ArrayList<Free> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		
		ArrayList<Free> list = new FreeDao().selectList(conn,currentPage,limit);
		System.out.println("[Free게시판 selectList확인]:"+list);
		close(conn);
		return list;
		
	}

	public Free selectFreeBoard(int free_no) {
		Connection conn = getConnection();
		
		FreeDao fDao = new FreeDao();
		
		int result = fDao.updateCount(conn,free_no);
		System.out.println("[Free게시판 service] 조회수 증가 결과 :"+result);
		
		Free f = null;

		if(result>0) {
			commit(conn);
			f = fDao.selectBoard(conn,free_no);
			System.out.println("[service] 조회한 게시글 결과:"+f);
				
		}else {
			rollback(conn);
		}
		close(conn);
		return f;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		
		FreeDao fDao = new FreeDao();
		
		int result = fDao.insertReply(conn,r);
		
		ArrayList<Reply> rList = null;
		if(result>0) {
			commit(conn);
			rList = fDao.selectReplyList(conn,r.getFree_no());
			System.out.println("[Free게시판 service]게시글 조회:"+rList);
		}else {
			rollback(conn);
			System.out.println("댓글  인서트 실패 ");
		}
		close(conn);
		return rList;
	}

	public ArrayList<Reply> selectReply(int free_no) {
		Connection conn = getConnection();
		
		FreeDao fDao = new FreeDao();
		ArrayList<Reply> f = null;

		f = fDao.selectReplyList(conn,free_no);
		System.out.println("[service] 조회한 게시글 결과:"+f);
				
		close(conn);
		return f;
	}



}
