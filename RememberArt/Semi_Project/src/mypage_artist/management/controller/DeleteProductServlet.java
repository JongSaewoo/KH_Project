package mypage_artist.management.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage_artist.management.model.service.Mypage_ArtistService;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/delete.p")////////////////////////////////////////////////////////////////////////////////////확인해줭
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Mypage_ArtistService ma = new Mypage_ArtistService();
		
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
		int paint_no = Integer.valueOf(request.getParameter("paint_no"));
		
		int deleteResult = ma.deleteProduct(bWriter, paint_no);
		
		int deleteBResult = ma.deleteBasket(bWriter, paint_no);
		
		RequestDispatcher view = null;
		
		if(deleteResult > 0) {
			response.sendRedirect("PM.list");
		}else {
			view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg","장바구니 삭제 실패");
		}
		
		
		/*
		 * if(deleteResult > 0) { view =
		 * request.getRequestDispatcher("views/mypage_artist/product_management.jsp");
		 * }else { view = request.getRequestDispatcher("views/common/errorPage.jsp");
		 * request.setAttribute("msg","작품 삭제 실패"); } view.forward(request, response);
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
