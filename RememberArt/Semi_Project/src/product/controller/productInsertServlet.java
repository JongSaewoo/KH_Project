package product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Attachment;
import product.model.vo.product;

/**
 * Servlet implementation class productInsertServlet
 */
@WebServlet("/insert.th")
public class productInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public productInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");

		int maxSize = 1024 * 1024 * 10; // 10Mbyte

		// 경로 추출
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println(root);

		// 파일 저장경로
		String savePath = root + "thumbnail_uploadFiles/";
		System.out.println(savePath);

		// 파일명 변경 아직하지않음
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		// 저장파일 이름
		ArrayList<String> saveFiles = new ArrayList<>();

		// getFileNames() = 폼에서 전송된 파일 리스트들의 name들을 반환한다.
		Enumeration<String> files = multiRequest.getFileNames();

		while (files.hasMoreElements()) {

			String name = files.nextElement();

			if (multiRequest.getFilesystemName(name) != null) {
				saveFiles.add(multiRequest.getOriginalFileName(name));
			}
		}

		// 파일외에 것들 변수 저장 작품명 작가명 테마 제작년도 가격 태그 대표이미지
		String pname = multiRequest.getParameter("pname");
		String aname = multiRequest.getParameter("aname");
		String thema = multiRequest.getParameter("select-thema");
		String year = multiRequest.getParameter("year");
		int price = Integer.valueOf(multiRequest.getParameter("price"));
		String paintInt = multiRequest.getParameter("paint_int");
		int size = Integer.valueOf(multiRequest.getParameter("size")); //사이즈 (호수)
//		String bWriter = String.valueOf(((Member) request.getSession().getAttribute("loginUser")).getUserNo());
		String bWriter = (((Member) request.getSession().getAttribute("loginUser")).getUserId());
		System.out.println(bWriter);
		// 태그는 여러개를 받아야함.
		/*
		 * String[] irr = multiRequest.getParameterValues("tagname");
		 * 
		 * String tagname = "";
		 */

		/*
		 * if (irr != null) { tagname = String.join(",", irr); } // 태그는 한번 갔다온후 작품번호로
		 * 다시한번 보내서 저장 System.out.println(tagname);
		 */

		product p = new product(pname, aname, thema, year, price, paintInt, bWriter,size);

		ArrayList<Attachment> fileList = new ArrayList<>();

		for (int i = saveFiles.size() - 1; i >= 0; i--) { // Enumberation결과가 뒤집어져 있으므로 다시 뒤집어서 fileList에 쌓자
			Attachment at = new Attachment();
			at.setFilePath(savePath);
			at.setSavefileName(saveFiles.get(i));

			// 대표 이미지가 originFiles에서의 마지막 인덱스로 하기 위해서 조건 처리를 하자
			if (i == saveFiles.size() -3) {
				at.setFileLevel(0);
			} else {
				at.setFileLevel(1);
			}

			fileList.add(at);
		}
		int result = new ProductService().insertProduct(p, fileList);

		if (result > 0) {
			response.sendRedirect("PM.list");		//상품목록 서블릿으로 이동
		} else {
			for (int i = 0; i < saveFiles.size(); i++) {
				File failedFile = new File(savePath + saveFiles.get(i));
				failedFile.delete();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
