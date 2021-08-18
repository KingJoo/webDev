<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dao.*" errorPage="error.jsp"%>
<%
	//1.오라클 데이터 읽기
	EmpDAO dao=new EmpDAO();
	ArrayList<EmpVO> list=dao.empListData();
	/*
		1. 목록
		2. 상세보기
		3. 수정, 삭제, 댓글
		4. 새글, 사원등록
		CURD (INSERT, UPDATE, SELECT, DELETE)
		
		VO는 해당 테이블 컬럼만 가지고 있어야 함
		다른 변수명 설정 ---> 날짜, 여러개를 묶어서 사용
		JOIN / SUBQUER ---> 포함클래스
	*/

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel=stylesheet href=table.css>
<style type=text/css>
#container{
	width:100%;
	margin-top:30px;
}
table{
	margin:0px auto;
	width: 900px;
}
h1{
	text-align:center;
}

</style>
</head>
<body>
	<div id=container>
		<h1>사원목록</h1>
		<table>
			<tr>
				<th>사원</th>
				<th>이름</th>
				<th>직위</th>
				<th>사수</th>
				<th>입사일</th>
				<th>급여</th>
				<th>성과급</th>
				<th>부서명</th>
				<th>근무지</th>
				<th>급여등급</th>
			</tr>
			<%
				for(EmpVO vo:list){
			%>
				<tr>
				<td align=center><%=vo.getEmpno()%></td>
				<td align=center><%=vo.getEname()%></td>
				<td align=center><%=vo.getJob()%></td>
				<td align=center><%=vo.getMgr()%></td>
				<td align=center><%=vo.getHiredate()%></td>
				<td align=center><%=vo.getSal()%></td>
				<td align=center><%=vo.getComm()%></td>
				<td align=center><%=vo.getDvo().getDname()%></td>
				<td align=center><%=vo.getDvo().getLoc()%></td>
				<td align=center><%=vo.getSvo().getGrade()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

</body>
</html>