package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class updateMemberServlet
 */
@WebServlet("/update.me")
public class updateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String nickName = request.getParameter("nickName");
		
		Member m = new Member(userId,userName,userPwd,email,phone,address,nickName); 
		//이 순서는 화면단에 보이는 거랑 상관x DB 순서 맞춰주기
		int result = new MemberService().updateMember(m);
		System.out.println("회원 정보 수정 Servlet에서 update 결과:"+result);
		
		RequestDispatcher view = null; 
		HttpSession session = request.getSession();
		
		if(result >0) {
			session.setAttribute("loginUser",m);
			/* view = request.getRequestDispatcher("views/member/SignSuccess.jsp"); */
			view = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg","성공적으로 회원정보를 수정했습니다.");
		}else {
			view = request.getRequestDispatcher("view/member/SignError.jsp");
			request.setAttribute("msg", "회원 정보 수정에 실패했습니다.");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
