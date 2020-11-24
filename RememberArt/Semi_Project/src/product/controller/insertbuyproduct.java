package product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import product.model.service.ProductService;

/**
 * Servlet implementation class insertbuyproduct
 */
@WebServlet("/insert.po")
public class insertbuyproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertbuyproduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ProductService pService = new ProductService();
		
		//작품번호
		int paint_no = Integer.valueOf(request.getParameter("paint_no"));
		//아이디
		String orderid = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		//주문하시는분
		String ordername = request.getParameter("ordername");
		String orderphone = request.getParameter("orderphone");
		String orderEmail = request.getParameter("orderEmail");
		String orderadress1 =request.getParameter("orderadress1");
		String orderadress2 = request.getParameter("orderadress2");
		String orderadress3 = request.getParameter("orderadress3");
		
		String orderadress = orderadress1 + " " + orderadress2 + " " + orderadress3;

		//받으시는분
		
		String receivename = request.getParameter("receivename");
		String receivephone = request.getParameter("receivephone");
		String receiveaddress1 = request.getParameter("receiveaddress1");
		String receiveaddress2 = request.getParameter("receiveaddress2");
		String receiveaddress3 = request.getParameter("receiveaddress3");
		String receiveaddress = receiveaddress1 + " " + receiveaddress2 + " " + receiveaddress3;
		String receivecontent = request.getParameter("receivecontent");
		
		//주문방법
		String orderrule = request.getParameter("orderrule");
		
		System.out.println(orderid);
		System.out.println(paint_no);
		System.out.println(ordername);
		System.out.println(orderphone);
		System.out.println(orderadress);
		System.out.println(receivename);
		System.out.println(receivephone);
		System.out.println(receiveaddress);
		System.out.println(orderrule);
		
		int result = new ProductService().insertorder(paint_no,orderid,ordername, orderphone, orderEmail, orderadress,
														receivename,receivephone,receiveaddress,orderrule,receivecontent);
		
		
		// 자신의 장바구니에서 . paint_no, user_id -> buy_yn -> y 변경 
		int result2 = pService.updateBasket(paint_no,orderid);
		
		  if (result > 0){ 
			  response.sendRedirect("Mo.li");
			  }
		  else {
		  System.out.println("실패");
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
