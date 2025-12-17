
package org.example.Thread.producerConsumer;


public class Producer extends Thread {
    BoundedBuffer buffer;

    public Producer(BoundedBuffer b) {
        buffer = b;
    }

    public void run() {
        while (true) {
            int v = (int) (Math.random() * 10);

            buffer.insert(v);

            buffer.display();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }

}
