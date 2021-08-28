<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MemberDAO"></jsp:useBean>
<%
    // id,pwd
    String id=request.getParameter("id");
    String pwd=request.getParameter("pwd");
    System.out.println("id="+id);
    System.out.println("pwd="+pwd);
    String result=dao.isLogin(id, pwd);
    // NOID , NOPWD , name
    if(result.equals("NOID"))
    {
%>
       <script>
        alert("ID가 존재하지 않습니다");
        history.back();
       </script>
<%
    }
    else if(result.equals("NOPWD"))
    {
%>
      <script>
       alert("비밀번호가 틀립니다");
       history.back();
      </script>
<%
    }
    else
    {
    	// 로그인 
    	// session에 저장 
    	session.setAttribute("id", id); // 30분간 서버에 저장된 내용이 유지
    	session.setAttribute("name", result);
    	// main으로 이동 
    	response.sendRedirect("main.jsp");
    }
%>





