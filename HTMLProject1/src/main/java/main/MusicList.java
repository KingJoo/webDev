package main;

import java.io.*;
import java.util.*;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MusicList")
public class MusicList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 서버에서 클라이언트로 전송 -> HTML 전송
		// 필요한 데이터 저장 -> HTML
		PrintWriter out = response.getWriter(); // 브라우저 읽어갈 위치를 지정한다
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>뮤직 목록</h1>");
		out.println("<table width=90% id=table_content>");
		out.println("<tr bgcolor=green>");
		out.println("<th></th>");
		out.println("<th>노래명</th>");
		out.println("<th>가수명</th>");
		out.println("<th>앨범</th>");
		out.println("</tr>");
		
		MusicDAO dao = new MusicDAO();
		ArrayList<MusicVO> list = dao.musicAllData();
		for(MusicVO vo : list) {
			out.println("<tr class=dataTr>");
			out.println("<td align=center><img src="+vo.getPoster()+" width=30 height=30></td>");
			out.println("<td><a href=MusicDetail?no="+vo.getNo()+">"+vo.getTitle()+"</a></td>");
			out.println("<td>"+vo.getSinger()+"</td>");
			out.println("<td>"+vo.getAlbum()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		
		
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}
}