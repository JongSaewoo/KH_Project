package artistapply.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import artistapply.model.service.ApplyService;
import artistapply.model.vo.Apply;
import artistapply.model.vo.Career;
import member.model.vo.Member;
import mypage_artist.RefundQnACard.model.service.ArtistService;
import mypage_artist.RefundQnACard.model.vo.PageInfo;
import mypage_artist.RefundQnACard.model.vo.QnA_Q;
import product.model.vo.Attachment;

/**
 * Servlet implementation class UpdateApplyServlet
 */
@WebServlet("/update.ap")
public class UpdateApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인세션
		request.setCharacterEncoding("utf-8");
		
		int maxSize = 1024 * 1024 * 10;
		
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println(root);
		
		String savePath = root + "apply_uploadFiles/";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		// 저장될 파일 이름
		ArrayList<String> saveFiles = new ArrayList<>();
		
		// 원본 파일 이름
		Apply ap = new Apply();
		
		// 폼에서 전송된 파일의 파일 이름을 가져옴
		Enumeration<String> files = multiRequest.getFileNames();
		
		// 이력 사항 파일 이름 저장
		String name = files.nextElement();
		System.out.println(name);
		String carfile = multiRequest.getFilesystemName(name);
		
		// 작가 프로필 이미지 파일 이름 저장
		name = files.nextElement();
		System.out.println(name);
		String artistpro = multiRequest.getFilesystemName(name);
		
		
		String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserId(); // 작성자 아이디
		String introduce = multiRequest.getParameter("introduce");	// 작가 소개
		String etc = multiRequest.getParameter("etc"); // 기타 이력
		
		String term1 = multiRequest.getParameter("career-t1");	// 경력 기간1
		String career1 = multiRequest.getParameter("career1");	// 경력 기간1
		
		String term2 = multiRequest.getParameter("career-t2");	// 경력 사항2
		String career2 = multiRequest.getParameter("career2");	// 경력 기간2
		
		String term3 = multiRequest.getParameter("career-t3");	// 경력 사항3
		String career3 = multiRequest.getParameter("career3");	// 경력 기간3
		
		// 경력 사항 한줄씩 담기
		ArrayList<Career> carlist = new ArrayList<>();	
		carlist.add(new Career(writer, career1, term1));
		carlist.add(new Career(writer, career2, term2));
		carlist.add(new Career(writer, career3, term3));
		
		// 경력 사항을 제외한 입력값과 첨부파일 이름을 apply 객체에 담기
		Apply apply = new Apply(writer, introduce, etc, carfile, artistpro);
		
		// 입력값과 파일 이름이 담긴 apply 객체와 경력사항이 담긴 객체 배 carlist를 service로 보내기
		// result는 조건처리를 위하여 int로 받음
		int result = new ApplyService().updateApply(apply, carlist);
		
		if(result>0) {
	    	System.out.println("성공");
	    }else {
	    	System.out.println("실패");
	    
	    	File failedFile1 = new File(savePath + carfile);
	    	failedFile1.delete();
	    	
	    	File failedFile2 = new File(savePath + artistpro);
	    	failedFile2.delete();
	    	
	    	
	    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
