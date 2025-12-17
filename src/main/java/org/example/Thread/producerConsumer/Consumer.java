package org.example.Thread.producerConsumer;

import static java.lang.Thread.sleep;

public class Consumer extends Thread {
    BoundedBuffer buffer;

    public Consumer(BoundedBuffer b) {
        buffer = b;
    }

    public void run() {
        while (true) {
            int v = buffer.remove();

            buffer.display();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }

}
