package co.za.damien.chapter3.s2.collections.complete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Collection interface - contains useful methods for working with lists, sets and queueus
 */
public class CommonCollectionMethods {

    public void commonMethods() {
        Collection list = new ArrayList();
        //add(E element) - returns true for some collections, others have logic to determine if it was successful
        System.out.println(list.add("add")); //true
        System.out.println(list.add(2)); //true

        Collection set = new HashSet();
        System.out.println(set.add("add")); //true
        System.out.println(set.add("add")); //false

        //boolean remove(Object object), two remove methods, one takes the index, the other takes the value
        System.out.println(list.remove("add")); //true
        System.out.println(list.remove("notInList")); //false
        System.out.println(list.remove(100)); //throws IndexOutOfBoundsException

        //boolean isEmpty() and int size()
        System.out.println(list.isEmpty());
        System.out.println(list.size());

        //void clear() - discard all elements of a collection
        list.clear();

        //boolean contains(Object object)
        System.out.println(list.contains("add"));
    }

    public static void main(String[] args) {
        CommonCollectionMethods methods = new CommonCollectionMethods();
        methods.commonMethods();
    }
}
