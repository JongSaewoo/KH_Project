package board.amateur.controller;

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

import board.amateur.model.service.AmateurService;
import board.amateur.model.vo.Reply;

/**
 * Servlet implementation class InsertReplyServlet
 */
@WebServlet("/insertReply.am")
public class InsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writer = request.getParameter("writer");				//댓글 작성자
		int bid = Integer.parseInt(request.getParameter("bid"));	//게시글번호
		String content = request.getParameter("content");			//내용
		
		System.out.println("writer:"+writer);
		System.out.println("bid:"+bid);
		System.out.println("content:"+content);
		
		Reply r = new Reply(bid,content,writer);
		
		
		ArrayList<Reply> rList = new AmateurService().insertReply(r);
		System.out.println("[InsertReplyServlet]댓글 등록 rList:"+rList);
		JSONArray rListArray = new JSONArray();
		JSONObject rListObj = null;
		
		for(Reply user : rList) {
			rListObj = new JSONObject();
			
			java.util.Date createDate = new java.util.Date(user.getReply_date().getTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String cDate = sdf.format(createDate);
			

			rListObj.put("reply_no", user.getReply_no());
			rListObj.put("reply", user.getReply());
			rListObj.put("event_no", user.getEvent_no());
			rListObj.put("user_id", user.getUser_id());
			rListObj.put("reply_date", cDate);
			
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
