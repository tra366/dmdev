package com.dmdev.integration;

import com.dmdev.util.HibernateTestUtil;
import com.dmdev.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

public abstract class IntegrationTestBase {

    private static SessionFactory sessionFactory;
    Session session;

    @BeforeAll
    static void init() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @BeforeEach
    void TransactionStart() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void TransactionCommit() {
        session.getTransaction().commit();
        session.close();
    }

}
