package org.example.layeredarchitecture.dao;

public @interface AuthorHeader {
    String name() default "ahmad";
    String date();
} 
