<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dao.*"%>
    <%
    	//사용자가 보낸 데이터 받기
    	String strPage=request.getParameter("page");// 이전 다음 클릭시 page 값 받음, 처음 구동시 null
    	if(strPage==null)
    		strPage="1";
    	int curpage=Integer.parseInt(strPage);
    	//DAO 연결, DAO에서 요청한 데이터 받기
    	BoardDAO dao=new BoardDAO();
    	ArrayList<BoardVO> list = dao.boardListData(curpage);
    	
    	int totalpage=dao.boardTotalPage();
    	
    	int count=dao.boardCount();
    	count=count-(curpage*10-10);//번호출력 -> 게시물 번호 출력 아님, 일괄적 출력
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type=text/css>
.container{
	margin-top:30px;
}
.row{
	margin:0px outo;
	width:800px;
}
h1{
	text-align:center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$('#findBtn').click(function(){
		let ss=$('#ss').val();
		if(ss.trim()===""){
			$('#ss').focus();
			return;
		}
		$('form').submit(); //action에 있는 파일로 데이터 전송!
	})
</script>
</head>
<body>
	<div class="container">
		<h1>자유게시판</h1>
		<div class="row">
			<table class=table>
				<tr>
					<td>
						<a href="insert.jsp" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
			</table>
			<table class="table table-striped">
				<tr class="danger">
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<%
					for(BoardVO vo:list){
				%>
					<tr>
						<td width=10% class="text-center"><%=count-- %></td>
						<td width=45%><a href="detail.jsp?no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
							<%
								//new 새글 표시~
								String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								String dbday=vo.getRegdate().toString();
								if(today.equals(dbday)){									
							%>
									&nbsp;<sup style="color:red">new</sup>
							<%
								}
							%>
						</td>
						<td width=15% class="text-center"><%=vo.getName() %></td>
						<td width=20% class="text-center"><%=vo.getRegdate().toString() %></td>
						<td width=10% class="text-center"><%=vo.getHit() %></td>
					</tr>
				<%
					}
				%>
			</table>
			<table class=table>
				<tr>
					<td>
					<form method=post action=find.jsp>
						search:
						<select name=fs class=input-sm>
							<option value="name">이름</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
						</select>
						<input type=text name=ss size=15 class=input-sm id=ss>
						<input type=button value=찾기 class="btn btn-sm btn-primary" id=findBtn>
					</form>
					</td>
					<td class=text-right>
						<a href="list.jsp?page=<%= curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-danger">이전</a>
							<%=curpage %> page / <%=totalpage %> pages
						<a href="list.jsp?page=<%= curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-danger">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>