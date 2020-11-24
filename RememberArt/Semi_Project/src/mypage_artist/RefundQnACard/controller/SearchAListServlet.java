package mypage_artist.RefundQnACard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import artistapply.model.vo.Apply;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.dao.ArtistDao;
import mypage_artist.RefundQnACard.model.service.ArtistService;
import mypage_artist.RefundQnACard.model.vo.BuyList_a;
import mypage_artist.RefundQnACard.model.vo.PageInfo;
import product.model.vo.Attachment;

/**
 * Servlet implementation class SearchAListServlet
 */
@WebServlet("/alist.search")
public class SearchAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String artist = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		
		ArtistService aService = new ArtistService();
		
		String order_status = request.getParameter("order_status");
		String term = request.getParameter("term");
		String calendar1 = request.getParameter("calendar1");
		String calendar2 = request.getParameter("calendar2");
		
		String loginName = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
	
		
		System.out.println("주문상태:"+order_status);
		System.out.println("기간:"+term);
		System.out.println("날짜1:"+calendar1);
		System.out.println("날짜2:"+calendar2);
		System.out.println("이름:"+loginName);
		
		
		// 게시판 리스트 조회해오기
		ArrayList<BuyList_a> search_list = aService.alistSearch(order_status, term, calendar1, calendar2, loginName );
		for(int i = 0 ; i < search_list.size(); i++) {
			System.out.println(search_list.get(i));
		}
		
		ArrayList<Attachment> alist = aService.selectAList(loginName);
		
		for(int i = 0 ; i <alist.size(); i++) {
			System.out.println(alist.get(i));
		}
		
		// 프로필 사진 불러오기
		Apply aphoto = aService.selectPhoto(artist);
		System.out.println(aphoto);
		
		
		
		RequestDispatcher view = null;
		if(!search_list.isEmpty()) {
			view = request.getRequestDispatcher("views/mypage_artist/searchRefund.jsp");
			request.setAttribute("aphoto", aphoto);
			request.setAttribute("alist", alist);
			request.setAttribute("search_list", search_list);
		}else {
			view = request.getRequestDispatcher("views/common/errorPage.jsp");
		    request.setAttribute("msg","내용 조회 실패");
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
