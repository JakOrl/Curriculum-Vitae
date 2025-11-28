package assignment2;
// Done , not understood yet, Research //
public class QuickSort {

    private static int partition(int[] A, int p, int r)
    {
        // TASK 2.B.a
        int x = A[p];
        int i = p - 1;
        int j = r + 1;

        while (true) {

            do{
                j--;
            } while (j >= p && A[j] > x);

            do {
                i++;
            } while (i <= r && A[i] < x);

            if (i < j){
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            } else {
                return j;
            }
        }

    }

    private static void quicksort(int[] A, int p, int r)
    {
        // TASK 2.B.b
        if (p < r){
            int q = partition(A, p, r);
            quicksort(A, p, q);
            quicksort(A, q + 1, r);
        }
    }

    public static void quicksort(int[] A)
    {
        quicksort(A, 0, A.length-1);
    }

    private static void print(int[] A)
    {
        for (int i=0; i<A.length; i++)
        {
            System.out.print(A[i] + ((i<A.length-1)?", ":""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = new int[] {5,2,8,1,3,9,7,4,6};
        quicksort(A);
        print(A);
    }

}
