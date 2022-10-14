package indi.crisp.mylin.dao;

import indi.crisp.mylin.util.MybatisUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermDAOTest {
    private PermDAO permDAO = MybatisUtil.getSqlSession().getMapper(PermDAO.class);

    @Test
    void findPermByID() {
    }

    @Test
    void findPermList() {
    }

    @Test
    void insertPerm() {
    }

    @Test
    void updatePerm() {
    }

    @Test
    void updatePermAuto() {
    }

    @Test
    void deletePerm() {
    }

    @Test
    void countAll() {
    }

    @Test
    void findEmpnoList() {
        permDAO.findEmpnoList(1).forEach(System.out::println);
    }
}