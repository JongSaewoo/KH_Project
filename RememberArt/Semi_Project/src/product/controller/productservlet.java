package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.model.vo.PageInfo;
import product.model.service.ProductService;
import product.model.vo.Attachment;
import product.model.vo.product;

/**
 * Servlet implementation class product
 */
@WebServlet("/list.po")
public class productservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService pService = new ProductService();
		
		int listCount = pService.getListCount();
		
		int currentPage;		//현재 페이지를 저장할 변수
		int limit; 				//한 페이지에 보여질 게시글 수
		int maxPage;			//전체 페이지의 맨 마지막 페이지
		int startPage;			//한번에 표시될 페이지가 시작할 페이지
		int endPage;			//한번에 표시될 페이지가 끝나는 페이지
		
		currentPage = 0;
		// 하지만 페이지 전환시 전달받은 현재 페이지가 있을 시 해당 페이지를 currentPage로 적용
		if(request.getParameter("currentPage")!= null) {
			currentPage = Integer.valueOf(request.getParameter("currentPage")); 
		}else {
			currentPage=1;
		}
		
		limit=4;
		                            
		maxPage = (int)((double)listCount/limit + 0.75);
		
		startPage = (((int)((double)currentPage/limit+0.75))-1)*limit+1;
		
		endPage = startPage+limit-1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
 
		
		PageInfo pi = new PageInfo(currentPage,listCount,limit,maxPage,startPage,endPage);

		ArrayList<product> list = pService.selectList(currentPage, limit);
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		ArrayList<Attachment> alist = pService.selectAList(currentPage, limit);
		
		for(int i = 0;i<alist.size();i++) {
			System.out.println(alist.get(i));
		}
		
		RequestDispatcher view = null;
		if(!list.isEmpty()) {
			view = request.getRequestDispatcher("views/product/product.jsp");
			
			request.setAttribute("alist", alist);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			System.out.println("상품내용 조회 실패");
		}
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
