package co.za.damien.chapter7.introduction;

/**
 * Thread - smallest unit of execution that can be scheduled by an OS
 * Process - group of associated threads that execute in the same environment
 * Shared environment - threads that share the same memory space and can communicate directly with each other
 *
 *  Static variables - can be useful for performing complex multi threaded tasks
 *
 *  Task - single unit of work performed by a thread
 *   -- in this chapter task will be implemented as a lambda expression
 */
public class ThreadsIntroduction {

    private void distinguishingThreadTypes() {
        /**
         * All java apps are multi threaded
         *  System defined threads - created by JVM - runs in background e.g. garbage collection thread
         *  User defined threads - defined by developer
         *
         * When a system defined thread has an error it throws an Error not an Exception
         *
         * Normal applications only have 1 user defined thread - one that calls main method
         *  called single-threaded application
         *
         * *NOT IN EXAM - daemon thread- does not prevent JVM from exiting when program finishes
         *  all threads can be marked as daemon threads
         */
    }

    private void understandingThreadConcurrency() {
        /**
         * Concurrency - execute multiple threads at the same time
         *
         * Thread scheduler - determines which threads should be currently executing
         *  may employ a round robin schedule - each thread receives an equal number of CPU cycles
         * Context Switch - process of storing a thread's current state and later restoring to continue
         * execution
         *
         * Thread can interrupt or supersede another thread if it has a higher priority
         *
         * Thread priority - numeric value - used by scheduler
         *
         * Default - user defined threads - Thread.NORM_PRIORITY
         *
         * If threads have same priority then the scheduler chooses randomly
         */
        Integer lowestPriority = Thread.MIN_PRIORITY;
        Integer midPriority = Thread.NORM_PRIORITY;
        Integer highPriority = Thread.MAX_PRIORITY;
    }

    private void introducingRunnable() {
        /**
         * Functional Interface that takes no arguments and returns no data
         *
         * Used to define the work a thread will execute
         *
         * Commonly used in class definitions - but will be used as lambdas in the exam
         */
    }

    public void creatingAThread() {
        /**
         * Simplest way - Thread class
         * 1. Define the thread with the task
         * 2. Start the task by Thread.start()
         *
         * EXAM - order of thread execution is not guaranteed! you will need to determine the result
         * yourself
         *
         * Two ways of defining the task
         *
         * 1. Provide a runnable object or lambda expression to the thread constructor
         * 2. Create a class that extends Thread and override the run method
         *
         * Only extend the Thread class if you are creating your own priority based thread.
         */
    }

    private void polling() {
        /**
         * Using Thread.sleep(milliseconds) to delay a thread
         * Throws InterruptedException
         */
    }
}
