package indi.crisp.mylin.service;


import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Dept;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:10
 * 部门相关的业务接口，
 * 部门的操作是需要记录到数据库中
 */
public interface DeptService {

    /**
     * 新增部门
     * @param dept
     * @return
     * @throws AppAbnormal
     */
    int insertDept(Dept dept) throws AppAbnormal;

    /**
     * 遣散部门
     * @param dno
     * @return
     * @throws AppAbnormal
     */
    int deleteDept(int dno) throws AppAbnormal;

    /**
     * 更新部门负责人
     * @param dept
     * @return
     * @throws AppAbnormal
     */
    int updateDept(Dept dept) throws AppAbnormal;

    /**
     * 分页查看部门信息
     * @param start
     * @param limit
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Dept>> findList(int start, int limit) throws AppAbnormal;

    /**
     * 查看一个部门的信息
     * @param id
     * @return
     * @throws AppAbnormal
     */
    Feedback<Dept> findDept(int id) throws AppAbnormal;
}
