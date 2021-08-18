package dao;
import java.sql.*;
import java.util.*;
public class ZipcodeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	public ZipcodeDAO() {
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
	
	public ArrayList<ZipcodeVO> postFind(String dong){
		ArrayList<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		try {
			getConnection();
			String sql="SELECT zipcode,sido,gugun,dong,bunji "
					+ "FROM zipcode "
					+ "WHERE dong Like '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ZipcodeVO vo=new ZipcodeVO();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
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
}