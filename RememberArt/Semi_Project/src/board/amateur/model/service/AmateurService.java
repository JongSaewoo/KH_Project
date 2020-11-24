package board.amateur.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.amateur.model.dao.AmateurDao;
import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.AmateurLike;
import board.amateur.model.vo.FileManagement;
import board.amateur.model.vo.Reply;
import product.model.dao.ProductDao;
import product.model.vo.MasterpieceCount;
import product.model.vo.masterpiece;

public class AmateurService {

	public int insertBoard(Amateur a, ArrayList<FileManagement> fileList) {
		Connection conn = getConnection();
		AmateurDao aDao = new AmateurDao();
		
		int result1 = aDao.insertBoard(conn,a);
		int result2 = aDao.insertAmateurFile(conn,fileList);
		
		System.out.println("[service]게시글 등록여부 : "+result1);
		System.out.println("[service]파일 등록여부 : "+result2);
		if(result1>0 && result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result1;
	}

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new AmateurDao().getListCount(conn);
		close(conn);
		if(listCount>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return listCount;
	}

	public ArrayList<Amateur> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		
		ArrayList<Amateur> list = new AmateurDao().selectList(conn,currentPage, limit);
		System.out.println("[service]게시글 리스트 출력:"+list);
		close(conn);
		return list;
	}

	public ArrayList<FileManagement> selectList() {
		Connection conn = getConnection();
		
		ArrayList<FileManagement> list = new AmateurDao().selectList(conn);
		System.out.println("[service]파일 리스트 출력:"+list);
		close(conn);
		
		return list;
	}

	public Amateur selectBoard(int aid) {
		Connection conn = getConnection();
		
		AmateurDao aDao = new AmateurDao();
		
		int result = aDao.updateCount(conn,aid);
		System.out.println("[service] 조회수 증가 결과 :"+result);
		
		Amateur a = null;

		if(result>0) {
			commit(conn);
			a = aDao.selectBoard(conn,aid);
			System.out.println("[service] 조회한 게시글 결과:"+a);
				
		}else {
			rollback(conn);
		}
		close(conn);
		return a;
	}

	public FileManagement selectFile(int event_no) {
		Connection conn = getConnection();
		
		FileManagement fm = null;
		fm = new AmateurDao().selectFile(conn,event_no);
		System.out.println("[service] 조회한 파일명 결과 :"+fm);
		
		return fm;
	}
	
	/* 선택한 게시글의 댓글 리스트 조회용 서비스
	 * @param bid
	 * 
	 * @return ArrayList<Reply>
	 */
	public ArrayList<Reply> selectReplyList(int event_no) {
		Connection conn = getConnection();
		
		ArrayList<Reply> rList = new AmateurDao().selectReplyList(conn, event_no);
		System.out.println("[아마추어 게시판에서 게시글을 클릭했을 때] 댓글 인서트 결과 값(service):"+rList);
		close(conn);
		return rList;
	}
	/*
	 * 댓글 추가 후 새로운 갱신된 댓글 리스트 조회용 서비스
	 * @param r
	 * 
	 * @return ArrayList<Reply>
	 */
	public ArrayList<Reply> insertReply(Reply r) {

		Connection conn = getConnection();
		AmateurDao aDao = new AmateurDao();
		
		int result = aDao.insertReply(conn,r);
		
		ArrayList<Reply> rList = null;
		System.out.println("[service]댓글 인서트 결과값:"+result);
		if(result>0) {
			commit(conn);
			rList = aDao.selectReplyList(conn, r.getEvent_no());
			System.out.println("[service]댓글 인서트 결과값:"+rList);
		}else {
			rollback(conn);
			System.out.println("[service]댓글 인서트 실패");
		}
		close(conn);
		return rList;
	}


	//좋아요
	public int insertHeart(String user, int event_no) {
		Connection conn = getConnection();
		AmateurDao aDao = new AmateurDao();
		int check=1;
		int result = aDao.insertHeart(conn,user,event_no);
		if(result>0) {
			int update = aDao.updateHeart(conn, event_no, check);
		}
		close(conn);
		return result;
	}

	public int deleteHeart(String user, int event_no) {
		Connection conn = getConnection();
		AmateurDao aDao = new AmateurDao();
		int check=0;
		int result = aDao.deleteHeart(conn,user,event_no);
		if(result>0) {
			int update = aDao.updateHeart(conn, event_no, check);
		}
		close(conn);
		return result;
	}

	public int selectEventLike(int event_no) {
		Connection conn = getConnection();
		int count = new AmateurDao().selectEventLike(conn,event_no);
		
		return count;
	}

	public AmateurLike selectEventLikeO(int event_no) {
		Connection conn = getConnection();
		
		AmateurLike list = new AmateurDao().selectLikeList(conn, event_no);
		System.out.println("count출력:"+list);
		close(conn);
		
		return list;
	}

	public AmateurLike showHeart(int event_no, String user) {
		Connection conn = getConnection();
		
		AmateurLike show = new AmateurDao().selectLikeList(conn, event_no, user);
		System.out.println("count출력:"+show);
		close(conn);
		
		return show;
	}

	public ArrayList<Amateur> select(String category, String keyword) {
		ArrayList<Amateur> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = new AmateurDao().select(conn, category, keyword);
		
		commit(conn);
		return list;
	}

	




}
