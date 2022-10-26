package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.expand.EmployeeVO;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;
import org.apache.ibatis.annotations.Param;

/**
 * @author luzhaoya@qq.com
 * @time 2022-10-14 15:14
 * 员工相关的业务接口
 * 员工的操作是需要记录到数据库中
 */
public interface EmployeeService {
    int insertEmployee(Employee employee) throws AppAbnormal;
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

    /**
     * 根据员工id查看员工信息
     * @param eid
     * @return
     * @throws AppAbnormal
     */
    Feedback<Employee> findEmpByID(int eid) throws AppAbnormal;

    /**
     * 根据员工id查看部门负责人(可能为null)
     */
    Feedback<Employee> findEmpDeptHost(int eid) throws AppAbnormal;

    /**
     * 根据部门id查看负责人（可能为null）
     */
    Feedback<Employee> findDidEmp(int deptId) throws AppAbnormal;

    /**
     * 获取员工的联系人聊天列表
     * @param eid
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Employee>> findDeptEmpList(int eid, int start, int step) throws AppAbnormal;

    /**
     * 获取经理的联系人列表
     */
    Feedback<Paginate<Employee>> findDeptHostEmpList(int eid, int start, int step) throws AppAbnormal;

    /**
     * 分页查看全部员工
     * @param start
     * @param step
     * @return
     * @throws AppAbnormal
     */
    Feedback<Paginate<Employee>> findList(int start, int step) throws AppAbnormal;
}
