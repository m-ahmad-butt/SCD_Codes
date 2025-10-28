package org.example.DAO;

import java.util.List;

public class ContactDAODemo {
    public static void main(String[] args) throws Exception {
        ContactDbDAO dao = new ContactDbDAO();

        // Create and save
        Contact c = new Contact("Alice Example", "alice@example.com");
        boolean ok = dao.save(c);
        System.out.println("Saved: " + ok + " -> " + c);

        // List all
        List<Contact> all = dao.findAll();
        System.out.println("All contacts (" + all.size() + "):");
        for (Contact t : all) System.out.println(t);

        // Find, update, delete demo
        if (c.getId() != null) {
            Contact found = dao.findById(c.getId());
            System.out.println("Found: " + found);
            found.setEmail("alice.updated@example.com");
            dao.update(found);
            System.out.println("After update: " + dao.findById(found.getId()));
            dao.deleteById(found.getId());
            System.out.println("Deleted id " + found.getId());
        }
    }
}
