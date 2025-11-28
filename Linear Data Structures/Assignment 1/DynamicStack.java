package assignment1;

public class DynamicStack implements Stack<Object> {
    List<Object> S = new DoubleLinkedList();

    public void push(Object x) {
        // TASK 2.B.a
        S.prepend(x);
    }

    public Object pop() {
        // TASK 2.B.b
        Object x = S.getFirst();
        S.deleteFirst();
        return x;
    }

    public Object peek() {
        // TASK 2.B.c
        return S.getFirst();
    }

    public boolean empty() {
        // TASK 2.B.d
        return S.empty();
    }

    public static void main(String[] args) {
        Stack<Object> test = new DynamicStack();
        System.out.println(test.empty());
        for (int i=0; i<10; i++) {
            test.push(i+100);
        }
        System.out.println(test.empty());
        System.out.println(test.peek());
        for (int i=0; i<5; i++) {
            int x = (int)test.pop();
            System.out.print(x + " ");
        }
        System.out.println();
        for (int i=0; i<15; i++) {
            test.push(i);
        }
        while (!test.empty()) {
            int x = (int)test.pop();
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
