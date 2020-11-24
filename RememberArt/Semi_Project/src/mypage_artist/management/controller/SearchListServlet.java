package mypage_artist.management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.vo.PageInfo;
import member.model.vo.Member;
import mypage_artist.management.model.service.Mypage_ArtistService;
import mypage_artist.management.model.vo.Mypage_artist;
import oracle.sql.DATE;

/**
 * Servlet implementation class SearchListServlet
 */
@WebServlet("/Search.MP")
public class SearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		
		Mypage_ArtistService ma = new Mypage_ArtistService();
		
		String order_status = request.getParameter("order_status");
		String term = request.getParameter("term");
		String calendar1 = request.getParameter("calendar1");
		String calendar2 = request.getParameter("calendar2");
		
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
		
		
		System.out.println(order_status);
		System.out.println(term);
		System.out.println(calendar1);
		System.out.println(calendar2);
		System.out.println(bWriter);
		
		// 1_1. 게시판 리스트 갯수 구하기
		int listCount = ma.getListCount_OM(bWriter);
		System.out.println(listCount);
		
		// 페이지 수 처리용 변수 선언
		int currentPage;		// 현재 페이지를 저장할 변수
		int limit;					// 한 페이지에 보여질 게시글 수
		int maxPage;				// 전체 페이지의 맨 마지막 페이지
		int startPage;			// 한번에 표시될 페이지가 시작할 페이지
		int endPage;				// 한번에 표시될 페이지가 끝나는 페이지

		// * currentPage - 현재 페이지
		// 기본 게시판 페이지는 1페이지부터 시작
		currentPage = 0;
		// 하지만 페이지 전환시 전달받은 현재 페이지가 있을 시 해당 페이지를 currentPage로 적용
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));	
			// String -> int
		}else {
			currentPage = 1;
		}		
		
		// * limit - 한 페이지에 보여질 목록 갯수
		limit = 10;
		
		// * maxPage - 총 페이지 수
		// 목록 수가 123개이면 페이지 수는 13페이지가 됨
		maxPage = (int)((double)listCount/limit + 0.9);
		
		// * startPage - 현재 페이지에 보여질 시작 페이지 수
		//  아래쪽에 페이지 수가 10개씩 보여지게 할 경우
		//  1, 11, 21, 31, ...
		startPage = (((int)((double)currentPage/limit + 0.9))-1)*limit + 1;
		
		// * endPage - 현재 페이지에 보여질 마지막 페이지 수
		//  아래쪽에 페이지 수가 10개씩 보여지게 할 경우
		// 10, 20, 30, 40, ...
		endPage = startPage + limit - 1;
		
		
		// maxPage(총 페이지 수)가 endPage보다 작을 경우
		// ex) maxPage => 13이고 endPage => 20이면
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 위에서 계산한 모든 페이지 관련 변수들을 PageInfo 클래스의
		// 객체에 담자.(PageInfo클래스를 만들자)
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage,
				                   startPage, endPage);
		
		ArrayList<Mypage_artist> search_list = new ArrayList<>();
		
		search_list = ma.listSearch(order_status, term, calendar1, calendar2, bWriter);
		System.out.println(search_list);
		
		RequestDispatcher view = null;
		if(!search_list.isEmpty()) {
			view = request.getRequestDispatcher("views/mypage_artist/searchResult.jsp");
			request.setAttribute("search_list", search_list);
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
