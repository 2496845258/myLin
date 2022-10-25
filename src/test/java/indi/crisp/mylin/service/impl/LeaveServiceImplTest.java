package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.pojo.expand.LeaveVO;
import indi.crisp.mylin.service.LeaveService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveServiceImplTest {
    private LeaveService leaveService = new LeaveServiceImpl();
    @Test
    void findEmpList() throws AppAbnormal {
        for ( var i : leaveService.findEmpList(1,0,10).getResult().getList()) {
            System.out.println(((LeaveVO)i).getEmpName());
        }
    }
}