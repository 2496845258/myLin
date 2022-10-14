package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Roleperm;

public interface RolepermDAO {

	Roleperm findRolepermByID(@Param("rprid") int rprid);
	List<Roleperm> findRolepermList(@Param("start") int start, @Param("count") int count);
	Integer insertRoleperm(@Param("roleperm") Roleperm roleperm);
	Integer updateRoleperm(@Param("roleperm") Roleperm roleperm);
	Integer updateRolepermAuto(@Param("roleperm") Roleperm roleperm);
	Integer deleteRoleperm(@Param("rprid") int rprid);
	Integer countAll();
}
