package board.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;
import board.notice.model.vo.Notice;
import board.notice.model.vo.PageInfo;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/list.no")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeListServlet() {    super();    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService nService = new NoticeService();
		
		int listCount = nService.getListCount();

		int currentPage;		// 현재 페이지를 저장할 변수
		int limit;				// 한 페이지에 보여질 게시글 수
		int maxPage;			// 전체 페이지의 맨 마지막 페이지
		int startPage;			// 한번에 표시될 페이지가 시작할 페이지
		int endPage;			// 한번에 표시될 페이지가 끝나는 페이지
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));	
		}
		System.out.println("currentPage:"+currentPage);
		limit = 10;
		maxPage = (int)((double)listCount/limit+0.9);
		startPage = (((int)((double)currentPage/limit+0.9))-1)*limit+1;
		endPage = startPage+limit-1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage,startPage, endPage);
		
		ArrayList<Notice> list = nService.selectList(currentPage, limit);
		
		//화면으로
		RequestDispatcher view = null;
			view = request.getRequestDispatcher("views/board/notice/noticeBoard.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
