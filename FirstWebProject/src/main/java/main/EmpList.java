package main;
import java.util.*;
import dao.*;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EmpList")
public class EmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서버에서 클라이언트로 전송 -> HTML 전송
		response.setContentType("text/html;charset=UTF-8");
		// 필요한 데이터 저장 -> HTML
		PrintWriter out = response.getWriter(); // 브라우저 읽어갈 위치를 지정한다
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<ul>");
		out.println("<li style=\"display:inline-block;width:150px\">사원목록</li>");
		out.println("<li style=\"display:inline-block;width:150px\">사원상세</li>");
		out.println("<li style=\"display:inline-block;width:150px\">사원등록</li>");
		out.println("<li style=\"display:inline-block;width:150px\">사원수정</li>");
		out.println("<li style=\"display:inline-block;width:150px\">사원삭제</li>");
		out.println("</ul>");
		out.println("<center>"); // CSS(margin:0px auto)
		out.println("<h1>사원 목록</h1>");
		out.println("<table border=1 bordercolor=black width=90%");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>사번</th>");
		out.println("<th>이름</th>");
		out.println("<th>직위</th>");
		out.println("<th>사수</th>");
		out.println("<th>입사일</th>");
		out.println("<th>급여</th>");
		out.println("<th>성과급</th>");
		out.println("<th>부서번호</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		EmpDAO dao=new EmpDAO();
		ArrayList<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list) {
			out.println("<tr>");
			out.println("<td>"+vo.getEmpno()+"</td>");
			out.println("<td>"+vo.getEname()+"</td>");
			out.println("<td>"+vo.getJob()+"</td>");
			out.println("<td>"+vo.getMgr()+"</td>");
			out.println("<td>"+vo.getHiredate().toString()+"</td>");
			out.println("<td>"+vo.getSal()+"</td>");
			out.println("<td>"+vo.getComm()+"</td>");
			out.println("<td>"+vo.getDeptno()+"</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("<tfoot>");
		out.println("<tr>");
		out.println("<td>급여합</td>");
		out.println("<td colspan=3>"+list.get(0).getSum()+"</td>");
		out.println("<td>급여평균</td>");
		out.println("<td colspan=3>"+list.get(0).getAvg()+"</td>");
		out.println("</tr>");
		out.println("</tfoot>");
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
}
