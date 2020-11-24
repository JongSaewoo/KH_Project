package mypage_user.mainOrderRefundWish.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import mypage_user.mainOrderRefundWish.model.service.MorwService;
import mypage_user.mainOrderRefundWish.model.vo.Morw;
import product.model.service.ProductService;
import product.model.vo.Attachment;

/**
 * Servlet implementation class SearchRefundServlet
 */
@WebServlet("/SearchRefundServlet")
public class SearchRefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRefundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MorwService mService = new MorwService();
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginUser");
		
		String order_status = request.getParameter("searchStatus");
		String term = request.getParameter("term");
		String calendar1 = request.getParameter("calendar1");
		String calendar2 = request.getParameter("calendar2");
		
		System.out.println(order_status);
		System.out.println(term);
		System.out.println(calendar1);
		System.out.println(calendar2);
		
		ArrayList<Morw> refund_search_list = new ArrayList<>();
		
		
		ArrayList<Attachment> plist = new ArrayList<Attachment>();
	    ProductService pService = new ProductService();
	      
	    plist = pService.selectAllalist();
		
		
	    refund_search_list=mService.searchRefundList(order_status, term, calendar1, calendar2,loginMember.getUserId());
		
		RequestDispatcher view = null;
		if(!refund_search_list.isEmpty()) {
			view = request.getRequestDispatcher("views/mypage_user/refundSearchResult.jsp");
			request.setAttribute("refund_search_list", refund_search_list);
			request.setAttribute("plist",plist);
			
		}else {
			view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg","검색 결과가 없습니다");
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
