package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.EmployeeDAO;
import indi.crisp.mylin.dao.PermDAO;
import indi.crisp.mylin.dao.RoleDAO;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.expand.EmployeeVO;
import indi.crisp.mylin.pojo.expand.PermVO;
import indi.crisp.mylin.service.EmployeeService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Migrate;
import indi.crisp.mylin.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

public class EmployeeServiceImpl implements EmployeeService {



    @Override
    public int insertEmployee(Employee employee) throws AppAbnormal {
        return 0;
    }

    @Override
    public int deleteEmployee(int empno) throws AppAbnormal {
        return 0;
    }

    @Override
    public int updateEmployee(Employee employee) throws AppAbnormal {
        return 0;
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

        // 判断账号是否存在
        if (null != dao.findEmployeebyAccount(account)){
            return AppEnum.EMPLOYEE_ACCOUNT_SAME.getCode();
        }
        try {
            Employee employee = new Employee();
            // 员工账户
            employee.setEloginid(account);
            // 盐
            employee.setEsalt(UUID.randomUUID().toString().replace("-",""));
            // 员工密码 加密
            employee.setEpwd(DigestUtils.md5DigestAsHex((password+employee.getEsalt()).getBytes()));
            // 员工状态
            employee.setEstatus(1);
            // 员工登录时间
            employee.setElasttime(new Timestamp(System.currentTimeMillis()));
            // 员工默认名
            Random random = new Random();
            employee.setEname("uesr-"+ random.nextInt(100));
            // 员工默认部门
            employee.setEdept(0);
            // 员工默认角色
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

        // 判断账号是否存在
        if (null == employeeDAO.findEmployeebyAccount(account)){
            return new Feedback<>().setStatusCode(AppEnum.EMPLOYEE_ACCOUNT_NULL.getCode());
        }


        try {
            Employee employee = employeeDAO.findEmployeebyAccount(account);
            // 员工离职
            if (employee.getEstatus() == 1202){
                return new Feedback<>().setStatusCode(AppEnum.EMPLOYEE_LEAVE.getCode());
            }
            // 密码错误
            if (!employee.getEpwd().equals(DigestUtils.md5DigestAsHex((password+employee.getEsalt()).getBytes()))){
                return new Feedback<>().setStatusCode(AppEnum.EMPLOYEE_PASSWORD_WRONG.getCode());
            }

            Migrate.change(employee,employeeVO);
            employeeVO.setRole(roleDAO.findEmpnoRole(employeeVO.getEmpno()));
            employeeVO.setPermList(permDAO.findEmpnoList(employee.getErole()));
            return new Feedback<>().setResult(employeeVO).setStatusCode(AppEnum.EMPLOYEE_LOGIN_YES.getCode());

        }finally {
           session.close();
        }

    }


}
