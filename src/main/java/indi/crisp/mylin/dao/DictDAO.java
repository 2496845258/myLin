package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Dict;

public interface DictDAO {

	Dict findDictByID(@Param("did") int did);
	List<Dict> findDictList(@Param("start") int start, @Param("count") int count);
	Integer insertDict(@Param("dict") Dict dict);
	Integer updateDict(@Param("dict") Dict dict);
	Integer updateDictAuto(@Param("dict") Dict dict);
	Integer deleteDict(@Param("did") int did);
	Integer countAll();

	Dict findByKey(@Param("key") int key);
}
