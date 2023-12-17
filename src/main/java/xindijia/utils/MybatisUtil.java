package xindijia.utils;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xia
 * @since 2023/12/16/11:54
 */
public class MybatisUtil {
    static SqlSessionFactory sqlSessionFactory = null;


    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
