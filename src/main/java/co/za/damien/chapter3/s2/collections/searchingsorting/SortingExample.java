package co.za.damien.chapter3.s2.collections.searchingsorting;

import java.util.*;

/**
 * sort() and binarySearch allows you to pass through a Comparator object, when you dont want to use
 * the natural order
 */
public class SortingExample {

    static class Rabbit{ int id; }

    private void sortingExample() {
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());
//        Collections.sort(rabbits);      // DOES NOT COMPILE - list does not implement Comparable
        Comparator<Rabbit> c = (r1, r2) -> r1.id - r2.id;
        Collections.sort(rabbits, c); // using a custom comparator

        List<String> names = Arrays.asList("Fluffy", "Hoppy");
        Comparator<String> c2 = Comparator.reverseOrder();
        int index = Collections.binarySearch(names, "Hoppy", c2);
        System.out.println(index);  // prints -1, because binary search expects the order to be in reverse(descending,
        //but the order is ascending
    }
}
