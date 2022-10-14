package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.RemarkDAO;
import indi.crisp.mylin.pojo.Remark;
import indi.crisp.mylin.service.RemarkService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.MybatisUtil;

public class RemarkServiceImpl implements RemarkService {
    @Override
    public int insertRemark(Remark remark) throws AppAbnormal {
        if ( null == remark ) {
            throw new AppAbnormal(AppEnum.ERROR_REMARK_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var remarkDAO = session.getMapper(RemarkDAO.class);
            if ( remarkDAO.insertRemark(remark) > 0 ) {
                return AppEnum.REMARK_INSERT_YES.getCode();
            }
            return AppEnum.REMARK_INSERT_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public int deleteRemark(int kid) throws AppAbnormal {
        if ( 0 > kid ) {
            throw new AppAbnormal(AppEnum.ERROR_REMARK_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var remarkDAO = session.getMapper(RemarkDAO.class);
            if ( remarkDAO.deleteRemark(kid) > 0 ) {
                return AppEnum.REMARK_DELETE_YES.getCode();

            }
            return AppEnum.REMARK_DELETE_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public int updateRemark(Remark remark) throws AppAbnormal {
        if ( null == remark ) {
            throw new AppAbnormal(AppEnum.ERROR_REMARK_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var remarkDAO = session.getMapper(RemarkDAO.class);
            if ( remarkDAO.updateRemarkAuto(remark) > 0 ) {
                return AppEnum.REMARK_UPDATE_YES.getCode();
            }
            return AppEnum.REMARK_UPDATE_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Remark> findRemarkList(int index, int step) throws AppAbnormal {
        //需要缓存，来存储个人便签的个数。查询不可能每次都走数据库的
        return null;
    }
}
