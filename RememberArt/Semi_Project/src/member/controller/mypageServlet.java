package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class mypageServlet
 */
@WebServlet("/mypage.me")
public class mypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		System.out.println("정보수정용 Servlet에서 로그인 한 사용자 id:"+userId);
		
		Member member = new MemberService().selectMember(userId);
		System.out.println(member);
		RequestDispatcher view = null; 
		
		if(member != null) {
			
			System.out.println("조회 한 회원 정보"+member);
			view = request.getRequestDispatcher("views/member/signUpView.jsp");
			request.setAttribute("member",member);
		}else {
			view = request.getRequestDispatcher("views/member/SignError");
			request.setAttribute("msg","회원 조회 실패");
		}
		
		view.forward(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
