package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.service.EmployeeService;
import indi.crisp.mylin.util.Feedback;

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
        return 0;
    }

    @Override
    public Feedback<Employee> login(String account, String password) throws AppAbnormal {
        return null;
    }
}
