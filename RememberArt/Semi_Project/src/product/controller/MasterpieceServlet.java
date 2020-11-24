package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import board.amateur.model.vo.Reply;
import member.model.vo.Member;
import product.model.service.ProductService;

/**
 * Servlet implementation class MasterpieceServlet
 */
@WebServlet("/masterpiece.po")
public class MasterpieceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterpieceServlet() {
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
		String heartYN = request.getParameter("heartYN");
		
		System.out.println(bWriter);
		System.out.println(paint_no);
		System.out.println(heartYN);
		if(heartYN.equals("Y")) {
			int result = pService.insertmasterpiece(bWriter,paint_no);
		
			System.out.println(result);
		}else if(heartYN.equals("N")) {
			int result = pService.deletemasterpiece(bWriter,paint_no);
			
			System.out.println(result);	
		}
		
		int count = 0;
		count = pService.selectMasterpiece2(paint_no);
		
		
		System.out.println("좋아요 갯수" + count);

		
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
