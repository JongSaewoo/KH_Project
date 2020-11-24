package board.inquiry.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.inquiry.model.dao.InquiryDao;
import board.inquiry.model.vo.Inquiry;


public class InquiryService {

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = new InquiryDao().getListCount(conn);
		System.out.println("[InquiryService]게시글 갯수 출력:"+listCount);
		close(conn);
		return listCount;
	}

	public ArrayList<Inquiry> insertQuestion(Inquiry in, int currentPage, int limit) {
		Connection conn = getConnection();
		InquiryDao inDao = new InquiryDao();
		ArrayList<Inquiry> dataResult = null;
		int Result  = inDao.insertQuestion(conn,in);
		if(Result>0) {
			 dataResult = new InquiryDao().selectList(conn, currentPage,limit);
			System.out.println("[service]문의 인서트 결과값:"+dataResult);
			commit(conn);
			
		}	
		return dataResult;
	}



	public ArrayList<Inquiry> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		
		InquiryDao inDao = new InquiryDao();
		
		ArrayList<Inquiry> selectList = inDao.selectList(conn, currentPage, limit);
		
		return selectList;
	}

	
}
