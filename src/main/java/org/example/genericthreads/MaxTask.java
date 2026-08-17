package org.example.genericthreads;

import java.util.ArrayList;

public class MaxTask <B extends Comparable<B>> implements Runnable{
    ArrayList<B> list;
    public MaxTask(ArrayList<B> list){
        this.list = list;
    }

    @Override
    public void run() {
        B max = list.get(0);
        for(B b : list){
            if(max.compareTo(b) < 0){
                max = b;
            }
        }
        System.out.println(max);
    }
}
