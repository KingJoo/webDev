package com.sist.dao;
import java.util.*;

import com.sist.db.DBCreate;

import java.sql.*;
public class MemberDAO {
   private Connection conn;
   private PreparedStatement ps;
   private DBCreate db=new DBCreate();
   public String isLogin(String id,String pwd)
   {
	   String result="";
	   try
	   {
		   
		   conn=db.getConnection();
		   
		   String sql="SELECT COUNT(*) FROM jsp_member "
				     +"WHERE id=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   int count=rs.getInt(1);
		   System.out.println("count="+count);
		   rs.close();
		   if(count==0)
		   {
			   result="NOID";
		   }
		   else
		   {
			  
			   sql="SELECT pwd,name FROM jsp_member "
				  +"WHERE id=?";
			   
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, id);
			   rs=ps.executeQuery();
			   rs.next();
			   String db_pwd=rs.getString(1);
			   String name=rs.getString(2);
			   System.out.println("db_pwd="+db_pwd+",pwd="+pwd);
			   System.out.println("name="+name);
			   rs.close();
			   
			   if(pwd.equals(db_pwd))
			   {
				   result=name;
			   }
			   else 
			   {
				   result="NOPWD";
			   }
			   
		   }
		   
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   db.disConnection(conn, ps);
	   }
	   return result;
   }
}
















