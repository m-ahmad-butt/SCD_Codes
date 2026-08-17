package org.example.genericthreads;


import java.util.ArrayList;

public class GenericThreadsDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(-3);
        list.add(4);
        list.add(5);

        Thread t1 = new Thread(new MinTask(list));
        Thread t2 = new Thread(new MaxTask(list));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
