package board.notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import board.notice.model.dao.NoticeDao;
import board.notice.model.vo.Notice;
public class NoticeService {

	public int insertNotice(Notice noticeBoard) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn,noticeBoard);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = new NoticeDao().getListCount(conn);
		System.out.println("[NoticeService]게시글 갯수 출력:"+listCount);
		close(conn);
		return listCount;
	}

	//selectList_ 선택한 리스트를 화면에 띄우는 메소드
	//추후 매개변수로  몇개의 페이지를 띄울지 정하는 변수 두개를 매개변수로 넘겨줄 것 (미완)
	public ArrayList<Notice> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn,currentPage, limit);
		System.out.println("[NoticeService]selectList출력-"+list);
		
		close(conn);
		return list;
	}

	public Notice selectNotice(int noti_no) {
		Connection conn = getConnection();
		NoticeDao nDao = new NoticeDao();
		
		//Notice에는 조회수를 따로 Count하지 않는다.(다른 게시판은 별도)
		
		Notice n = nDao.selectNotice(conn,noti_no);

		return n;
	}

}
