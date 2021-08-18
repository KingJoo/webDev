package dao;
import java.sql.*;
import java.util.*;

public class DAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	public DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disConnection() {
		try {
			if(ps!=null)	ps.close();
			if(conn!=null)	ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//수집
	public void dataInsert(VO vo) {
		try {
			getConnection();
			String sql="INSERT INTO book_info(isbn,category,subCategory,poster,title,subtitle,writer,publisher,etcInfo,price,discount,text,imgs,contentsTable,tags,publicationDay) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setLong(1, vo.getIsbn());
			ps.setString(2, vo.getCategory());
			ps.setString(3, vo.getSubCategory());
			ps.setString(4, vo.getPoster());
			ps.setString(5, vo.getTitle());
			ps.setString(6, vo.getSubtitle());
			ps.setString(7, vo.getWriter());
			ps.setString(8, vo.getPublisher());
			ps.setString(9, vo.getEtcInfo());
			ps.setString(10, vo.getPrice());
			ps.setString(11, vo.getDiscount());
			ps.setString(12, vo.getText());
			ps.setString(13, vo.getImgs());
			ps.setString(14, vo.getContentsTable());
			ps.setString(15, vo.getTags());
			ps.setString(16, vo.getPublicationDay());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
}
