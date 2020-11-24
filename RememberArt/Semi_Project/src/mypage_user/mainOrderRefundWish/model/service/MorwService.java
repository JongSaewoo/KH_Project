package mypage_user.mainOrderRefundWish.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import mypage_artist.management.model.dao.Mypage_ArtistDao;
import mypage_artist.management.model.vo.Mypage_artist;
import mypage_user.mainOrderRefundWish.model.dao.MorwDao;
import mypage_user.mainOrderRefundWish.model.vo.Morw;
import product.model.vo.Attachment;

public class MorwService {

	
	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new MorwDao().getListCount(conn);
		
		close(conn);
			
		return listCount;
	}

	public ArrayList<Morw> selectList(String user_id) {
		Connection conn = getConnection();
		
		ArrayList<Morw> list = new MorwDao().selectList(conn,user_id);
		//BoardDao를 가서 selectList메소드 구현하기
		
		
		close(conn);
		return list;
	}
//	
//	public ArrayList<Morw> selectList(String userId, String searchStatus) {
//		
//		Connection conn = getConnection();
//		
//		ArrayList<Morw> list = new MorwDao().selectList(conn,userId,searchStatus);
//		//BoardDao를 가서 selectList메소드 구현하기
//		
//		
//		close(conn);
//		return list;
//		
//	}


	public ArrayList<Morw> refundList(String user_id) {
		Connection conn = getConnection();
		
		ArrayList<Morw> list = new MorwDao().refundList(conn,user_id);
		System.out.println("service : "+list.size());
		close(conn);
		return list;
	}

	public ArrayList<Morw> wishlistList(String user_id) {
		Connection conn =getConnection();
		
		ArrayList<Morw> list = new MorwDao().wishlistList(conn,user_id);
		close(conn);
		return list;
	}
	
	public void wishlistDelete(String whereSQL) {
		Connection conn =getConnection();
		
		new MorwDao().wishlistDelete(conn,whereSQL);
		
		close(conn);
		
	}

	public ArrayList<Attachment> selectPList(String userId) {
		Connection conn = getConnection();
		ArrayList<Attachment> plist = new MorwDao().selectPlist(conn,userId);
		
		
		close(conn);
		return plist;
	}

	public void updateStatus(String param) {
		Connection conn =getConnection();
		
		new MorwDao().updateStatus(conn,param);
		
		close(conn);
		
		
		
	}

	public int insertMessage(Morw morw)  {
		Connection conn =getConnection();
		
		int result = new MorwDao().insertMessage(conn,morw);
		
		close(conn);
		
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
		
	}


	public int insertRefund(Morw morw) {
		Connection conn =getConnection();
		
		int result = new MorwDao().insertRefund(conn,morw);
		
		close(conn);
		
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
		
	}

	public void updateStatus2(String orderNo) {
		Connection conn =getConnection();
		
		new MorwDao().updateStatus2(conn,orderNo);
		
		close(conn);
		
	}

	public int countSelectList(String userId) {
	
		Connection conn = getConnection();
		
		int listCount = new MorwDao().countSelectList(conn,userId );
		
		close(conn);
		
		return listCount;

	}

	public int countSelectList2(String userId) {
		Connection conn = getConnection();
		
		int listCount = new MorwDao().countSelectList2(conn,userId );
		
		close(conn);
		
		return listCount;
	}

	public ArrayList<Morw> searchList(String order_status, String term, String calendar1, String calendar2,
			String userId) {
		Connection conn = getConnection();
		
		ArrayList<Morw> search_list = new MorwDao().listSearch(conn, order_status, term, calendar1, calendar2, userId);
		
		close(conn);
		System.out.println(search_list);
		return search_list;
	}

	public ArrayList<Morw> searchRefundList(String order_status, String term, String calendar1, String calendar2,
			String userId) {
		Connection conn = getConnection();
		
		ArrayList<Morw> refund_search_list = new MorwDao().searchRefundList(conn, order_status, term, calendar1, calendar2, userId);
		
		close(conn);
		System.out.println(refund_search_list);
		return refund_search_list;
	}

	
}
