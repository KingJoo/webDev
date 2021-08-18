package dao;
import java.sql.*;
import java.util.*;

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
	
	//연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//해제
	public void disConnection() {
		try {
			if(ps!=null)	ps.close();
			if(conn!=null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 아래 메소드들 : 기능 처리
	
	// 오라클에서 모든 데이터 가져오기
	public ArrayList<EmpVO> empAllData(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno,"
					+ "(SELECT SUM(sal) FROM emp),(SELECT ROUND(AVG(sal)) FROM emp) "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);//쿼리 준비
			ResultSet rs=ps.executeQuery();//결과값 = 준비된쿼리.실행요청
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setMgr(rs.getInt(4));
				vo.setHiredate(rs.getDate(5));
				vo.setSal(rs.getInt(6));
				vo.setComm(rs.getInt(7));
				vo.setDeptno(rs.getInt(8));
				vo.setSum(rs.getInt(9));
				vo.setAvg(rs.getDouble(10));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
}
