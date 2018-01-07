package co.za.damien.chapter7.executor;

import java.util.concurrent.*;

/**
 * To schedule for a future time or repeatedly
 *  use ScheduledExecutorService (extends ExecutorService interface)
 *
 *  4 methods in exam
 *
 *  Two methods below return ScheduledFuture<V> , same as Future except has a getDelay()
 *  schedule(Callable<V> callable, long delay, TimeUnit unit)
 *  - creates and executes a callable after given delay
 *
 *  schedule(Runnable command, long delay, TimeUnit unit)
 *  - creates and executes a runnable after given delay
 *
 *  scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
 *  - creates and executes runnable after given delay creating a new task every period value
 *
 *  scheduledAtFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
 *  -creates and executes runnable after given delay and then applies delay between each execution and
 *  commencement of next
 *
 *
 */
public class SchedulerExamples {

    private void schedulerExecutorServiceExamples() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable task1 = () -> System.out.println("Hello");
        Callable<String> task2 = () -> "Monkey";

        Future<?> result1 = service.schedule(task1, 10, TimeUnit.SECONDS);
        ScheduledFuture<String> result2 = service.schedule(task2, 8, TimeUnit.SECONDS);

        //Initial delay of 1 second, creates a new task every 3 seconds thereafter regardless if the
        //previous one completed
        ScheduledFuture<?> result3 = service.scheduleAtFixedRate(task1, 1,3, TimeUnit.SECONDS);

        //Initial delay of 1 second, creates a new task every 3 seconds after the previous one has completed
        ScheduledFuture<?> result4 = service.scheduleWithFixedDelay(task1, 1,3, TimeUnit.SECONDS);

        /**
         * The above two do not take a Callable as they could run infinitely and would return an infinite
         * number of futures
         */
    }

    public static void main(String[] args) {

    }
}
