package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.LogDAO;
import indi.crisp.mylin.pojo.Log;
import indi.crisp.mylin.service.LogService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.MybatisUtil;
import indi.crisp.mylin.util.Paginate;

public class LogServiceImpl implements LogService {
    @Override
    public int insertLog(Log log) throws AppAbnormal {
        if ( log == null ) {
            throw new AppAbnormal(AppEnum.ERROR_LOG_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var logDAO = session.getMapper(LogDAO.class);
            if ( logDAO.insertLog(log) > 0 ) {
                session.commit();
                return AppEnum.LEAVE_INSERT_YES.getCode();
            }
            return AppEnum.LOG_INSERT_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Log>> findLogList(int index, int step) throws AppAbnormal {
        if ( index < 0 || step < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_LOG_FIND_LIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var logDAO = session.getMapper(LogDAO.class);
            var logs = logDAO.findLogList(index, step);
            return new Feedback<Paginate>().setResult(new Paginate<Log>().setList(logs).setIndex(index).setStep(logs.size()));
        } finally {
            session.close();
        }
    }
}
