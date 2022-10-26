package indi.crisp.mylin.service.impl;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppEnum;
import indi.crisp.mylin.dao.DeptDAO;
import indi.crisp.mylin.dao.DictDAO;
import indi.crisp.mylin.dao.EmployeeDAO;
import indi.crisp.mylin.dao.LeaveDAO;
import indi.crisp.mylin.pojo.Leave;
import indi.crisp.mylin.pojo.expand.LeavePO;
import indi.crisp.mylin.pojo.expand.LeaveVO;
import indi.crisp.mylin.service.LeaveService;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Migrate;
import indi.crisp.mylin.util.MybatisUtil;
import indi.crisp.mylin.util.Paginate;

import java.util.LinkedList;

public class LeaveServiceImpl implements LeaveService {
    @Override
    public int insertLeave(Leave leave) throws AppAbnormal {
        if ( leave == null ) {
            throw new AppAbnormal(AppEnum.ERROR_LEAVE_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var empDAO = session.getMapper(EmployeeDAO.class);
            var emp = empDAO.findEmployeeByID(leave.getVfrom());
            if ( emp == null ) {
                return AppEnum.EMP_FIND_ID_NO.getCode();
            }
            //经理请假就给高管审批
            if ( emp.getErole() == 2 ) {
                //经理请假
                leave.setVto(4);
                var leaveDAO = session.getMapper(LeaveDAO.class);
                if ( leaveDAO.insertLeave(leave) > 0) {
                    session.commit();
                    return AppEnum.LEAVE_INSERT_YES.getCode();
                }
                return AppEnum.LEAVE_INSERT_NO.getCode();
            }
            //普通员工走下面
            var deptDAO = session.getMapper(DeptDAO.class);
            var empt = deptDAO.findDeptByID(emp.getEdept());
            if ( empt == null ) {
                return AppEnum.DEPT_FIND_NO.getCode();
            }
            if ( empt.getDhost() == 0 ) {
                //如果没有就发给高管
                leave.setVto(4);
            } else {
                //有就发给负责人
                leave.setVto(empt.getDhost());
            }

            //插入请假信息
            var leaveDAO = session.getMapper(LeaveDAO.class);
            if ( leaveDAO.insertLeave(leave) > 0) {
                session.commit();
                return AppEnum.LEAVE_INSERT_YES.getCode();
            }
            return AppEnum.LEAVE_INSERT_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public int updateLeave(Leave leave) throws AppAbnormal {
        if ( leave == null ) {
            throw new AppAbnormal(AppEnum.ERROR_LEAVE_NULL);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var leaveDAO = session.getMapper(LeaveDAO.class);
            if ( leaveDAO.updateLeaveAuto(leave) > 0) {
                session.commit();
                return AppEnum.LEAVE_UPDATE_NO.getCode();
            }
            return AppEnum.LEAVE_UPDATE_NO.getCode();
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Leave>> findList(int start, int limit) throws AppAbnormal {
        if ( start < 0 || limit < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_LEAVE_FIND_LIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var leaveDAO = session.getMapper(LeaveDAO.class);
            var leaves = leaveDAO.findLeaveList(start, limit);
            var count = leaveDAO.countAll();
            return new Feedback<Paginate>()
                    .setResult(new Paginate<Leave>()
                            .setList(leaves)
                            .setCountAll(count)
                            .setIndex(start)
                            .setStep(leaves.size()));
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Leave>> findEmpList(int eid, int start, int limit) throws AppAbnormal {
        if ( eid < 0 || start < 0 || limit < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_LEAVE_FIND_LIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var leaveDAO = session.getMapper(LeaveDAO.class);
            var leaves = leaveDAO.findEmpIdList(eid, start, limit);

            if ( leaves.size() == 0 ) {
                return new Feedback<>().setResult(new Paginate<Leave>().setList(leaves));
            }

            var empDAO = session.getMapper(EmployeeDAO.class);
            var name = empDAO.findEmployeeByID(eid).getEname();

            var leaves2 = new LinkedList<Leave>();
            var dictDAO = session.getMapper(DictDAO.class);
            for ( var i : leaves ) {
                var leaveVO = new LeaveVO();
                Migrate.change(i,leaveVO);
                leaveVO.setSpStatus(dictDAO.findByKey(i.getVstatus()).getDvalue());
                leaveVO.setEmpName(name);
                leaves2.add(leaveVO);
            }

            return new Feedback<Paginate>()
                    .setResult(new Paginate<Leave>()
                            .setList(leaves2)
                            .setIndex(start)
                            .setStep(leaves.size()));
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Leave>> findDeptLeave(int did, int start, int limit) throws AppAbnormal {
        if ( did < 0 || start < 0 || limit < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_LEAVE_FIND_LIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var leaveDAO = session.getMapper(LeaveDAO.class);
            var leaves = leaveDAO.findDeptLeave(did, start, limit);
            return new Feedback<Paginate>()
                    .setResult(new Paginate<Leave>()
                            .setList(leaves)
                            .setIndex(start)
                            .setStep(leaves.size()));
        } finally {
            session.close();
        }
    }

    @Override
    public Feedback<Paginate<Leave>> findToId(int tid, int start, int limit) throws AppAbnormal {
        if ( tid < 0 || start < 0 || limit < 0 ) {
            throw new AppAbnormal(AppEnum.ERROR_LEAVE_FIND_LIST);
        }
        var session = MybatisUtil.getSqlSession();
        try {
            var leaveDAO = session.getMapper(LeaveDAO.class);
            var leaves = leaveDAO.findToId(tid, start, limit);
            return new Feedback<Paginate>()
                    .setResult(new Paginate<Leave>()
                            .setList(leaves)
                            .setIndex(start)
                            .setStep(leaves.size()));
        } finally {
            session.close();
        }
    }

}
