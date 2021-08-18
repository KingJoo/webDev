package main;

import java.io.*;
import java.util.*;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 서버에서 클라이언트로 전송 -> HTML 전송
		// 필요한 데이터 저장 -> HTML
		PrintWriter out = response.getWriter(); // 브라우저 읽어갈 위치를 지정한다
		MusicDAO dao= new MusicDAO();
		String no=request.getParameter("no");
		MusicVO vo=dao.musicDetailData(Integer.parseInt(no));
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>"+vo.getTitle()+"</h1>");
		out.println("<h3>"+vo.getSinger()+"</h3>");
		out.println("<embed src=//youtube.com/embed/"+vo.getKey()+" width=700 height=350>");// 동영상 실행 태그 //<iframe> : 보안(VueJS,ReactJS) // <video> : 파일이 있을 때 사용 가능
		out.println("<a href=MusicList>목록</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
}
