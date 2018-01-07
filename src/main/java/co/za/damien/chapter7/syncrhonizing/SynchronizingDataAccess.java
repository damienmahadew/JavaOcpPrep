package co.za.damien.chapter7.syncrhonizing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread safety is the property of an object that guarantees safe execution by
 * multiple threads at the same time
 *
 * Threads run in the same environment sharing memory - have to secure data
 *
 * Race condition - unexpected result of two tasks executing at the same time
 * see method below for unsafe operation
 *
 * We can prevent this by using the atomic package
 *  atomic - operation carried out as a single unit of execution
 *
 *  Atomic classes :
 *
 *  AtomicBoolean
 *  AtomicInteger
 *  AtomicIntegerArray
 *  AtomicLong
 *  AtomicLongArray
 *  AtomicReference - generic object reference
 *  AtomicReferenceArray - array of the above
 *
 *  TO ensure data is updated in order - common technique is a monitor or lock
 *  Monitor supports mutual exclusion or the property that at most one thread is execution a
 *  particular segment of code at a given time -- see method below
 *
 *  You can also synchronize methods -- see below
 *
 *  synchronization is taking multiple threads and making them behave in a single thread manner
 *  basically protecting data integrity at the cost of performance
 */
public class SynchronizingDataAccess {

    private int sheepCount = 0;
    private void incrementAndReport() {
        System.out.print((++sheepCount) + " ");
    }

    private static void raceConditionExample() {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SynchronizingDataAccess dataAccess = new SynchronizingDataAccess();
            for (int i=0; i< 10; i ++) {
                service.submit(() -> dataAccess.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void atomicTypesAndMethodsExamples() {
        /**
         * This will resolve double numbers appearing using ++ or --
         * As there is a read and write operation in the same line and one value might change during the
         * operation by another thread
         */
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        //Returns value
        atomicBoolean.get();
        //sets value
        atomicBoolean.set(true);
        //getAndSet new value
        atomicBoolean.getAndSet(true);

        AtomicInteger integer = new AtomicInteger(5);
        //++value
        integer.incrementAndGet();
        //value++
        integer.getAndIncrement();
        //--value
        integer.decrementAndGet();
        //value++
        integer.incrementAndGet();
    }

    private void monitorExample() {
        /**
         * any object can be used as a monitor along with the syncrhonized keyword
         */

        Object someObjectThatNeedsToBeThreadSafe = new Object();
        synchronized (someObjectThatNeedsToBeThreadSafe) {
            //do something here
            /**
             * Each thread will first see if another thread is using this block if not they acquire the lock
             */
        }

        synchronized (this) {
            //do something here
        }

        //if you are using a random lock object, use final so it cant be changed
        final Object lock = new Object();
        synchronized (lock) {
            //do something here
        }
    }

    private synchronized void syncrhonizedMethod() {
        //this method is thread safe
    }

    private static synchronized void staticSynchronizedMethod() {
        //you can synchronize static methods, the lock is on the class
    }

    public static void main(String[] args) {
        raceConditionExample();
    }
}
