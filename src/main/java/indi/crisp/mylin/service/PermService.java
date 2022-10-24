package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Perm;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:26
 * 权限相关的业务接口，
 * 权限的操作是需要记录到数据库中
 */
public interface PermService {
    /**
     * 根据角色id查询对应的权限
     * @param rid
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Perm>> findPermByRoleID(int rid) throws AppAbnormal;
}
