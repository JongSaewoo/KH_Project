package mypage_artist.RefundQnACard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import artistapply.model.dao.ApplyDao;
import artistapply.model.vo.Apply;
import artistapply.model.vo.Career;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.dao.ArtistDao;
import mypage_artist.RefundQnACard.model.vo.BuyList_a;
import mypage_artist.RefundQnACard.model.vo.Message;
import mypage_artist.RefundQnACard.model.vo.QnA_Q;
import product.model.vo.Attachment;

public class ArtistService {

	public int getListCount(String name) {
		Connection conn = getConnection();

		int listCount = new ArtistDao().getListCount(conn, name);

		close(conn);

		return listCount;
	}

	// 환불 리스트 받아오기
	public ArrayList<BuyList_a> selectList(String name, int currentPage, int limit) {
		Connection conn = getConnection();

		ArrayList<BuyList_a> list = new ArtistDao().selectList(conn, name, currentPage, limit);
		// BoardDao를 가서 selectList메소드 구현하기
		
		System.out.println("buylist_a list 출력 : " + list);

		close(conn);

		return list;
	}
	
	// 작가 이름로 이미지 받아오기
	public ArrayList<Attachment> selectAList(String name) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list2 =new ArtistDao().selectphoto(conn, name);
		
		System.out.println("list2 출력 : " + list2);
		
		close(conn);
		
		return list2;
	}


	// 감동카드 리스트 받아오기 
	public ArrayList<Message> selectCardList(String artistName, int currentPage, int limit) {
		
		Connection conn = getConnection();
		ArrayList<Message> list = new ArtistDao().selectCardList(conn, artistName, currentPage, limit);
		// BoardDao를 가서 selectList메소드 구현하기
		
		System.out.println("Message list 출력 : " + list);

		close(conn);

		return list;
	}

	// 감동카드 이미지 받아오기
	public ArrayList<Attachment> selectCAList(String artistName) {
		Connection conn = getConnection();
		ArrayList<Attachment> list2 =new ArtistDao().selectCAphoto(conn, artistName);
		
		System.out.println("list2 출력 : " + list2);
		
		close(conn);
		
		return list2;
	}

	// qna 목록 조회하기
	public ArrayList<QnA_Q> selectQnAList(String artistName, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<QnA_Q> qnalist = new ArtistDao().selectQnAList(conn, artistName, currentPage, limit);
		
		close(conn);
		
		return qnalist;
	}

	// qna 사진 받아오기
	public ArrayList<Attachment> selectQpList(String artistName) {
		Connection conn = getConnection();
		ArrayList<Attachment> list2 =new ArtistDao().selectQpphoto(conn, artistName);
		
		System.out.println("list2 출력 : " + list2);
		
		close(conn);
		
		return list2;
	}
	
	// 프로필 사진 받아오기
	public Apply selectPhoto(String user_id) {
		
		Connection conn = getConnection();
		
		Apply aphoto = new ArtistDao().selecAphoto(conn, user_id);
		
		System.out.println("aphoto : " + aphoto);
		
		close(conn);
		
		return aphoto;
	}

	public void updateRefund(String[] paramArray) {
		Connection conn = getConnection();
		
		new ArtistDao().updateRefundList(conn, paramArray);
		
		
		close(conn);
		
	}

	public void deleteCard(String[] messageArray) {
		Connection conn = getConnection();
		
		new ArtistDao().deleteCardList(conn, messageArray);
		
		close(conn);
		
	}

	public Member selectMember(String artist) {
		Connection conn = getConnection();
		
		Member m = new ArtistDao().selectMember(conn, artist);
		
		close(conn);
		
		return m;
	}

	public int getQnAListCount(String artistName) {
		Connection conn = getConnection();

		int listCount = new ArtistDao().getQnAListCount(conn, artistName);

		close(conn);

		return listCount;
	}

	public int getCardListCount(String artistName) {
		Connection conn = getConnection();

		int listCount = new ArtistDao().getCardListCount(conn, artistName);

		close(conn);

		return listCount;
	}

	public Apply selectApply(String userId) {
		Connection conn = getConnection();
		
		Apply artist = new ArtistDao().selectApply(conn, userId);
		
		close(conn);
		
		return artist;
	}

	public ArrayList<Career> selectCareer(String userId) {
		Connection conn = getConnection();
		

		ArrayList<Career> career =new ArtistDao().selectCareer(conn, userId);
		
		close(conn);
		return career;
	}

	public ArrayList<BuyList_a> alistSearch(String order_status, String term, String calendar1, String calendar2,
			String loginName) {
		Connection conn = getConnection();
		
		ArrayList<BuyList_a> alist = new ArtistDao().alistSearch(conn, order_status, term, calendar1, calendar2, loginName);
		
		close(conn);
		return alist;
	}

	public ArrayList<QnA_Q> qlistSearch(String order_status, String term, String calendar1, String calendar2,
			String loginName) {
		Connection conn = getConnection();
		ArrayList<QnA_Q> qlist= new ArtistDao().qlistSearch(conn, order_status, term, calendar1, calendar2,loginName);
		
		
		close(conn);
		return qlist;
	}

}
