<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     쿠키가 전송  => 로그인(아마존) , 세션으로 변경됨 => 최신 본 영화 (다른 컴퓨터로 이동시에는 데이터가 없다)
               => 자동 로그인  
     ========= 쿠키는 서버에서 클라이언트로 전송 (단점 : 문자열만 저장이 가능 , 키는 반드시 알파벳으로 시작한다)
     쿠키 단계
     1) 쿠키 생성 단계
        Cookie cookie=new Cookie(키,값) => 키, 값이 저장 
     2) 쿠키 저장 단계
        사용기간 지정 => setMaxAge(초) => setMaxAge(60) => 60*60 => 1시간 , 60*60*24 => 1일 
                   => 쿠키를 제거 => setMaxAge(0)
     3) 쿠키 전송 단계 
        response.addCookie(cookie) => 클라이언트로 전송 
     4) 기타 : 쿠키읽기 
        Cookie[] cook=request.getCookies()
     5) 루트에 저장 => 경로명 설정 => setPath("/")
     
     ** response => HTML|Cookie ==> 평상시 (HTML) 
                   =============
                   중간에 Cookie를 전송할 프로그램을 제어  => detail_before.jsp
 --%>
<%
    String mno=request.getParameter("mno");
    // Cookie에 저장 
    Cookie cookie=new Cookie("m"+mno,mno);
    cookie.setMaxAge(60*60); // 1시간
    // 클라이언트 전송 
    response.addCookie(cookie);
    // 화면 이동 
    response.sendRedirect("detail.jsp?mno="+mno);
%>







