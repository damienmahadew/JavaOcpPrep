package co.za.damien.chapter7.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Concurrency includes classes that be can be used to coordinate tasks among a group of related threads.
 * Two classes:
 * CyclicBarrier
 * ForkJoinPool
 */
public class ManagingConcurrentProcessesExamples {

    private void removeAnimals() {
        System.out.println("Removing animals");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addAnimals() {
        System.out.println("adding the animals");
    }

    private void performTask() {
        removeAnimals();
        cleanPen();
        addAnimals();
    }

    private void performTaskWithCyclicBarriers(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            //handle exception here
        }
    }

    private void creatingCyclicBarrier() {
        ExecutorService service = null;

        /**
         * Although the order is correct for single thread,
         * When using multiple threads, the order is incorrect
         * Removing animals
         Removing animals
         Cleaning the pen
         Removing animals
         Cleaning the pen
         adding the animals
         Removing animals
         Cleaning the pen
         adding the animals
         adding the animals
         Cleaning the pen
         adding the animals
         */
        try {
            service = Executors.newFixedThreadPool(4);
            ManagingConcurrentProcessesExamples examples = new ManagingConcurrentProcessesExamples();
            for (int i=0; i<4; i++) {
                service.submit(() -> examples.performTask());
            }
        } finally {
            if (service != null) service.shutdown();
        }

        /**
         * Use cyclic barrier class to improve results - takesin its constructor a limit value,
         * indicating the number of threads to wait for.
         * As each thread finishes it calls the await() method on the cyclic barrier.
         * Once the specified number of threads have each called await(), the barrier is released and all the
         * threads can continue
         *
         * correct way of doing the above implementation
         *
         * Removing animals
         Removing animals
         Removing animals
         Removing animals
         Cleaning the pen
         Cleaning the pen
         Cleaning the pen
         Cleaning the pen
         ***pen cleaned
         adding the animals
         adding the animals
         adding the animals
         adding the animals
         *
         * NOTE : the number of threads specified must be equal or greater than the number passed
         * to the cyclic barrier otherwise the threads will hang as the cyclic barrier will not continue processing
         *
         * After cyclic barrier is broken, all threads are released and no of threads waiting on the cyclic barrier
         * goes back to zero, at this point the cyclic barrier may be used again.
         */
        service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            ManagingConcurrentProcessesExamples examples = new ManagingConcurrentProcessesExamples();
            /**
             * requires objects to be static or passed through to synchronize
             */
            CyclicBarrier c1 = new CyclicBarrier(4);
            //different type of constructor - takes in a runnable method upon completion
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("***pen cleaned"));
            for (int i=0; i<4; i++) {
                service.submit(() -> examples.performTaskWithCyclicBarriers(c1, c2));
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void forkJoinExamples() {

    }

    public static void main(String[] args) {
        ManagingConcurrentProcessesExamples examples = new ManagingConcurrentProcessesExamples();
        examples.creatingCyclicBarrier();
    }
}
