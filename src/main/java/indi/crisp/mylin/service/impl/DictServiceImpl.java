package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.DictDAO;
import indi.crisp.mylin.pojo.Dict;
import indi.crisp.mylin.service.DictService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.MybatisUtil;

public class DictServiceImpl implements DictService {
    @Override
    public Feedback<Dict> findByKey(int key) throws AppAbnormal {
        if ( key < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_DICT_ID);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var dictDAO  = session.getMapper(DictDAO.class);
            var dict = dictDAO.findByKey(key);
            if ( dict == null ) {
                return new Feedback<>().setResult(dict).setStatusCode(AppEnum.DICT_FIND_YES.getCode());
            }
            return new Feedback<>().setStatusCode(AppEnum.DICT_FIND_NO.getCode()).setExplain(AppEnum.DICT_FIND_NO.getDescribe());
        } finally {
            session.close();
        }
    }
}
