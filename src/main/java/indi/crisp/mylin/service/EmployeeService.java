package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.expand.EmployeeVO;
import indi.crisp.mylin.util.Feedback;

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

    int register(String account, String password) throws AppAbnormal;

    /**
     *
     * @param account-账号
     * @param password-密码
     * @return
     * @throws AppAbnormal-泛型中保存的具体为VO
     */
    Feedback<Employee> login(String account, String password) throws AppAbnormal;

    // 根据账号查找员工
    Employee findEmployeebyAccount(String account) throws  AppAbnormal;
}
