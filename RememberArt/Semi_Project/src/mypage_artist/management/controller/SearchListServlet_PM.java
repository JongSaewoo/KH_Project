package mypage_artist.management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage_artist.management.model.service.Mypage_ArtistService;
import mypage_artist.management.model.vo.Mypage_artist;
import product.model.service.ProductService;
import product.model.vo.Attachment;

/**
 * Servlet implementation class SearchListServlet_PM
 */
@WebServlet("/Search.PM")
public class SearchListServlet_PM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListServlet_PM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		Mypage_ArtistService ma = new Mypage_ArtistService();
		
		String category = request.getParameter("category");
		System.out.println("하"+category);
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
		
		ArrayList<Mypage_artist> search_list_PM = new ArrayList<>();
		
		search_list_PM = ma.listSearch_PM(category, bWriter);
		System.out.println(search_list_PM);
		
		ArrayList<Attachment> alist = new ArrayList<Attachment>();
		ProductService pService = new ProductService();
		
		alist = pService.selectAllalist();
		
		RequestDispatcher view = null;
		if(!search_list_PM.isEmpty()) {
			view = request.getRequestDispatcher("views/mypage_artist/searchResult_PM.jsp");
			request.setAttribute("search_list_PM", search_list_PM);
			request.setAttribute("alist",alist);
			
		}else {
			view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg","검색 결과가 없습니다");
		}
		
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
