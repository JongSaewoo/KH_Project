package mypage_user.mainOrderRefundWish.model.dao;
import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mypage_artist.management.model.vo.Mypage_artist;
import mypage_user.mainOrderRefundWish.model.vo.Morw;
import product.model.vo.Attachment;


public class MorwDao {

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) FROM MYPAGE_USER WHERE BUY_YN=1";
		int listCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}
	
	public ArrayList<Morw> selectList(Connection conn,String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Morw> list = new ArrayList<>();
		
		String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS \r\n" + 
				"FROM ORDER_TABLE O\r\n" + 
				"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) \r\n" + 
				"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO) \r\n" + 
				"WHERE O.USER_ID=? AND PP.Filelevel='0' ORDER BY O.ORDER_NO DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);

			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Morw m = new Morw(rset.getString("order_no"),
									rset.getInt("paint_no"),
									rset.getString("afile"),
									rset.getString("paint_name"),
									rset.getString("ARTIST_NAME"),
									rset.getInt("paint_price"),
									rset.getDate("order_date"),
									rset.getString("order_status"));
				list.add(m);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


//	public ArrayList<Morw> selectList(Connection conn, String userId, String searchStatus) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		ArrayList<Morw> list = new ArrayList<>();
//
//		String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS " + 
//				"FROM ORDER_TABLE O " + 
//				"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO) " + 
//				"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) " + 
//				"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO) " + 
//				"WHERE O.USER_ID=? AND PP.FILELEVEL = 0 ORDER BY O.ORDER_NO DESC";
//
//		
//		String whereSql = "";
//		if(whereSql.equals(searchStatus)){
//		whereSql += " AND ORDER_STATUS='"+searchStatus+"' ";
//		
//		}
//		
//			// 조건없이 순수 조회일 떄
//			String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS " + 
//					"FROM ORDER_TABLE O " + 
//					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO) " + 
//					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) " + 
//					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO) " + 
//					"WHERE O.USER_ID=?"+whereSql+" ORDER BY O.ORDER_NO DESC";
//			
//			try {
//				pstmt = conn.prepareStatement(query);
//				pstmt.setString(1, userId);
//				rset = pstmt.executeQuery();
//				
//				while(rset.next()) {
//					Morw m = new Morw(rset.getString("order_no"),
//							rset.getInt("paint_no"),
//							rset.getString("afile"),
//							rset.getString("paint_name"),
//							rset.getString("ARTIST_NAME"),
//							rset.getInt("paint_price"),
//							rset.getDate("order_date"),
//							rset.getString("order_status"));
//					list.add(m);
//				}
//				
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				close(rset);
//				close(pstmt);
//			}
//			
//			return list;
//			
//	}


	//환불
	public ArrayList<Morw> refundList(Connection conn, String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Morw> list = new ArrayList<>();
		
		String query = "SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS " + 
				"FROM BUY_LIST B " + 
				"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO) " + 
				"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO) " + 
				"WHERE B.ORDER_STATUS LIKE '환불%' AND PP.Filelevel='0'AND B.USER_ID=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Morw m = new Morw(rset.getString("ORDER_NO"),
								  rset.getInt("PAINT_NO"),
								  rset.getString("AFILE"),
								  rset.getString("PAINT_NAME"),
								  rset.getString("ARTIST_NAME"),
								  rset.getInt("PAINT_PRICE"),
								  rset.getString("ORDER_STATUS"));
				list.add(m);
				System.out.println("dao단 refund list 출력"+list);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}

	//장바구니
	public ArrayList<Morw> wishlistList(Connection conn, String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Morw> list = new ArrayList<>();
		
		String query = "SELECT B.BASKET_NO,P.PAINT_NO,P.PAINT_NAME,P.ARTIST_NAME,P.PAINT_PRICE,BUY_YN \r\n" + 
				"FROM PAINT P \r\n" + 
				"JOIN BASKET B ON (P.PAINT_NO=B.PAINT_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (P.PAINT_NO=PP.PAINT_NO)\r\n" + 
				"WHERE USER_ID=? AND PP.Filelevel='0' AND BUY_YN='N'";

		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Morw m = new Morw(rset.getInt("BASKET_NO"),
								  rset.getInt("PAINT_NO"),
								  rset.getString("PAINT_NAME"),
								  rset.getString("ARTIST_NAME"),
								  rset.getInt("PAINT_PRICE"),
								  rset.getString("BUY_YN"));
				
				System.out.println("아티스트네임"+rset.getString("ARTIST_NAME"));
				
				list.add(m);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		System.out.println("wishlist dao단 출력"+list);
		
		return list;
	}
	
	public void wishlistDelete(Connection conn, String whereSQL) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "DELETE FROM BASKET "+whereSQL;
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
	}

	public ArrayList<Attachment> selectPlist(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Attachment> plist = new ArrayList<>();
		
		String query = "SELECT * FROM PAINT_PHOTO WHERE FILELEVEL=0";
		
		
		try {
			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Attachment p = new Attachment(rset.getInt("PAINT_NO"),
											  rset.getString("AFILE"),
											  rset.getString("FILEPATH"),
											  rset.getInt("FILELEVEL"));
				plist.add(p);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return plist;
	}

	public void updateStatus(Connection conn, String param) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BUY_LIST SET ORDER_STATUS = '수령완료' WHERE ORDER_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, param);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
	
	}

	public int insertMessage(Connection conn, Morw morw) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO MESSAGE(ORDER_NO,PAINT_NO,USER_ID,MESSAGE,PAINT_NAME,ARTIST_NAME) VALUES(?,?,?,?,?,?)";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,morw.getOrderNo());
			pstmt.setInt(2,morw.getPaintNo());
			pstmt.setString(3,morw.getUserId());
			pstmt.setString(4,morw.getMessage());
			pstmt.setString(5, morw.getPaintName());
			pstmt.setString(6, morw.getArtistName());
			
			result = pstmt.executeUpdate();
			
			System.out.println("Dao단 result"+result);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	

	public int insertRefund(Connection conn, Morw morw) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO REFUND(ORDER_NO,PAINT_NO,USER_ID,REFUND_RSN,ACCOUNT_NAME,ACCOUNT_NO,BANK) VALUES(?,?,?,?,?,?,?)";
		
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1,morw.getOrderNo());
			pstmt.setInt(2, morw.getPaintNo());
			pstmt.setString(3, morw.getUserId());
			pstmt.setString(4, morw.getRefundReason());
			pstmt.setString(5, morw.getAccountName());
			pstmt.setString(6, morw.getAccountNo());
			pstmt.setString(7, morw.getBank());
			
			result = pstmt.executeUpdate();
			
			System.out.println("Dao단 환불 result"+result);
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return result;
	
	}

	public void updateStatus2(Connection conn, String orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BUY_LIST SET ORDER_STATUS = '환불신청' WHERE ORDER_NO =? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
	}

	public int countSelectList(Connection conn, String userId) {
	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT count(O.ORDER_NO)\r\n" + 
				"FROM ORDER_TABLE O \r\n" + 
				"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) \r\n" + 
				"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO) \r\n" + 
				"WHERE O.USER_ID=?  AND pp.filelevel='0'";
		
		int listCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println("페이지네이션 가져와p"+listCount);
		return listCount;

	}

	public int countSelectList2(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT count(B.ORDER_NO)\r\n" + 
				"FROM BUY_LIST B \r\n" + 
				"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO)\r\n" + 
				"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO)\r\n" + 
				"WHERE B.ORDER_STATUS LIKE '환불%' AND pp.filelevel='0' AND B.USER_ID=?";
		
		int listCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println("페이지네이션 가져와p"+listCount);
		return listCount;
	}

	public ArrayList<Morw> listSearch(Connection conn, String order_status, String term, String calendar1,
			String calendar2, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Morw> search_list = new ArrayList<>();
		Morw m  =new Morw();
		
		//세개 모두 null 일때 >> 아무것도 옵션을 주지 않았을때
		if(order_status.isEmpty() && term == null && (calendar1.isEmpty()||calendar2.isEmpty())) {
			String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS\r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO)\r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=?  AND PP.Filelevel='0'";
					
			try {
				pstmt =conn.prepareStatement(query);
				pstmt.setString(1, userId);

				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				System.out.println(search_list);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		// status만 눌렀을 때 == term, cal은 null	== done
		}else if(term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS\r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO)\r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? AND B.ORDER_STATUS=? AND PP.Filelevel='0'";
			
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,userId);
				pstmt.setString(2, order_status);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				System.out.println("리펀 텀만 눌렀을때" +search_list);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		
			//term만 눌렀을때 == orderstatus,cal 은 null
		}else if(order_status.isEmpty() && (calendar1.isEmpty() || calendar2.isEmpty())) {
			String query = "SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS \r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO)\r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? AND ORDER_DATE BETWEEN (SYSDATE - ?) AND SYSDATE AND PP.Filelevel='0'";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,userId);
				pstmt.setString(2, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				System.out.println(search_list);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
		}
		//cal만 클릭했을 때 == status, term == null
		else if(order_status.isEmpty() && term==null) {
			String query = "SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS\r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? And ORDER_DATE BETWEEN ? And ? AND PP.Filelevel='0'";
			
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,userId);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		//status, term 두개 클릭 했을 때 == cal == null
		}else if(calendar1.isEmpty() || calendar2.isEmpty()) {
			String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS \r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO)\r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? AND ORDER_STATUS=? AND ORDER_DATE BETWEEN (SYSDATE - ?) AND SYSDATE AND PP.Filelevel='0'";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				pstmt.setString(2, order_status);
				pstmt.setString(3, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				
				System.out.println(search_list);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
				
			}
		
		//term, cal 두개 클릭했을 때 == status== null
		}else if(order_status.isEmpty()) {
			String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS\r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? and ORDER_DATE BETWEEN ? AND ? AND PP.Filelevel='0'";
					
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				
				System.out.println(search_list);
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		//staus, cal만 클릭할 떼 == term == null	
		}else if(term==null) {
			String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS\r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? AND ORDER_STATUS=? AND ORDER_DATE BETWEEN ? AND ? AND PP.FILELEVEL='0'";
			
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				pstmt.setString(2, order_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);

				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				
				System.out.println(search_list);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		
			
			//다 선택했을 때 ==done
		}else {
			String query ="SELECT O.ORDER_NO, P.PAINT_NO,PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_DATE,B.ORDER_STATUS\r\n" + 
					"FROM ORDER_TABLE O\r\n" + 
					"JOIN BUY_LIST B ON (O.ORDER_NO=B.ORDER_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (O.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON (O.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE O.USER_ID=? AND ORDER_STATUS=? AND ORDER_DATE BETWEEN ? AND ? AND PP.FILELEVEL='0'";
			
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				pstmt.setString(2, order_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);

				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					m = new Morw(rset.getString("order_no"),
							rset.getInt("paint_no"),
							rset.getString("afile"),
							rset.getString("paint_name"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("paint_price"),
							rset.getDate("order_date"),
							rset.getString("order_status"));
						search_list.add(m);
				}
				
				System.out.println(search_list);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		System.out.println("써치결과"+search_list);
		
		return search_list;
	}

	public ArrayList<Morw> searchRefundList(Connection conn, String order_status, String term, String calendar1,
			String calendar2, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Morw> refund_search_list = new ArrayList<>();
		
		
		//세가지 모두 null
		if(order_status.isEmpty() && term == null && (calendar1.isEmpty()||calendar2.isEmpty())) {
			
			String query = "SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS\r\n" + 
					"FROM BUY_LIST B \r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO) \r\n" + 
					"WHERE B.ORDER_STATUS LIKE '환불%' AND PP.Filelevel='0'AND B.USER_ID='user1'";
			
			
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, userId);
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
		//order status 만 눌렀을 때
		}else if(term == null && (calendar1.isEmpty() || calendar2.isEmpty())){
			String query = "SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS  \r\n" + 
					"FROM BUY_LIST B \r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE B.ORDER_STATUS =? AND PP.Filelevel='0'AND B.USER_ID=?";
			
			
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, order_status);
				pstmt.setString(2, userId);
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
		
			
		//term만 눌렀을 때
		}else if(order_status.isEmpty() && (calendar1.isEmpty() || calendar2.isEmpty())) {
			
			String query ="SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS \r\n" + 
					"FROM BUY_LIST B \r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO)  \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO) \r\n" + 
					"WHERE B.ORDER_STATUS LIKE '환불%' AND ORDER_DATE BETWEEN (SYSDATE - ?) AND SYSDATE  AND PP.Filelevel='0' AND B.USER_ID=?";
			
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, term);
				pstmt.setString(2, userId);
				rset=pstmt.executeQuery();

				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}			
		}
		//cal만 눌렀을 때	
		else if(order_status.isEmpty() && term==null) {
			String query ="SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS \r\n" + 
					"FROM BUY_LIST B \r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO)  \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO) \r\n" + 
					"WHERE B.ORDER_STATUS LIKE '환불%' And ORDER_DATE BETWEEN ? And ? AND PP.Filelevel='0' AND B.USER_ID=?";
		
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, calendar1);
				pstmt.setString(2, calendar2);
				pstmt.setString(3, userId);
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
		//status, term만 클릭시
		}else if(calendar1.isEmpty() || calendar2.isEmpty()) {
			String query = "SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS \r\n" + 
					"FROM BUY_LIST B\r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE B.ORDER_STATUS =? AND ORDER_DATE BETWEEN (SYSDATE - ?) AND SYSDATE AND PP.Filelevel='0'AND B.USER_ID=?";
			
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, order_status);
				pstmt.setString(2, term);
				pstmt.setString(3, userId);
				
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
			//term, cal만 클릭시
		}else if(order_status.isEmpty()) {
			String query ="SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS \r\n" + 
					"FROM BUY_LIST B \r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO) \r\n" + 
					"WHERE B.ORDER_STATUS LIKE '환불%' And ORDER_DATE BETWEEN ? And ? AND B.USER_ID=?";
		
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, calendar1);
				pstmt.setString(2, calendar2);
				pstmt.setString(3, userId);
				
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
		
			//status, cal 클릭시 
		}else if(term==null) {
			String query = "SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS \r\n" + 
					"FROM BUY_LIST B \r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO)  \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO) \r\n" + 
					"WHERE B.ORDER_STATUS LIKE '환불%' And ORDER_DATE BETWEEN ? And ? AND B.USER_ID=?";
			
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, calendar1);
				pstmt.setString(2, calendar2);
				pstmt.setString(3, userId);
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
		//모두 클릭시	
		}else {
			String query ="SELECT B.ORDER_NO,P.PAINT_NO, PP.AFILE,PAINT_NAME,P.ARTIST_NAME,PAINT_PRICE,B.ORDER_STATUS \r\n" + 
					"FROM BUY_LIST B\r\n" + 
					"JOIN PAINT_PHOTO PP ON (B.PAINT_NO=PP.PAINT_NO) \r\n" + 
					"JOIN PAINT P ON(B.PAINT_NO=P.PAINT_NO)\r\n" + 
					"WHERE B.ORDER_STATUS =? AND ORDER_DATE  BETWEEN ? And ? AND PP.Filelevel='0'AND B.USER_ID=?";
			
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, order_status);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				pstmt.setString(4, userId);
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					Morw m = new Morw(rset.getString("ORDER_NO"),
							rset.getInt("PAINT_NO"),
							rset.getString("AFILE"),
							rset.getString("PAINT_NAME"),
							rset.getString("ARTIST_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getString("ORDER_STATUS"));
					refund_search_list.add(m);
					System.out.println("dao단 refund list 출력"+refund_search_list);
				}		
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!리펀써치결과"+refund_search_list);
		
		return refund_search_list;
		
		
	}

	
}
