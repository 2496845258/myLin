package indi.crisp.mylin.service;


import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Dept;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:10
 * 部门相关的业务接口，
 * 部门的操作是需要记录到数据库中
 */
public interface DeptService {
    int insertDept(Dept dept) throws AppAbnormal;
    int deleteDept(int dno) throws AppAbnormal;
    int updateDept(Dept dept) throws AppAbnormal;
}
