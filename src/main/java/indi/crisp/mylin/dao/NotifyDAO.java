package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Notify;

public interface NotifyDAO {

	Notify findNotifyByID(@Param("nid") int nid);
	List<Notify> findNotifyList(@Param("start") int start, @Param("count") int count);
	Integer insertNotify(@Param("notify") Notify notify);
	Integer updateNotify(@Param("notify") Notify notify);
	Integer updateNotifyAuto(@Param("notify") Notify notify);
	Integer deleteNotify(@Param("nid") int nid);
	Integer countAll();

	List<Notify> findByEmpID(@Param("fid") int fid, @Param("tid") int tid, @Param("start") int start, @Param("count") int count );
}
