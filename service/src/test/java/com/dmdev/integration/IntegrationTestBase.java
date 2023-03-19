package com.dmdev.integration;

import com.dmdev.dao.ChartRepository;
import com.dmdev.dao.MatrixRepository;
import com.dmdev.dao.NameSeriesRepository;
import com.dmdev.dao.SeriesRepository;
import com.dmdev.dao.SourceRepository;
import com.dmdev.dao.TypeSeriesRepository;
import com.dmdev.dao.UserRepository;
import com.dmdev.util.config.*;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class IntegrationTestBase {

    private static SessionFactory sessionFactory;
    @Getter
    private static Session session;
    @Getter
    private static UserRepository userRepository;
    @Getter
    private static SeriesRepository seriesRepository;
    @Getter
    private static MatrixRepository matrixRepository;
    @Getter
    private static ChartRepository chartRepository;
    @Getter
    private static SourceRepository sourceRepository;
    @Getter
    private static NameSeriesRepository nameSeriesRepository;
    @Getter
    private static TypeSeriesRepository typeSeriesRepository;

    @BeforeAll
    static void init() {
        var context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfiguration.class);
        context.refresh();
        session = context.getBean(Session.class);
        sessionFactory = session.getSessionFactory();
        userRepository = context.getBean(UserRepository.class);
        seriesRepository = context.getBean(SeriesRepository.class);
        matrixRepository = context.getBean(MatrixRepository.class);
        chartRepository = context.getBean(ChartRepository.class);
        sourceRepository = context.getBean(SourceRepository.class);
        nameSeriesRepository = context.getBean(NameSeriesRepository.class);
        typeSeriesRepository = context.getBean(TypeSeriesRepository.class);
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @BeforeEach
    void transactionStart() {
        session.beginTransaction();
    }

    @AfterEach
    void transactionRollback() {
        session.getTransaction().rollback();
    }

}
