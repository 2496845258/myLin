package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Dept;

public interface DeptDAO {

	Dept findDeptByID(@Param("dno") int dno);
	List<Dept> findDeptList(@Param("start") int start, @Param("count") int count);
	Integer insertDept(@Param("dept") Dept dept);
	Integer updateDept(@Param("dept") Dept dept);
	Integer updateDeptAuto(@Param("dept") Dept dept);
	Integer deleteDept(@Param("dno") int dno);
	Integer countAll();

	/**
	 * 根据部门id查找负责人
	 * @param did
	 * @return
	 */
	Dept findDidEmp(@Param("did") int did);
}
