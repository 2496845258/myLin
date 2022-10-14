package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Log;
import indi.crisp.mylin.util.Feedback;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:27
 * 日志记录相关的业务接口，
 * 日志的操作是需要记录到数据库中
 */
public interface LogService {
    /**
     * 日志能新增
     * @param log
     * @return
     * @throws AppAbnormal
     */
    int insertLog(Log log) throws AppAbnormal;

    /**
     * 日志能查看
     * @param index
     * @param step
     * @return
     * @throws AppAbnormal
     */
    Feedback<Log> findLogList(int index, int step) throws AppAbnormal;
}
