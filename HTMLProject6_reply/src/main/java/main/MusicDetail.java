package main;

import java.io.*;
import java.util.ArrayList;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 화면 출력, 화면 UI
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String no=request.getParameter("no");
		MelonDAO dao=new MelonDAO();
		MelonVO vo=dao.melonDetailData(Integer.parseInt(no));
		ArrayList<MelonReplyVO> list=dao.replyListData(Integer.parseInt(no));
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>"+vo.getTitle()+"</h1>");
		out.println("<h3>"+vo.getSinger()+"</h3>");
		out.println("<embed src=http://youtube.com/embed/"+vo.getKey()+"  width=800 height=450></embed>");
		out.println("<table id=table_content width=800>");
		out.println("<tr>");
		out.println("<td align=right>");
		out.println("<a href=MusicList>목록</a></td>");
		out.println("</tr>");
		out.println("</table>");
		// 댓글 
		out.println("<div style=\"height:30px\"></div>");//inline style
		out.println("<table id=table_content width=800>");
		out.println("</td></tr>");
		for(MelonReplyVO mvo:list) {
			out.println("<table id=table_content width=800>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<span style=\"color:blue\">"+mvo.getName()+"</span>");
			out.println("&nbsp;"+mvo.getDbday());
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td valign=top align=left height=150>");
			out.println("<pre style=\"white-space:pre-wrap;overflow-y:auto\">"+mvo.getMsg()+"</pre>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
		}
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<form method=post action=MusicDetail>");
		// MusicDetail로 데이터를 전송한다 => 호출하는 메소드 doPost
		// Web에서 데이터를 전송 => 사용자가 보낸 모든 데이터는 request안에 들어 온다 
		// request는 형식 Map
		/*
		 *    request.setAttribute("name","홍길동")
		 *    request.setAttribute("msg","댓글...");
		 *    request.setAttribute("no",1)
		 *    ====================================== tomcat
		 *    
		 *    ==> request.getParameter("name"); =>  홍길동 => servlet,JSP 동일하게 처리 JSTL
		 *    
		 *    구구단
		 *      <table>
		 *       <tr>
		 *         <%
		 *              for(int i=2;i<=9;i++)
		 *              {
		 *         %>
		 *                 <td><%= i+"단"%></td>
		 *         <%
		 *              }
		 *         %>
		 *       </tr>
		 *       <%
		 *           for(int i=1;i<=9;i++)
		 *           {
		 *       %>
		 *               <tr>
		 *       <%
		 *               for(int j=2;j<=9;j++)
		 *               {
		 *        %>
		 *        
		 *                  <td><%= j+"*"+i+"="+j*i%>
		 *        <%
		 *               }
		 *        %>
		 *               </tr>
		 *        <%
		 *               }
		 *        %>
		 *        
		 *        <c:forEach var="i" begin="1" end="9">
		 *          <tr>
		 *          <c:forEach var="j" begin="2" end="9">
		 *           <td>출력</td>
		 *          </c:forEach>
		 *          </tr>
		 *        </c:forEach> ==> spring
		 *       
		 */
		out.println("<table id=table_content width=800>");
		out.println("<tr>");
		out.println("<td>이름:");
		out.println("<input type=text name=name size=15></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<input type=hidden name=no value="+vo.getNo()+">"); // 뮤직번호 (프로그래머가 알 수 있게 표시=> <input type=hidden>
		// hidden => 데이터가 전송이 가능 
		out.println("<textarea rows=5 cols=80 name=msg style=\"float:left\"></textarea>");
		// float:left   => button (<input type=submit>)
		out.println("<button style=\"width:100px;height:80px;background-color:blue;color:white;float:left;-webkit-border-radius:5px;margin-left:5px\">댓글쓰기</button>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	// 사용자가 넘겨준 데이터를 받아서 처리, 데이터베이스 연동
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8"); // 디코딩으로 받아 처리
			/*
			 * 한글 -> %ED%95%9C%EA (인코딩)
			 * %ED%95%9C%EA -> 한글 (디코딩)
			 */
		} catch (Exception e) {}
		String mno=request.getParameter("no");//hidden에서 받아온 음악번호
		String name=request.getParameter("name");
		String msg=request.getParameter("msg");
		
		MelonReplyVO vo = new MelonReplyVO();
		vo.setMno(Integer.parseInt(mno));
		vo.setName(name);
		vo.setMsg(msg);
		
		//DAO로 전송 -> 오라클에 추가
		MelonDAO dao=new MelonDAO();
		dao.replyInsert(vo);
		
		//이동(새로고침)
		response.sendRedirect("MusicDetail?no="+mno); //댓글 출력
	}

}
