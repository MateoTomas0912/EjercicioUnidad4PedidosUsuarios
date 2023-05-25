package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try{
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Session getSession(){return sessionFactory.openSession();}
}
