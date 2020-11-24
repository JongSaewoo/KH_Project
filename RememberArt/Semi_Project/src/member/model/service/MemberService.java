package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.vo.Member;



public class MemberService {
	
	public Member loginMember(Member member) {
		System.out.println("서비스 단까지 왔다.");
		Connection conn = getConnection();
		
		Member loginMember = new MemberDao().loginMember(conn,member);
		
		close(conn);

		return loginMember;
	}

	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().idCheck(conn,userId);
		
		close(conn);
		return result;

	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn,member);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}close(conn);
		
		return result;
	}

	public Member selectMember(String userId) {
		Connection conn = getConnection();
		
		Member loginMember = new MemberDao().selectMember(conn,userId);
		
		close(conn);
		
		
		return loginMember;
	}
	
	public int updateMember(Member member) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn,member);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
			close(conn);
			
		return result;
	}

	public int deleteMember(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn,userId);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int updatePwd(String id ,String body) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn,id,body);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return result;
	}

	
	
	

}
