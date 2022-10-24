package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.DeptDAO;
import indi.crisp.mylin.dao.PermDAO;
import indi.crisp.mylin.pojo.Dept;
import indi.crisp.mylin.pojo.Perm;
import indi.crisp.mylin.service.PermService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.MybatisUtil;
import indi.crisp.mylin.util.Paginate;

public class PermServiceImpl implements PermService {
    @Override
    public Feedback<Paginate<Perm>> findPermByRoleID(int rid) throws AppAbnormal {
        if ( rid < 1 ) {
            throw new AppAbnormal(AppEnum.ERROR_DEPT_FINDLIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var permDAO = session.getMapper(PermDAO.class);
            var count = permDAO.countAll();
            var permList = permDAO.findEmpnoList(rid);
            return new Feedback<>().setStatusCode(AppEnum.PERM_FIND_YES.getCode()).setResult(new Paginate<Perm>().setList(permList));
        } finally {
            session.close();
        }
    }
}
