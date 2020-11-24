package board.free.controller;

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

import board.free.model.service.FreeService;
import board.free.model.vo.Reply;
import member.model.vo.Member;

/**
 * Servlet implementation class FreeInsertReplyServlet
 */
@WebServlet("/insertReply.ee")
public class FreeInsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeInsertReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writer = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		int bid = Integer.parseInt(request.getParameter("bid"));	//게시글번호
		String content = request.getParameter("content");			//내용
		
		System.out.println("에이작스 시이작");
		System.out.println("writer:"+writer);
		System.out.println("bid:"+bid);
		System.out.println("content:"+content);
		
		Reply r = new Reply(bid, writer, content);
		
		ArrayList<Reply> rList = new FreeService().insertReply(r);
		System.out.println("[자유게시판]댓글 등록 rList:"+rList);

		JSONArray rListArray = new JSONArray();
		JSONObject rListObj = null;
		
		for(Reply user : rList) {
			rListObj = new JSONObject();
			
			java.util.Date createDate = new java.util.Date(user.getFree_r_date().getTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String cDate = sdf.format(createDate);
			

			rListObj.put("reply_no", user.getFree_r_no());
			rListObj.put("reply", user.getFree_r_content());
			rListObj.put("event_no", user.getFree_no());
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
