package indi.crisp.mylin.dao;

import indi.crisp.mylin.abnormal.AppAbnormal;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Employee;

public interface EmployeeDAO {

	Employee findEmployeeByID(@Param("empno") int empno);
	List<Employee> findEmployeeList(@Param("start") int start, @Param("count") int count);
	Integer insertEmployee(@Param("employee") Employee employee);
	Integer updateEmployee(@Param("employee") Employee employee);
	Integer updateEmployeeAuto(@Param("employee") Employee employee);
	Integer deleteEmployee(@Param("empno") int empno);
	Integer countAll();
	// 根据账号查找员工
	Employee findEmployeebyAccount(@Param("account") String account) throws AppAbnormal;

	/**
	 * 根据员工id查找出部门负责人
	 * @param eid
	 * @return
	 */
	Employee findEmpDeptHost(@Param("eid") int eid);

	/**
	 * 根据部门id查找负责人
	 * @param did
	 * @return
	 */
	Employee findDidEmp(@Param("did") int did);

	/**
	 * 查看普通员工的聊天列表
	 * @param eid
	 * @return
	 */
	List<Employee> findDeptEmpList(@Param("eid") int eid, @Param("start") int start, @Param("count") int count);

	/**
	 * 查看经理的聊天列表
	 * @param eid
	 * @param start
	 * @param count
	 * @return
	 */
	List<Employee> findDeptHostEmpList(@Param("eid") int eid, @Param("start") int start, @Param("count") int count);
}
