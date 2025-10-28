package org.example.DAO;

import java.util.List;

public interface ContactDAO {
    boolean save(Contact contact) throws Exception;
    boolean update(Contact contact) throws Exception;
    boolean deleteById(int id) throws Exception;
    Contact findById(int id) throws Exception;
    List<Contact> findAll() throws Exception;
}
