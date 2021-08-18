package dao;
import java.util.*;
import java.sql.*;

/*
 * 1. 목록(페이징-인라인)
 * 2. 상세보기
 * 3. 데이터 추가(최종화면->목록)
 * 4. 데이터 수정(최종화면->상세->비밀번호:Ajax)
 * 5. 데이터 삭제(최종화면->목록->비밀번호:Ajax->화면 변경없이 그자리에서 처리)
 * 6. 찾기(최종화면->목록->새로운 jsp OR 목록 출)
 */
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void disConnection() {
		try {
			if(ps!=null)	ps.close();
			if(conn!=null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//목록출력 : VO 여러개 필요
	public ArrayList<BoardVO> boardListData(int page){
		ArrayList<BoardVO> list=new ArrayList<>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit "
					+ "FROM jspBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";	//인라인뷰(데이터 나눠 가지고 올 때 사용)
			ps=conn.prepareStatement(sql);
			int rowSize=10;//페이지당 출력 개수
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO();
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
	
	// 총페이지 구하기
	public int boardTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM jspBoard";
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
	
	// 2. 상세보기, 조회수 증가  ==> VO한개 출력 
	public BoardVO boardDetailData(int no){
    	BoardVO vo=new BoardVO();
    	try{
    		getConnection(); 
    		//조회수 증가 
    		String sql="UPDATE jspBoard SET "
    				  +"hit=hit+1 "
    				  +"WHERE no="+no;
    		ps=conn.prepareStatement(sql);
    		ps.executeUpdate();
    		//상세 보기 
    		sql="SELECT no,name,subject,content,regdate,hit "
    		   +"FROM jspBoard "
    		   +"WHERE no="+no;
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		vo.setNo(rs.getInt(1));
    		vo.setName(rs.getString(2));
    		vo.setSubject(rs.getString(3));
    		vo.setContent(rs.getString(4));
    		vo.setRegdate(rs.getDate(5));
    		vo.setHit(rs.getInt(6));
    		rs.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		disConnection();
    	}
    	return vo;
    }
	
	//글쓰기 : VO 한개 필요
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO jspBoard(no,name,subject,content,pwd) VALUES("
					+ "jb_no_seq.nextval,?,?,?,?)";
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
	//수정하기 : VO 한개 필요 
	//-> 기존 입력된 데이터 읽어오기
	public BoardVO boardUpdateData(int no) {
		BoardVO vo=new BoardVO();
		try {
			getConnection();
			String sql="SELECT no,name,subject,content "
					+ "FROM jspBoard "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	//-> 비밀번호 확인 후 수정 / 비밀번호 다시입력요청 
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		try {
			getConnection();
			String sql="SELECT pwd FROM jspBoard "
					+ "WHERE no="+vo.getNo();
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			//디비에서 가져온 비밀번호와 비교하기
			if(db_pwd.equals(vo.getPwd())) {
				//실제 수정
				bCheck=true;
				sql="UPDATE jspBoard SET "
						+ "name=?,subject=?,content=? "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return bCheck;
	}
	//삭제하기 : 게시물번호
	public boolean boardDelete(int no,String pwd) {
		boolean bCheck=false;
		try {
			getConnection();
			String sql="SELECT pwd FROM jspBoard "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				sql="DELETE FROM jspBoard "
						+ "WHERE no="+no;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return bCheck;
	}
	
	public int boardCount() {
		int count=0;
		try {
			getConnection();
			String sql="SELECT COUNT(*) FROM jspBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return count;
	}
	
	//찾기 : VO 여러개
	public ArrayList<BoardVO> boardFindData(String fs, String ss){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,regdate,hit "
					+ "FROM jspBoard "
					+ "WHERE "+fs+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO();
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
	
}