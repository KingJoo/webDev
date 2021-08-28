<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"/>
<%--
      데이터 : id,password,name,sex,birthday,post, addr1 addr2,tel ,content,hobby
             String id=request.getParameter("id");
             String name=request.getParameter("id");
             String sex=request.getParameter("id");
             String birthday=request.getParameter("id");
             String post=request.getParameter("id");
             String addr1=request.getParameter("id");
             String addr2=request.getParameter("id");
             String tel=request.getParameter("id");
             String content=request.getParameter("id");
             MemberBean m=new MemberBean();
             m.setId(id);
             m.setPassword(pwd);
             =
             =
             =
             =
             =
             =
             
             <jsp:useBean id="m" class="MemberBean">
               <jsp:setProperty name="m" property="*"/>
             </jsp:useBean>
      데이터 : mno 
             String mno=request.getParameter("mno");
             
             <jsp:useBean id="m" class="MemberBean">
               <jsp:setProperty name="m" property="mno"/>
             </jsp:useBean>
 --%>
<%-- MovieDAO dao=new MovieDAO() --%>
<%
     String mno=request.getParameter("mno");
     // Cookie전송 
     MovieBean m=dao.movieDetailData(Integer.parseInt(mno));
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
   width:700px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
   <div class="container">
    <h1>&lt;<%=m.getTitle() %>&gt; 상세보기</h1>
    <div class="row">
      <table class="table">
       <tr>
         <td width="30%" class="text-center" rowspan="8">
           <img src="<%=m.getPoster() %>" width=100%>
         </td>
         <td colspan="2"><%=m.getTitle() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">장르</span></td>
         <td width=55%><%=m.getGenre() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">등급</span></td>
         <td width=55%><%=m.getGrade() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">국가</span></td>
         <td width=55%><%=m.getNation() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">상영일</span></td>
         <td width=55%><%=m.getRegdate() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">시간</span></td>
         <td width=55%><%=m.getTime() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">평점</span></td>
         <td width=55%><%=m.getScore() %></td>
       </tr>
       <tr>
         <td width=15% class="text-right"><span style="color:gray">누적관객</span></td>
         <td width=55%><%=m.getShowUser() %></td>
       </tr>
       <tr>
         <td colspan="3" class="text-right">
           <a href="#" class="btn btn-sm btn-success">예매</a>
           <a href="#" class="btn btn-sm btn-info">찜하기</a>
           <a href="list.jsp" class="btn btn-sm btn-danger">목록</a>
         </td>
       </tr>
      </table>
    </div>
   </div>
</body>
</html>









