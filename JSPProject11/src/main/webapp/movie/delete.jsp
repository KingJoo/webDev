<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     Cookie[] cookie=request.getCookies();
     if(cookie!=null && cookie.length>0)
     {
    	 for(int i=0;i<cookie.length;i++)
    	 {
    		 if(cookie[i].getName().startsWith("m"))
    		 {
    			 cookie[i].setMaxAge(0);// 쿠키 제거 
    			 response.addCookie(cookie[i]);
    		 }
    	 }
     }
     response.sendRedirect("list.jsp");
%>