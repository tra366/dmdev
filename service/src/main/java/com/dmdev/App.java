package com.dmdev;

import com.dmdev.entity.Chart;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;

import java.util.Map;

public class App {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             // DataImportTestUtil.importData(sessionFactory);
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var charts = session.createQuery("select ch from Chart ch", Chart.class)
                    .list();
            charts.forEach(chart -> System.out.println(chart.getSerieses().size()));



            Map<String, Object> properties = Map.of(
                    GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("withSeriesAndUser")
            );

            var chart = session.find(Chart.class, 1, properties);
            System.out.println(chart.getSerieses().size());

            session.getTransaction().commit();
        }

    }
}