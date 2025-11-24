package org.example.ORM;
public class Main {
    public static void main(String[] args) {

        UserDao dao = new UserDao();

        User u = new User("Ahmad");
        dao.save(u);

        User r = dao.get(u.getId());
        System.out.println("GET: " + r.getName());

        r.setName("Updated");
        dao.update(r);

        for (User x : dao.getAll()) {
            System.out.println("USER: " + x.getId() + " - " + x.getName());
        }

        dao.delete(r);
    }
}
