package assignment1;

public class ArrayQueue implements Queue<Object> {
    private Object[] Q;

        private int tail;
        private int head;
        private int maxSize;
        private int size;

    public ArrayQueue(int capacity) {
        // TASK 3.A.a
        Q = new Object[capacity];
        tail = 0;
        head = 0;
        maxSize = capacity;
    }

    public void enqueue(Object x) {
        // TASK 3.A.b
        if (size == maxSize) {
            throw new RuntimeException("queue is full!");
        }
        Q[tail] = x;
        if (tail == Q.length - 1) {
            tail = 0;
        } else {
            tail = tail + 1;
        }
        size++;

    }

    public Object dequeue() {
        // TASK 3.A.c
        if (size == 0) {
            throw new RuntimeException("Queue is empty");
        }
        Object x = Q[head];
        Q[head] = null;
        if (head == Q.length - 1) {
            head = 0;
        } else {
            head = head + 1;
        }
        size--;
        return x;
    }

    public Object next() {
        // TASK 3.A.d
        if (size == 0){
            throw new RuntimeException("Queue is empty");
        }
        return  Q[head];
    }

    public boolean empty() {
        // TASK 3.A.e
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Queue<Object> test = new ArrayQueue(20);
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.enqueue(i+100);
        }
        System.out.println(test.empty());
        System.out.println(test.next());
        for (int i=0; i<5; i++) {
            int x = (int)test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i=0; i<15; i++) {
            test.enqueue(i);
        }
        while (!test.empty()) {
            int x = (int)test.dequeue();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
