package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		// Properties의 파일의 경로를 URL객체로 반환해서 getPath()를 쓰면 String형의 경로가 됨
//		String fileName = JDBCTemplate.class.getResource("/sql/driver.properties").getPath();
		
		try {
//			prop.load(new FileReader(fileName));
//			String driver = prop.getProperty("driver");
//			String url = prop.getProperty("url");
//			String user = prop.getProperty("user");
//			String password = prop.getProperty("password");
			
			//1. Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DBMS와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "SEMI", "SEMI");
			
			conn.setAutoCommit(false); // 트랜잭션 처리 자동 여부
			// 기본값은 true, 원칙은 application에서 제어해야 하므로
			// 우리가 나중에 if문으로 처리하자.
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!= null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!= null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset!= null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

