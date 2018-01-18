package co.za.damien.chapter3.collections.comparing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Numbers sort before letters and upper case sorts before lower case
 * You can also sort numbers you create - use interface Comparable
 * <p>
 * If you implement Comparable you can use these objects when comparing
 * <p>
 * Comparator - which is used to specify a different order than the object itself provides
 * <p>
 * !Important - number 0 is returned when the current object is equal to the argument to compareTo
 * A number less than 0 is returned when the current object is smaller than the argument to compareTo
 * A number greater than 0 is returned when the current object is greater than the argument compareTo
 * <p>
 * when dealing with legacy code, (without generics) the compareTO requires a cast
 * <p>
 * java.lang.Comparable
 * <p>
 * * make sure your equals() has the same implementation as compareTo = 0
 * If there are discrepencies, you can use comparator to define the sort somewhere else
 *
 * Difference                               Comparable              Comparator
 * Package name                             java.lang               java.util
 * Interface must be implemented            Yes                     No
 * by class comparing
 * Method name in interface                 compareTo               compare
 * Number of parameters                     1                       2
 * Common to declare using lambda           No                      Yes
 *
 * If you are using TreeSet and TreeMap, the objects used as the element, key must implement Comparable or you must
 * pass in a Comparator
 */
public class ComparingExamples {

    private void comparableExamples() {
        /**
         * Comparable interface has just one method
         */
        class someClass implements Comparable<someClass> {

            private String duck;

            public String getDuck() {
                return duck;
            }

            @Override
            public int compareTo(someClass o) {
                return duck.compareTo(o.duck);
            }
        }
    }

    private void comparatorExamples() {
        /**
         * Sometimes you want to sort an object that did not implement comparable or
         * sort objects in different ways at different times.
         *
         * Comparator is actually a functional interface - single abstract method
         */
        class Duck implements Comparable<Duck> {
            private String name;
            private int weight;

            public Duck(String name, int weight) {
                this.name = name;
                this.weight = weight;
            }

            public String getName() {
                return name;
            }

            public int getWeight() {
                return weight;
            }

            public String toString() {
                return name;
            }

            public int compareTo(Duck d) {
                return name.compareTo(d.name);
            }
        }
//        Comparator<Duck> comparator = new Comparator<Duck>() {
//            @Override
//            public int compare(Duck o1, Duck o2) {
//                return o1.getWeight() - o2.getWeight();
//            }
//        };
        Comparator<Duck> comparator =(Duck o1, Duck o2) -> o1.getWeight() - o2.getWeight();

        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack", 7));
        ducks.add(new Duck("Puddles", 10));
        Collections.sort(ducks);
        System.out.println(ducks);             // [Puddles, Quack]
        Collections.sort(ducks, comparator);
        System.out.println(ducks);             // [Quack, Puddles]

        //using default methods on Comparator

        Comparator<Duck> newComparator = new Comparator<Duck>() {
            @Override
            public int compare(Duck o1, Duck o2) {
                Comparator<Duck> c = Comparator.comparing(s -> s.getName());
                c = c.thenComparingInt(s -> s.getWeight());
                return c.compare(o1, o2);
            }
        };
    }
}
