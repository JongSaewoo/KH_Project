package board.inquiry.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.inquiry.model.service.InquiryService;
import board.inquiry.model.vo.Inquiry;
import board.notice.model.vo.PageInfo;

/**
 * Servlet implementation class inquiryListServlet
 */
@WebServlet("/list.in")
public class inquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inquiryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InquiryService inService = new InquiryService();
		request.setCharacterEncoding("UTF-8");
		
		int listCount = inService.getListCount();
		
		
		int currentPage;		// 현재 페이지를 저장할 변수
		int limit;				// 한 페이지에 보여질 게시글 수
		int maxPage;			// 전체 페이지의 맨 마지막 페이지
		int startPage;			// 한번에 표시될 페이지가 시작할 페이지
		int endPage;			// 한번에 표시될 페이지가 끝나는 페이지
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));	
		}
		
//		System.out.println("currentPage:"+currentPage);
		limit = 10;
		maxPage = (int)((double)listCount/limit+0.9);
		startPage = (((int)((double)currentPage/limit+0.9))-1)*limit+1;
		endPage = startPage+limit-1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage,startPage, endPage);

		ArrayList<Inquiry> list = inService.selectList(currentPage,limit);
		
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		RequestDispatcher view = null;
		
			view = request.getRequestDispatcher("views/board/inquiry/inquiryBoard.jsp");
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
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
