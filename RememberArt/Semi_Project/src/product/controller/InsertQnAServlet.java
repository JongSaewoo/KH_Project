package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import board.amateur.model.vo.Reply;
import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Paint_QnA;

/**
 * Servlet implementation class InsertQnAServlet
 */
@WebServlet("/insertQnA.po")
public class InsertQnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertQnAServlet() {
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
		String writer = (((Member) request.getSession().getAttribute("loginUser")).getUserId());			//댓글 작성자
		int paint_no = Integer.parseInt(request.getParameter("paint_no"));	//게시글번호
		String content = request.getParameter("content");
		
		System.out.println("writer:"+writer);
		System.out.println("paint_no:"+paint_no);
		System.out.println("content:"+content);
		
		Paint_QnA qna0 = new Paint_QnA();
		int result = pService.insertQuestion(writer,paint_no,content);
		
		ArrayList<Paint_QnA> qna = pService.selectQ(paint_no);
		
		//ArrayList<Paint_QnA> qna2 = pService.selectP();
		System.out.println(qna);
		JSONArray rListArray = new JSONArray();
		JSONObject rListObj = null;
		
		for(Paint_QnA user : qna) {
			rListObj = new JSONObject();
			

			rListObj.put("pq_no", user.getPq_no());
			rListObj.put("pqusetion", user.getPqusetion());
			rListObj.put("pq_date", user.getPq_date());
			rListObj.put("paint_no", user.getPaint_no());
			rListObj.put("user_id", user.getUser_id());
			rListObj.put("pq_yn", user.getPq_yn());
			
			rListArray.add(rListObj);
		}
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(rListArray);
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
