package board.free.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.free.model.service.FreeService;
import board.free.model.vo.Free;
import board.free.model.vo.Reply;

/**
 * Servlet implementation class FreeDetailServlet
 */
@WebServlet("/detail.ee")
public class FreeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		FreeService fService = new FreeService();
		
		int free_no = Integer.valueOf(request.getParameter("free_no"));
		
		Free free = fService.selectFreeBoard(free_no);
		
		ArrayList<Reply> reply = fService.selectReply(free_no);
		
			request.setAttribute("free", free);
			request.setAttribute("rList", reply);
			request.getRequestDispatcher("views/board/free/freeDetail.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
