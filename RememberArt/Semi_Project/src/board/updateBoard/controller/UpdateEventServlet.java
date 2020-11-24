package board.updateBoard.controller;

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

import board.amateur.model.vo.Amateur;
import board.amateur.model.vo.FileManagement;
import board.updateBoard.model.service.UpdateService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateEventServlet
 */
@WebServlet("/update.am")
public class UpdateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEventServlet() {
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
		
		int updateNo = Integer.valueOf(request.getParameter("updateNo"));

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
		System.out.println("업데이트 될 파일:"+fileList);
		int result = new UpdateService().insertBoard(a,fileList,updateNo);
		
			System.out.println("파일 등록 완료");
			response.sendRedirect("list.am");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
