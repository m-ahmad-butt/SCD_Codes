package org.example.genericthreads;


import java.util.ArrayList;

public class MinTask<T extends Comparable<T>> implements Runnable {
    ArrayList<T> list;

    public MinTask(ArrayList<T> list){
        this.list = list;
    }

    @Override
    public void run() {
        T obj = list.get(0);
        for(int j=1;j<list.size();j++){
            T x = list.get(j);
            if(obj.compareTo(x) > 0){
                obj = x;
            }
        }
        System.out.println(obj);
    }
}
