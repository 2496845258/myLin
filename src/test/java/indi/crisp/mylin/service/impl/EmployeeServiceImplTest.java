package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.service.EmployeeService;
import indi.crisp.mylin.util.Feedback;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    EmployeeService service = new EmployeeServiceImpl();
    @Test
    void register() throws AppAbnormal {

        service.register("2050134213@qq.com","123456");
    }

    @Test
    void login() throws AppAbnormal {
        Feedback<Employee> login = service.login("2050134213@qq.com", "123456");
        System.out.println(login.getResult());
    }
}