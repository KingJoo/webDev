<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<%
	//1.전송된 데이터 받기
	String pwd=request.getParameter("pwd");
	String no=request.getParameter("no");
	//DAO 연결 -> 비밀번호 확인 -> true면 삭제 false면 history.back()
	BoardDAO dao=new BoardDAO();
	boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
	if(bCheck){
		response.sendRedirect("list.jsp");
	}
	else{
%>
	<script>
	alert("비밀번호가 틀립니다!!");
	history.back();
	</script>
<%		
	}
%>