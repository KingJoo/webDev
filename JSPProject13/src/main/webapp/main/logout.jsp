<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form method=post action="logout_ok.jsp">
 <table class="table">
   <tr>
     <td width=200>
      이메일:10통 &nbsp; 쪽지:1개
     </td>
   </tr>
   <tr>
     <td width=200>
      <%= session.getAttribute("name")%>님 로그인되었습니다
     </td>
   </tr>
   <tr>
     <tr>
       <td width=200 class="text-right">
         <input type=submit value=로그아웃 class="btn btn-sm btn-danger">
       </td>
     </tr>
   </tr>
  </table>
  </form>
</body>
</html>