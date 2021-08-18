package dao;

import java.sql.*;
import java.util.*;

public class MelonDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	public MelonDAO() {
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
	
	// 목록 출력
	public ArrayList<MelonVO> musicListData(){
		ArrayList<MelonVO> list=new ArrayList<MelonVO>();
		try {
			getConnection();
			String sql="SELECT no,title,poster FROM melon_cjw ORDER BY no";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				MelonVO vo=new MelonVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	
	// 상세(댓글)
	public MelonVO melonDetailData(int no) {
		MelonVO vo=new MelonVO();
		try {
			getConnection();
			String sql="SELECT no,title,poster,singer,key FROM melon_cjw WHERE no="+no+" ORDER BY no";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setSinger(rs.getString(4));
			vo.setKey(rs.getString(5));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	
	// 댓글 추가
	public void replyInsert(MelonReplyVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO melonReply VALUES("
					+ "mr_rno_seq.nextval,?,?,?,SYSDATE"
					+ ")";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getMno());
			ps.setString(2, vo.getName()); // '홍길동'
			ps.setString(3, vo.getMsg());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	// 댓글 읽기
	public ArrayList<MelonReplyVO> replyListData(int mno){
		ArrayList<MelonReplyVO> list = new ArrayList<MelonReplyVO>();
		try {
			getConnection();
			String sql="SELECT rno,name,msg,TO_CHAR(regdate,'YYYY\"년\" MM\"월\" DD\"일\" HH24:MI:SS') "
					+ "FROM melonReply "
					+ "WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				MelonReplyVO vo=new MelonReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setMsg(rs.getString(3));
				vo.setDbday(rs.getString(4));
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
