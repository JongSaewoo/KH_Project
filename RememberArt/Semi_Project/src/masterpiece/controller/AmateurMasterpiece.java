package masterpiece.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import masterpiece.model.service.AmateurMPService;
import product.model.vo.Attachment;
import product.model.vo.product;

/**
 * Servlet implementation class AmateurMasterpiece
 */
@WebServlet("/amateur.master")
public class AmateurMasterpiece extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmateurMasterpiece() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AmateurMPService amService = new AmateurMPService();
		
		int firstPaint = 1;
		int lastPaint = 6;
		ArrayList<Amateur> masterList = amService.showMasterpiece(firstPaint, lastPaint);
		ArrayList<FileManagement> masterFileList = amService.showMasterpiece();
		
		ArrayList<product> proMasterList = amService.showProMasterpiece(firstPaint, lastPaint);
		ArrayList<Attachment> proMasterFileList = amService.showProMasterpiece();
		
		
		request.setAttribute("masterList", masterList);
		request.setAttribute("masterFileList", masterFileList);
		request.setAttribute("masterProList", proMasterList);
		request.setAttribute("masterProFileList", proMasterFileList);
		
	
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
