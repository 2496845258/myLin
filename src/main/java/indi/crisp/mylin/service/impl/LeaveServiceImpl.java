package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Leave;
import indi.crisp.mylin.service.LeaveService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

public class LeaveServiceImpl implements LeaveService {
    @Override
    public int insertLeave(Leave leave) throws AppAbnormal {
        return 0;
    }

    @Override
    public int updateLeave(Leave leave) throws AppAbnormal {
        return 0;
    }

    @Override
    public Feedback<Paginate<Leave>> findList(int start, int limit) throws AppAbnormal {
        return null;
    }

    @Override
    public Feedback<Paginate<Leave>> findList(int eid) throws AppAbnormal {
        return null;
    }
}
