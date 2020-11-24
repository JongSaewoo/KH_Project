package board.amateur.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.amateur.model.service.AmateurService;
import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;

/**
 * Servlet implementation class AmateurSearchServlet
 */
@WebServlet("/search.am")
public class AmateurSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmateurSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AmateurService aService = new AmateurService();
		request.setCharacterEncoding("UTF-8");

		String category = request.getParameter("Category")!=null?request.getParameter("Category"):null;
		String keyword = request.getParameter("search_title")!=null?request.getParameter("Category"):null;
		
		System.out.println("검색카테고리:"+category);
		System.out.println("검색어:"+keyword);
		
		ArrayList<Amateur> list = aService.select(category,keyword);
		ArrayList<FileManagement> fileList = aService.selectList();
		
		System.out.println("검색 결과"+list);
		
		if(list!=null) {
			request.setAttribute("amateur", list);
			request.setAttribute("fileList", fileList);			
		}else {
			request.setAttribute("amateur",null);
			request.setAttribute("fileList", null);
		}

		request.getRequestDispatcher("views/board/amateur/amateurSearch.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
