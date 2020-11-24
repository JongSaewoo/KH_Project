package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Paint_QnA;
import product.model.vo.product;

/**
 * Servlet implementation class InsertAServlet
 */
@WebServlet("/insertA.po")
public class InsertAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductService pService =new ProductService();
		System.out.println("잘왔어?");
		//String writer = (((Member) request.getSession().getAttribute("loginUser")).getUserId());//로그인한사람.
		int qna_no = Integer.parseInt(request.getParameter("qna_no"));							//qna번호
		String content = request.getParameter("content");
		int paint_no = Integer.parseInt(request.getParameter("paint_no"));	
		
		//System.out.println(writer);
		System.out.println(qna_no);
		System.out.println(content);
		int result =0;
		int result2 =0;
		
		product p = pService.selectpaint(paint_no);
		Member m = (Member) request.getSession().getAttribute("loginUser");
		
		if(m.getUserName().equals(p.getArtist_name())) {
			result = pService.insertAnwser(qna_no,content);
			result2 = pService.updateQ(qna_no);
			System.out.println(result);
			System.out.println(result2);
			Paint_QnA qna2 = pService.selectP2(qna_no);
			
			
			
			JSONObject rListObj = new JSONObject();
			
			//rListObj.put("pq_no", qna2.getPq_no());
			rListObj.put("panswer", qna2.getPanswer());
			rListObj.put("pa_date", qna2.getPq_date());
			response.setContentType("application/json; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.print(rListObj);
			out.flush();
			out.close();
		}
		else {
			JSONObject rListObj2 = null;
			response.setContentType("application/json; charset=utf-8");
			
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
