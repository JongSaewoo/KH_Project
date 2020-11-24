package board.deleteBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.deleteBoard.model.service.deleteService;

/**
 * Servlet implementation class DeleteFreeServlet
 */
@WebServlet("/delete.ee")
public class DeleteFreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFreeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteService dService = new deleteService();
		
		int deleteNo = Integer.valueOf(request.getParameter("deleteNo"));
		System.out.println("삭제할 행 번호"+deleteNo);
		
		int deleteResultN = dService.deleteFree(deleteNo);
		
		if(deleteResultN>0) {
			System.out.println("데이터가 성공적으로 삭제되었습니다.");
			response.sendRedirect("list.ee");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
