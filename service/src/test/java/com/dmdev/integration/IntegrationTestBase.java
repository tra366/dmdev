package com.dmdev.integration;

import com.dmdev.dao.SeriesDao;
import com.dmdev.dao.UserDao;
import com.dmdev.util.DataImportTestUtil;
import com.dmdev.util.HibernateTestUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class IntegrationTestBase {

    private static SessionFactory sessionFactory;
    @Getter
    private static Session session;
    @Getter
    private UserDao myDao = UserDao.getInstance();
    @Getter
    private SeriesDao seriesDao = SeriesDao.getInstance();

    @BeforeAll
    static void init() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
        DataImportTestUtil.importData(sessionFactory);
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @BeforeEach
    void transactionStart() {
        //       session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void transactionRollback() {
        session.getTransaction().rollback();
        //      session.close();
    }

}
