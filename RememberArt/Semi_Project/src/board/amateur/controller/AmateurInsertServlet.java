package board.amateur.controller;

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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.amateur.model.service.AmateurService;
import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import board.free.model.service.FreeService;
import member.model.vo.Member;

/**
 * Servlet implementation class AmateurInsertServlet
 */
@WebServlet("/insert.am")
public class AmateurInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmateurInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int maxSize = 1024 * 1024 * 10;
		
		//경로 추출 후 파일 저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root+"thumbnail_uploadFiles/amateur/";
		
		MultipartRequest multiRequest = new MultipartRequest(request,savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		ArrayList<String> saveFiles = new ArrayList<>();
		
		Enumeration<String> files = multiRequest.getFileNames();
		
		while(files.hasMoreElements()) {
			String name=files.nextElement();
			
			if(multiRequest.getFilesystemName(name)!=null) {
				saveFiles.add(multiRequest.getFilesystemName(name));
			}
		}
		
		
		String event_title = multiRequest.getParameter("event_title");
		String event = multiRequest.getParameter("event");
		String user = String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserName());

		System.out.println("[servlet] insert 정보 출력_제목:"+event_title);
		System.out.println("[servlet] insert 정보 출력_작성자:"+user);
		System.out.println("[servlet] insert 정보 출력_내용:"+event);
		
		Amateur a = new Amateur(event_title, event,user);
		
		ArrayList<FileManagement> fileList = new ArrayList<>();
		
		for(int i=saveFiles.size()-1; i>=0;i--) {
			FileManagement fm = new FileManagement();
			fm.setEvent_path(savePath);
			fm.setEvent_file(saveFiles.get(i));
			
			fileList.add(fm);
		}
		int result = new AmateurService().insertBoard(a,fileList);
		
		if(result>0) {
			System.out.println("파일 등록 완료");
			response.sendRedirect("list.am");
		}else {
			for(int i =0;i<saveFiles.size();i++) {
				File failedFile = new File(savePath+saveFiles.get(i));
				failedFile.delete();
			}
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
