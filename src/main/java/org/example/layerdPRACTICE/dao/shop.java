package org.example.layerdPRACTICE.dao;

import java.util.ArrayList;
import java.util.Hashtable;

@header(date="12-12-12")
public interface shop {
    ArrayList<Hashtable<String,Object>> load();
    void save (ArrayList<Hashtable<String,Object>> items);
    int del(String code);
} 