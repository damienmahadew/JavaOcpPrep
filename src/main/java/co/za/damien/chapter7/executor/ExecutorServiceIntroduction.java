package co.za.damien.chapter7.executor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Concurrency API - java 5
 *  Introduced ExecutorService - creates and manages threads for you
 *
 *  First obtain instance of ExecutorService interface and send the service tasks
 *      Features
 *          1. Thread pooling
 *          2. Scheduling
 *  Recommended anytime you need to create and execute a separate task even if it is a single thread
 *
 *  If you try and shutdown a thread after it has been created and it fails to stop will result in the program hanging
 *
 *  execute is a void - you can use submit() to get a Future object which can be used to determine if the
 *  task is complete
 *
 *  EXAM : methods to know for exam
 *
 *      void execute(Runnable command)
 *
 *      Future<?> submit(Callable<T> task)
 *      - Executes some point in the future, returns a Future representing the pending result of the task
 *
 *      !the two below will wait until the results are available before returning control to the enclosing program
 *      Future.isDone() may return true if the task completed or exception thrown
 *
 *      <T> List<Future<t>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException,
 *      ExecutionException
 *      - executes the tasks synchronously - returns List of Future objects in the same order
 *
 *      <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException,
 *      ExecutionException
 *      - executes the given tasks synchronously returning the result of one of the finished tasks,
 *      cancelling any unfinished tasks. Generally the first task, but not guaranteed
 *
 *  submit is preferred even if the future object is not used
 */
public class ExecutorServiceIntroduction {

    private void executorExamples() {
        /**
         * Interface - obtain instance from Executors factory
         *
         * With a single thread executor - results are guaranteed to be in order
         *
         * It queues each task
         */
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("Begin");
            service.execute(() -> System.out.println("Printing zoo inventory"));
            service.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    System.out.println("printing record = " + i);
                }
            });
            service.execute(() -> System.out.println("Printing zoo inventory 2"));
            System.out.println("end");
        } finally {
            /**
             * Must call shutdown otherwise a non-daemon thread is created and application will
             * never terminate. The executor service does not implement AutoCloseable
             *
             * If you call shutdown, it will reject any new tasks and throw a RejectedExecutionException
             * If tasks are still queued from before, the isShutdown() will return true and isTerminated() will
             * return false
             */
            if (service != null) service.shutdown();

            /**
             * You can use shutdownNow() to stop all running tasks and discards any that have not been started as
             * yet . returns a List<Runnable> of tasks that were not started
             */

            List<Runnable> listOfUnexecutedTasks = service.shutdownNow();

        }
    }

    private void futureExamples() throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<?> future = service.submit(()-> {
            System.out.println("Hello");
        });

        //methods available

        boolean isDone = future.isDone(); //true if completed, exception or cancelled

        boolean isCancelled = future.isCancelled(); // true if cancelled before completed

        boolean cancel = future.cancel(true); // attempts to cancel

        Object result = future.get(); //gets result, waits endlessly if not yet available

        Object resultAgain = future.get(1000, TimeUnit.MILLISECONDS); //tries to get result in time specified,
        // if not received in time, a TimeoutException is thrown
    }

    private void callableExamples() throws Exception {
        /**
         * Callable added in Java 5, similar to Runnable except it returns a value and can throw a checked exception
         * Available as functional interface in Java 8
         * Similar to Supplier in functional interfaces except this can throw an exception, almost impossible to  tell them apart though
         * you can cast -> (Callable<Integer>)() -> {throw new Exception}
         *
         * note if no value returned then it may be considered a runnable expression and will not cater for checked exceptions
         */
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            Future<Integer> future = service.submit(() -> 30+11);
            System.out.println("result from future = " + future.get());
        } finally {
            service.shutdown();
        }
    }

    private void waitingForTasks() throws InterruptedException {
        /**
         * Alternate way of waiting for tasks. Instead of future.get()
         */
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            //add tasks
        } finally {
            if (service != null) service.shutdown();
        }

        if (service != null) {
            service.awaitTermination(1, TimeUnit.MINUTES);

            if (service.isTerminated()) {
                System.out.println("All tasks finished");
            } else {
                System.out.println("At least one task is finished");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorServiceIntroduction service = new ExecutorServiceIntroduction();
        service.executorExamples();
        service.callableExamples();
    }
}
