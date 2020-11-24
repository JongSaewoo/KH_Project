package mypage_artist.management.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static common.JDBCTemplate.*;

import mypage_artist.management.model.vo.Mypage_artist;
import product.model.vo.product;

public class Mypage_ArtistDao {

	public int getListCount_PM(Connection conn, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(p.paint_no) from paint p\r\n" + 
				"join member m on (p.artist_name = m.user_name)\r\n" + 
				"where nickname = ?";
		
		int listCount_PM = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount_PM = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println("상품관리p"+listCount_PM);
		return listCount_PM;
	}
	
	public int getListCount_OM(Connection conn, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(p.paint_no) from paint p\r\n" + 
				"join buy_list bl on (p.paint_no = bl.paint_no)\r\n" + 
				"where pay_status is not null";
		
		int listCount_OM = 0;
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount_OM = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println("주문관리p"+listCount_OM);
		return listCount_OM;
	}

	public int getListCount_SM(Connection conn, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(p.paint_no) from paint p\r\n" + 
				"join buy_list bl on (p.paint_no = bl.paint_no)\r\n" + 
				"where pay_status is not null";
		
		int listCount_SM = 0;
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount_SM = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(listCount_SM);
		return listCount_SM;
	}


	public ArrayList<Mypage_artist> selectList_PM(Connection conn, int currentPage, int limit, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> PM_list = new ArrayList<>();
		
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* \r\n" + 
				"FROM (SELECT  P.PAINT_NO, AFILE, P.PAINT_NAME, ARTIST_NAME, p.PAINT_PRICE, FILELEVEL FROM PAINT P\r\n" + 
				"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
				"left JOIN BUY_LIST BL ON (P.PAINT_NO = BL.PAINT_NO)\r\n" + 
				"left JOIN MEMBER ON (BL.USER_ID = MEMBER.USER_ID)\r\n" + 
				"WHERE  p.ARTIST_NAME=? AND FILELEVEL=0 AND AUC_YN='N' order by 1)A)\r\n" + 
				"WHERE RNUM >=? AND RNUM <=?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getInt("paint_no"),
																			 rset.getString("afile"),
																			 rset.getString("paint_name"),
																			 rset.getString("artist_name"),
																			 rset.getInt("paint_price"));
				PM_list.add(myart);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(PM_list);
		return PM_list;
	}



	public ArrayList<Mypage_artist> selectList_SM(Connection conn, int currentPage, int limit, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> SM_list = new ArrayList<>();
		
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* \r\n" + 
				"FROM (SELECT ORDER_NO, AFILE, PAINT_NAME, ARTIST_NAME, ORDER_DATE, SHIP_DATE, ORDER_STATUS FROM BUY_LIST BL\r\n" + 
				"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
				"WHERE artist_name = ? and filelevel=0 order by 1)A)\r\n" + 
				"WHERE RNUM >=? AND RNUM <=?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getInt("order_no"),
																				 rset.getString("afile"),
																				 rset.getString("paint_name"),
																				 rset.getString("artist_name"),
																				 rset.getDate("order_date"),
																				 rset.getDate("ship_date"),
																				 rset.getString("order_status"));
				SM_list.add(myart);
				
			}
			System.out.println(SM_list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return SM_list;
	}



	public ArrayList<Mypage_artist> selectList_OM(Connection conn, int currentPage, int limit, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> OM_list = new ArrayList<>();
		
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* \r\n" + 
				"FROM (SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
				"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
				"WHERE  filelevel=0 and artist_name=? and order_status = '배송준비중' order by 1)A)\r\n" + 
				"WHERE RNUM >=? AND RNUM <=?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(
																				rset.getInt("order_no"),
																				 rset.getString("afile"),
																				 rset.getString("paint_name"),
																				 rset.getString("artist_name"),
																				 rset.getInt("paint_price"),
																				 rset.getString("order_status"));
				OM_list.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("dao" +OM_list);
		return OM_list;
	}



	public ArrayList<Mypage_artist> detail_order_view(Connection conn, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> DOV_OP = new ArrayList<>();
		
		String query = "SELECT ORDER_NO, AFILE, PAINT_NAME, ARTIST_NAME, PAINT_PRICE, ORDER_STATUS FROM BUY_LIST BL\r\n" + 
				"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
				"WHERE  USER_ID = ?";
	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getInt("order_no"),
																				 rset.getString("afile"),
																				 rset.getString("paint_name"),
																				 rset.getString("artist_name"),
																				 rset.getInt("paint_price"),
																				 rset.getString("order_status"));
				DOV_OP.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return DOV_OP;
	}



	public ArrayList<Mypage_artist> detail_deposit_view(Connection conn, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> DOV_D = new ArrayList<>();
		
		String query = "SELECT ORDER_NAME, PAY_TYPE, ORDER_PHONE FROM ORDER_TABLE\r\n" + 
				"WHERE USER_ID=?";
	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getString("order_name"),
																				 rset.getString("pay_type"),
																				 rset.getString("order_phone"));
				DOV_D.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return DOV_D;
	}



	public ArrayList<Mypage_artist> detail_shipping_view(Connection conn, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> DOV_SI = new ArrayList<>();
		
		String query = "SELECT REC_NAME, REC_LIST, REC_MESSAGE FROM ORDER_TABLE\r\n" + 
				"WHERE USER_ID=?";
	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getString("rec_name"),
																				 rset.getString("rec_list"),
																				 rset.getString("rec_message"));
				DOV_SI.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return DOV_SI;
	}

	public ArrayList<Mypage_artist> listSearch_SM(Connection conn, String shipping_status, String term, String calendar1,
			String calendar2, String bWriter) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Mypage_artist> search_list_SM = new ArrayList<>();
		Mypage_artist myart  =new Mypage_artist();
		
		if(shipping_status.isEmpty() && term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {  //검색 옵션의 값이 null 일때
			String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
					"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
					"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
					"WHERE artist_name = ? and filelevel = 0";	
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				System.out.println(search_list_SM);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {		//배송처리상태의 값만 있을 때
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_status = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, shipping_status);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(shipping_status.isEmpty() && (calendar1.isEmpty() || calendar2.isEmpty())) {		//기간만 가지고 검색 할 때
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_date between (sysdate - ?) and (sysdate)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				
				System.out.println(search_list_SM);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		else if(shipping_status.isEmpty() && term.isEmpty()) {		//달력 날짜만 가지고 검색할 때
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				
				System.out.println(search_list_SM);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		else if(calendar1.isEmpty() || calendar2.isEmpty()) {		// 배송처리상태와 기간을 가지고 검색 할 때
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_status = ? and order_date between (sysdate - ?) and (sysdate)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, shipping_status);
				pstmt.setString(3, term);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				
				System.out.println(search_list_SM);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
			
		}
		else if(shipping_status.isEmpty()) {		//  기간과 달력을 입력했을 때 (달력 기준으로 검색)
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, calendar1);
				pstmt.setString(3, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				
				System.out.println(search_list_SM);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
			
			
		}else if(term.isEmpty()) {			// 배송 처리 상태와 달력의 값을 가지고 검색
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_status = ? and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, shipping_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				System.out.println(search_list_SM);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}else {				// 모든 값을 입력했을 때 (기간과 달력이 동시입력이면 달력을 기준으로)
			String query = "select order_no, afile, paint_name, artist_name, order_date, ship_date from buy_list bl\r\n" + 
					"join paint p on (bl.paint_no = p.paint_no)\r\n" + 
					"join paint_photo pp on (p.paint_no = pp.paint_no)\r\n" + 
					"where artist_name = ? and filelevel=0 and order_status = ? and order_date between ? and ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, shipping_status);
				pstmt.setString(3, calendar1);
				pstmt.setString(4, calendar2);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("order_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getDate("order_date"),
																					   rset.getDate("ship_date"));
					search_list_SM.add(myart);
				}
				System.out.println(search_list_SM);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}	
		}
		
		System.out.println("dkdk"+search_list_SM);
		
	return search_list_SM;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Mypage_artist> listSearch(Connection conn, String order_status, String term, String calendar1,
			String calendar2, String bWriter) {
		
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			ArrayList<Mypage_artist> search_list = new ArrayList<>();
			Mypage_artist myart  =new Mypage_artist();
			
			if(order_status.isEmpty() && term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
					}
					System.out.println(search_list);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}
			}
			else if(term == null && (calendar1.isEmpty() || calendar2.isEmpty())) {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? AND ORDER_STATUS=? and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, order_status);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
					}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}
			}
			else if(order_status.isEmpty() && (calendar1.isEmpty() || calendar2.isEmpty())) {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and order_date between (sysdate - ?) and (sysdate) and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, term);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
					}
					
					System.out.println(search_list);
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}
				
			}
			else if(order_status.isEmpty() && term.isEmpty()) {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and order_date between ? and ? and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, calendar1);
					pstmt.setString(3, calendar2);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
					}
					
					System.out.println(search_list);
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}
				
			}
			else if(calendar1.isEmpty() || calendar2.isEmpty()) {
				String query ="SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and order_status = ? and order_date between (sysdate - ?) and (sysdate) and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, order_status);
					pstmt.setString(3, term);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
					}
					
					System.out.println(search_list);
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}	
				
			}
			else if(order_status.isEmpty()) {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and order_date between ? and ? and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, calendar1);
					pstmt.setString(3, calendar2);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("ORDER_NO"),
																						   rset.getString("AFILE"),
																						   rset.getString("PAINT_NAME"),
																						   rset.getString("ARTIST_NAME"),
																						   rset.getInt("PAINT_PRICE"),
																						   rset.getString("ORDER_STATUS"));
						search_list.add(myart);
					}
					
					System.out.println(search_list);
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}	
				
				
			}else if(term.isEmpty()) {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and order_status=? and order_date between ? and ? and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, order_status);
					pstmt.setString(3, calendar1);
					pstmt.setString(4, calendar2);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
					}
					System.out.println(search_list);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}
			}else {
				String query = "SELECT order_no, afile, paint_name, artist_name, paint_price, order_status FROM BUY_LIST BL\r\n" + 
						"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
						"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
						"WHERE artist_name = ? and order_status=? and order_date between ? and ? and filelevel = 0";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bWriter);
					pstmt.setString(2, order_status);
					pstmt.setString(3, calendar1);
					pstmt.setString(4, calendar2);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						myart = new Mypage_artist(rset.getInt("order_no"),
																						   rset.getString("afile"),
																						   rset.getString("paint_name"),
																						   rset.getString("artist_name"),
																						   rset.getInt("paint_price"),
																						   rset.getString("order_status"));
						search_list.add(myart);
						
					}
					System.out.println(search_list);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}	
			}
			
			System.out.println("dkdk"+search_list);
			
		return search_list;
	}
	
	public ArrayList<Mypage_artist> listSearch_PM(Connection conn, String category, String bWriter) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Mypage_artist> search_list_PM = new ArrayList<>();
		Mypage_artist myart  =new Mypage_artist();
		
		if(category.isEmpty() ) {
			String query = "SELECT PAINT.PAINT_NO, AFILE, PAINT_NAME, ARTIST_NAME, PAINT_PRICE FROM PAINT\r\n" + 
					"JOIN PAINT_PHOTO PP ON (PAINT.PAINT_NO = PP.PAINT_NO)\r\n" + 
					"WHERE FILELEVEL=0 AND ARTIST_NAME = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("paint_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getInt("paint_price"));
					search_list_PM.add(myart);
				}
				System.out.println(search_list_PM);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else{
			String query = "SELECT PAINT.PAINT_NO, AFILE, PAINT_NAME, ARTIST_NAME, PAINT_PRICE FROM PAINT\r\n" + 
					"JOIN PAINT_PHOTO PP ON (PAINT.PAINT_NO = PP.PAINT_NO)\r\n" + 
					"WHERE FILELEVEL=0 AND ARTIST_NAME = ? AND CATEGORY = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bWriter);
				pstmt.setString(2, category);
					
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					myart = new Mypage_artist(rset.getInt("paint_no"),
																					   rset.getString("afile"),
																					   rset.getString("paint_name"),
																					   rset.getString("artist_name"),
																					   rset.getInt("paint_price"));
					search_list_PM.add(myart);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		
		System.out.println("dkdk"+search_list_PM);
		
		
		return search_list_PM;
	}



	public ArrayList<Mypage_artist> selectList_DOV_P(Connection conn, String bWriter, int order_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> DOV_plist = new ArrayList<>();
		
		String query = "SELECT ORDER_NO, AFILE, PAINT_NAME, ARTIST_NAME, PAINT_PRICE, ORDER_STATUS FROM BUY_LIST BL \r\n" + 
				"JOIN PAINT P ON (BL.PAINT_NO = P.PAINT_NO)\r\n" + 
				"JOIN PAINT_PHOTO PP ON (P.PAINT_NO = PP.PAINT_NO)\r\n" + 
				"where artist_name=? and filelevel=0 and order_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, order_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getInt("order_no"),
																			 rset.getString("afile"),
																			 rset.getString("paint_name"),
																			 rset.getString("artist_name"),
																			 rset.getInt("paint_price"),
																			 rset.getString("order_status"));
				DOV_plist.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("plist="+DOV_plist);
		return DOV_plist;
	}



	public ArrayList<Mypage_artist> selectList_DOV_D(Connection conn, String bWriter, int order_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> DOV_dlist = new ArrayList<>();
		
		String query = "SELECT ORDER_NAME, PAY_TYPE, ORDER_PHONE FROM ORDER_TABLE\r\n" + 
				"join paint on (order_table.paint_no = paint.paint_no)\r\n" + 
				"WHERE artist_name= ? and order_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, order_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(rset.getString("order_name"),
																			 rset.getString("pay_type"),
																			 rset.getString("order_phone"));
				DOV_dlist.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("dlist="+DOV_dlist);
		return DOV_dlist;
	}



	public ArrayList<Mypage_artist> selectList_DOV_S(Connection conn, String bWriter, int order_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Mypage_artist> DOV_slist = new ArrayList<>();
		
		String query = "SELECT REC_NAME, REC_ADD, REC_MESSAGE FROM RECEIPT_TABLE\r\n" + 
				"join paint on (receipt_table.paint_no = paint.paint_no)\r\n" + 
				"WHERE artist_name= ? and order_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, order_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Mypage_artist myart = new Mypage_artist(0,
																			rset.getString("rec_name"),
																			 rset.getString("REC_ADD"),
																			 rset.getString("rec_message"));
				DOV_slist.add(myart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("slist=" + DOV_slist);
		return DOV_slist;
	}

	public int deleteProduct(Connection conn, String bWriter, int paint_no) {
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		String query = "UPDATE PAINT SET AUC_YN = 'Y'\r\n" + 
							   "WHERE ARTIST_NAME = ? AND PAINT_NO = ?";			
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, paint_no);
				
			deleteResult = pstmt.executeUpdate();
			System.out.println("dao");
				if(deleteResult>0) {
				System.out.println("delete가 성공적으로 이루어졌습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return deleteResult;
	}

	public int deleteBasket(Connection conn, String bWriter, int paint_no) {
		PreparedStatement pstmt = null;
		int deleteBResult = 0;
		
		String query = "DELETE FROM BASKET WHERE PAINT_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, paint_no);
			
			deleteBResult = pstmt.executeUpdate();
			if(deleteBResult > 0) {
				System.out.println("delete 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleteBResult;
	}

	public int updateOrder_status(Connection conn, int order_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "UPDATE BUY_LIST SET ORDER_STATUS ='배송완료' WHERE ORDER_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, order_no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("dao에서의 결과"+result);
		return result;
	}



	



	



}
























