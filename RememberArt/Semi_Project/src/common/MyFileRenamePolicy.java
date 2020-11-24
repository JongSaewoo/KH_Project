package	common;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {
				// 파일을 업로드 한 시간대로 파일명이 재작성 되도록 만들자.
				// Date형을 만들기 위한 long형의 매개변수 생성
				long currentTime = System.currentTimeMillis();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				
				// 초까지를 해도 겹칠 경우를 대비해서 랜덤값도 생성해서 붙이자.
				int ranNum = (int)(Math.random()*100000);	// 0~99999 랜덤수 생성
				
				// 확장자 명 가져오기(사용자가 올린 파일의 확장자 추출)
				String name = originFile.getName(); // 원본 파일명(확장자 포함)
				
				int dot = name.lastIndexOf(".");
				// abc.jpg -> .은 3번 인덱스에 있다.
				// abc -> .이 없으므로 -1을 반환
				
				String ext = null; 	// 확장자를 담을 String변수
				
				if(dot != -1) {		// 확장자가 있다면..
					// dot 포함해서 확장자 부분 추출
					ext = name.substring(dot);
					
				}else {				// 확장자가 없다면..
					// 확장자가 없는 상태
					ext = "";
				}
				
				String fileName = sdf.format(new Date(currentTime)) + ranNum + ext;
				
				File newFile = new File(originFile.getParent(), fileName);
				
				return newFile;
	}

}
