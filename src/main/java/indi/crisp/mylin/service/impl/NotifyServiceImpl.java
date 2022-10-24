package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.NotifyDAO;
import indi.crisp.mylin.pojo.Notify;
import indi.crisp.mylin.service.NotifyService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.MybatisUtil;
import indi.crisp.mylin.util.Paginate;

public class NotifyServiceImpl implements NotifyService {
    @Override
    public int insert(Notify notify) throws AppAbnormal {
        if ( notify == null ) {
            throw new AppAbnormal(AppEnum.ERROR_NOTIFY_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var notifyDAO = session.getMapper(NotifyDAO.class);
            if ( notifyDAO.insertNotify(notify) > 0 ) {
                session.commit();
                return AppEnum.NOTIFY_INSERT_YES.getCode();
            }
            return AppEnum.NOTIFY_INSERT_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Notify>> findByEmpID(int eid1, int eid2, int index, int step) throws AppAbnormal {
        if ( eid1 < 0 || eid2 < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_NOTIFY_FIND_TAR);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var notifyDAO = session.getMapper(NotifyDAO.class);
            var notifies = notifyDAO.findByEmpID(eid1, eid2, index, step);
            return new Feedback<>().setResult(new Paginate<Notify>().setList(notifies).setStep(notifies.size()).setIndex(index));
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Notify>> findList(int start, int limit) throws AppAbnormal {
        if ( start < 0 || limit < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_NOTIFY_FIND_LIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var notifyDAO = session.getMapper(NotifyDAO.class);
            var notifies = notifyDAO.findNotifyList(start, limit);
            return new Feedback<>().setResult(new Paginate<Notify>().setList(notifies).setIndex(start).setStep(notifies.size()));
        } finally {
            session.close();
        }
    }
}
