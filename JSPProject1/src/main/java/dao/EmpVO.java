package dao;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmpVO {
	private int empno,mgr,sal,comm,deptno;
	private String ename,job;
	private Date hiredate;
	//클래스 포함
	private DeptVO dvo=new DeptVO();
	private SalgradeVO svo=new SalgradeVO();
}
