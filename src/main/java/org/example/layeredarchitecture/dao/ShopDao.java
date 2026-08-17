package org.example.layeredarchitecture.dao;

import java.util.ArrayList;
import java.util.Hashtable;

@AuthorHeader(date="12-12-12")
public interface ShopDao {
    ArrayList<Hashtable<String,Object>> load();
    void save (ArrayList<Hashtable<String,Object>> items);
    int del(String code);
} 