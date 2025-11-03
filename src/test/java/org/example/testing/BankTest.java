package org.example.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.testing.*;;

public class BankTest {
  
    @Test
    void bankCreate(){
       BankAccount bc = new BankAccount("NULL",0.0);
      bc.deposit(100);
       assertEquals(100, bc.getBalance());
    }

    @Test
    void bankWithdrwal(){
    BankAccount bc = new BankAccount("NULL",0.0);
       assertThrows(IllegalArgumentException.class, ()-> bc.withdraw(100));
       bc.setBln(200);
       bc.withdraw(100);
       assertEquals(100, bc.getBalance());
    }
    
}
