package assignment2;
//DONE//

public class MergeSort {

    private static int[] merge(int[] A1, int[] A2) {
        // TASK 2.A.a
        int l1 = A1.length;
        int l2 = A2.length;
        int[] K = new int[l1 + l2];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < l1 && j < l2) {
            if (A1[i] <= A2[j]) {
                K[k] = A1[i];
                i++;
            } else {
                K[k] = A2[j];
                j++;
            }
            k++;
        }

        while (i < l1) {
            K[k] = A1[i];
            i++;
            k++;
        }

        while (j < l2) {
            K[k] = A2[j];
            j++;
            k++;
        }
        return K;
    }

    public static int[] mergesort(int[] A)
    {
        // TASK 2.A.b
        int half = A.length/2;
        if (half == 0){
            return A;
        }


        int[] L = new int[half];
        System.arraycopy(A, 0, L, 0, half);

        int rightlength = A.length - half;
        int[] R = new int[rightlength];
        System.arraycopy(A, half, R, 0, rightlength);

        L = mergesort(L);
        R = mergesort(R);

        return merge(L, R);

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
        print(merge(new int[] {1,3,5,7,9}, new int[] {2,4,6,8}));
        print(mergesort(new int[] {5,2,8,1,3,9,7,4,6} ));
    }

}
