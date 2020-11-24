package board.updateBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.amateur.model.service.AmateurService;
import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;

/**
 * Servlet implementation class SelectEventServlet
 */
@WebServlet("/select.am")
public class SelectEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			request.setCharacterEncoding("UTF-8");
			AmateurService aService = new AmateurService();
			
			int updateNo = Integer.valueOf(request.getParameter("updateNo"));
			
			Amateur amateur = aService.selectBoard(updateNo);
			FileManagement fileList = aService.selectFile(updateNo);
			
			request.setAttribute("amateur", amateur);
			request.setAttribute("fileList", fileList);
			
			request.getRequestDispatcher("views/board/amateur/amateurUpdate.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
