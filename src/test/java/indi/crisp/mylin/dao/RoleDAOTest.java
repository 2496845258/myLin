package indi.crisp.mylin.dao;

import indi.crisp.mylin.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDAOTest {

    SqlSession session = MybatisUtil.getSqlSession();
    @Test
    void findRoleByID() {
        RoleDAO roleDAO = session.getMapper(RoleDAO.class);
        System.out.println(roleDAO.findRoleByID(2));
    }
}