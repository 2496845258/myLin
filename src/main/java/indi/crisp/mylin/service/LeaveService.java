package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Leave;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:31
 * 员工请假相关的业务接口，
 * 员工请假的操作是需要记录到数据库中
 */
public interface LeaveService {
    /**
     * 记录一个员工的请假
     * @param leave
     * @return
     * @throws AppAbnormal
     */
    int insertLeave(Leave leave) throws AppAbnormal;

    /**
     * 更新一个员工的请假信息
     * @param leave
     * @return
     * @throws AppAbnormal
     */
    int updateLeave(Leave leave) throws AppAbnormal;

    /**
     * 分页查看请假信息
     * @param start
     * @param limit
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Leave>> findList(int start, int limit) throws AppAbnormal;

    /**
     * 分页查看某个员工的所有请假信息
     * @param eid
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Leave>> findList(int eid) throws AppAbnormal;
}
