package org.example.Thread.producerConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BoundedBuffer {

    int[] buffer;
    int capacity;
    int count;

    public BoundedBuffer(int c) {
        capacity = c;
        buffer = new int[capacity];
        count = 0;
    }

    public synchronized void insert(int v) {

        while (count == capacity) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }


        if (count < capacity) {
            System.out.println("Inserting " + v);
            buffer[count] = v;
            count++;
        }

        notifyAll();
    }

    public synchronized int remove() {
        int v = Integer.MIN_VALUE;

        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }


        if (count > 0) {
            count--;
            v = buffer[count];
            System.out.println("Removing " + v);
        }

        notifyAll();
        return v;
    }

    public synchronized void display() {
        System.out.print("Buffer state:");
        for (int i = 0; i < count; i++) {
            System.out.print(buffer[i] + " ");
        }
        System.out.println();
    }


}
