package mypage_artist.management.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import mypage_artist.management.model.dao.Mypage_ArtistDao;
import mypage_artist.management.model.vo.Mypage_artist;

public class Mypage_ArtistService {

	public int getListCount_PM(String bWriter) {
		Connection conn = getConnection();
		
		int listCount_PM = new Mypage_ArtistDao().getListCount_PM(conn, bWriter);
		
		close(conn);
		
		return listCount_PM;
	}
	
	public int getListCount_OM(String bWriter) {
		Connection conn = getConnection();
		
		int listCount_OM = new Mypage_ArtistDao().getListCount_OM(conn, bWriter);
		
		close(conn);
		
		return listCount_OM;
	}

	public int getListCount_SM(String bWriter) {
		Connection conn = getConnection();
		
		int listCount_SM = new Mypage_ArtistDao().getListCount_SM(conn, bWriter);
		
		close(conn);
		
		return listCount_SM;
	}

	public ArrayList<Mypage_artist> selectList_PM(int currentPage, int limit, String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> PM_list = new Mypage_ArtistDao().selectList_PM(conn, currentPage, limit, bWriter);
		
		close(conn);
		return PM_list;
	}

	public ArrayList<Mypage_artist> selectList_SM(int currentPage, int limit, String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> SM_list = new Mypage_ArtistDao().selectList_SM(conn, currentPage, limit, bWriter);
		
		close(conn);
		return SM_list;
	}

	public ArrayList<Mypage_artist> selectList_OM(int currentPage, int limit, String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> OM_list = new Mypage_ArtistDao().selectList_OM(conn, currentPage, limit, bWriter);
		
		close(conn);
		return OM_list;
	}

	public ArrayList<Mypage_artist> detail_order_view(String bWriter) {
		Connection conn = getConnection();
	
		ArrayList<Mypage_artist> DOV_OP = new Mypage_ArtistDao().detail_order_view(conn, bWriter);
		
		close(conn);
		return DOV_OP;
	}

	public ArrayList<Mypage_artist> detail_deposit_view(String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> DOV_D = new Mypage_ArtistDao().detail_deposit_view(conn, bWriter);
		
		close(conn);
		return DOV_D;
	}

	public ArrayList<Mypage_artist> detail_shipping_view(String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> DOV_SI = new Mypage_ArtistDao().detail_shipping_view(conn, bWriter);
		
		close(conn);
		return DOV_SI;
	}

	public ArrayList<Mypage_artist> listSearch(String order_status, String term, String calendar1, String calendar2,
			String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> search_list = new Mypage_ArtistDao().listSearch(conn, order_status, term, calendar1, calendar2, bWriter);
		
		close(conn);
		System.out.println(search_list);
		return search_list;
	}
	public ArrayList<Mypage_artist> listSearch_PM(String category, 
			String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> search_list_PM = new Mypage_ArtistDao().listSearch_PM(conn, category, bWriter);
		
		close(conn);
		System.out.println(search_list_PM);
		return search_list_PM;
	}
	public ArrayList<Mypage_artist> listSearch_SM(String shipping_status, String term, String calendar1, String calendar2,
			String bWriter) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> search_list_SM = new Mypage_ArtistDao().listSearch_SM(conn, shipping_status, term, calendar1, calendar2, bWriter);
		
		close(conn);
		System.out.println(search_list_SM);
		return search_list_SM;
	}

	public ArrayList<Mypage_artist> selectList_DOV_P(String bWriter, int order_no) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> DOV_plist = new Mypage_ArtistDao().selectList_DOV_P(conn,  bWriter, order_no);
		
		close(conn);
		
		return DOV_plist;
	}

	public ArrayList<Mypage_artist> selectList_DOV_D(String bWriter, int order_no) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> DOV_dlist = new Mypage_ArtistDao().selectList_DOV_D(conn,  bWriter, order_no);
		
		close(conn);
		
		return DOV_dlist;
	}

	public ArrayList<Mypage_artist> selectList_DOV_S(String bWriter, int order_no) {
		Connection conn = getConnection();
		
		ArrayList<Mypage_artist> DOV_slist = new Mypage_ArtistDao().selectList_DOV_S(conn,  bWriter, order_no);
		
		close(conn);
		
		return DOV_slist;
	}

	public int deleteProduct(String bWriter, int paint_no) {		
		Connection conn = getConnection();
		
		int deleteResult = new Mypage_ArtistDao().deleteProduct(conn, bWriter, paint_no);
		
		if(deleteResult > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return deleteResult;
	}

	public int deleteBasket(String bWriter, int paint_no) {
		Connection conn = getConnection();
		
		int deleteBResult = new Mypage_ArtistDao().deleteBasket(conn, bWriter, paint_no);
		
		if(deleteBResult > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return deleteBResult;
	}

	public int updateOrder_status(int order_no) {
		Connection conn = getConnection();
		
		int result = new Mypage_ArtistDao().updateOrder_status(conn,order_no);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return result;
	}





	


}












