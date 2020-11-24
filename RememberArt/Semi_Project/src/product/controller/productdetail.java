package product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import artistapply.model.vo.Apply;
import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Attachment;
import product.model.vo.MasterpieceCount;
import product.model.vo.Paint_QnA;
import product.model.vo.masterpiece;
import product.model.vo.product;

/**
 * Servlet implementation class productdetail
 */
@WebServlet("/detail.po")
public class productdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productdetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ProductService pService = new ProductService();
		
		int paint_no = Integer.valueOf(request.getParameter("paint_no")); //작품번호
		System.out.println(paint_no);
		
		product plist = new product();
		plist = pService.selectsearch(paint_no);
		
		Apply apply = new Apply();
		apply = pService.selectApply(plist);
		System.out.println(apply);
		
		System.out.println("plist" + plist);
		ArrayList<Attachment> alist = new ArrayList<Attachment>();
		
		alist = pService.selectAttachment(paint_no);	
		System.out.println(alist);
		
		ArrayList<product> sizelist = new ArrayList<product>();
		
		sizelist = pService.selectsize();
		
		System.out.println(sizelist);
		String bWriter ="";
		if(((Member) request.getSession().getAttribute("loginUser")) != null){
		bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserId()); //아이디
		}
		
		System.out.println(bWriter);
		
		masterpiece mp = new masterpiece();
		if(bWriter != null) {
		mp = pService.selectMasterpiece(paint_no,bWriter);
		}
		System.out.println(mp);
		
		MasterpieceCount mc = new MasterpieceCount();
		mc = pService.countMasterpiece(paint_no);
		
		Paint_QnA qa = new Paint_QnA();

		ArrayList<Paint_QnA> qna = new ArrayList<Paint_QnA>();
		
		qna = pService.selectQ(paint_no);
		
		System.out.println(qna);
		
		ArrayList<Paint_QnA> qna2 = new ArrayList<Paint_QnA>();
		
		qna2 = pService.selectP();
		System.out.println(qna2);
		
		//QNA Q A 받아옴
		
		RequestDispatcher view = null;
		
		if(plist != null && alist != null && !sizelist.isEmpty()) {
			view = request.getRequestDispatcher("views/product/productdetail.jsp");
			
			request.setAttribute("plist", plist);
			request.setAttribute("alist", alist);
			request.setAttribute("sizelist", sizelist);
			request.setAttribute("aplly", apply);
			request.setAttribute("mp",mp);
			request.setAttribute("mc", mc);
			request.setAttribute("qna", qna);
			request.setAttribute("qna2", qna2);
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
