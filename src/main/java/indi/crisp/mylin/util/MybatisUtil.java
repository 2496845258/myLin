package indi.crisp.mylin.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MybatisUtil {
    private static SqlSessionFactory factory = buid();

    /**
     * 从mybatis获取一个连接
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        //默认关闭自动提交事务
        return getSqlSession(false);
    }

    public static SqlSession getSqlSession(Boolean b) {
        return factory.openSession(b);
    }

    /**
     * 用于变量的初始化方法，通过该方式第一次启动服务的时候就会加载，提升用户体验。
     * 不写静态块主要是为了方便好看
     *
     * @return
     */
    private static SqlSessionFactory buid() {
        try {
            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis.xml"));
        } catch (IOException e) {
            System.err.println("找不到资源文件");
            e.printStackTrace();
            return null;
        }
    }

}
