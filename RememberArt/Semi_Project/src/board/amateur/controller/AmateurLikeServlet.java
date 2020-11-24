package board.amateur.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.amateur.model.service.AmateurService;
import board.amateur.model.vo.Amateur;
import member.model.vo.Member;

/**
 * Servlet implementation class AmateurLikeServlet
 */
@WebServlet("/like.am")
public class AmateurLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmateurLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		AmateurService aService = new AmateurService();
		
		int event_no = Integer.valueOf(request.getParameter("event_no"));
		String user = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		String heartYN = request.getParameter("heartYN");

		if(heartYN.equals("Y")) {
			int result = aService.insertHeart(user,event_no);
		}else {
			int result = aService.deleteHeart(user,event_no);
		}
		int count = 0;
		count = aService.selectEventLike(event_no);
		
		PrintWriter out = response.getWriter();
		out.print(count);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
