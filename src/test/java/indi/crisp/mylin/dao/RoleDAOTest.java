package indi.crisp.mylin.dao;

import indi.crisp.mylin.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDAOTest {

    SqlSession session = MybatisUtil.getSqlSession();
    RoleDAO roleDAO = session.getMapper(RoleDAO.class);
    @Test
    void findRoleByID() {
        System.out.println(roleDAO.findRoleByID(2));
    }

    @Test
    void testFindRoleByID() {
    }

    @Test
    void findRoleList() {
    }

    @Test
    void insertRole() {
    }

    @Test
    void updateRole() {
    }

    @Test
    void updateRoleAuto() {
    }

    @Test
    void deleteRole() {
    }

    @Test
    void countAll() {
    }

    @Test
    void findEmpnoRole() {
        System.out.println(roleDAO.findEmpnoRole(1));
    }
}