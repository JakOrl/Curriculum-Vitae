package Pong_Source.Services;

import java.util.*;

/**
 * Conducts a stress test of the Java Virtual Machine (JVM) memory limits.
 * <p>
 * This class facilitates Part 3 of the assignment, analyzing the behavior
 * of the Heap and Stack under extreme conditions to trigger
 * {@link OutOfMemoryError} and {@link StackOverflowError}.
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */

public class StressTest {

    /**
     * Entry point for the memory experiments.
     * Switch between testing methods by uncommenting the desired test call.
     * * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        runStack(0);
        // runHeap();
    }


    /**
     * Executes a memory allocation test to exhaust the JVM Heap memory.
     * <p>
     * This method continuously instantiates large long arrays (long[100000])
     * and stores them in a list. This simulates a memory leak or excessive
     * object creation until the -Xmx limit is reached.
     * </p>
     */
    public static void runHeap(){

        byte[] reserve = new byte[10 * 1024 * 1024];

        List<long[]> list = new ArrayList<>();
        int count = 0;
        long startTime = System.currentTimeMillis();
        try{
            while(true){
                list.add(new long[100000]);
                count++;
                if (count % 100 == 0){
                    System.out.println("Objects:" + count);
                }
            }
        } catch (OutOfMemoryError e){

            reserve = null;
            System.gc();

            long endTime = System.currentTimeMillis();
            double timeTaken = (endTime - startTime) / 1000.0;

            System.err.println("\n--- HEAP FAILURE ---");
            System.out.println("Total Objects: " + count);
            System.out.println("Time Elapsed: " + timeTaken + " seconds");
        }
    }

    /**
     * Executes a deep recursion test to exhaust the JVM Stack memory.
     * <p>
     * This method recursively calls itself without a base case, forcing the
     * stack to grow until it exceeds the size limit defined by the -Xss flag.
     * </p>
     * * @param depth The current depth of the recursion, used for metric tracking.
     */
    public static void runStack(int depth){
        try{runStack(depth + 1);
        } catch (StackOverflowError e){
            System.err.println("--- STACK FAILURE REACHED ---");
            System.out.println("Max Depth:" + depth);
        }

    }

}
