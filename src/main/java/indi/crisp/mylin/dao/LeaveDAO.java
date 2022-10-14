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
}
