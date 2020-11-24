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
 * Servlet implementation class insertMemberServlet
 */
@WebServlet(name = "InsertMemberServlet", urlPatterns = "/insert.me")
public class insertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public insertMemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		String nickName = request.getParameter("nickName");
		String phone = request.getParameter("phone");
	
		
		String var1=request.getParameter("sample6_postcode");
		String var2=request.getParameter("sample6_address");
		String var3=request.getParameter("sample6_detailAddress");
		String var4=request.getParameter("sample6_extraAddress");
		
		String address = "";
		address +=var1;
		address +=var2;
		address +=var3;
		address +=var4;
		
		
		String email = request.getParameter("email");
		Member member = new Member(userId, userName,userPwd , nickName, phone, address, email);
		int result = new MemberService().insertMember(member);

		
		
		String page = "";
		if (result > 0) {
			page = "views/member/signIn.jsp";
			request.setAttribute("msg", "회원가입 성공!!");
		} else {
			page = "views/member/SignUp.jsp";
			request.setAttribute("msg", "회원가입 실패");

		}

		RequestDispatcher view = request.getRequestDispatcher(page);

		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
