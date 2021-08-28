<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     getAttribute() : 세션에 저장된 값을 읽을 경우
     setAttribute() : 세션에 저장 
     invalidate() : 해제 (로그아웃 => 브라우저 종료)
 --%>
<%
     String id=(String)session.getAttribute("id");
     // session에 ID가 저장되었는지 확인  ==> id=null(로그인이 안됨),id가 null아니면 저장 
     String jsp="";
     String main_jsp="";
     if(id==null)
     {
    	 jsp="login.jsp";
    	 main_jsp="default.jsp";
     }
     else
     {
    	 jsp="logout.jsp"; 
    	 main_jsp="../movie/list.jsp";
     }
     
    /* String mode=request.getParameter("mode");
     if(mode==null)
    	 mode="1";
     String main_jsp="";
     switch(mode)
     {
     case "1":
    	 main_jsp="default.jsp";
    	 break;
     case "2":
         main_jsp="../movie/list.jsp";		
         break;
     }*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 30px;
}
.row {
  margin: 0px auto;
  width:960px;
}
</style>
</head>
<body>
   <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td colspan="2" class="text-center" height=100>Header</td>
        </tr>
        <tr>
          <td class="text-center" valign="top" height=500 width=200>
            <jsp:include page="<%=jsp %>"></jsp:include>
          </td>
          <td class="text-center" valign="top" height=500 width=760>
            <jsp:include page="<%=main_jsp %>"></jsp:include>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center" height=100>Footer</td>
        </tr>
      </table>
    </div>
   </div>
</body>
</html>











