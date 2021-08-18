<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	width:960px;
}
</style>
</head>
<body>
<div class=container>
	<div class=row>
		<table class=table>
			<tr>
				<td colspan=2 class=text-center>Header</td>
			</tr>
			<tr>
				<td class=text-center valign=top height=500 width=200>
					<jsp:include page="login.jsp"></jsp:include>
				</td>
				<td class=text-center valign=top height=500 width=760></td>
			</tr>
			<tr>
				<td colspan=2 class=text-center height=100>Footer</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>