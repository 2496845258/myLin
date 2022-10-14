package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Log;

public interface LogDAO {

	Log findLogByID(@Param("lid") int lid);
	List<Log> findLogList(@Param("start") int start, @Param("count") int count);
	Integer insertLog(@Param("log") Log log);
	Integer updateLog(@Param("log") Log log);
	Integer updateLogAuto(@Param("log") Log log);
	Integer deleteLog(@Param("lid") int lid);
	Integer countAll();
}
