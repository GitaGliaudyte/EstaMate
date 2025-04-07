package io.github.gitagliaudyte.estamate.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.cdi.SessionFactoryProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class SqlSessionProducer {

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Produces
    @RequestScoped
    public SqlSession produceSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public void close(@Disposes SqlSession sqlSession) {
        sqlSession.close();
    }
}
