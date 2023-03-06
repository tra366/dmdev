package com.dmdev.integration;

import com.dmdev.dao.ChartDao;
import com.dmdev.dao.SeriesDao;
import com.dmdev.dao.UserDao;
import com.dmdev.util.DataImportTestUtil;
import com.dmdev.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class IntegrationTestBase {

    private static SessionFactory sessionFactory;
    public Session session;
    public UserDao myDao = UserDao.getInstance();
    public ChartDao chartDao = ChartDao.getInstance();
    public SeriesDao seriesDao = SeriesDao.getInstance();

    @BeforeAll
    static void init() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
        DataImportTestUtil.importData(sessionFactory);
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @BeforeEach
    void transactionStart() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void transactionRollback() {
        session.getTransaction().rollback();
        session.close();
    }

}
