package mypage_user.qna.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mypage_user.qna.model.vo.Qna;

public class QnaDao {

	public ArrayList<Qna> selectList(Connection conn,String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Qna> list = new ArrayList<>();
		
		String query = "SELECT PA.PAINT_NO,PA.PAINT_NAME,P.PQUESTION,P.PQ_DATE,P.PQ_YN \r\n" + 
				"FROM PAINT_Q P \r\n" + 
				"LEFT JOIN PAINT PA ON (P.PAINT_NO=PA.PAINT_NO) \r\n" + 
				"LEFT JOIN MEMBER M ON (P.USER_ID= M.USER_ID)\r\n" + 
				"WHERE M.USER_ID =?";
		
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna(rset.getInt("paint_no"),
								  rset.getString("paint_name"),
								  rset.getString("pquestion"),
								  rset.getDate("pq_date"),
								  rset.getString("pq_yn"));
				list.add(qna);
				//System.out.println("qna dao단 list 출력"+list);
						
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Qna> selectList2(Connection conn,String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Qna> list2 = new ArrayList<>();
		
		String query = "SELECT Q_CATE,QUESTION_TITLE,Q_DATE,Q_YN FROM QUESTION WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna(rset.getString("Q_CATE"),
						  rset.getString("QUESTION_TITLE"),
						  rset.getDate("Q_DATE"),
						  rset.getString("Q_YN"));
				list2.add(qna);
				System.out.println("qna dao단 table 2 list 출력"+list2);
				
			}
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		return list2;
	}

	
	
	

}
