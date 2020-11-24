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

import board.notice.model.vo.PageInfo;
import member.model.vo.Member;
import mypage_user.mainOrderRefundWish.model.service.MorwService;
import mypage_user.mainOrderRefundWish.model.vo.Morw;
import product.model.vo.Attachment;

/**
 * Servlet implementation class MainOrderListservlet
 */
@WebServlet("/Mo.li")
public class MainOrderListservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainOrderListservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menu = request.getParameter("menu");
		System.out.println("서블릿 왔니?");
		//로그인세션
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginUser");
		


		MorwService mService = new MorwService();
		//페이지 네이션
		// 1_1. 게시판 리스트 갯수 구하기
		int listCount = mService.countSelectList(loginMember.getUserId());
	
		
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
		
		
		
		
		
		ArrayList<Morw> list = mService.selectList(loginMember.getUserId());
//		ArrayList<Morw> list = mService.selectList(loginMember.getUserId(),searchStatus);
		ArrayList<Attachment> plist = mService.selectPList(loginMember.getUserId());
		
		
		//System.out.println("testsssss");
		RequestDispatcher view = null;
		
		//메인으로갈때
		String page = "views/mypage_user/mypage_main.jsp";
		
		
		//ORDER로 갈때
		if("order".equals(menu)) {
			page = "views/mypage_user/mypage_order.jsp";
		}
		
		
		view = request.getRequestDispatcher(page);
		request.setAttribute("list",list);
		request.setAttribute("plist", plist);
		request.setAttribute("pi", pi);
		
		view.forward(request, response);
		
		//boardListView.jsp 만들러 가자!
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        doGet(request, response); 
		
		 
		 //화면에서 전달한 order_no parmeter 받기 
		 String param = request.getParameter("order_no");
		 
		 
		 
		 MorwService mService = new MorwService();
		 
		 //상태 업데이트 메소드 실행
		 mService.updateStatus(param);
		 
		 
		
	}

}
