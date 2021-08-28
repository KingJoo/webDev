package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.db.*;
public class MovieDAO {
   private Connection conn;
   private PreparedStatement ps;
   private DBCreate db=new DBCreate();
   public ArrayList<MovieBean> movieListData(int page)
   {
	   ArrayList<MovieBean> list=new ArrayList<MovieBean>();
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT mno,poster,title,num "
				     +"FROM (SELECT mno,poster,title, rownum as num "
				     +"FROM (SELECT mno ,poster, title "
				     +"FROM daum_movie ORDER BY mno ASC)) "
				     +"WHERE num BETWEEN ? AND ?";
		   // ?에 값을 설정 
		   int rowSize=12;
		   int start=(rowSize*page)-(rowSize-1); // rownum은 1번부터 시작한다 
		   //  page = 1     12-11 => 1 , 12
		   //  page = 2     24-11 => 13, 24
		   int end=rowSize*page;
		   
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   
		   // 실행
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   MovieBean bean=new MovieBean(); // <jsp:useBean>는 JSP에서만 사용이 가능 
			   bean.setMno(rs.getInt(1));
			   bean.setPoster(rs.getString(2));
			   bean.setTitle(rs.getString(3));
			   list.add(bean);
		   }
		   rs.close();
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return list;
   }
   // 총페이지
   public int movieTotalPage()
   {
	   int total=0;
	   try
	   {
		   conn=db.getConnection();
		   String sql="SELECT CEIL(COUNT(*)/12.0) FROM daum_movie";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   total=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return total;
   }
   // 상세보기 
   /*
    *   MNO      NOT NULL NUMBER        
		CNO               NUMBER        
		TITLE    NOT NULL VARCHAR2(300) 
		REGDATE           VARCHAR2(100) 
		GENRE    NOT NULL VARCHAR2(200) 
		NATION            VARCHAR2(100) 
		GRADE    NOT NULL VARCHAR2(50)  
		TIME     NOT NULL VARCHAR2(30)  
		SCORE             NUMBER(2,1)   
		SHOWUSER          VARCHAR2(100) 
		POSTER   NOT NULL VARCHAR2(260) 
		STORY             CLOB       
    */
   public MovieBean movieDetailData(int mno)
   {
	   MovieBean bean=new MovieBean();
	   try
	   {
		   // * => 오라클에 출력된 순서로 =1번
		   // 지정=> 지정된 순서로 읽는다 
		   conn=db.getConnection();
		   String sql="SELECT mno,title,regdate,genre,nation,grade,time,score,showuser,poster "
				     +"FROM daum_movie "
				     +"WHERE mno=?";
		   ps=conn.prepareStatement(sql);
		   // ?에 값을 채운다 
		   ps.setInt(1, mno);
		   // 실행 요청
		   ResultSet rs=ps.executeQuery();
		   rs.next(); // 데이터 출력 위치에 커서를 이동한다 
		   
		   bean.setMno(rs.getInt(1));
		   bean.setTitle(rs.getString(2));
		   bean.setRegdate(rs.getString(3));
		   bean.setGenre(rs.getString(4));
		   bean.setNation(rs.getString(5));
		   bean.setGrade(rs.getString(6));
		   bean.setTime(rs.getString(7));
		   bean.setScore(rs.getDouble(8));
		   bean.setShowUser(rs.getString(9));
		   bean.setPoster(rs.getString(10));
		   rs.close();
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return bean;
   }
}




















