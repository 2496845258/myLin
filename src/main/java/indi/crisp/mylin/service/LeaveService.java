package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Leave;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:31
 * 员工离职相关的业务接口，
 * 员工离职的操作是需要记录到数据库中
 */
public interface LeaveService {
    /**
     * 记录一个员工的离职
     * @param leave
     * @return
     * @throws AppAbnormal
     */
    int insertLeave(Leave leave) throws AppAbnormal;

    /**
     * 更新一个员工的离职信息
     * @param leave
     * @return
     * @throws AppAbnormal
     */
    int updateLeave(Leave leave) throws AppAbnormal;
}
