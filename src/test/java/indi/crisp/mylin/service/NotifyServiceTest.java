package indi.crisp.mylin.service;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.service.impl.NotifyServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifyServiceTest {
    private NotifyService service = new NotifyServiceImpl();
    @Test
    void insert() throws AppAbnormal {
        System.out.println(service.findByEmpID(1, 2, 0, 10));
    }

    @Test
    void findByEmpID() {
    }

    @Test
    void findList() {
    }
}