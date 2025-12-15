package org.example.generic;

public class GenericStack<T> {
    private Object[] arr;
    private int top;
    private int capacity;

    public GenericStack(int cap) {
        capacity = cap;
        arr = new Object[capacity];
        top = -1;
    }

    public GenericStack() {
        this(10);
    }

    public void push(T val) {
        if (top + 1 == capacity) resize();
        arr[++top] = val;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T val = (T) arr[top];
        arr[top--] = null;
        return val;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return (T) arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, capacity);
        arr = newArr;
        capacity = newCapacity;
    }

    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        GenericStack<Integer> st = new GenericStack<>();
        st.push(10);
        st.push(20);
        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.isEmpty());
    }
}
