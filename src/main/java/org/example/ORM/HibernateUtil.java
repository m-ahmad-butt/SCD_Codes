package org.example.orm;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();
   public static SessionFactory get() { return sf; }
}
