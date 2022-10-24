package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Notify;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:35
 * 通知相关的业务接口，
 * 通知相关的操作是需要记录到数据库中
 */
public interface NotifyService {
    /**
     * 新增一条记录
     * @param notify
     * @return
     * @throws AppAbnormal
     */
    int insert(Notify notify) throws AppAbnormal;

    /**
     * 查看自己和某个人的消息
     * @param eid1
     * @param eid2
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Notify>> findByEmpID(int eid1, int eid2, int index, int step) throws AppAbnormal;

    /**
     * 分页查看全部人的所有消息
     * @param start
     * @param limit
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Notify>> findList(int start, int limit) throws AppAbnormal;
}
