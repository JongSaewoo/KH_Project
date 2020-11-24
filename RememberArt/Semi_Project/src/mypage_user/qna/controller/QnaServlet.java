package mypage_user.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.notice.model.vo.PageInfo;
import member.model.vo.Member;
import mypage_user.mainOrderRefundWish.model.vo.Morw;
import mypage_user.qna.model.service.QnaService;
import mypage_user.qna.model.vo.Qna;

/**
 * Servlet implementation class QnaServlet
 */
@WebServlet("/qna")
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaService qService = new QnaService();
		System.out.println("qna서블릿 이동 확인");
		
		//로그인세션
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginUser");
		
		
		
		
		//상품문의
		ArrayList<Qna> list = qService.selectList(loginMember.getUserId());
		//1:1문의
		ArrayList<Qna> list2 = qService.selectList2(loginMember.getUserId());
		
		
		
		RequestDispatcher view = null;
		
		request.setAttribute("list",list);
		request.setAttribute("list2", list2);
		view = request.getRequestDispatcher("views/mypage_user/mypage_pna.jsp");
		
		
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
