<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"/>
<%--
       MovieDAO dao=new MovieDAO()
       
       전송 (서버 ==> 클라이언트)
       HTML / Cookie ==> response는 JSP당 한번만 전송이 가능 
       detail.jsp ========> 상세보기 (HTML)
       detail_before.jsp => Cookie전송 
 --%>
<%
     // 페이지 받기
     String strPage=request.getParameter("page");
     // 처음에 한번 => 사용자로부터 페이지를 받지 못한다 => 디폴트 설정 
     if(strPage==null)
    	 strPage="1";
     int curpage=Integer.parseInt(strPage);
     // 페이지에 해당되는 데이터 받기 
     ArrayList<MovieBean> list=dao.movieListData(curpage);
     // 화면에 출력 
     // 페이지 설정 => 총페이지
     int totalpage=dao.movieTotalPage();
     
     // 쿠키를 읽어와서 출력 
     Cookie[] cookies=request.getCookies(); // 쿠키나 세션을 가지고 올때는 request를 이용해서 
     //  cookie => request.getCookies() , session => request.getSession()
     ArrayList<MovieBean> cList=new ArrayList<MovieBean>();
     /*
         쿠키에 저장된 값을 읽어 올때 => 키(getName()) 
                                   값(getValue())
     */
     if(cookies!=null && cookies.length>0)
     {
    	 for(int i=0;i<cookies.length;i++)
    	 {
    		 if(cookies[i].getName().startsWith("m"))
    		 {
    			 String no=cookies[i].getValue();
    			 MovieBean bean=dao.movieDetailData(Integer.parseInt(no));
    			 cList.add(bean);
    		 }
    	 }
     }
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
   width:1200px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h1>다음 영화 목록</h1>
    <div class="row">
      <%
          for(MovieBean bean:list)
          {
      %>
              <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="detail_before.jsp?mno=<%=bean.getMno()%>">
			        <img src="<%= bean.getPoster() %>" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p><%= bean.getTitle() %></p>
			        </div>
			      </a>
			    </div>
			  </div>  
      <%
          }
      %>
    </div>
    <div class="row">
      <div class="text-right">
        <a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-success">이전</a>
        <%=curpage %> page / <%=totalpage %> pages
        <a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-danger">다음</a>
      </div>
    </div>
    <div class="row">
      <h3>최근 본 영화(Cookie=>본 컴퓨터에 저장이 된다)</h3>
      <hr>
      <table class="table">
        <tr>
          <td colspan="5">
            <a href="delete.jsp" class="btn btn-sm btn-primary">쿠키삭제</a>
          </td>
        </tr>
        <tr>
          <%
              int j=0;
              for(int i=cList.size()-1;i>=0;i--)
              {
            	  if(j>4) break;
            	  MovieBean m=cList.get(i);
          %>
                 <td class="text-center">
                  <a href="detail.jsp?mno=<%=m.getMno()%>"><img src="<%=m.getPoster() %>" width=100%></a>
                 </td>
          <%
                 j++;
              }
          %>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>














