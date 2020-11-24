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

import artistapply.model.vo.Apply;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.service.ArtistService;
import mypage_artist.RefundQnACard.model.vo.BuyList_a;
import mypage_artist.RefundQnACard.model.vo.PageInfo;
import mypage_artist.RefundQnACard.model.vo.QnA_Q;
import product.model.vo.Attachment;

/**
 * Servlet implementation class ArtistQnAListServlet
 */
@WebServlet("/list.qna")
public class ArtistQnAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistQnAListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//로그인세션
				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginUser");
				String artistId = loginMember.getUserId();
				
				ArtistService aService = new ArtistService();
				
				// 작가 이름 가져오기
				String name = (((Member) request.getSession().getAttribute("loginUser")).getUserName());
				System.out.println(name);
				
			
				int listCount = aService.getQnAListCount(name);
				
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
				maxPage = (int)((double)listCount/limit + 0.9);
				
				// * startPage - 현재 페이지에 보여질 시작 페이지 수
				startPage = (((int)((double)currentPage/limit + 0.9))-1)*limit + 1;
				
				// * endPage - 현재 페이지에 보여질 마지막 페이지 수
				endPage = startPage + limit - 1;
				
				// maxPage(총 페이지 수)가 endPage 보다 작을 경우
				if(maxPage < endPage) {
					endPage = maxPage;
				}
				
				// 위에서 계산한 모든 페이지 관련 변수들을 PageInfo 클래스의
				// 객체에 담자.(PageInfo클래스를 만들자)
				PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage,
						                   startPage, endPage);
				
				// qna 게시판 리스트 조회해오기
				
				ArrayList<QnA_Q> qnalist = aService.selectQnAList(name, currentPage, limit);
				for(int i = 0 ; i < qnalist.size(); i++) {
					System.out.println(qnalist.get(i));
				}
				
				ArrayList<Attachment> alist = aService.selectQpList(name);
				
				for(int i = 0 ; i <alist.size(); i++) {
					System.out.println(alist.get(i));
				}
				
				// 프로필 사진 불러오기
				Apply aphoto = aService.selectPhoto(artistId);
				System.out.println(aphoto);
				
				// 출력이 잘 나오는걸 확인하면 이제 화면단으로 넘겨주자
				
				RequestDispatcher view = null;
//				if(!qnalist.isEmpty()) {
					view = request.getRequestDispatcher("views/mypage_artist/art-qna.jsp");
					request.setAttribute("qnalist", qnalist);
					request.setAttribute("alist", alist);
					request.setAttribute("pi", pi);
					request.setAttribute("aphoto", aphoto);
//				}else {
//					view = request.getRequestDispatcher("views/common/errorPage.jsp");
//				    request.setAttribute("msg","문의 받은 내역이 없습니다.");
//				}
				
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
