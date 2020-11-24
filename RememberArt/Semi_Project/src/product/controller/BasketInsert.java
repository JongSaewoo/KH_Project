package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Basket;
import product.model.vo.product;

/**
 * Servlet implementation class BasketInsert
 */
@WebServlet("/basket.po")
public class BasketInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasketInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ProductService pService = new ProductService();
		
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		int paint_no = Integer.valueOf(request.getParameter("paint_no"));
		System.out.println(paint_no);
		
		product po = new product();
		
		po = pService.selectsearch(paint_no);
		
		Basket bs = new Basket();
		System.out.println("po : " +po);
		
		int result =0;
		result = pService.insertBasket(po,bWriter);
		System.out.println(result);
		
		
		if(result>0) {
			response.sendRedirect("list.po");

		}else {
			System.out.println("장바구니 실패");
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
