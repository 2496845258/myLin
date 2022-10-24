package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Notify;
import indi.crisp.mylin.service.NotifyService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

public class NotifyServiceImpl implements NotifyService {
    @Override
    public int insert(Notify notify) throws AppAbnormal {
        return 0;
    }

    @Override
    public Feedback<Notify> findByEmpID(int eid1, int eid2) throws AppAbnormal {
        return null;
    }

    @Override
    public Feedback<Paginate<Notify>> findList(int start, int limit) throws AppAbnormal {
        return null;
    }
}
