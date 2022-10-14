package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    @Test
    void register() throws AppAbnormal {
        EmployeeService service = new EmployeeServiceImpl();
        service.register("2050134213@qq.com","123456");
    }
}