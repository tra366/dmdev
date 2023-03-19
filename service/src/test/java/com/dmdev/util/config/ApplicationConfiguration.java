package com.dmdev.util.config;

import com.dmdev.util.DataImportTestUtil;
import com.dmdev.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

@Configuration
@ComponentScan(basePackages = "com.dmdev.dao")
public class ApplicationConfiguration {

    @Bean
    EntityManager entityManager() {
        SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
        DataImportTestUtil.importData(sessionFactory);
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        return session;
    }

}
