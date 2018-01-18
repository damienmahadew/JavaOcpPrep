package co.za.damien.chapter3.collections.complete;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * A queue is a collection that orders its elements in a specific order for processing.
 * Typical queue processes its elements in a first in, first out order but other orderings are possible
 * e.g. last in first out
 *
 * Implement the Collection interface
 *
 * You use a queue when elements are added and removed in a specific order. Typically used for sorting elements
 * prior to processing them
 *
 * Queues are assumed to be FIFO, but some implementations use LIFO
 * LinkedList implements both list and queue, allows entries in the front and back, but it isnt as efficient as
 * pure queue
 *
 * An ArrayDeque is pure double-ended - Java 6, stores elements in a resizable array - more efficient
 * than LinkedList
 *
 * Deque (Deck)
 */
public class QueueExamples {

    private void arrayDequeExamples() {
        /**
         * Method               Description                          For Queue          For Stack
         * boolean add(E e)     adds an element to the back         Yes                 No
         *                      of the queue and returns true
         *                      or throws an exception
         * E element()          Returns next element or throws      Yes                 No
         *                      an exception if empty queue
         * boolean offer(E e)   Adds an element to the back of      Yes                 No -- some queues have limited size
         *                      the queue and returns whether
         *                      successful
         * E remove()           Removes and returns next element    Yes                 No
         *                      or throws an exception if empty
         * void push(E e)       Adds an element to the front of     Yes                 Yes  -- rest are in Queue interface
         *                      the queue
         * E poll()             Removes and returns next element    Yes                 No
         *                      or returns null if empty
         * E peek()             Returns next element or returns     Yes                 Yes
         *                      null if empty
         * E pop()              removes and returns next element    No                  Yes
         *                      throws an exception if empty
         *
         * When talking about LIFO remember - push poll peek
         * FIFO  - offer poll peek
         */
        //Queue example
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println(queue.offer(10));  // true
        System.out.println(queue.offer(4));   // true
        System.out.println(queue.peek());     // 10
        System.out.println(queue.poll());     // 10
        System.out.println(queue.poll());     // 4
        System.out.println(queue.peek());     // null

        //Stack example
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        stack.push(4);
        System.out.println(stack.peek());     // 4
        System.out.println(stack.poll());     // 4
        System.out.println(stack.poll());     // 10
        System.out.println(stack.peek());     // null
    }
}
