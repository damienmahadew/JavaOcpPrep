package co.za.damien.chapter7.problems;

/**
 * Threading problems can occur in multi-threaded applications when two or more threads
 * interact in an unexpected and undesirable way. e.g. two threads may block each other from accessing code
 *
 */
public class IdentifyingThreadingProblems {

    private void understandingLiveness() {
        /**
         * Liveness - the ability of an application to be able to execute in a timely manner
         * Liveness problems - when an application is unresponsive or stuck
         *
         * Three types:
         *
         * Deadlock, Starvation, LiveLock
         *
         * Deadlock:
         * occurs when two or more threads are blocked forever, each waiting on the other
         *
         * preventing deadlocks - cant in most situations. - other hand - numerous strategies to avoid deadlocks
         * 1. order resource requests
         *
         * Starvation:
         * Occurs when a single thread is denied access to a shared resource or lock. The thread is active
         * but is unable to complete work as other threads are constantly taking the resource
         *
         * LiveLock:
         * Occurs when two or more threads are conceptually blocked forever, although they are still active
         *  and trying to complete their task. Special case of resource starvation where two or more threads
         *  actively try to acquire locks , are unable to do so, and restart the process
         *
         *  Basically two threads trying to resolve a deadlock
         *
         */
    }

    private void managingRaceConditions() {
        /**
         * Race condition is an undesirable result that occurs when two tasks, which should be completed
         * sequentially, are completed at the same time.
         *
         * Race conditions lead to invalid data, e.g. two people creating an online account at the same time
         * with the same username
         * best case - one person wins and the other is given an error message
         */
    }
}
