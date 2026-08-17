package org.example.exampractice.quiz;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Generic Vector for numeric types. Starts at capacity C and grows.
// iterator() walks from last element to first.
public class Vector<T extends Number> implements Iterable<T> {
    private Object[] data;
    private int capacity;
    private int size;

    public Vector(int c) {
        if (c <= 0) {
            c = 1;
        }
        capacity = c;
        data = new Object[capacity];
        size = 0;
    }

    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        data[size++] = value;
    }

    private void grow() {
        int newCap = capacity * 2;
        Object[] bigger = new Object[newCap];
        System.arraycopy(data, 0, bigger, 0, size);
        data = bigger;
        capacity = newCap;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T> {
        private int index = size - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[index--];
        }
    }

    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>(2);
        v.add(10);
        v.add(20);
        v.add(30); // grows past C

        System.out.print("Reverse: ");
        for (Integer n : v) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
