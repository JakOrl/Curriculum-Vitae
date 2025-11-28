package assignment2;

public class HeapOfBinaryTries {
    private BinaryTrie[] A;
    private int heapsize;


    private void swap(int i, int j) {
        BinaryTrie temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private void heapify(int i)
    {
        // TASK 3.A.a
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < heapsize && A[left] != null && A[left].compare(A[smallest])){
            smallest = left;
        }

        if (right < heapsize && A[right] != null && A[right].compare(A[smallest])){
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);

            heapify(smallest);
        }
    }

    public HeapOfBinaryTries(BinaryTrie[] A) {
        // TASK 3.A.b
        this.A = A;
        this.heapsize = A.length;
        for (int i = heapsize/2; i >= 0; i--)
            heapify(i);
    }

    public BinaryTrie extractMin()
    {
        // TASK 3.A.c
        if (heapsize < 1){
            return null;
        }

        BinaryTrie min = A[0];
        A[0] = A[heapsize - 1];
        heapsize--;
        if (heapsize > 0) {
            heapify(0);
        }
        return min;
    }

    public void insert(BinaryTrie x) {
        // TASK 3.A.d
        heapsize = heapsize + 1;
        int i = heapsize - 1 ;
        while (i > 0){
            int parent = (i - 1) / 2;

            if (A[parent].compare(x)){
                break;
            }

            A[i] = A[parent];
            i = parent;
        }
        A[i] = x;
    }

    public int size()
    {
        return heapsize;
    }
}
