package indi.crisp.mylin.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MybatisUtilTest {

    @Test
    void getSqlSession() {
        System.out.println(MybatisUtil.getSqlSession().getConnection());
    }

    @Test
    void testGetSqlSession() {

    }
}