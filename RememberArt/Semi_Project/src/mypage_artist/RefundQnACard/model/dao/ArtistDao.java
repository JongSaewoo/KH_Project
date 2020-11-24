package mypage_artist.RefundQnACard.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import artistapply.model.vo.Apply;
import artistapply.model.vo.Career;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.vo.BuyList_a;
import mypage_artist.RefundQnACard.model.vo.Message;
import mypage_artist.RefundQnACard.model.vo.QnA_Q;
import product.model.vo.Attachment;

public class ArtistDao {

	public int getListCount(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT (*) FROM BUY_LIST B JOIN PAINT P ON(P.PAINT_NO = B.PAINT_NO) JOIN MEMBER M ON(M.USER_NAME = P.ARTIST_NAME)"
				+ "WHERE P.ARTIST_NAME = ? AND ORDER_STATUS LIKE '환불%'";
		int listCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	public ArrayList<BuyList_a> selectList(Connection conn, String artistName, int currentPage, int limit) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<BuyList_a> list = new ArrayList<>();
		
		String query = "SELECT *"
				+ "FROM BUY_LIST B JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
				+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS LIKE '환불%' OR B.ORDER_STATUS LIKE '반품%' AND B.PAINT_NO BETWEEN ? AND ?";
	
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, artistName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				BuyList_a b = new BuyList_a(
				
				rset.getString("order_no"),
				rset.getString("user_id"),
				rset.getString("order_status"),
				rset.getInt("paint_no"),
				rset.getString("paint_price"),
				rset.getString("artist_name"),
				rset.getString("paint_name"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Attachment> selectphoto(Connection conn, String artistName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		String query = "SELECT * FROM PAINT_PHOTO PP JOIN PAINT P ON (PP.PAINT_NO = P.PAINT_NO)"
				+ "JOIN BUY_LIST B ON (PP.PAINT_NO = B.PAINT_NO) JOIN MEMBER M ON(M.USER_NAME = P.ARTIST_NAME)"
				+ "WHERE FILELEVEL=0 AND P.ARTIST_NAME =?";
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, artistName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Attachment p = new Attachment(rset.getInt("PAINT_NO"),
										rset.getString("AFILE"),
										rset.getString("FILEPATH"),	
										rset.getInt("FILELEVEL"));

				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao attachment:" + list);

		return list;
	}


	// 감동 카드 리스트 받아오기
	public ArrayList<Message> selectCardList(Connection conn, String artistName, int currentPage, int limit) {
	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Message> list = new ArrayList<>();
		
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* \n" + 
				"FROM (SELECT * FROM MESSAGE ME JOIN MEMBER M ON(ME.USER_ID = M.USER_ID)\n" + 
				"WHERE ARTIST_NAME =? ORDER BY ORDER_NO DESC) A) WHERE RNUM >=?  AND RNUM <=?";
		
		// 쿼리문 실행시 조건절에 넣을 변수를 (ROWNUM에 대한 조건 시 필요) 연산처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, artistName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Message m = new Message(
				
				rset.getString("order_no"),
				rset.getInt("paint_no"),
				rset.getString("user_id"),
				rset.getString("message"),
				rset.getString("paint_name"),
				rset.getString("artist_name"),
				rset.getString("user_name"));
				
				list.add(m);
			}
			
			System.out.println("테스트 출력 : " + list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	// 카드 메세지 사진
	public ArrayList<Attachment> selectCAphoto(Connection conn, String artistName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		String query = "SELECT * FROM PAINT_PHOTO P JOIN MESSAGE M ON (P.PAINT_NO = M.PAINT_NO) WHERE FILELEVEL=0 AND M.ARTIST_NAME = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, artistName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Attachment p = new Attachment(rset.getInt("PAINT_NO"),
										rset.getString("AFILE"),
										rset.getString("FILEPATH"),	
										rset.getInt("FILELEVEL"));

				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao attachment:" + list);

		return list;
	}
	
	public ArrayList<QnA_Q> selectQnAList(Connection conn, String artistName, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		ArrayList<QnA_Q> qnalist = new ArrayList<>();
		
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.*  FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO) WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A) WHERE RNUM >= ? AND RNUM <=?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, artistName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				QnA_Q q = new QnA_Q(
						
						rset.getInt("pq_no"),
						rset.getString("pquestion"),
						rset.getString("pq_date"),
						rset.getInt("paint_no"),
						rset.getString("user_id"),
						rset.getString("pq_yn"),
						rset.getString("paint_name")
						
						);
				
				qnalist.add(q);
				
				System.out.println(q);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return qnalist;
	}

