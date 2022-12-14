package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.DeptDAO;
import indi.crisp.mylin.dao.EmployeeDAO;
import indi.crisp.mylin.dao.PermDAO;
import indi.crisp.mylin.dao.RoleDAO;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.expand.EmployeeVO;
import indi.crisp.mylin.service.EmployeeService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Migrate;
import indi.crisp.mylin.util.MybatisUtil;
import indi.crisp.mylin.util.Paginate;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public int insertEmployee(Employee employee) throws AppAbnormal {
        if ( employee == null ) {
            throw new AppAbnormal(AppEnum.ERROR_EMP_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var employeeDAO = session.getMapper(EmployeeDAO.class);
            if ( employeeDAO.insertEmployee(employee) > 0 ) {
                session.commit();
                return AppEnum.EMP_INSERT_YES.getCode();
            }
            return AppEnum.EMP_INSERT_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public int updateEmployee(Employee employee) throws AppAbnormal {
        if ( employee == null ) {
            throw new AppAbnormal(AppEnum.ERROR_EMP_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var employeeDAO = session.getMapper(EmployeeDAO.class);
            if ( employeeDAO.updateEmployeeAuto(employee) > 0 ) {
                session.commit();
                return AppEnum.EMP_UPDATE_YES.getCode();
            }
            return AppEnum.EMP_UPDATE_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public int register(String account, String password) throws AppAbnormal {
        if (account == null){
            throw  new AppAbnormal(AppEnum.ERROR_ACCOUNT_NULL);
        }
        if (null == password){
            throw  new AppAbnormal(AppEnum.ERROR_PASSWORD_NULL);
        }
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDAO dao = session.getMapper(EmployeeDAO.class);

        // ????????????????????????
        if (null != dao.findEmployeebyAccount(account)){
            return AppEnum.EMPLOYEE_ACCOUNT_SAME.getCode();
        }
        try {
            Employee employee = new Employee();
            // ????????????
            employee.setEloginid(account);
            // ???
            employee.setEsalt(UUID.randomUUID().toString().replace("-",""));
            // ???????????? ??????
            employee.setEpwd(DigestUtils.md5DigestAsHex((password+employee.getEsalt()).getBytes()));
            // ????????????
            employee.setEstatus(1);
            // ??????????????????
            employee.setElasttime(new Timestamp(System.currentTimeMillis()));
            // ???????????????
            Random random = new Random();
            employee.setEname("uesr-"+ random.nextInt(100));
            // ??????????????????
            employee.setEdept(0);
            // ??????????????????
            employee.setErole(1);
            if (dao.insertEmployee(employee) > 0){
                session.commit();
                return AppEnum.EMPLOYEE_REGISTER_YES.getCode();
            }
            return AppEnum.EMPLOYEE_REGISTER_NO.getCode();
        }finally {
            session.close();
        }



    }

    @Override
    public Feedback<Employee> login(String account, String password) throws AppAbnormal {
        if (account == null){
            throw  new AppAbnormal(AppEnum.ERROR_ACCOUNT_NULL);
        }

        if (null == password){
            throw  new AppAbnormal(AppEnum.ERROR_PASSWORD_NULL);
        }
        SqlSession session = MybatisUtil.getSqlSession();
        EmployeeDAO employeeDAO = session.getMapper(EmployeeDAO.class);
        EmployeeVO employeeVO = new EmployeeVO();
        RoleDAO roleDAO = session.getMapper(RoleDAO.class);
        PermDAO permDAO = session.getMapper(PermDAO.class);

        // ????????????????????????
        if (null == employeeDAO.findEmployeebyAccount(account)){
            return new Feedback<>().setStatusCode(AppEnum.EMPLOYEE_ACCOUNT_NULL.getCode()).setExplain(AppEnum.EMPLOYEE_ACCOUNT_NULL.getDescribe());
        }


        try {
            Employee employee = employeeDAO.findEmployeebyAccount(account);
            // ????????????
            if (employee.getEstatus() == 1202){
                return new Feedback<>().setStatusCode(AppEnum.EMPLOYEE_LEAVE.getCode()).setExplain(AppEnum.EMPLOYEE_LEAVE.getDescribe());
            }
            // ????????????
            if (!employee.getEpwd().equals(DigestUtils.md5DigestAsHex((password+employee.getEsalt()).getBytes()))){
                return new Feedback<>().setStatusCode(AppEnum.EMPLOYEE_PASSWORD_WRONG.getCode()).setExplain(AppEnum.EMPLOYEE_PASSWORD_WRONG.getDescribe());
            }

            Migrate.change(employee,employeeVO);
            employeeVO.setRole(roleDAO.findEmpnoRole(employeeVO.getEmpno()));
            employeeVO.setPermList(permDAO.findEmpnoList(employee.getErole()));
            return new Feedback<>().setResult(employeeVO).setStatusCode(AppEnum.EMPLOYEE_LOGIN_YES.getCode()).setExplain(AppEnum.EMPLOYEE_LOGIN_YES.getDescribe());

        }finally {
           session.close();
        }

    }
    @Override
    public Feedback<Employee> findEmpByID(int eid) throws AppAbnormal {
        if ( eid < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_EMP_FIND_ID);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var employeeDAO = session.getMapper(EmployeeDAO.class);
            var employee = employeeDAO.findEmployeeByID(eid);
            if ( employee == null ) {
                return new Feedback<>().setStatusCode(AppEnum.EMP_FIND_ID_NO.getCode());
            }
            return new Feedback<>().setResult(employee).setStatusCode(AppEnum.EMP_FIND_ID_YES.getCode());
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Employee> findEmpDeptHost(int eid) throws AppAbnormal {
        var session = MybatisUtil.getSqlSession();
        try {
            var empDAO = session.getMapper(EmployeeDAO.class);
            var emp = empDAO.findEmpDeptHost(eid);
            return new Feedback<Employee>().setResult(emp);
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Employee> findDidEmp(int deptId) throws AppAbnormal {
        var session = MybatisUtil.getSqlSession();
        try {
            var empDAO = session.getMapper(EmployeeDAO.class);
            var emp = empDAO.findDidEmp(deptId);
            return new Feedback<Employee>().setResult(emp);
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Employee>> findDeptEmpList(int eid, int start, int step) throws AppAbnormal {
        var session = MybatisUtil.getSqlSession();
        try {
            var employeeDAO = session.getMapper(EmployeeDAO.class);
            var employees = employeeDAO.findDeptEmpList(eid,start,step);
            return new Feedback<>().setResult(new Paginate<Employee>().setList(employees).setStep(step).setIndex(start));
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Employee>> findDeptHostEmpList(int eid, int start, int step) throws AppAbnormal {
        var session = MybatisUtil.getSqlSession();
        try {
            var employeeDAO = session.getMapper(EmployeeDAO.class);
            var employees = employeeDAO.findDeptHostEmpList(eid,start,step);
            return new Feedback<>().setResult(new Paginate<Employee>().setList(employees).setStep(step).setIndex(start));
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Employee>> findList(int start, int step) throws AppAbnormal {
        var session = MybatisUtil.getSqlSession();
        try {
            var employeeDAO = session.getMapper(EmployeeDAO.class);
            var employees = employeeDAO.findEmployeeList(start,step);

            var deptDAO = session.getMapper(DeptDAO.class);
            var empVOList = new LinkedList<Employee>();
            for ( var i : employees ) {
                var empVO = new EmployeeVO();
                Migrate.change(i,empVO);
                var t = deptDAO.findDeptByID(i.getEdept());
                if ( t == null ) {
                    empVO.setDeptName("????????????");
                } else {
                    empVO.setDeptName(t.getDname());
                }
                empVOList.addLast(empVO);
            }
            return new Feedback<>().setResult(new Paginate<Employee>().setList(empVOList).setStep(empVOList.size()).setIndex(start));
        } finally {
            session.close();
        }
    }

}
