package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Perm;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:26
 * 权限相关的业务接口，
 * 权限的操作是需要记录到数据库中
 */
public interface PermService {
    int insertPerm(Perm perm) throws AppAbnormal;
    int deletePerm(int pid) throws AppAbnormal;
    int updatePerm(Perm perm) throws AppAbnormal;
}
