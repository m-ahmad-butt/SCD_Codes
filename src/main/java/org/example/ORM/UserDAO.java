package org.example.ORM;

import org.hibernate.Session;
import java.util.List;

public class UserDao {

    public void save(User u) {
        Session s = HibernateUtil.get().openSession();
        s.beginTransaction();
        s.persist(u);
        s.getTransaction().commit();
        s.close();
    }

    public User get(int id) {
        Session s = HibernateUtil.get().openSession();
        User u = s.get(User.class, id);
        s.close();
        return u;
    }

    public List<User> getAll() {
        Session s = HibernateUtil.get().openSession();
        List<User> list = s.createQuery("from User", User.class).list();
        s.close();
        return list;
    }

    public void update(User u) {
        Session s = HibernateUtil.get().openSession();
        s.beginTransaction();
        s.merge(u);
        s.getTransaction().commit();
        s.close();
    }

    public void delete(User u) {
        Session s = HibernateUtil.get().openSession();
        s.beginTransaction();
        s.remove(u);
        s.getTransaction().commit();
        s.close();
    }
}
