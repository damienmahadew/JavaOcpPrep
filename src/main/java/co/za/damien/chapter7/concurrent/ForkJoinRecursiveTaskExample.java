package co.za.damien.chapter7.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by damien.mahadew on 2018-01-07.
 */
public class ForkJoinRecursiveTaskExample extends RecursiveTask<Double> {
    private int start;
    private int end;
    private  Double[] weights;

    public ForkJoinRecursiveTaskExample(int start, int end, Double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected Double compute() {
        if (end - start <= 3) {
            double sum = 0;
            for (int i= start; i< end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("animal weighed = " + i);
                sum += weights[i];
            }
            return sum;
        } else {
            int middle = start + ((end - start) /2);
            System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
            RecursiveTask<Double> otherTask = new ForkJoinRecursiveTaskExample(start, middle, weights);
            /**
             * Since invoke all does not return a value, we use fork and join
             * fork() instructs fork/join framework to complete task in separate thread,
             * while the join method causes the current thread to wait for results
             */
            otherTask.fork();

            /**
             * if otherTask.fork().join() is called - this method performs like a single thread with performance
             * need to ensure fork is called before a new task and join is called after
             */
            return new ForkJoinRecursiveTaskExample(middle, end, weights).compute() + otherTask.join();
        }
    }

    private void forkJoinIssues() {
        /**
         * can be a bit overwhelming
         * List of tips for identifying issues in fork/join examples
         *
         * 1. The class should extend RecursiveAction or RecursiveTask
         * 2. If the class extends RecursiveAction, should override compute void method and takes no arguments
         * 3. If recursiveTask - should override compute and return a generic type and takes no arguments
         * 4. invokeAll method takes two instances of fork/join class and does not return a result
         * 5. fork() method causes a new task to be submitted to the pool - similar to executor submit()
         * 6. join() is called after the fork() - causes current thread to wait for results from a subtask
         * 7. unlike fork(), calling compute within a compute method causes the task to wait for the results of the sub
         * task
         * 8. fork() should be called before the current thread performs a compute() operation, with join()
         * 9. since compute() takes no arguments, the constructor of the class is often used to pass instructions
         * to the task
         */
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new ForkJoinRecursiveTaskExample(0, weights.length, weights);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);
        System.out.println("Sum = " + sum);
    }
}
