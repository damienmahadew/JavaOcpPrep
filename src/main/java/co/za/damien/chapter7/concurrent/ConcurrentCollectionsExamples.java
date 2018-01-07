package co.za.damien.chapter7.concurrent;

import java.util.*;
import java.util.concurrent.*;

/**
 * Can use synchronized to handle thread safety in collections but
 * concurrent collection class is there for convenience, prevents us from making mistakes
 * in our own implementations, has performance enhancements that avoid unnecessary synchronizations
 *
 * see below for example
 *
 * Purpose of concurrent collection - solve memory consistency issues
 * - occurs when two threads have inconsistent views of what should be the same data
 *
 * when two threads try to modify a non-concurrent collection - ConcurrentModificationException
 * see example below
 *
 * Concurrent classses should be used anytime that multiple threads modify  a collections object outside a
 * synchronized block
 * Types of concurrent classes
 *
 *  Class Name                  Java Collections Framework Interface    Ordered     Sorted      Blocking
 *  ConcurrentHashMap           ConcurrentMap                           N           N           N
 *  ConcurrentLinkedDeque       Deque                                   Y           N           N
 *  ConcurrentLinkedQueue       Queue                                   Y           N           N
 *  ConcurrentSkipListMap       ConcurrentMap,SortedMap,NavigableMap    Y           Y           N
 *  ConcurrentSkipListSet       SortedSet,NavigableSet                  Y           Y           N
 *  CopyOnWriteArrayList        List                                    Y           N           N
 *  CopyOnWriteArraySet         Set                                     N           N           N
 *  LinkedBlockingDeque         BlockingDeque,BlockingQueue             Y           N           Y
 *  LinkedBlockingQueue         BlockingQueue                           Y           N           Y
 */
public class ConcurrentCollectionsExamples {

    private Map<String, String> someMap = new ConcurrentHashMap<>();

    private void put(String key, String value) {
        someMap.put(key, value);
    }

    public String get(String key) {
        return someMap.get(key);
    }

    private void concurrentModificationException() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        for(String key: map.keySet()) {
            map.remove(key);
        }
        /**
         * This will throw a ConcurrentModificationException because the iterator does not see the
         * updated map. Just change the HashMap to ConcurrentHashMap and its sorted!
         */
    }

    private void concurrentExamples() {
        //map
        Map<String, Integer> map = new ConcurrentHashMap<>();
        ConcurrentMap<String, Integer> map2 = new ConcurrentHashMap<>();
        map.put("zebra", 10);
        map.put("elephant", 50);
        System.out.println(map.get("elephant"));

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
        System.out.println("Peek queue = " + queue.peek());
        System.out.println("Poll queue = " + queue.poll());

        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(12);
        deque.push(40);
        System.out.println("Peek deque = " + deque.peek());
        System.out.println("Poll deque = " + deque.poll());
        System.out.println("Poll deque = " + deque.poll());
    }

    private void blockingQueueDequeExamples() {
        /**
         * LinkedBlockingQueue & LinkedBlockingDeque - regular queues except it includes
         * methods that will wait for a specific time to complete an operation
         *
         * new methods
         *
         * offer(E e, long timeout, TimeUnit unit)
         * - adds item to queue waiting a specific time, returning false if time elapses before
         * space is available
         *
         * LinkedBlockingQueue
         * poll(long timeout, TimeUnit unit)
         * -retrieves and removes item from queue waiting the specified time, returning null if
         * the time elapses before item is available
         *
         * LinkedBlockingQueue maintains a linked list
         *
         * LinkedBlockingDeque
         * offerFirst(E e, long timeout, TimeUnit unit)
         * -adds item to front of queue waiting for time, returning false if time elapses
         * before space available
         *
         * offerLast(E e, long timeout, TimeUnit unit)
         * -adds item to end of queue waiting for time, returning false if time elapses
         * before space is available
         *
         * pollFirst(long timeout, TimeUnit unit)
         *
         * pollLast(long timeout, TimeUnit unit)
         */

        try {
            BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
            blockingQueue.offer(30);
            blockingQueue.offer(20, 4, TimeUnit.SECONDS);

            System.out.println("queue poll = " + blockingQueue.poll());
            System.out.println("queue poll with timeout = " + blockingQueue.poll(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            //handle exception
        }

        try {
            BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
            blockingDeque.offerFirst(30);
            blockingDeque.offerFirst(10, 4, TimeUnit.SECONDS);
            blockingDeque.offerLast(20);
            blockingDeque.offerLast(40, 4, TimeUnit.SECONDS);

            System.out.println("deque poll first = " + blockingDeque.pollFirst());
            System.out.println("deque poll first with timeout = " + blockingDeque.pollFirst(10, TimeUnit.SECONDS));

            System.out.println("deque poll last = " + blockingDeque.pollLast());
            System.out.println("deque poll last with timeout = " + blockingDeque.pollLast(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            //handle exception
        }
    }

    private void skipListsExamples() {
        /**
         * SkipList, ConcurrentSkipListSet, ConcurrentSkipListMap
         * are current versions of their sorted counterparts, TreeSet, TreeMap
         *
         * Recommended that you use interfaces such as SortedMap, NavigableMap to as reference types
         */
    }

    private void copyOnWriteExamples() {
        /**
         * CopyOnWriteArrayList, CopyOnWriteArraySet - copies elements to a new structure
         * anytime an element is added, modified or removed from collection.
         * By modified - reference must change
         *
         * Although data is copied, the reference to the object does not change
         *
         * *Any iterator created before modification will not see the changes but use initial elements
         */
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4,3,52));
        for (Integer item : list) {
            System.out.println(item + " ");
            list.add(9);
        }

        System.out.println();
        System.out.println("size = " + list.size());

        /**
         * Output is 4 3 52, despite adding elements, it does not change the iterator
         * If we had to use a normal array list, a ConcurrentModificationException will be thrown
         *
         * these classes use a lot of memory as a new instance is created each time
         */
    }

    private void obtainingSynchronizedCollectionsExamples() {
        /**
         * concurrency api contains synchronized versions of existing non-concurrent collection objects.
         * from Collections class, returns a reference that is the same type of the underlying collection
         *
         * synchronizedCollection(Collection<T> c)
         * synchronizedList(List<T> list)
         * synchronizedMap(Map<K, V> m)
         * synchronizedNavigableMap(NavigableMap<K, V> m
         *  synchronizedNavigableSet(NavigableSet<K, V> s)
         *  synchronizedSet(Set<T> s)
         *  synchronizedSortedMap<SortedMap<K, V> m)
         *  synchronizedSortedSet(SortedSet<T> s)
         *
         *  NOTE using this does not ensure synchronization on iterators, you will need
         *  a synchronization block to cater for this
         *
         *  If these new collections are modified while being operated on they will throw a
         *  ConcurrentModificationException
         */

        List<Integer> list = new ArrayList<>();

        List<Integer> newList = Collections.synchronizedList(list);
    }

    public static void main(String[] args) {
        ConcurrentCollectionsExamples test =new ConcurrentCollectionsExamples();
        test.concurrentExamples();
        test.blockingQueueDequeExamples();
    }

}
