<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<%
    //한글 변환
    try{
    	request.setCharacterEncoding("UTF-8");//인코딩->디코딩(원상복귀)
    }catch(Exception e){}
    //데이터 받기
   	String name=request.getParameter("name");
   	String subject=request.getParameter("subject");
   	String content=request.getParameter("content");
   	String pwd=request.getParameter("pwd");
   	//데이터 묶어서
   	BoardVO vo=new BoardVO();
   	vo.setName(name);
   	vo.setSubject(subject);
   	vo.setContent(content);
   	vo.setPwd(pwd);
   	//DAO로 전송
   	BoardDAO dao=new BoardDAO();
   	dao.boardInsert(vo);
   	//화면이동
   	response.sendRedirect("list.jsp"); //서버에서 화면 이동, GET만 사용가능
%>