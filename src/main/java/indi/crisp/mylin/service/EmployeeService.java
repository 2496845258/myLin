package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Employee;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:14
 * 员工相关的业务接口
 * 员工的操作是需要记录到数据库中
 */
public interface EmployeeService {
    int insertEmployee(Employee employee) throws AppAbnormal;
    int deleteEmployee(int empno) throws AppAbnormal;
    int updateEmployee(Employee employee) throws AppAbnormal;
}
