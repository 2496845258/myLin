package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Dict;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:27
 * 数据字典相关的业务接口，
 * 数据字典只能查询
 */
public interface DictService {
    /**
     * 根据key查到某个数据字典中的记录
     * @param key
     * @return
     * @throws AppAbnormal
     */
    Feedback<Dict> findByKey(int key) throws AppAbnormal;
}
