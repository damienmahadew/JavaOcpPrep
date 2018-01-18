package co.za.damien.chapter3.collections.additions;

import java.util.*;
import java.util.function.*;

/**
 * Method references and lambdas are crucial in java 8
 * new methods:
 * removeIf() - most important thing - it is one of two methods that are on a collection and it takes a lambda parameter
 * forEach()
 * merge()
 * computeIfPresent()
 * computeIfAbsent()
 */
public class Java8Additions {

    static class Duck {
        private int weight;
        private String name;

        public int getWeight() {
            return weight;
        }

        public String getName() {
            return name;
        }
    }

    public static int compareByWeight(Duck d1, Duck d2) {
        return d1.getWeight() - d2.getWeight();
    }
    public static int compareByName(Duck d1, Duck d2) {
        return d1.getName().compareTo(d2.getName());
    }

    private void methodReferencesExamples() {
        /**
         * Method references are a way to make the code shorter by reducing some of the code
         * that can be inferred and simply mentioning the name of the method.
         */

        //instead of saying this:
        Comparator<Duck> duckComparator = (Duck d1, Duck d2) -> compareByWeight(d1, d2);
        //you could do this
        Comparator<Duck> duckComparatorInferred = Java8Additions::compareByWeight;
        /**
         * The :: tells the java to pass the parameters automatically into the method
         * :: returns a functional interface
         *
         * 4 Formats for method references
         * 1. Static methods
         * 2. Instance methods on a particular instance
         * 3. Instance methods on an instance to be determined at runtime
         * 4. Constructors
         */

        //the method reference are available to be called in the future

        //Static methods
        Consumer<List<Integer>> methodRef1 = Collections::sort;
        Consumer<List<Integer>> lambda1 = s -> Collections.sort(s);
        //sort has overloaded methods, but since Consumer takes only 1, it knows which one to use by inferring

        //instance methods
        String string = "abc";
        Predicate<String> methodRef2 = string::startsWith;
        Predicate<String> lambda2 = s -> string.startsWith(s);

        //calling an instance method without knowing the instance in advance
        Predicate<String> methodRef3 = String::isEmpty; //java knows there is no parameters to isEmpty - uses the isntance
        //variable created at runtime
        Predicate<String> lambda3 = s -> s.isEmpty();

        //Constructors - special type of reference that uses new instead of a method
        Supplier<ArrayList> methodRef4 = ArrayList::new;
        Supplier<ArrayList> lambda4 = () -> new ArrayList();
    }

    private void removingCondtionally() {
        removeIfExamples();
        replaceAllExamples();
        forEachExamples();
    }

    private void mapApis() {
        putIfAbsentExamples();
        mergeExamples();
        computeIfPresentExamples();

        /**
         * computeIfAbsent() - runs only when the key isnt present or value null
         *
         * if the Function returns null, the key will never be added to the map
         */
        Map<String, Integer> counts = new HashMap<>(); counts.put("Jenny", 15); counts.put("Tom", null);
        Function<String, Integer> mapper = (k) -> 1;
        Integer jenny = counts.computeIfAbsent("Jenny", mapper); // 15
        Integer sam = counts.computeIfAbsent("Sam", mapper); // 1
        Integer tom = counts.computeIfAbsent("Tom", mapper); // 1
        System.out.println(counts); // {Tom=1, Jenny=15, Sam=1}
    }

    private void computeIfPresentExamples() {
        /**
         * ifPresent() - calls BiFunction if the requested key is found
         */
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        BiFunction<String, Integer, Integer> mapper = (k, v) -> v + 1; //the return value is the new value
        Integer jenny = counts.computeIfPresent("Jenny", mapper);
        Integer sam = counts.computeIfPresent("Sam", mapper);
        System.out.println(counts); // {Jenny=2}
        System.out.println(jenny); // 2
        System.out.println(sam); // null
    }

    private void mergeExamples() {
        /**
         * merge() - allows adding logic to the problem of what to choose.
         * merge(key, newValue, someFunction), replaces the actual value with another
         * value returned from the function(oldValue, newValue)
         *
         * if the key does not exist, it adds the key,value pair to the map
         *
         * Mapping function only called when two non null values
         */
        BiFunction<String, String, String> mapper = (v1, v2)
                -> v1.length() > v2.length() ? v1: v2;
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");
        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);
        System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
        System.out.println(jenny);     // Bus Tour
        System.out.println(tom);       // Skyride

        //* if mapping function is called and the result is null, the key is removed from the map
        BiFunction<String, String, String> nullMapper = (v1, v2) -> null;

        Map<String, String> newFavorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Bus Tour");
        favorites.merge("Jenny", "Skyride", nullMapper);
        favorites.merge("Sam", "Skyride", nullMapper); //same was added because the key was not in the original list
        System.out.println(favorites); // {Tom=Bus Tour, Sam=Skyride}
    }

    private void putIfAbsentExamples() {
        /**
         * New methods on the map interface, but only merge() is on the exam. The other two are computeIfPresent(),
         * and computeIfAbsent()
         */
        //sometimes you need to update a specific key in a map
        Map<String, String> map = new HashMap<>();
        map.put("Jenny", "Bus");
        //to update
        map.put("Jenny", "Bus Tour");

        //you can use putIfAbsent() - will add to the map if it isnt already there, skips it if the value is not null*
        map.putIfAbsent("Tom", "Cruise");
    }

    private void forEachExamples() {
        /**
         * Looping is common. Few ways of doing this, an iterator, an enhanced for loop, etc..
         */
        List<String> cats = Arrays.asList("Annie", "Ripley");
        cats.forEach(s -> System.out.println(s));
        //or
        cats.forEach(System.out::println);
    }

    private void replaceAllExamples() {
        /**
         * replaceAll - lets you pass a lambda expression and have it applied to each element in the list
         * void replaceAll(UnaryOperator<E> o) - takes one parameter and returns a value of the same type
         */
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.replaceAll(x -> x*2);
        System.out.println(list); // [2, 4, 6]
    }

    private void removeIfExamples() {
        /**
         * Java 8 - new method - removeIf
         * boolean removeId(Predicate<? super E> filter)
         * Lambdas use deferred execution, this allows specifying logic to run when that point in code is reached
         */
        List<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list);  // [Magician, Assistant]
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list);  // [Magician]
    }

    private void basicsOfMergeAndComputeMethods() {
        /**
         * Scenario         merge                   computeIfAbsent                 computeIfPresent
         * Key in map       result of function      No action                       Result of function
         *
         * Key not in map   adds new value to map   Result of function              No action
         *
         * Function         BiFunction(oldValue,    BiFunction(key, existing        Function(key, returnType)
         * interface used   newValue, returnType)   value, return type) - returns   returns new value
         *                  returns new value       returns new value
         */
    }

    private void mergeAndComputeWithNulls() {
        /**
         * key has          mapping function returns    merge               computeIfAbsent        ComputeIfPresent
         * null in map      null                        remove key          no change in map        no change
         *
         * null in map      not null                    set key to          add key with with       no change
         *                                              mapping function    mapping function
         *                                              result              result
         *
         * non null         null                        remove key          no change               remove key
         *
         * non null         non null                    set key to          no change               set key to mapping
         *                                              mapping function                            function result
         *                                              result
         *
         * no key           null                        add key             no change               no change
         *
         * no key           non null                    add key             add key with value      no change
         *                                                                  = result of func
         */
    }
}
