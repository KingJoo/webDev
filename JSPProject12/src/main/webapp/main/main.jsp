<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      스프링 : @SessionAttribute
      232 page 
      => 233page => 브라우저마다 세션이 따로 존재(각 클라이언트마다 따로 생성)
      => 쿠키는 클라이언트 측의 데이터 저장소 
      => 세션은 서버측에 데이터 저장소  
      서버에 저장 
      1. 로그인 => 서버에 클라이언트의 기본정보 저장 
                 ID,Name
                 ======= Session,Cookie => 원하는 JSP에서 사용이 가능
                         (전역변수)
      2. 장바구니 => 임시 저장 
      3. 예매하기 
      4. 댓글쓰기 
      ==========================================================
      session을 사용하기 위한 기능 (내장 객체) => 미리 생성이 된 객체 
      => request , response , out , application , pageContext , exception , (page=this,config=web.xml)
         session 
      => ***1) 저장 : setAttribute(키,값) => 키(String) , 값(Object) => 모든 클래스를 사용 할 수 있다 
         ***2) 읽기 : getAttribute(키)
         3) 저장 : => 30분 => setMaxactiveInterval(초) => 1800초 ==> 30분
         ***4) 삭제 :
             = 일부삭제 (장바구니) => removeAttribute()
             = 전체삭제 => invalidate() => 로그아웃시에 주로 사용 
         5) 처음 생성 => isNew()
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>