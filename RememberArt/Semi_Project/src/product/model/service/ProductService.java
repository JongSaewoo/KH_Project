package product.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import artistapply.model.vo.Apply;
import product.model.dao.ProductDao;
import product.model.vo.Attachment;
import product.model.vo.MasterpieceCount;
import product.model.vo.Paint_QnA;
import product.model.vo.masterpiece;
import product.model.vo.product;


public class ProductService {

	public int getListCount() {
		
		Connection conn = getConnection();
		int listCount = new ProductDao().getListCount(conn);
		close(conn);
		
		return listCount;
	}

	public ArrayList<product> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		
		ArrayList<product> list = new ProductDao().selectList(conn,currentPage, limit);
		System.out.println("ProductService:List출력-"+list);
		
		close(conn);
		return list;
	}

	public int insertProduct(product p, ArrayList<Attachment> fileList) {

		
		Connection conn = getConnection();
		ProductDao pDao = new ProductDao();
		
		int result1 = pDao.insertProduct(conn,p);
		int result2 = pDao.insertAttachment(conn,fileList);
//		int result3 = pDao.insertmasterpiece(conn,p);
//		int result4 = pDao.insertTag(conn,irr);
		
		if(result1>0 && result2> 0 /*&& result3 >0*/) {
	         commit(conn);
	      }else {
	         rollback(conn);
	      }
	      close(conn);
	      return result2;
	}

	public ArrayList<Attachment> selectAList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Attachment> list2 =new ProductDao().selectphoto(conn,currentPage,limit);
		System.out.println(list2);
		close(conn);
		return list2;
	}

	
	public ArrayList<product> selectsearch(product po) {
		Connection conn = getConnection();

		ArrayList<product> list = new ArrayList<product>();
		list = new ProductDao().selectsearch(conn,po);

		close(conn);
		return list;
	}
	public ArrayList<product> selectsearch2(product po) {
		Connection conn = getConnection();

		ArrayList<product> list = new ArrayList<product>();
		list = new ProductDao().selectsearch2(conn,po);

		close(conn);
		return list;
	}
	public ArrayList<product> selectsearch3(product po) {
		Connection conn = getConnection();

		ArrayList<product> list = new ArrayList<product>();
		list = new ProductDao().selectsearch3(conn,po);

		close(conn);
		return list;
	}
	public ArrayList<product> selectsearch4(product po) {
		Connection conn = getConnection();

		ArrayList<product> list = new ArrayList<product>();
		list = new ProductDao().selectsearch4(conn,po);

		close(conn);
		return list;
	}


	public ArrayList<product> selectSearchTag(String[] tagname) {
		Connection conn = getConnection();
		
			ArrayList<product> tagList = new ProductDao().selectrealsearch(conn,tagname);
		
			close(conn);
		return tagList;
	}

	public ArrayList<Attachment> selectAllalist() {
		Connection conn = getConnection();
		
		ArrayList<Attachment> alist = new ArrayList<Attachment>();
		alist = new ProductDao().selectAllalist(conn);
		
		close(conn);
		return alist;
	}

	public product selectsearch(int paint_no) {
		Connection conn = getConnection();
		product plist =new ProductDao().selectsearch(conn,paint_no);
		
		
		close(conn);
		return plist;
	}
	

	public ArrayList<Attachment> selectAttachment(int paint_no) {
		Connection conn = getConnection();
		ArrayList<Attachment> alist =new ProductDao().selectAttachment(conn,paint_no);
		
		
		close(conn);
		return alist;
	}

	public ArrayList<product> selectsize() {
		Connection conn = getConnection();
		
		ArrayList<product> plist =new ProductDao().selectsize(conn);
		System.out.println("service="+plist);
		close(conn);
		return plist;
	}

	public ArrayList<product> payList() {
		Connection conn = getConnection();
		
		ArrayList<product> pay_list = new ProductDao().payList(conn);
		
		close(conn);
		return pay_list;
	}

	public Apply selectApply(product plist) {
		Connection conn = getConnection();
		
		Apply apply = new Apply();
		apply= new ProductDao().selectApply(conn,plist);
		close(conn);
		return apply;

	}

	public int insertmasterpiece(String bWriter, int paint_no) {
		Connection conn = getConnection();
		
		int result = new ProductDao().insertmasterpiece(conn, bWriter,paint_no);
		
		System.out.println(result);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int deletemasterpiece(String bWriter, int paint_no) {
		Connection conn = getConnection();
		
		int result = new ProductDao().deletemasterpiece(conn, bWriter,paint_no);
		
		System.out.println(result);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int insertBasket(product po, String bWriter) {
		Connection conn = getConnection();
		
		int result = 0;
		
		result = new ProductDao().insertBasket(conn,po,bWriter);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public masterpiece selectMasterpiece(int paint_no, String bWriter) {
		Connection conn = getConnection();
		
		masterpiece mp = new ProductDao().selectMasterpiece(conn,bWriter,paint_no);
		return mp;
	}

	public int selectMasterpiece2(int paint_no) {
		Connection conn = getConnection();
		int count = new ProductDao().selectMasterpiece2(conn,paint_no);
		
		return count;
	}

	public MasterpieceCount countMasterpiece(int paint_no) {
		Connection conn = getConnection();
		MasterpieceCount mc = new ProductDao().countMasterpiece(conn,paint_no);
		return mc;
	}

	public ArrayList<Paint_QnA> selectQ(int paint_no) {
		Connection conn = getConnection();
		ArrayList<Paint_QnA>  pqa = new ProductDao().selectQ(conn,paint_no);
		close(conn);
		return pqa;
	}

	public ArrayList<Paint_QnA> selectP() {
		Connection conn = getConnection();
		ArrayList<Paint_QnA>  pqa = new ProductDao().selectA(conn);
		return pqa;
	}

	public int insertQuestion(String writer, int paint_no, String content) {
		Connection conn = getConnection();
		int result = new ProductDao().insertQuestion(conn,writer,paint_no,content);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public int insertAnwser(int qna_no, String content) {
		Connection conn = getConnection();
		int result = new ProductDao().insertAnwser(conn,qna_no,content);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public int updateQ(int qna_no) {
		Connection conn = getConnection();
		int result = new ProductDao().updateQ(conn,qna_no);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public int insertorder(int paint_no, String orderid, String ordername, String orderphone, String orderEmail,
			String orderadress, String receivename, String receivephone, String receiveaddress, String orderrule, String receivecontent) {
		Connection conn = getConnection();
		
		int result = new ProductDao().insertbuylist(conn,paint_no,orderid);
		int result1 =0;
		
		if(result>0) {
		 result1 =new ProductDao().insertordertable(conn,paint_no,orderid,ordername,orderphone,orderEmail,orderadress,orderrule);
		}else {
			rollback(conn);
		}
		
		int result2 =0;
		
		if(result1>0) {
		result2 = new ProductDao().insertreceivetable(conn,orderid,paint_no,receivename,receivephone,receiveaddress,receivecontent);
		}else {
			rollback(conn);
		}
		
		
		
		if(result2 >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result2;
	}

	public product selectpaint(int paint_no) {
		Connection conn = getConnection();

		product p = new product();
		p = new ProductDao().selectpaint(conn,paint_no);

		close(conn);
		return p;
	}

	public int updateBasket(int paint_no, String orderid) {
		Connection conn = getConnection();
		
		int result = new ProductDao().updateBasket(conn,paint_no,orderid);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

	public Paint_QnA selectP2(int qna_no) {
		Connection conn = getConnection();
		
		Paint_QnA qna = new ProductDao().selectP(conn,qna_no);
		
		return qna;
	}


	


	



}
