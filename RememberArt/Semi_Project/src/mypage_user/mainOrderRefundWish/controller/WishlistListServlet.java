package mypage_user.mainOrderRefundWish.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import mypage_user.mainOrderRefundWish.model.service.MorwService;
import mypage_user.mainOrderRefundWish.model.vo.Morw;
import product.model.vo.Attachment;

/**
 * Servlet implementation class WishlistListServlet
 */
@WebServlet("/Wishlist")
public class WishlistListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishlistListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션
		HttpSession session = request.getSession();
		Member loginUser =(Member) session.getAttribute("loginUser");
		System.out.println("loginUser 확인" +loginUser);
		
		
		MorwService mService = new MorwService();
		
		System.out.println("wishlist 서브릿 이동 확인");
	
		
		ArrayList<Morw> list = mService.wishlistList(loginUser.getUserId());
		System.out.println("리스트 사이즈 : "+list);
		
		ArrayList<Attachment> plist = mService.selectPList(loginUser.getUserId());
		
		for(int i = 0; i<plist.size(); i++) {
			System.out.println("서블렛 단의 plist" + plist.get(i));
		}
		
		RequestDispatcher view = null;
		
		if(!list.isEmpty()) {
			request.setAttribute("list", list);
			request.setAttribute("plist", plist);
			view = request.getRequestDispatcher("views/mypage_user/mypage_wishlist.jsp");
		}else {
			list = new ArrayList<Morw>();
			plist = new ArrayList<Attachment>();
			request.setAttribute("list", list);
			request.setAttribute("plist", plist);
			view = request.getRequestDispatcher("views/mypage_user/mypage_wishlist.jsp");
		}
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		// 화면에서 전달한 basket_no parameter를 받는다
		String param = request.getParameter("basket_no");
		
		// 기존에 (,)콤마로 분리한 String을 (,)콤마로 split 하여 배열로 생성
		String basketNoArr[] = param.split(",");
		
		// delete from table where no=1 or no=2 이런 형식으로 만들기 위한 문자열 변수
		String whereSQL = "";
		
		// 위에서 split한 문자 배열을 whereSQL을 만들기위해 반복문 실행
		for(int i=0; i<basketNoArr.length; i++) {
			// 기존 값이 비어있으면 WHERE 로 진행
			if("".equals(whereSQL)) {
				whereSQL = " WHERE BASKET_NO="+basketNoArr[i];
			}
			
			// 기존 값이 있으면 OR 로 분리하여 여러 건 삭제하기 위한 용도
			else {
				whereSQL += " OR BASKET_NO="+basketNoArr[i];
			}
		}
		MorwService mService = new MorwService();
		
		// 삭제 메소드 실행
		mService.wishlistDelete(whereSQL);
	}

}
