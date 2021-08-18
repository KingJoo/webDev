package dao;
import java.sql.*;
import java.util.*;
public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	public MusicDAO() {
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
			if(conn!=null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 전체데이터 가져오기
	public ArrayList<MusicVO> musicAllData(){
		ArrayList<MusicVO> list = new ArrayList<>();
		try {
			getConnection();
			String sql="SELECT no,poster,title,singer,album,key "
					+ "FROM melon_cjw "
					+ "ORDER BY no";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				MusicVO vo=new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setSinger(rs.getString(4));
				vo.setAlbum(rs.getString(5));
				vo.setKey(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	
	// 상세보기
	public MusicVO musicDetailData(int no) {
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql="SELECT no,title,singer,key "
					+ "FROM melon_cjw "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setKey(rs.getString(4));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	
}
