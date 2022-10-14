package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Role;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:17
 * 角色相关的业务接口，
 * 角色的操作是需要记录到数据库中
 */
public interface RoleService {
    int insertRole(Role role) throws AppAbnormal;
    int deleteRole(int rid) throws AppAbnormal;
    int updateRole(Role role) throws AppAbnormal;

}
