<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<%
	//한글 변환
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception e){}
	//전송된 값 받기
	String no=request.getParameter("no");
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	//묶어서
	BoardVO vo=new BoardVO();
	vo.setNo(Integer.parseInt(no));
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	BoardDAO dao= new BoardDAO();
	//수정하기 메소드 호출 -> 매개변수 ->vo전송
	boolean bCheck=dao.boardUpdate(vo);
	//4.이동 -> 내용보기로 이동(내용변경)
	if(bCheck){
		//수정이 된 경우(detail.jsp)
		response.sendRedirect("detail.jsp?no="+vo.getNo());
	}
	else{//비밀번호 틀림 -> 원상복귀(update.jsp)
%>
	<script>
	alert("비밀번호가 틀립니다\n다시 입력하세요");
	history.back();
	</script>
<%
	}
%>