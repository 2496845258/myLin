package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Leave;

public interface LeaveDAO {

	Leave findLeaveByID(@Param("vid") int vid);
	List<Leave> findLeaveList(@Param("start") int start, @Param("count") int count);
	Integer insertLeave(@Param("leave") Leave leave);
	Integer updateLeave(@Param("leave") Leave leave);
	Integer updateLeaveAuto(@Param("leave") Leave leave);
	Integer deleteLeave(@Param("vid") int vid);
	Integer countAll();

	/**
	 * 查询一个员工的请假记录
	 * @param eid
	 * @param start
	 * @param count
	 * @return
	 */
	List<Leave> findEmpIdList(@Param("eid") int eid, @Param("start") int start, @Param("count") int count);

	/**
	 * 查询一个部门的请假记录
	 * @param did
	 * @param start
	 * @param count
	 * @return
	 */
	List<Leave> findDeptLeave(@Param("did") int did, @Param("start") int start, @Param("count") int count);

	/**
	 * 根据审批人查询自己能审批的请假记录
	 * @param tid
	 * @param start
	 * @param count
	 * @return
	 */
	List<Leave> findToId(@Param("tid") int tid, @Param("start") int start, @Param("count") int count);
}
