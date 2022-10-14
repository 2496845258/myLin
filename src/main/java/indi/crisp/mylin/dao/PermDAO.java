package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Perm;

public interface PermDAO {

	Perm findPermByID(@Param("pid") int pid);
	List<Perm> findPermList(@Param("start") int start, @Param("count") int count);
	Integer insertPerm(@Param("perm") Perm perm);
	Integer updatePerm(@Param("perm") Perm perm);
	Integer updatePermAuto(@Param("perm") Perm perm);
	Integer deletePerm(@Param("pid") int pid);
	Integer countAll();
}
