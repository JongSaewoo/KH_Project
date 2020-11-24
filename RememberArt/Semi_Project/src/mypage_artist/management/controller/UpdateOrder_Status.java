package mypage_artist.management.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage_artist.management.model.service.Mypage_ArtistService;
import mypage_artist.management.model.vo.Mypage_artist;

/**
 * Servlet implementation class UpdateOrder_Status
 */
@WebServlet("/updatedeposit.ud")
public class UpdateOrder_Status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder_Status() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mypage_ArtistService aService = new Mypage_ArtistService();
		
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		int order_no = Integer.valueOf(request.getParameter("order_no"));
		
		System.out.println("주문번호" +order_no);
		System.out.println(bWriter);
		
		int result = aService.updateOrder_status(order_no);
		
		if(result >0) {
			response.sendRedirect("SM.list");
		}else {
			System.out.println("입금확인 실패");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
