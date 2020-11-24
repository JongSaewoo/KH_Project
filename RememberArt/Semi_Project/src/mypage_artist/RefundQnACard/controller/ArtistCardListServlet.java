package mypage_artist.RefundQnACard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import artistapply.model.service.ApplyService;
import artistapply.model.vo.Apply;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.service.ArtistService;
import mypage_artist.RefundQnACard.model.vo.Message;
import mypage_artist.RefundQnACard.model.vo.PageInfo;
import product.model.vo.Attachment;

/**
 * Servlet implementation class ArtistCardListServlet
 */
@WebServlet("/list.ac")
public class ArtistCardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistCardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				//로그인세션
				HttpSession session = request.getSession();
//				System.out.println(loginMember);
//				Member loginMember = (Member) session.getAttribute("loginUser");
				Member artist = (Member) session.getAttribute("loginUser");
				String artistId = artist.getUserId();
				
				ArtistService aService = new ArtistService();
				
				// 작가 이름 가져오기
				String name = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
				System.out.println(name);
				
				
				int listCount = aService.getCardListCount(name);
				
				// ---------- 페이징 처리 추가 ---------------
				int currentPage;		// 현재 페이지를 저장할 변수
				int limit;				// 한 페이지에 보여질 게시글 수
				int maxPage;			// 전체 페이지의 맨 마지막 페이지
				int startPage;			// 한번에 표시될 페이지가 시작할 페이지
				int endPage;			// 한번에 표시될 페이지가 끝나는 페이지
				
				currentPage = 0;
				// 하지만 페이지 전환시 전달받은 현재 페이지가 있을 시 해당 페이지를 currentPage로 적용
				if(request.getParameter("currentPage") != null) {
					currentPage = Integer.valueOf(request.getParameter("currentPage"));	
					// String -> int
				}else {
					currentPage = 1;
				}
				
				// * limit - 한 페이지에 보여질 목록 갯수
				limit = 5;
				
				// * maxPage - 총 페이지 수
				// 목록 수가 123개이면 페이지 수는 13페이지가 됨
				maxPage = (int)((double)listCount/limit + 0.9);
				
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
				
				// 위에서 계산한 모든 페이지 관련 변수들을 PageInfo 클래스의 객체에 담자(PageInfo클래스를 만들자)
				PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage,
						                   startPage, endPage);
				
				// 감동 메세지 게시판 리스트 조회해오기
				ArrayList<Message> mlist = aService.selectCardList(name, currentPage, limit);
				for(int i = 0 ; i < mlist.size(); i++) {
					System.out.println("섭,ㄹㄹ;ㅅ"+mlist.get(i));
				}
				
				ArrayList<Attachment> alist = aService.selectCAList(name);
				for(int i = 0 ; i <alist.size(); i++) {
					System.out.println(alist.get(i));
				}
				
				// 프로필 사진 불러오기
				Apply aphoto = aService.selectPhoto(artistId);
				System.out.println("프로필 사진 : " + aphoto);
				
				// 화면단으로 넘겨주기
				RequestDispatcher view = null;
//				if(!mlist.isEmpty()) {
					view = request.getRequestDispatcher("views/mypage_artist/art-cardlist.jsp");
					request.setAttribute("mlist", mlist);
					request.setAttribute("alist", alist);
					request.setAttribute("pi", pi);
					request.setAttribute("aphoto", aphoto);
//				}else {
//					view = request.getRequestDispatcher("views/common/errorPage.jsp");
//				    request.setAttribute("msg","받은 카드가 없습니다.");
//				}
//				
				view.forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		// 화면에서 값을 전달받음
		String message = request.getParameter("order_no");
		// 확인 
		System.out.println(message);
		
		ArtistService aService = new ArtistService();
		
		// 가져온 값을 나눔
		String[] messageArray = message.split(",");
		// 삭제 메소드 실행
		aService.deleteCard(messageArray);
		
	}

}
