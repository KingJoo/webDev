package main;

import java.io.*;

import dao.BoardDAO;
import dao.BoardVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 화면 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				+ "		<h1>글쓰기</h1>\n"
				+ "		<form method=post action=\"BoardInsert\">\n"
				+ "		<table id=table_content width=600>\n"
				+ "			<tr>\n"
				+ "				<th width=20% align=right>이름</th>\n"
				+ "				<td width=80%>\n"
				+ "					<input type=text name=name size=50>\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "			<tr>\n"
				+ "				<th width=20% align=right>제목</th>\n"
				+ "				<td width=80%>\n"
				+ "					<input type=text name=subject size=50>\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "			<tr>\n"
				+ "				<th width=20% align=right>내용</th>\n"
				+ "				<td width=80%>\n"
				+ "					<textarea rows=20 cols=20 name=content></textarea>\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "			<tr>\n"
				+ "				<th width=20% align=right>비밀번호</th>\n"
				+ "				<td width=80%>\n"
				+ "					<input type=password name=pwd size=50>\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "			<tr>\n"
				+ "				<td colspan=2 align=center>\n"
				+ "					<input type=submit value=\"글쓰기\">\n"
				+ "					<input type=button value=\"취소\" onclick=\"javascript:history.back()\">\n"
				+ "				</td>\n"
				+ "			</tr>\n"
				+ "		</table>\n"
				+ "		</form>\n"
				+ "	</center>\n"
				+ "</body>\n"
				+ "</html>";
		out.println(html);
	}

	// 처리(Insert, Update)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// request -> 사용자의 요청값을 지님
			// 1->2byte 디코딩
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO dao=new BoardDAO();
		dao.boardInsert(vo);
		
		response.sendRedirect("BoardList");
	}

}
