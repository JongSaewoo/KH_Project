package product.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import artistapply.model.vo.Apply;
import product.model.vo.Attachment;
import product.model.vo.MasterpieceCount;
import product.model.vo.Paint_QnA;
import product.model.vo.masterpiece;
import product.model.vo.product;

public class ProductDao {

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;

		String query = "SELECT COUNT(*) FROM PAINT";
		int listCount = 0;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				
				listCount = rset.getInt(1);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}

		return listCount;
	}

	public ArrayList<product> selectList(Connection conn, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<product> list = new ArrayList<>();

		String query = "SELECT * FROM PAINT WHERE PAINT_NO BETWEEN ? AND ? ORDER BY 1";

		// 쿼리문 실행시 조건절에 넣을 변수를 (ROWNUM에 대한 조건 시 필요) 연산 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				product p = new product(rset.getInt("PAINT_NO"), 
						rset.getString("PAINT_NAME"),
				rset.getInt("PAINT_PRICE"),
				rset.getInt("SIZE_NO"),
				rset.getString("ARTIST_NAME"),
				rset.getString("AUC_YN"));
				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao:게시글 출력 확인_" + list);

		return list;
	}

	public int insertProduct(Connection conn, product p) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "INSERT INTO PAINT VALUES(SEQ_PID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 'N')";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, p.getPaint_name());
			pstmt.setInt(2, p.getPatint_price());
			pstmt.setString(3, p.getCategory());
			pstmt.setString(4, p.getArtist_name());
			pstmt.setString(5, p.getPaint_int());
			pstmt.setString(6, p.getPaint_mdate());
			pstmt.setInt(7, p.getSize_no());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "INSERT INTO PAINT_PHOTO VALUES(SEQ_PNO.NEXTVAL,SEQ_PID.CURRVAL,?,?,?)";
		try {
			for (int i = 0; i < fileList.size(); i++) {
				Attachment at = fileList.get(i);
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, at.getSavefileName());
				pstmt.setString(2, at.getFilePath());
				pstmt.setInt(3, at.getFileLevel());
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public int insertmasterpiece(Connection conn, product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO MASTERPIECE VALUES(SEQ_PID.CURRVAL, ?,0)";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, p.getpId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertTag(Connection conn, String[] irr) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO TAG VALUES(?,SEQ_PID.CURRVAL)";
		try {
			for (int i = 0; i < irr.length; i++) {
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, irr[i]);
				result += pstmt.executeUpdate();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Attachment> selectphoto(Connection conn, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Attachment> list = new ArrayList<>();

		String query = "SELECT * FROM PAINT_PHOTO WHERE FILELEVEL=0 AND PAINT_NO BETWEEN ? AND ? ORDER BY 1";

		// 쿼리문 실행시 조건절에 넣을 변수를 (ROWNUM에 대한 조건 시 필요) 연산 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

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

	public ArrayList<product> selectsearch(Connection conn, int currentPage, int limit, product po) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<product> list = new ArrayList<>();

		String query = "SELECT * FROM PAINT WHERE PAINT_NO BETWEEN ? AND ?";

		// 쿼리문 실행시 조건절에 넣을 변수를 (ROWNUM에 대한 조건 시 필요) 연산 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				product p = new product(rset.getInt("PAINT_NO"), 
						rset.getString("PAINT_NAME"),
				rset.getInt("PAINT_PRICE"),
				rset.getInt("SIZE_NO"),
				rset.getString("ARTIST_NAME"));
				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao:게시글 출력 확인_" + list);

		return list;
	}

	public ArrayList<product> selectsearch(Connection conn, product po) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<product> list = new ArrayList<>();
		product p = new product();
		list.add(p);
		
		//작가이름 null일경우
			String query ="SELECT * FROM PAINT WHERE CATEGORY=? AND PAINT_PRICE BETWEEN 0 AND ?";// AND PAINT_NO BETWEEN ? AND ?
			
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, po.getCategory());
				pstmt.setInt(2, po.getPatint_price());
				
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					 p = new product(rset.getInt("PAINT_NO"), 
											rset.getString("PAINT_NAME"),
											rset.getInt("PAINT_PRICE"),
											rset.getInt("SIZE_NO"),
											rset.getString("ARTIST_NAME"));
											
					list.add(p);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
				
			}
			System.out.println("ProductDao:게시글 출력 확인_" + list);
		
		
		return list;
	}

	public ArrayList<product> selectrealsearch(Connection conn,String[] tagname) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM TAG WHERE TAG_NAME=?";
		ArrayList<product> taglist = new ArrayList<>();
		try {
			for (int i = 0; i < tagname.length; i++) {
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, tagname[i]);
				
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					product p = new product(rset.getInt("PAINT_NO"), 
											rset.getString("TAG_NAME"));
							taglist.add(p);
				}
		}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		System.out.println(taglist);
		return taglist;
		
	}


	public ArrayList<Attachment> selectAllalist(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Attachment> list = new ArrayList<Attachment>();
		String query = "SELECT * FROM PAINT_PHOTO WHERE FILELEVEL=0";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Attachment a = new Attachment(rset.getInt("PAINT_NO"),
										rset.getString("AFILE"),
										rset.getString("FILEPATH"),	
										rset.getInt("FILELEVEL"));

				list.add(a);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

	public product selectsearch(Connection conn, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		product p = new product();
		String query = "SELECT * FROM PAINT WHERE PAINT_NO=?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, paint_no);
			
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				p = new product(rset.getInt("PAINT_NO"),
										rset.getString("PAINT_NAME"),
										rset.getInt("PAINT_PRICE"),	
										rset.getString("CATEGORY"),
										rset.getString("ARTIST_NAME"),
										rset.getString("PAINT_INT"),
										rset.getString("PAINT_MDATE"),
										rset.getInt("SIZE_NO"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}

	public ArrayList<Attachment> selectAttachment(Connection conn, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Attachment> alist = new ArrayList<Attachment>();
		
		String query = "SELECT * FROM PAINT_PHOTO WHERE PAINT_NO=?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, paint_no);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				 Attachment a = new Attachment(rset.getInt("AFILE_NO"),
										rset.getInt("PAINT_NO"),
										rset.getString("AFILE"),	
										rset.getString("FILEPATH"),
										rset.getInt("FILELEVEL"));
				 alist.add(a);
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return alist;
	}

	public ArrayList<product> selectsize(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<product> plist = new ArrayList<>();
		
		String query = "SELECT * FROM PAINT_SIZE";
		
		try {
			pstmt= conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				product p = new product(rset.getInt("SIZE_NO"),
											rset.getInt("WIDTH"),
											rset.getInt("HEIGHT"));

				plist.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("dao"+plist);
		
	
		
		return plist;
	}

	public ArrayList<product> payList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<product> pay_List = new ArrayList<product>();
		
		String query = "SELECT PAINT_NAME, PAINT_PRICE";
		
		try {
			pstmt= conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				product p = new product(rset.getString("paint_name"),
													  rset.getInt("patint_price"));

				pay_List.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
	
		
		return pay_List;
	}

	public Apply selectApply(Connection conn, product plist) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Apply apply = new Apply();
		
		String query = "SELECT * FROM SELLER WHERE USER_ID = (SELECT USER_ID FROM MEMBER WHERE USER_NAME=?)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1,plist.getArtist_name());
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				apply = new Apply(rset.getString("USER_ID"),
									rset.getString("ARTIST_INT"),
									rset.getString("ARTIST_PRO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return apply;

	}

	public int insertmasterpiece(Connection conn,String bWriter, int paint_no) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "INSERT INTO MASTERPIECE VALUES(?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, paint_no);
			pstmt.setString(2, bWriter);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		System.out.println(result);
		
		return result;
	}

	public int deletemasterpiece(Connection conn, String bWriter, int paint_no) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "DELETE FROM MASTERPIECE WHERE USER_ID=? AND PAINT_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bWriter);
			pstmt.setInt(2, paint_no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		System.out.println(result);
		
		return result;
	}

	public int insertBasket(Connection conn, product po, String bWriter) {
		PreparedStatement pstmt = null;

		int result = 0;
		
		String query = "INSERT INTO BASKET VALUES(SEQ_BSK.NEXTVAL,?,?,?,?,'N')";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,po.getPaint_name());
			pstmt.setInt(2, po.getPatint_price());
			pstmt.setString(3, bWriter);
			pstmt.setInt(4, po.getPaint_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public masterpiece selectMasterpiece(Connection conn, String bWriter, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		masterpiece ma = new masterpiece();
		
		String query ="SELECT * FROM MASTERPIECE WHERE USER_ID=? AND PAINT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,bWriter);
			pstmt.setInt(2, paint_no);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				ma = new masterpiece(rset.getInt("PAINT_NO"),
						rset.getString("USER_ID"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(ma);
		return ma;
	}

	public int selectMasterpiece2(Connection conn, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count =0;
		String query="SELECT COUNT(*)좋아요수 FROM MASTERPIECE WHERE PAINT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, paint_no);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt("좋아요수");
									}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("좋아요 갯수"+count);
		return count;
	}

	public MasterpieceCount countMasterpiece(Connection conn, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MasterpieceCount mc = new MasterpieceCount();
		String query = "SELECT COUNT(*)좋아요수 FROM MASTERPIECE WHERE PAINT_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, paint_no);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				mc = new MasterpieceCount(rset.getInt("좋아요수"));
									}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return mc;
	}

	public ArrayList<Paint_QnA> selectQ(Connection conn, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Paint_QnA qa = new Paint_QnA();
		ArrayList<Paint_QnA> pqa = new ArrayList<Paint_QnA>();
		String query = "SELECT * FROM PAINT_Q WHERE PAINT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, paint_no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				qa = new Paint_QnA(rset.getInt("PQ_NO"),
									rset.getString("PQUESTION"),
									rset.getString("PQ_DATE"),
									rset.getInt("PAINT_NO"),
									rset.getString("USER_ID"),
									rset.getString("PQ_YN"));
				pqa.add(qa);
							}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		return pqa;
	}

	public ArrayList<Paint_QnA> selectA(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Paint_QnA qa = new Paint_QnA();
		ArrayList<Paint_QnA> pqa = new ArrayList<Paint_QnA>();
		
		
		String query = "SELECT * FROM PAINT_A";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				qa = new Paint_QnA( 
									rset.getInt("PQ_NO"),
									rset.getString("PANSWER"),
									rset.getString("PA_DATE"));
				pqa.add(qa);
									}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return pqa;
	}

	public int insertQuestion(Connection conn, String writer, int paint_no, String content) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		String query = "INSERT INTO PAINT_Q VALUES(SEQ_QNA.NEXTVAL, ?, SYSDATE, ?, ?, 'N')";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, content);
			pstmt.setInt(2, paint_no);
			pstmt.setString(3, writer);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	public int insertAnwser(Connection conn, int qna_no, String content) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		String query = "INSERT INTO PAINT_A VALUES(?, ?,SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, qna_no);
			pstmt.setString(2, content);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(result);
		return result;
	}

	public int updateQ(Connection conn, int qna_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		
		String query = "UPDATE PAINT_Q SET PQ_YN='Y' WHERE  PQ_NO=? AND PQ_YN='N'";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, qna_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	public int insertordertable(Connection conn, int paint_no, String orderid, String ordername, String orderphone,
			String orderEmail, String orderadress, String orderrule) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		
		String query = "INSERT INTO ORDER_TABLE VALUES(SEQ_ORD.CURRVAL,?, ?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, paint_no);
			pstmt.setString(2,orderid);
			pstmt.setString(3,ordername);
			pstmt.setString(4,orderphone);
			pstmt.setString(5,orderEmail);
			pstmt.setString(6,orderadress);
			pstmt.setString(7,orderrule);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(result);
		return result;
	}

	public int insertreceivetable(Connection conn, String orderid, int paint_no, String receivename,
			String receivephone, String receiveaddress, String receivecontent) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		
		String query = "INSERT INTO RECEIPT_TABLE VALUES(SEQ_ORD.CURRVAL,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, paint_no);
			pstmt.setString(2, orderid);
			pstmt.setString(3, receivename);
			pstmt.setString(4, receivephone);
			pstmt.setString(5, receiveaddress);
			pstmt.setString(6, receivecontent);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(result);
		return result;
	}

	public int insertbuylist(Connection conn, int paint_no, String orderid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		
		String query = "INSERT INTO BUY_LIST VALUES(SEQ_ORD.NEXTVAL,?,?,DEFAULT,'배송준비중',DEFAULT,NULL,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, paint_no);
			pstmt.setString(2, orderid);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(result);
		return result;
	}

	public ArrayList<product> selectsearch2(Connection conn, product po) {
 		//카테고리가없을경우
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<product> list = new ArrayList<>();
		product p = new product();
		list.add(p);
			
			String query ="SELECT * FROM PAINT WHERE ARTIST_NAME = ? AND PAINT_PRICE BETWEEN 0 AND ? ORDER BY 1 ";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, po.getPaint_name());
				pstmt.setInt(2, po.getPatint_price());
				
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					 p = new product(rset.getInt("PAINT_NO"), 
							rset.getString("PAINT_NAME"),
							rset.getInt("PAINT_PRICE"),
							rset.getInt("SIZE_NO"),
							rset.getString("ARTIST_NAME"));
						
					list.add(p);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			System.out.println("ProductDao:게시글 출력 확인_" + list);
		
		return list;
	}
	public ArrayList<product> selectsearch3(Connection conn, product po) {
		//작가이름과 카테고리가 null일경우
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<product> list = new ArrayList<>();
		product p = new product();
		list.add(p);
		
		String query ="SELECT * FROM PAINT WHERE PAINT_PRICE BETWEEN 0 AND ? ORDER BY 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, po.getPatint_price());
			
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				p = new product(rset.getInt("PAINT_NO"), 
						rset.getString("PAINT_NAME"),
						rset.getInt("PAINT_PRICE"),
						rset.getInt("SIZE_NO"),
						rset.getString("ARTIST_NAME"));
						list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao:게시글 출력 확인_" + list);
		return list;
	}

	public ArrayList<product> selectsearch4(Connection conn, product po) {
		//전부다 있을경우( 가격은 0으로 처리되기떄문에 따로 예외처리를 안해줘도된다.)
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<product> list = new ArrayList<>();
		product p = new product();
		list.add(p);
		
		String query ="SELECT * FROM PAINT WHERE ARTIST_NAME=? AND CATEGORY=? AND PAINT_PRICE BETWEEN 0 AND ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, po.getPaint_name());
			pstmt.setString(2, po.getCategory());
			pstmt.setInt(3, po.getPatint_price());
			
			
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				 p = new product(rset.getInt("PAINT_NO"), 
						rset.getString("PAINT_NAME"),
						rset.getInt("PAINT_PRICE"),
						rset.getInt("SIZE_NO"),
						rset.getString("ARTIST_NAME"));
						list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ProductDao:게시글 출력 확인_" + list);
	

		return list;
	}

	public product selectpaint(Connection conn, int paint_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		product p = new product();
	
		String query ="SELECT * FROM PAINT WHERE PAINT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, paint_no);
			
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				 p = new product(rset.getInt("PAINT_NO"), 
						rset.getString("PAINT_NAME"),
						rset.getInt("PAINT_PRICE"),
						rset.getInt("SIZE_NO"),
						rset.getString("ARTIST_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}

	public int updateBasket(Connection conn, int paint_no, String orderid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result= 0;
		String query = "UPDATE BASKET SET BUY_YN='Y' WHERE  PAINT_NO=? AND USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, paint_no);
			pstmt.setString(2, orderid);
			
			result = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public Paint_QnA selectP(Connection conn, int qna_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Paint_QnA qna = new Paint_QnA();
		
		String query = "SELECT * FROM PAINT_A WHERE PQ_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, qna_no);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				 qna = new Paint_QnA(rset.getInt("PQ_NO"), 
						rset.getString("PANSWER"),
						rset.getString("PA_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		System.out.println(qna);
		return qna;
	}

}
