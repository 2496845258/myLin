package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Remark;

public interface RemarkDAO {

	Remark findRemarkByID(@Param("kid") int kid);
	List<Remark> findRemarkList(@Param("start") int start, @Param("count") int count);
	Integer insertRemark(@Param("remark") Remark remark);
	Integer updateRemark(@Param("remark") Remark remark);
	Integer updateRemarkAuto(@Param("remark") Remark remark);
	Integer deleteRemark(@Param("kid") int kid);
	Integer countAll();
}
