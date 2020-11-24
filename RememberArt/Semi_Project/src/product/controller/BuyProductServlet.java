package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.Attachment;
import product.model.vo.product;

/**
 * Servlet implementation class BuyProductServlet
 */
@WebServlet("/Buy.po")
public class BuyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ProductService pService = new ProductService();

		
		int paint_no = Integer.valueOf(request.getParameter("paint_no"));//paint_no 가져옴.

		System.out.println(paint_no);
		
		product po = new product();
		po =  pService.selectsearch(paint_no);
		System.out.println(po);
		ArrayList<Attachment> at = new ArrayList<Attachment>();
		
		at = pService.selectAttachment(paint_no);
		System.out.println(at);
		

		RequestDispatcher view = null;
		
		if(po !=null) {
			view = request.getRequestDispatcher("views/product/productpay.jsp");
				request.setAttribute("po", po);
				request.setAttribute("at", at);
				System.out.println("가랏");
			
		}else {
		System.out.println("불러오기 실패");	
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
	}

}
