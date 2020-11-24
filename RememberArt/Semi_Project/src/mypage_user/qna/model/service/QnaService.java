package mypage_user.qna.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import mypage_user.mainOrderRefundWish.model.dao.MorwDao;
import mypage_user.qna.model.dao.QnaDao;
import mypage_user.qna.model.vo.Qna;

import static common.JDBCTemplate.*;


public class QnaService {

	public ArrayList<Qna> selectList(String user_id) {
		Connection conn = getConnection();
		
		ArrayList<Qna> list = new QnaDao().selectList(conn,user_id);
		
		System.out.println("qna service단 list 출력"+list);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Qna> selectList2(String user_id) {
		Connection conn = getConnection();
		
		ArrayList<Qna> list2 = new QnaDao().selectList2(conn,user_id);
		
		System.out.println("qna service단 list 출력"+list2);
		
		close(conn);
		
		return list2;
	}


	

	
}
