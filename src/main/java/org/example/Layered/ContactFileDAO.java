package org.example.Layered;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactFileDAO implements ContactDAO {
    private static final String FILE_PATH = "contacts.txt";

    @Override
    public boolean save(Contact contact) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(contact.getId() + "," + contact.getName() + "," + contact.getEmail());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Contact contact) throws Exception {
        List<Contact> allContacts = findAll();
        boolean updated = false;

        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getId().equals(contact.getId())) {
                allContacts.set(i, contact);
                updated = true;
                break;
            }
        }

        if (updated) {
            writeAll(allContacts);
        }
        return updated;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        List<Contact> allContacts = findAll();
        boolean removed = allContacts.removeIf(c -> c.getId() == id);
        if (removed) {
            writeAll(allContacts);
        }
        return removed;
    }

    @Override
    public Contact findById(int id) throws Exception {
        for (Contact c : findAll()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Contact> findAll() throws Exception {
        List<Contact> list = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return list; // return empty if file doesn’t exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Integer id = data[0].equals("null") ? null : Integer.parseInt(data[0]);
                    list.add(new Contact(id, data[1], data[2]));
                }
            }
        }
        return list;
    }

    // helper to overwrite the file
    private void writeAll(List<Contact> contacts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Contact c : contacts) {
                writer.write(c.getId() + "," + c.getName() + "," + c.getEmail());
                writer.newLine();
            }
        }
    }
}
