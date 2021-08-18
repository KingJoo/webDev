package main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BoardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String html="<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "<title>Insert title here</title>\n"
				+ "<link rel=\"stylesheet\" href=\"table.css\">\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "	<center>\n"
				+ "		<h1>자유게시판</h1>\n"
				+ "		<table id=\"table_content\" width=800>\n"
				+ "			<tr>\n"
				+ "				<td>\n"
				+ "					<a href=\"BoardInsert\">새글</a>\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "		</table>\n"
				+ "		<div style=\"height:390px\">"
				+ "		<table id=\"table_content\" width=800>\n"
				+ "			<tr>\n"
				+ "				<th width=10%>번호</th>\n"
				+ "				<th width=45%>제목</th>\n"
				+ "				<th width=15%>이름</th>\n"
				+ "				<th width=20%>작성일</th>\n"
				+ "				<th width=10%>조회수</th>\n"
				+ "			</tr>";
		out.println(html);
		
		BoardDAO dao=new BoardDAO();
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curpage=Integer.parseInt(strPage);
		ArrayList<BoardVO> list=dao.boardListData(curpage);
		int totalpage=dao.boardTotalPage();
		for(BoardVO vo:list) {
			out.println("<tr>");
			out.println("<td width=10% align=center>"+vo.getNo()+"</td>");
			out.println("<td width=45%>"+vo.getSubject());
			String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String dbday=vo.getRegdate().toString();
			if(today.equals(dbday)) {
				out.println("&nbsp;<sup><font color=red>new</font></sup>");
			}
			out.println("</td>");
			out.println("<td width=15% align=center>"+vo.getName()+"</td>");
			out.println("<td width=20% align=center>"+vo.getRegdate()+"</td>");
			out.println("<td width=10% align=center>"+vo.getHit()+"</td>");
			out.println("</tr>");
		}
		html="</table></div>"
				+ "<table id=\"table_content\" width=800>\n"
				+ "			<tr>\n"
				+ "				<td align=left>\n"
				+ "					Search:\n"
				+ "					<select name=fs>\n"
				+ "						<option value=name>이름</option>\n"
				+ "						<option value=subject>제목</option>\n"
				+ "						<option value=content>내용</option>\n"
				+ "					</select>\n"
				+ "					<input type=text name=ss size=15>\n"
				+ "					<input type=submit value=찾기>\n"
				+ "				</td>\n"
				+ "				<td align=right>\n"
				+ "					<a href=BoardList?page="+(curpage>1?curpage-1:curpage)+">이전</a>&nbsp;\n"
				+						curpage+" page / "+totalpage+" page&nbsp;"
				+ "					<a href=BoardList?page="+(curpage<totalpage?curpage+1:curpage)+">다음</a>\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "		</table>\n"
				+ "	</center>\n"
				+ "</body>\n"
				+ "</html>";
		out.println(html);
	}
}
