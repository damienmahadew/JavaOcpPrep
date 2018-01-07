package co.za.damien.chapter7.executor;

/**
 * Thread pool - group of pre instantiated reusable threads that are available to perform a set of tasks
 *
 * Executors factory has :
 *
 *  newSingleThreadExecutor() - ExecutorService
 *  -single thread, results are processed sequentially
 *
 *  newSingleThreadScheduledExecutor() - ScheduledExecutorService
 *  - same as above but threads are scheduled
 *
 *  newCachedThreadPool() - ExecutorService
 *  - creates a thread pool that creates new threads as needed but will reuse existing threads when they
 *  are available
 *
 *  newFixedThreadPool(int nThreads) - ExecutorService
 *  - creates a thread pool with a fixed number of reusable threads
 *
 *  newScheduledThreadPool(int nThreads) - ScheduledExecutorService
 *  - same as above but for scheduled tasks
 *
 *  normally use Runtime.getRuntime().availableProcessors() to get available thread pool size
 */
public class IncreasingConcurrencyWithPoolsExamples {
}