	public ArrayList<Attachment> selectQpphoto(Connection conn, String artistName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		String query = "SELECT * FROM PAINT_PHOTO PP JOIN PAINT P ON ( P.PAINT_NO = PP.PAINT_NO) WHERE FILELEVEL=0 AND P.ARTIST_NAME = ?";
				

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, artistName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Attachment p = new Attachment(rset.getInt("PAINT_NO"),
										rset.getString("AFILE"),
										rset.getString("FILEPATH"),	
										rset.getInt("FILELEVEL"));

				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao attachment:" + list);

		return list;
	}
	

	public Apply selecAphoto(Connection conn, String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Apply a = new Apply();
		
		String query = "SELECT*FROM SELLER WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user_id);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
			a = new Apply(
					
					rset.getString("user_id"),
					rset.getString("artist_int"),
					rset.getString("car_etc"),
					rset.getString("car_file"),
					rset.getString("artist_pro")
					
					);
			
			}
			
			System.out.println(a);
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		
		return a;
	}

	public void updateRefundList(Connection conn, String[] paramArray) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BUY_LIST SET ORDER_STATUS = '환불 완료' WHERE ORDER_NO = ?";
		
		try {
			for(int i = 0 ; i < paramArray.length ; i++) {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, paramArray[i]);
			
			result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(result);
		
	}

	public void deleteCardList(Connection conn, String[] messageArray) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "DELETE FROM MESSAGE WHERE ORDER_NO = ?";
		
		try {
			for(int i = 0 ; i < messageArray.length ; i++) {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, messageArray[i]);
			
			result += pstmt.executeUpdate();
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		// 확인
		System.out.println(result);
		
	}

	public Member selectMember(Connection conn, String artist) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member m = new Member();
		
		String query = "SELECT*FROM MEMBER WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, artist);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
			m = new Member(
					
					rset.getString("user_id"),
					rset.getString("user_name"),
					rset.getString("account_grade")
					
					);
			
			}
			
			System.out.println(m);
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		
		return m;
	}

	public int getQnAListCount(Connection conn, String artistName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM PAINT_Q Q JOIN PAINT P ON (P.PAINT_NO = Q.PAINT_NO) JOIN MEMBER M ON(M.USER_NAME = P.ARTIST_NAME) WHERE P.ARTIST_NAME = ?";
		
		int listCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, artistName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
				
				System.out.println("카운트 : " + listCount);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	public int getCardListCount(Connection conn, String artistName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM MESSAGE M JOIN MEMBER ME ON(ME.USER_ID = M.USER_ID) WHERE ARTIST_NAME =?";
		
		int listCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, artistName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
				
				System.out.println("카드리스트 카운트 : " + listCount);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return listCount;
	}

	public Apply selectApply(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM SELLER WHERE USER_ID = ?";
		
		Apply artist = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				artist = new Apply(
						
						rset.getString("user_id"),
						rset.getString("apply_date"),
						rset.getString("artist_int"),
						rset.getString("artist_pro"),
						rset.getString("car_etc"),
						rset.getString("car_file")
						
						);
				
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
			
		}
		
		return artist;
	}

	public ArrayList<Career> selectCareer(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Career> career = new ArrayList<>();
		
		
		String query = "SELECT * FROM CAREER WHERE USER_ID = ? ";
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Career c = new Career(rset.getString("user_id"),
									  rset.getString("career"),
									  rset.getString("car_term")
							);
		
				career.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return career;
	}

	public ArrayList<BuyList_a> alistSearch(Connection conn, String order_status, String term, String calendar1,
			String calendar2, String loginName) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<BuyList_a> alist = new ArrayList<>();
		
		
		if(order_status.isEmpty()&& term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS LIKE '환불%' OR B.ORDER_STATUS LIKE '반품%'"
					; //쿼리문 변경
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(term==null && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS =?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(order_status.isEmpty() && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS LIKE '환불%' OR B.ORDER_STATUS LIKE '반품%'" + 
					"and order_date between (sysdate - ?) and (sysdate)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		else if(order_status.isEmpty() && term == null) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS LIKE '환불%' OR B.ORDER_STATUS LIKE '반품%'" +
					"and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		else if(calendar1.isEmpty() || calendar2.isEmpty()) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE artist_name = ? and order_status = ? and order_date between (sysdate - ?) and (sysdate)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				pstmt.setString(3, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
			
		}
		else if(order_status.isEmpty()) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS LIKE '환불%' OR B.ORDER_STATUS LIKE '반품%'" +
					"and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
			
			
		}else if(term == null) {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE P.ARTIST_NAME = ? AND B.ORDER_STATUS =? and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}else {
			String query = "SELECT *"
					+ "FROM BUY_LIST B "
					+ "JOIN PAINT P ON (P.PAINT_NO = B.PAINT_NO) "
					+ "JOIN MEMBER M ON (M.USER_NAME = P.ARTIST_NAME)"
					+ "WHERE artist_name = ? and order_status=? and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					BuyList_a ba = new BuyList_a(
							rset.getString("order_no"),
							rset.getString("user_id"),
							rset.getString("order_status"),
							rset.getInt("paint_no"),
							rset.getString("paint_price"),
							rset.getString("artist_name"),
							rset.getString("paint_name"));
							
							alist.add(ba);
							
							
				}
				System.out.println(alist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
		}
		
		System.out.println("alist"+alist);
		
	return alist;
	}

	public ArrayList<QnA_Q> qlistSearch(Connection conn, String order_status, String term, String calendar1,
			String calendar2, String loginName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		
		System.out.println("order_status :" + order_status);
		
		ArrayList<QnA_Q> qlist = new ArrayList<>();
		
		if(order_status.isEmpty() && term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.*  FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO) WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
					
				}
				System.out.println("전체 목록 :"+ qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A) WHERE PQ_YN = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(order_status.isEmpty() && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)"+ 
					"WHERE PQ_DATE BETWEEN (SYSDATE - ?) AND (SYSDATE)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		else if(order_status.isEmpty() && term == null) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)"+ 
					"WHERE PQ_DATE BETWEEN ? AND ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		else if(calendar1.isEmpty() || calendar2.isEmpty()) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)"
					+ "WHERE PQ_YN = ? AND PQ_DATE BETWEEN (SYSDATE - ?) AND (SYSDATE)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				pstmt.setString(3, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
			
		}
		else if(order_status.isEmpty()) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)"
					+ "WHERE PQ_DATE BETWEEN ? AND ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
			
			
		}else if(term == null) {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)"
					+ "WHERE PQ_YN = ? AND PQ_DATE BETWEEN ? AND ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}else {
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* "
					+ "FROM (SELECT * FROM PAINT_Q Q JOIN PAINT P ON(Q.PAINT_NO = P.PAINT_NO)"
					+ "WHERE ARTIST_NAME =? ORDER BY PQ_DATE DESC) A)"
					+ "WHERE PQ_YN = ? AND PQ_DATE BETWEEN ? AND ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, loginName);
				pstmt.setString(2, order_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					QnA_Q q = new QnA_Q(
							rset.getInt("pq_no"),
							rset.getString("pquestion"),
							rset.getString("pq_date"),
							rset.getInt("paint_no"),
							rset.getString("user_id"),
							rset.getString("pq_yn"),
							rset.getString("paint_name"));
							
							qlist.add(q);
							
							
				}
				System.out.println(qlist);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
		}
		
		System.out.println("qlist"+qlist);
		
	return qlist;
	}

	

}



















