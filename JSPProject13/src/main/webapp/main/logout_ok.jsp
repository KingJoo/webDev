<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     // session에 등록 모든 데이터를 지운다 
     session.invalidate();// id=null,name=null
     // 이동 => main.jsp
     response.sendRedirect("main.jsp");
%>