<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<%
	//detail.jsp -> no값 받는다
	String no=request.getParameter("no");
	//DAO연결
	BoardDAO dao=new BoardDAO();
	BoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top:30px;
}
.row{
    margin:0px auto;
    width:600px;
}
h1{
 	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//not null일때 처리
	$('#writeBtn').click(function(){
		let name=$('#name').val();
		if(name.trim()===""){
			$('#name').focus();
			return;
		}
		let subject=$('#subject').val();
		if(subject.trim()===""){
			$('#subject').focus();
			return;
		}
		let content=$('#content').val();
		if(subject.trim()===""){
			$('#content').focus();
			return;
		}
		let pwd=$('#pwd').val();
		if(subject.trim()===""){
			$('#pwd').focus();
			return;
		}
		$('form').submit();	// 데이터를 action
	})
})
</script>
</head>
<body>
	<div class="container">
	<h1>수정하기</h1>
		<div class="row">
		<form method=post action=update_ok.jsp>
			<table class=table>
				<tr>
        			<th width=15% class="text-right success">이름</th>
        			<td width=85%>
         				<input type=text name=name id=name size=15 class="input-sm" value=<%=vo.getName() %>>
         				<input type=hidden name=no value=<%=vo.getNo()%>>
          			</td>
           		</tr>
				<tr>
					<th width=15% class="text-right success">제목</th>
					<td width=85%>
						<input type=text name=subject id=subject size=50 class=input-sm value="<%=vo.getSubject() %>">
					</td>
				</tr>
				<tr>
					<th width=15% class="text-right success">내용</th>
					<td width=85%>
						<textarea rows=8 cols=55 name=content id=content><%=vo.getContent()%></textarea>
					</td>
				</tr>
				<tr>
					<th width=15% class="text-right success">비밀번호</th>
					<td width=85%>
						<input type=password name=pwd id=pwd size=10 class=input-sm>
					</td>
				</tr>
				<tr>
					<td colspan=2 class=text-center>
						<input type=button class="btn btn-sm btn-primary" id=writeBtn value=수정>
						<input type=button class="btn btn-sm btn-primary" value="취소" 
            			onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>