package assignment2;
// DONE //

public class DivideAndConquer {

    public static int fibonacci(int n) {
        // TASK 1.A.a
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        int prob_1 = fibonacci(n - 1);
        int prob_2 = fibonacci(n - 2);
        return prob_1 + prob_2;
    }

    public static int search(int[] A, int v)
    {
        // TASK 1.A.b
        int n  = A.length;
        int result = binary_search(A, 0, n-1, v);
        if (result == -1){
            System.out.println("Element not found");
        }
        return result;
    }

    public static int binary_search(int [] A, int low, int high,int v) {
        if (high >= low) {
            int mid = low + (high - low) / 2;

            if (A[mid] == v)
                return mid;

            if (A[mid] > v)
                return binary_search(A, low, mid - 1, v);

            return binary_search(A, mid + 1, high, v);
        }
        return -1;
    }

    public static void hanoi(int n, char A, char B, char C)
    {
        // TASK 1.A.c
        if (n == 0){
            return;
        } else {
            hanoi(n-1, A, C, B);
            System.out.println(A + " --> " + C);
            hanoi(n-1,C,B,A);
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            System.out.println(fibonacci(i));
        }
        System.out.println();
        for (int i=0; i<10; i++) {
            System.out.println(search(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, i));
        }
        System.out.println();
        hanoi(4, 'A', 'B', 'C');
    }
}
