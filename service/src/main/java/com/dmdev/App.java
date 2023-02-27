package com.dmdev;

import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        User user = User.builder()
                .username("IPetrov")
                .firstName("Ivan")
                .lastName("Ivanov")
                .role(Role.BUILDER)
                .password("123")
                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                Transaction transaction = session1.beginTransaction();
                session1.saveOrUpdate(user);
                User myUser = session1.get(User.class,user.getId());
                session1.getTransaction().commit();
            }
        }

    }
}