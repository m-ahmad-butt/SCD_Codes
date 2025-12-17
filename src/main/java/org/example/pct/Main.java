
package org.example.pct;


public class Main {

   
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(10);


        Producer p1 = new Producer(buffer);
        Producer p2 = new Producer(buffer);
        Consumer c1 = new Consumer(buffer);
        Consumer c2 = new Consumer(buffer);


        p1.start();
        c1.start();
        p2.start();
        c2.start();

    }

}
