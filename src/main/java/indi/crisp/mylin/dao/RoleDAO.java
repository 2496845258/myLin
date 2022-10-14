package indi.crisp.mylin.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import indi.crisp.mylin.pojo.Role;

public interface RoleDAO {

	Role findRoleByID(@Param("rid") int rid);
	List<Role> findRoleList(@Param("start") int start, @Param("count") int count);
	Integer insertRole(@Param("role") Role role);
	Integer updateRole(@Param("role") Role role);
	Integer updateRoleAuto(@Param("role") Role role);
	Integer deleteRole(@Param("rid") int rid);
	Integer countAll();
}
