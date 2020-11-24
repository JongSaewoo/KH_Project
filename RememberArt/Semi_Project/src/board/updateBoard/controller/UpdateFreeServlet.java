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

import board.amateur.model.vo.FileManagement;
import board.updateBoard.model.service.UpdateService;
import board.free.model.service.FreeService;
import board.free.model.vo.Free;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateFreeServlet
 */
@WebServlet("/update.ee")
public class UpdateFreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFreeServlet() {
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
		String savePath = root+"thumbnail_uploadFiles/free/";
		
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

		String free_title = multiRequest.getParameter("free_title");
		String free_content = multiRequest.getParameter("free_content");
		String user = String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserName());

		System.out.println("[servlet] insert 정보 출력_제목:"+free_title);
		System.out.println("[servlet] insert 정보 출력_작성자:"+user);
		System.out.println("[servlet] insert 정보 출력_내용:"+free_content);
		

		ArrayList<FileManagement> fileList = new ArrayList<>();
		
		for(int i = saveFiles.size()-1; i>=0; i--) {
			FileManagement fm = new FileManagement();
			fm.setEvent_path(savePath);
			fm.setEvent_file(saveFiles.get(i));
			
			fileList.add(fm);
		}
		
		String filePath = fileList.get(0).getEvent_file();
		Free f = new Free(user, free_title, free_content, filePath);
		int result = new UpdateService().insertBoard(f,updateNo);
		
		response.sendRedirect("list.ee");

		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);

	}

}
