package co.za.damien.chapter3.s2.collections.complete;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * A collection that does not allow duplicates
 * Implement the Collection interface
 *
 * Main difference between set implementations - do not allow duplicates
 * But each offer different functionality
 *
 * equals() method is used
 * hashCode() knows which bucket to pick up the element, if the same hashCode is used for
 * all elements, java will have to call equals on all th elements
 */
public class SetExamples {

    private void hashSetExamples() {
        /**
         * Hash set stores elements in a hash table, -uses the hashcode method of the objects to retrieve
         * them more efficiently
         * Adding elements and checking if its in the set takes constant time, however you lose the order
         */
        Set<Integer> set = new HashSet<>();
        boolean b1 = set.add(2);    //true
        boolean b2 = set.add(4);    //true
        boolean b3 = set.add(2);    //false

         /** TreeSet stores its elements in a sorted tree structure - always sorted.
         * Adding and checking if an element is present is O(logn)
         * TreeSet implements a special interface called NavigableSet
         */
         Set<Integer> treeSet = new TreeSet<>();
        //NavigableSet has more methods
        NavigableSet<Integer> navigableSet = new TreeSet<>(treeSet);
        // E lower(E e) - returns the greatest element <e or null if no such element
        Integer lower = navigableSet.lower(12);
        //E floor(E e) - returns the greatest element <=e or null
        Integer floor = navigableSet.floor(12);
        //E ceiling(E e) - returns the smallest element >= e or null
        Integer ceiling = navigableSet.ceiling(12);
        //E higher(E e) - returns the smallest element > e or null
        Integer higher = navigableSet.higher(12);



    }
}
