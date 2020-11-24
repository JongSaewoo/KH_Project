package board.amateur.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.AmateurLike;
import board.amateur.model.vo.FileManagement;
import board.amateur.model.vo.Reply;
import product.model.vo.MasterpieceCount;
import product.model.vo.masterpiece;
public class AmateurDao {

	//게시판에 글 쓰는 insert 쿼리문
	public int insertBoard(Connection conn, Amateur a) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String query = "INSERT INTO EVENT VALUES(EVENT_SEQ.NEXTVAL, ?,?,SYSDATE,?,DEFAULT,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, a.getEvent_title());
			pstmt.setString(2, a.getEvent());
			pstmt.setString(3, a.getUser_id());
			
//			System.out.println("[dao] insert data _ 제목:"+a.getEvent_title());
//			System.out.println("[dao] insert data _ 작성자:"+a.getUser_id());
//			System.out.println("[dao] insert data _ 내용:"+a.getEvent());
//			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//게시판에 파일 올리는 insert 쿼리문
	public int insertAmateurFile(Connection conn, ArrayList<FileManagement> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO EVENT_FILE VALUES(EVENT_SEQ.CURRVAL,?,?)";
		
		try {
			for(int i = 0; i<fileList.size();i++) {
				FileManagement fm = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, fm.getEvent_file());
				pstmt.setString(2, fm.getEvent_path());
				
				result=pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//게시글 갯수 세는 select count(*) 쿼리문  : 페이징 처리에 사용
	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM EVENT";
		int listCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
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

	//한 화면에 비춰질 게시글 목록 출력 쿼리문 BETWEEN ? AND ?
	public ArrayList<Amateur> selectList(Connection conn, int currentPage, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		ArrayList<Amateur> list = new ArrayList<>();
		String query = "SELECT * FROM EVENT WHERE EVENT_NO BETWEEN ? AND ?";
		
		int startRow = (currentPage -1)*limit+1;
		int endRow = startRow+limit-1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Amateur a = new Amateur(rset.getInt("event_no"),
										rset.getString("event_title"),
										rset.getString("user_id"),
										rset.getDate("event_date"),
										rset.getString("event"),
										rset.getInt("hit"),
										rset.getInt("event_like"));
				list.add(a);		
			}
			System.out.println("[dao] 게시글 불러오기:"+list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//사진 파일 리스트 전체 가져오기 : 화면단에서 게시글에 맞게 판별해줄 것이니 전체 불러오자
	public ArrayList<FileManagement> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		FileManagement fm1 = new FileManagement();
		ArrayList<FileManagement> list = new ArrayList<>();
		
//		list.add(fm1);
		String query = "SELECT * FROM EVENT_FILE";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				FileManagement fm = new FileManagement(rset.getInt("event_no"),
										rset.getString("event_file"),
										rset.getString("event_path"));
				
				list.add(fm);
			}
			System.out.println("[dao] 파일리스트 출력결과:"+list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//조회수 count HIT = HIT+1
	public int updateCount(Connection conn, int aid) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE EVENT SET HIT = HIT+1 WHERE EVENT_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//상세 게시글 조회(몇번째 게시글의 내용들을 불러오기)
	public Amateur selectBoard(Connection conn, int aid) {

		PreparedStatement pstmt = null;
		ResultSet rset= null;
		
		String query = "SELECT * FROM EVENT WHERE EVENT_NO=?";
		Amateur a = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aid);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				a = new Amateur(rset.getInt("EVENT_NO"),
								rset.getString("EVENT_TITLE"),
								rset.getString("USER_ID"),
								rset.getDate("EVENT_DATE"),
								rset.getString("EVENT"),
								rset.getInt("HIT"),
								rset.getInt("EVENT_LIKE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
			return a;
	
	}

	//상세 파일 조회(몇번째 게시글에 해당하는 파일 불러오기)
	public FileManagement selectFile(Connection conn, int aid) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		
		String query = "SELECT * FROM EVENT_FILE WHERE EVENT_NO=?";
		FileManagement fm = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aid);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				fm = new FileManagement(rset.getInt("EVENT_NO"),
								rset.getString("EVENT_FILE"),
								rset.getString("EVENT_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
			return fm;
	}

	//게시글 상세 조회 시 출력될 전체 댓글
	public ArrayList<Reply> selectReplyList(Connection conn, int event_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Reply> rList = null;
		
		String query = "SELECT * FROM EVENT_REPLY WHERE EVENT_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, event_no);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<Reply>();
			while(rset.next()) {
				rList.add(new Reply(rset.getInt("REPLY_NO"),
									rset.getInt("EVENT_NO"),
									rset.getString("REPLY"),
									rset.getDate("REPLY_DATE"),
									rset.getString("USER_ID")));
			}
			System.out.println("[아마추어게시판에서 게시글을 클릭했을 때(dao)] event_no 값:"+event_no);
			System.out.println("[아마추어게시판에서 게시글을 클릭했을 때(dao)] rList 값:"+rList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	//댓글 등록 insert
	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
//		System.out.println("AmateurDao"+r.getEvent_no());
//		System.out.println("AmateurDao"+r.getReply());
//		System.out.println("AmateurDao"+r.getUser_id());
//		
		String query = "INSERT INTO EVENT_REPLY VALUES(REPLY_SEQ.NEXTVAL,?,?,SYSDATE,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, r.getEvent_no());
			pstmt.setString(2, r.getReply());
			pstmt.setString(3, r.getUser_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("[댓글 insert]Amateur Dao:"+result);
		return result;
	}


	public int insertHeart(Connection conn, String user, int event_no) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "INSERT INTO EVENT_LIKE VALUES(?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, event_no);
			pstmt.setString(2, user);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		System.out.println("[EVENT_LIKE]에 INSERT성공:"+result);
		
		return result;
	}

	public int deleteHeart(Connection conn, String user, int event_no) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "DELETE FROM EVENT_LIKE WHERE USER_ID=? AND EVENT_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user);
			pstmt.setInt(2, event_no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		System.out.println("[EVENT_LIKE]에 DELETE성공:"+result);
		
		return result;
	}

	public int selectEventLike(Connection conn, int event_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count =0;
		String query="SELECT COUNT(*)좋아요수 FROM EVENT_LIKE WHERE EVENT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, event_no);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt("좋아요수");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("[EVENT_LIKE]에서 좋아요 갯수 가져오기:"+count);
		return count;
	}

	public AmateurLike selectLikeList(Connection conn,int event_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AmateurLike al = new AmateurLike();
		
		
		String query = "SELECT COUNT(*) 좋아요 FROM EVENT_LIKE WHERE EVENT_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, event_no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				al = new AmateurLike(rset.getInt("좋아요"));
			}
			System.out.println("[dao] 좋아요 출력결과:"+al);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return al;
	}

	public AmateurLike selectLikeList(Connection conn, int event_no, String user) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AmateurLike al = new AmateurLike();
		
		
		String query = "SELECT COUNT(*) 좋아요 FROM EVENT_LIKE WHERE EVENT_NO=? AND USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, event_no);
			pstmt.setString(2, user);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				al = new AmateurLike(rset.getInt("좋아요"));
			}
			System.out.println("[dao] 좋아요 출력결과:"+al);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return al;
	}

	public int updateHeart(Connection conn, int event_no, int check) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		if(check==1) {
			String query = "UPDATE EVENT SET EVENT_LIKE =EVENT_LIKE+1 WHERE EVENT_NO=?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, event_no);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}else {
			String query = "UPDATE EVENT SET EVENT_LIKE =EVENT_LIKE-1 WHERE EVENT_NO=?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, event_no);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		
		}
		return result;
	}

	public ArrayList<Amateur> select(Connection conn, String category, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Amateur> list = new ArrayList<>();
		
			try {
				String query = null;
				if(category.equals("title")) {
					query = "SELECT * FROM EVENT WHERE EVENT_TITLE LIKE ? ORDER BY EVENT_NO DESC";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, "%"+keyword+"%");
				}else if(category.equals("content")) {
					query = "SELECT * FROM EVENT WHERE EVENT LIKE ? ORDER BY EVENT_NO DESC";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, "%"+keyword+"%");
				
				}else if(category.equals("writer")){
					query = "SELECT * FROM EVENT WHERE USER_ID LIKE ? ORDER BY EVENT_NO DESC";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, "%"+keyword+"%");
				
				}
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Amateur a = new Amateur(rs.getInt("event_no"),
											rs.getString("event_title"),
											rs.getString("user_id"),
											rs.getDate("event_date"),
											rs.getString("event"),
											rs.getInt("hit"),
											rs.getInt("event_like"));
					list.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}


}
