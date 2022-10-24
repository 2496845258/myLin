package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Remark;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 14:10
 * 便签相关的业务接口
 * 便签是不记录日志的
 */
public interface RemarkService {
    //增删改查

    /**
     * 新增一个便签
     * @param remark-便签对象
     * @return int-表示执行状态
     * @throws AppAbnormal
     */
    int insertRemark(Remark remark) throws AppAbnormal;

    /**
     * 删除一个便签
     * @param kid-需要删除的便签id
     * @return int-执行结果码
     * @throws AppAbnormal
     */
    int deleteRemark(int kid) throws AppAbnormal;

    /**
     * 更新一个便签的信息
     * @param remark
     * @return
     * @throws AppAbnormal
     */
    int updateRemark(Remark remark) throws AppAbnormal;

    /**
     * 分页查询
     * @param index
     * @param step
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Remark>> findRemarkList(int index, int step) throws AppAbnormal;
}
