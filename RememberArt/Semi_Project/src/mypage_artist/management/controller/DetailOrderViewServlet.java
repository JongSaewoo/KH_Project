package mypage_artist.management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import mypage_artist.management.model.service.Mypage_ArtistService;
import mypage_artist.management.model.vo.Mypage_artist;

/**
 * Servlet implementation class DetailOrderViewServlet
 */
@WebServlet("/DO.view")
public class DetailOrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailOrderViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Mypage_ArtistService aService = new Mypage_ArtistService();
		
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
		int order_no = Integer.valueOf(request.getParameter("order_no"));
		
		System.out.println("주문번호" +order_no);
		
		ArrayList<Mypage_artist> DOV_plist = aService.selectList_DOV_P(bWriter, order_no);
		ArrayList<Mypage_artist> DOV_dlist = aService.selectList_DOV_D(bWriter, order_no);
		ArrayList<Mypage_artist> DOV_slist = aService.selectList_DOV_S(bWriter, order_no);
//		for(int i = 0 ; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		// 프로필 사진 불러오기
//		ArtistService aService1 = new ArtistService();
//		
//		Apply aphoto = aService1.selectPhoto(bWriter);
//		System.out.println(aphoto);
		
		
		// 출력이 잘 나오는걸 확인하면 이제 화면단으로 넘겨주자
		
		RequestDispatcher view = null;
		if(!DOV_plist.isEmpty() && !DOV_dlist.isEmpty()) {
			view = request.getRequestDispatcher("views/mypage_artist/detail_order_view.jsp");
			request.setAttribute("DOV_plist", DOV_plist);
			request.setAttribute("DOV_dlist", DOV_dlist);
			request.setAttribute("DOV_slist", DOV_slist);
		}else {
			view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg","상세정보 조회 실패");
		}
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
