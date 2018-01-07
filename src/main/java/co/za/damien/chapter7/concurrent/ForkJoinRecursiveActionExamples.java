package co.za.damien.chapter7.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * When a task gets too complicated we can split the task into multiple other tasks
 * using the fork/join framework
 *
 * The fork/join relies on the concept of recursion. Recursion is when a task calls itself to
 * solve a problem, a recursive solution is constructed with a base case and a recursive case
 *
 * Base case : a non recursive method that is used to terminate the recursive path
 *
 * Recursive case : a recursive method that may call itself one or multiple times
 *
 * If recursive function does not end, a StackOverflowError is thrown
 *
 * Steps to use fork join
 * 1. Create a ForkJoinTask - defines the recursive process
 * 2. Create a ForkJoinPool
 * 3. Start the ForkJoinTask
 *
 * You can implement fork/join by implementing one of two classes
 * RecursiveAction - Abstract class - implement compute() - void method
 * RecursiveTask - Abstract generic class - implement compute() returns generic type
 * Both implement ForkJoinTask Interface
 *
 *
 */
public class ForkJoinRecursiveActionExamples extends RecursiveAction {

    private int start;
    private int end;
    private Double[] weights;

    public ForkJoinRecursiveActionExamples(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected void compute() {
        if (end - start <= 3) {
            for  (int i=start; i<end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weighd = " + i);
            }
        } else {
            int middle = start + ((end - start) /2);
            System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
            invokeAll(new ForkJoinRecursiveActionExamples(weights, start, middle), new ForkJoinRecursiveActionExamples(weights, middle, end));
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];

        ForkJoinTask<?> task = new ForkJoinRecursiveActionExamples(weights, 0, weights.length);
        /**
         * This line below will determine the number of threads that can be used by the number of processors
         */
        ForkJoinPool pool  = new ForkJoinPool();
        pool.invoke(task);

        System.out.println();
        System.out.println("Weights: ");
        Arrays.asList(weights).stream().forEach(
                d-> {
                    System.out.println(d.intValue() + " ");
                }
        );
    }
}
