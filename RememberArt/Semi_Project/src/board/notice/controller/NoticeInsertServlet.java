package board.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.service.NoticeService;
import board.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/insert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NoticeInsertServlet() {	   super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 저장을 위한 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		String noti_title = request.getParameter("noti_title");
		String notice = request.getParameter("notice");
		
		Notice noticeBoard = new Notice(noti_title, notice);
		int result = new NoticeService().insertNotice(noticeBoard);
		
		if(result>0) {
			System.out.println("NoticeInsertServlet : 데이터가 성공적으로 Insert됨");
			response.sendRedirect("list.no");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
