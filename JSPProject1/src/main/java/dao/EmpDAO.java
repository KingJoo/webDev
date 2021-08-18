package dao;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.186:1521:XE";
	
	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void disConnection() {
		try {
			if(ps!=null)	ps.close();
			if(conn!=null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//데이터 읽기
	public ArrayList<EmpVO> empListData(){
		ArrayList<EmpVO> list=new ArrayList<>();
		try {
			getConnection();
			String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,dname,loc,grade "
					+ "FROM emp,dept,salgrade "
					+ "WHERE emp.deptno=dept.deptno "
					+ "AND sal BETWEEN losal AND hisal "
					+ "ORDER BY sal DESC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setMgr(rs.getInt(4));
				vo.setHiredate(rs.getDate(5));
				vo.setSal(rs.getInt(6));
				vo.setComm(rs.getInt(7));
				vo.getDvo().setDname(rs.getString(8));
				vo.getDvo().setLoc(rs.getString(9));
				vo.getSvo().setGrade(rs.getInt(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	public static void main(String[] args) {
		EmpDAO dao=new EmpDAO();
		ArrayList<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEname());
		}
	}
}
