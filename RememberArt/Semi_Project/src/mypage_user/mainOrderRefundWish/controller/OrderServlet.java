package mypage_user.mainOrderRefundWish.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import mypage_user.mainOrderRefundWish.model.service.MorwService;
import mypage_user.mainOrderRefundWish.model.vo.Morw;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/Mo.litwo")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* doGet(request, response); */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("!!!!!!!!!!!!!!!!!!AJAX 서블릿!!!!!!!!!!!!!!!!!!!!!");
		
		//화면에서 전달한 order_no parmeter 받기
		String param = request.getParameter("order_no");
		
		
		MorwService mService = new MorwService();
		
		//상태 업데이트 메소드 실행
		mService.updateStatus(param);
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginUser");
		
		
		//insert 작업을 위한 화면에서  전달한 message prameter 받기
		int paintNo = Integer.valueOf(request.getParameter("paint_no"));
		String orderNo = request.getParameter("order_no");
		String message = request.getParameter("message");
		String paintName = request.getParameter("paint_name");
		String artistName = request.getParameter("artist_name");
		
		System.out.println("나는 서블릿 단이다"+param+paintNo);
		System.out.println("ㄴㅏ는 서블릿"+message+paintName+artistName);
		

		
		//메세지 인서트 메소드 실행
		int result = mService.insertMessage(new Morw(param,paintNo,loginMember.getUserId(),message,paintName,artistName));
		
		
	}

}
