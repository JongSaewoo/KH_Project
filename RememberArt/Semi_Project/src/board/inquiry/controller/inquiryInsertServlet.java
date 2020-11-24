package board.inquiry.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.inquiry.model.service.InquiryService;
import board.inquiry.model.vo.Inquiry;

import board.notice.model.vo.PageInfo;

/**
 * Servlet implementation class inquiryInsertServlet
 */
@WebServlet("/insert.in")
public class inquiryInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inquiryInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		InquiryService inService = new InquiryService();


		String writer = request.getParameter("writer");
		String q_cate = request.getParameter("q_cate");
		String question_title = request.getParameter("question_title");
		String question = request.getParameter("question");
		
//		System.out.println("[servlet] writer:"+writer);
//		System.out.println("[servlet] q_cate:"+q_cate);
//		System.out.println("[servlet] question_title:"+question_title);
//		System.out.println("[servlet] question"+question);
		
		int listCount = inService.getListCount();
		
		Inquiry in = new Inquiry(writer,q_cate,question_title,question);
		
		int currentPage;		// 현재 페이지를 저장할 변수
		int limit;				// 한 페이지에 보여질 게시글 수
		int maxPage;			// 전체 페이지의 맨 마지막 페이지
		int startPage;			// 한번에 표시될 페이지가 시작할 페이지
		int endPage;			// 한번에 표시될 페이지가 끝나는 페이지
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));	
		}
		
//		System.out.println("currentPage:"+currentPage);
		limit = 10;
		maxPage = (int)((double)listCount/limit+0.9);
		startPage = (((int)((double)currentPage/limit+0.9))-1)*limit+1;
		endPage = startPage+limit-1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage,startPage, endPage);

	////////////////////////
		
		
		ArrayList<Inquiry> inList = new InquiryService().insertQuestion(in, currentPage, limit);
		
		
		JSONArray inListArray = new JSONArray();
		JSONObject inListObj = null;
		
		for(Inquiry user : inList) {
			inListObj = new JSONObject();
			
			java.util.Date createDate = new java.util.Date(user.getQ_date().getTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String cDate = sdf.format(createDate);
			

			inListObj.put("q_no", user.getQ_no());
			inListObj.put("user_id",user.getUser_id());
			inListObj.put("q_cate", user.getQ_cate());
			inListObj.put("q_date", cDate);
			inListObj.put("question", user.getQuestion());
			inListObj.put("q_yn", user.getQ_yn());
			inListObj.put("question_title", user.getQuestion_title());
			
			
			
			inListArray.add(inListObj);
		}
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(inListArray);
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
