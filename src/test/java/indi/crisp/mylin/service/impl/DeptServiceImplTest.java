package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeptServiceImplTest {

    @Test
    void findList() throws AppAbnormal {
        var t = new DeptServiceImpl();
        t.findList(0,10);
    }
}