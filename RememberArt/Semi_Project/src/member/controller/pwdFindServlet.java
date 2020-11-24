package member.controller;

import java.io.IOException;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/pwdFind.me")
public class pwdFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwdFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 硫붿씪 愿��젴 �젙蹂�
        String host = "smtp.naver.com";
        
        //寃��깋�븯�젮�뒗 �궗�엺�쓽 �븘�씠�뵒
        String id = request.getParameter("findId");
        String recipient = request.getParameter("findEmail");
//        System.out.println("id : " + id + ", email : " + recipient);	// 媛믪씠 �옒 �꽆�뼱�솕�뒗吏� �솗�씤
        
        // �슦由� �궗�씠�듃�뿉�꽌 蹂대궪 �븣 �벐�뒗 硫붿씪濡� 怨좎젙�빐�꽌 �벖�떎.
        final String username = "xowhtjdghkd@naver.com";
        final String password = "say140802@1**"; //"khtest01"; 
        int port=465;
         
   
        String subject = "由щħ踰� �븘�듃�뿉�꽌 �깉濡쒖슫 鍮꾨쾲�쓣  �깮�꽦�빐 諛쒖넚�빀�땲�떎.";
    
        String body = null;
        body = "�씠 鍮꾨�踰덊샇�뒗 �엫�떆 鍮꾨�踰덊샇�씠硫�, 媛쒖씤�젙蹂댁닔�젙�뿉�꽌 蹂�寃쏀빐二쇱꽭�슂. : ";
        
        // 鍮꾨쾲 8�옄由щ줈 �옄�룞�깮�꽦 �븣怨좊━利�
        String randomPwd = null;
        char[] arr= new char[8];
		
        for(int i=0; i<arr.length; i++) {
//		arr[i] =(char)((int)(Math.random()*58)+65);	// �쁺�뼱 ��臾몄옄遺��꽣 �냼臾몄옄
		arr[i] =(char)((int)(Math.random()*74)+48); // �닽�옄,�듅�닔湲고샇,�쁺�뼱(���냼臾몄옄) �굹�삤�룄濡� 踰붿쐞
		// 65遺��꽣 122源뚯� �옖�뜡�닔 �깮�꽦
        }
        
        randomPwd= new String(arr); // 臾멸뎄 �떎�쓬�뿉 鍮꾨쾲 遺숈뼱�굹�삤寃� +=
        
        
        
         int result = new MemberService().updatePwd(id,randomPwd);	
        
         body += randomPwd;
         
         if(result >0) {
        	 System.out.println("�깉濡쒖슫 鍮꾨쾲�씠 �깮�꽦�릺�뿀�뒿�땲�떎. �씠硫붿씪 �솗�씤�븯�꽭�슂.");
         }else {
        	 System.out.println("�깉濡쒖슫 鍮꾨쾲 �깮�꽦 �떎�뙣");
         }
        
//        int num = 'z';
//        int num2 ='A';
//        
//        System.out.println(num+","+num2);
//        
         
        Properties props = System.getProperties();
         
         
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
          
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
          
        Message mimeMessage = new MimeMessage(session);
        try {
			mimeMessage.setFrom(new InternetAddress(username));	
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));	// 諛쏆쓣 �궗�엺 �씠硫붿씪 二쇱냼
			mimeMessage.setSubject(subject);	
			mimeMessage.setText(body);			
			Transport.send(mimeMessage);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
