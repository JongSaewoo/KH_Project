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
 * Servlet implementation class RefundApplyServlet
 */
@WebServlet("/Mo.lithree")
public class RefundApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefundApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		HttpSession session = request.getSession(); 
		Member loginMember = (Member)session.getAttribute("loginUser");
		
		String orderNo = request.getParameter("order_no");
		int paintNo = Integer.valueOf(request.getParameter("paint_no"));
		String refundName = request.getParameter("refund_name");
		String selectBank= request.getParameter("select_bank");
		String account = request.getParameter("account");
		String refundReason = request.getParameter("refund_reason");
		
	
		 
		MorwService mService = new MorwService();
		
		//환불신청으로 orderstauts 바꾸는 메소드
		mService.updateStatus2(orderNo);
		 
		//환불 인서트 업데이트 메소드 실행
		 int result = mService.insertRefund(new Morw(orderNo,paintNo,loginMember.getUserId(),refundReason,refundName,account,selectBank));
		
		
	}

}
