package org.example.orm;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   private String name;

   public User() {}
   public User(String n) { this.name = n; }

   public int getId() { return id; }
   public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
