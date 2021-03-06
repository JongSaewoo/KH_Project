package mypage_artist.management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import artistapply.model.vo.Apply;
import board.notice.model.vo.PageInfo;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.service.ArtistService;
import mypage_artist.management.model.service.Mypage_ArtistService;
import mypage_artist.management.model.vo.Mypage_artist;
import product.model.service.ProductService;
import product.model.vo.Attachment;

/**
 * Servlet implementation class OMListServlet
 */
@WebServlet("/OM.list")
public class OMListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OMListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Mypage_ArtistService aService = new Mypage_ArtistService();
		
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
		String Writer = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		
		// 1_1. 게시판 리스트 갯수 구하기
				int listCount_OM = aService.getListCount_OM(bWriter);
				System.out.println(listCount_OM);
		// 페이지 수 처리용 변수 선언
				int currentPage;		// 현재 페이지를 저장할 변수
				int limit;					// 한 페이지에 보여질 게시글 수
				int maxPage;				// 전체 페이지의 맨 마지막 페이지
				int startPage;			// 한번에 표시될 페이지가 시작할 페이지
				int endPage;				// 한번에 표시될 페이지가 끝나는 페이지
		
				// * currentPage - 현재 페이지
				// 기본 게시판 페이지는 1페이지부터 시작
				currentPage = 0;
				// 하지만 페이지 전환시 전달받은 현재 페이지가 있을 시 해당 페이지를 currentPage로 적용
				if(request.getParameter("currentPage") != null) {
					currentPage = Integer.valueOf(request.getParameter("currentPage"));	
					// String -> int
				}else {
					currentPage = 1;
				}
				
				// * limit - 한 페이지에 보여질 목록 갯수
				limit = 10;
				
				// * maxPage - 총 페이지 수
				// 목록 수가 123개이면 페이지 수는 13페이지가 됨
				maxPage = (int)((double)listCount_OM/limit + 0.9);
				
				// * startPage - 현재 페이지에 보여질 시작 페이지 수
				//  아래쪽에 페이지 수가 10개씩 보여지게 할 경우
				//  1, 11, 21, 31, ...
				startPage = (((int)((double)currentPage/limit + 0.9))-1)*limit + 1;
				
				// * endPage - 현재 페이지에 보여질 마지막 페이지 수
				//  아래쪽에 페이지 수가 10개씩 보여지게 할 경우
				// 10, 20, 30, 40, ...
				endPage = startPage + limit - 1;
				
				
				// maxPage(총 페이지 수)가 endPage보다 작을 경우
				// ex) maxPage => 13이고 endPage => 20이면
				if(maxPage < endPage) {
					endPage = maxPage;
				}
				
				// 위에서 계산한 모든 페이지 관련 변수들을 PageInfo 클래스의
				// 객체에 담자.(PageInfo클래스를 만들자)
				PageInfo pi = new PageInfo(currentPage, listCount_OM, limit, maxPage,
						                   startPage, endPage);
				
				// 1_2. 게시판 리스트 조회해오기
				ArrayList<Mypage_artist> OM_list = aService.selectList_OM(currentPage, limit, bWriter);
				for(int i = 0 ; i < OM_list.size(); i++) {
					System.out.println("dddd"+OM_list.get(i));
				}
				System.out.println(OM_list);
				ProductService pService = new ProductService();
				ArrayList<Attachment> alist = pService.selectAList(currentPage, limit);
//				 프로필 사진 불러오기
				ArtistService aService1 = new ArtistService();
				
				Apply aphoto = aService1.selectPhoto(Writer);
				System.out.println("aphoto : " + aphoto);
				
				// 출력이 잘 나오는걸 확인하면 이제 화면단으로 넘겨주자
				
				RequestDispatcher view = null;
				if(!OM_list.isEmpty()) {
					view = request.getRequestDispatcher("views/mypage_artist/order_management.jsp");
					request.setAttribute("OM_list", OM_list);
					request.setAttribute("aphoto", aphoto);
					request.setAttribute("alist", alist);
					request.setAttribute("pi", pi);
				}else {
					view = request.getRequestDispatcher("views/common/errorPage.jsp");
					request.setAttribute("msg","게시판 조회 실패");
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
