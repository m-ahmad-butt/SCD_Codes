package org.example.threads;

class MyTask implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("Runnable thread: " + i);
        }
    }
}
