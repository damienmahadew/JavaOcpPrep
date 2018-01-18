package co.za.damien.chapter3.collections.complete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Ordered collection of elements that allows duplicate entries,
 * Elements can be accessed by an int index; - index starts at 0
 * All list implementations are ordered
 *
 * Implement the Collection interface
 */
public class ListExamples {

    private void arrayListExamples() {
        /**
         * ArrayList is like a resizable array, when elements are added, the list automatically grows.
         * Main advantage is you can look up any element in constant time, but adding or removing elements is slower
         * than accessing the element.
         * Good choice when you are reading more often than writing
         */
        List<String> list = new ArrayList<>();

        list.add("String");
        list.add(1, "AnotherString"); //moves the rest toward the end
        String s = list.get(1);
        int index = list.indexOf("AnotherString"); //-1 if not found
        int lastIndex = list.lastIndexOf("AnotherString"); // -1 if not found
        list.remove("AnotherString");
        String original = list.set(0, "FirstString"); // replaces index with new object, returns the original

        //using iterators - predates generics so have to cast
        Iterator iterator1 = list.iterator();
        while(iterator1.hasNext()) {
            String string = (String) iterator1.next();
            System.out.println(string);
        }

        //hybrid version available, removes casting
        Iterator<String> iterator2 = list.iterator();
        while(iterator2.hasNext()) {
            String string = iterator2.next();
            System.out.println(string);
        }
    }

    private void linkedListExamples() {
        /**
         * LinkedList is a special case because it implements both List and Queue.
         * Has all the methods of list and additional methods to add to the beginning and end of the list.
         * You can access, add and remove from the beginning and end of the list in constant time.
         * The trade off is dealing with an arbitrary index is not constant time.
         * Good when you will be using it like a queue.
         */
    }

    private void oldImplementations() {
        /**
         * Vector - ArrayList Introduced in java 1.2 and replaced vectors(does the same thing as array list but more slowly, but thread safe)
         *
         * Stack - data structure where you add and remove elements from the top of the stack. Stack extends Vector
         * Replacement for stack is ArrayDeque
         */
    }
}
