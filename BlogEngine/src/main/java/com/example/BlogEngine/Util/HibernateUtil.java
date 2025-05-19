package com.example.BlogEngine.Util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManagerFactory;

@Component
public class HibernateUtil {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUtil(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}