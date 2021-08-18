package dao;
import java.util.*;
import java.sql.*;
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	// 0. 드라이버 등록
	public BoardDAO() {
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
	
	// 기능(오라클과 연결) ---> setvlet을 통해 브라우저로 전송
	// 1. 목록 출력 -> 인라인뷰 (페이지 설정)
	// 2. 상세 보기 -> 조회수 증가, 상세보기
	// 3. 게시물 추가 <input type=text name=name>
	// 4. 수정 -> 태그 value속성
	// 5. 삭제
	// 6. 찾기 -> like
	
	// 1-a. 목록 출력
	public ArrayList<BoardVO> boardListData(int page){
		//page -> 사용자가 브러우저에서 전송해준 값
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit "
					+ "FROM board ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			int rowSize=10;
			int end = rowSize*page;
			int start = end-rowSize+1;
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO(); // JSP->bean
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
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
	// 1-b. 총페이지 구하기
	public int boardTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM board";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return total;
	}
	
	// 3. 게시물 추가
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO board VALUES("
					+ "(SELECT NVL(MAX(no)+1,1) FROM board),?,?,?,?,SYSDATE,0"
					+ ")";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
}
