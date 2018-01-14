package co.za.damien.chapter4.streams;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by damien.mahadew on 2018-01-14.
 */
public class AdvancedStreamPipelineConcepts {

    private void tricks() {
        List<String> cats = new ArrayList<>();
        cats.add("Annie");
        cats.add("Ripley");
        Stream<String> stream = cats.stream();
        cats.add("KC");
        System.out.println(stream.count()); // prints 3 because streams are lazy loaded
    }

    private void chainingOptionalsExamples() {
        //eg print an integer only if it is a 3 digit number
        Optional<Integer> integer = Optional.of(1234);

        integer.map(s -> "" + s)
                .filter(s -> s.length() ==3)
                .ifPresent(System.out::println);
        /**
         * EXAM ; fond of carving a single statement and identifying the pieces with a comment
         */

        //supposed you want to get an Optional<Integer> representing the length of the String contained in another optional
        Optional<String> stringStream = Optional.of("damien");
        Optional<Integer> result = stringStream.map(String::length);
    }

    private void checkedExceptions() throws IOException {
        /**
         * It is a problem when working with methods that throw a checked exception
         */
        //nothing wrong with the below - must have throws IOException at the top
        AdvancedStreamPipelineConcepts.throwCheckedException().stream().count();
        //but the next one has a compile issue
        testException();

        /**
         * Supplier interface does not allow checked exceptions, so one way would be to catch the exception
         * and throw a runtime exception, or you can catch the exception and throw a runtime exception in the method
         * that creates the list
         */
    }

    private void collectingResults() {
        /**
         * Many predefined collectors
         * Collector                                    Description                     Return value when passed to collect
         * averagingDouble(ToDoubleFunction f)          calculates the average for      Double
         * averagingInt(ToIntFunction f)                our three core primitive types
         * averagingLong(ToLongFunction f)
         *
         * counting()                                   counts the number of elements   Long
         *
         * groupingBy(Function f)                       Creates a map grouping by the   Map<K, List<T>>
         * groupingBy(Function f, Collector dc)         specified function with the
         * groupingBy(Function f, Supplier s,           optional type and optional
         * Collector dc)                                downstream collector
         *
         * joining()                                    Creates a single String using   String
         * joining(CharSequence cs)                     cs as a delimiter between
         *                                              elements if one is specified
         *
         * maxBy(Comparator c)                          Finds the largest/smallest      Optional<T>
         * minBy(comparator c)                          elements
         *
         * mapping(Function f, Collector dc)            Adds another level of           Collector
         *                                              collectors
         *
         * partitioningBy(Predicate p)                  Creates a map grouping by       Map<Boolean, List<T>>
         * partitioningBy(Predicate p, Collector dc)    the specified predicate
         *                                              with the optional further
         *                                              downstream collector
         *
         * summarizingDouble(ToDoubleFunction f)        Calculates average, min, max    DoubleSummaryStatistics
         * summarizingInt(ToIntFunction f)              and so on                       IntSummaryStatistics
         * summarizingLong( ToLongFunction f)                                           LongSummaryStatistics
         *
         * summingDouble(ToDoubleFunction f)            Calculates the sum for our      Double
         * summingInteger(ToIntFunction f)              three core primitive types      Integer
         * summingLong(ToLongFunction f)                                                Long
         *
         * toList()                                     creates an arbitrary type       List
         * toSet()                                      of list or set                  Set
         *
         * toCollection(Supplier s)                     Creates a collection of the     Collection
         *                                              specified type
         *
         * toMap(Function k, Function v)                Creates a map using functions   Map
         * toMap(Function k, Function v,                to map the keys, values, an
         * BinaryOperator m)                            optional merge function and
         * toMap(Function k, Function v,                an optional type
         * BinaryOperator m, Supplier s)
         */
    }

    private void collectingUsingBasicCollectors() {
        /**
         * Luckily many of these collectors work in the same way
         */
        Stream<String> stringStream = Stream.of("Lions", "Tigers", "Bears");
        //predefined collectors are in the Collectors class, important to pass the collector to the collect
        String result = stringStream.collect(Collectors.joining(", "));
        System.out.println(result); //lions, tigers, bears

        //another example - average length
        Double average = stringStream.collect(Collectors.averagingInt(String::length));
        System.out.println(average); //5.33333

        //converting to a set
        TreeSet<String> tree = stringStream.filter(s-> s.startsWith("t")).collect(Collectors.toCollection(TreeSet::new));
    }

    private void collectingIntoMaps() {
        /**
         * Collector code involving maps can get long
         * When creating a map, you need to specify to functions, 1 - tells the collector how to create the key,
         * 2 - tells the collector how to create the value
         */
        Stream<String> stringStream = Stream.of("Lions", "Tigers", "Bears");
        //first example - create a map from a stream
        Map<String, Integer> map = stringStream.collect(Collectors.toMap(s -> s, String::length));
        // s-> s can be written as Function.identity()
        map = stringStream.collect(Collectors.toMap(Function.identity(), String::length));

        /**
         * Problem - reversing the map, putting the length as the key and the name as the value
         * if there is a duplicate key, java throws an exception - IllegalStateException
         */
        // keeping the key as is, we join the values of the common keys with a comma
        Map<Integer, String> anotherMap = stringStream
                .collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2)); //{5=lions,bears, 6=tigers}
        //generally creates a hashmap, if you want to generate a treemap
        TreeMap<Integer, String> treeMap = stringStream
                .collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
        //{5=lions,bears, 6=tigers}
    }

    private void collectingUsingGroupingParitioningMapping() {
        /**
         * Get groups of names by their length - group by length
         */
        Stream<String> stringStream = Stream.of("Lions", "Tigers", "Bears");
        Map<Integer, List<String>> map = stringStream.collect(Collectors.groupingBy(String::length));

        //Can change the collection to set
        Map<Integer, Set<String>> setMap = stringStream.collect(Collectors.groupingBy(String::length, Collectors.toSet()));

        //Can change the Map Implementation as well
        Map<Integer, Set<String>> treeMap =
                stringStream.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));

        /**
         * Partitioning - special case of grouping
         * two possible groups - tru and false, like splitting a list into two parts
         */
        //e.g. two signs, one that is for an animal with 5 or fewer characters in their name, and the other for animals
        //with more than 5
        Map<Boolean, List<String>> partionMap = stringStream.collect(
                Collectors.partitioningBy(s->s.length() <= 5));  //{false=[tigers], true=[lions, bears]}
        //as with the grouping by we can change the type of list something else, but not map
        Map<Boolean, Set<String>> partionSetMap = stringStream.collect(
                Collectors.partitioningBy(s->s.length() <= 5, Collectors.toSet()));  //{false=[tigers], true=[lions, bears]}

        //does not compile
//        TreeMap<Boolean, Set<String>> partionTreeSetMap = stringStream.collect(
//                Collectors.partitioningBy(s->s.length() <= 5, TreeSet::new, Collectors.toSet()));  //{false=[tigers], true=[lions, bears]}

        //using an existing collector
        Map<Integer, Long> countingMap = stringStream
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(map); // {5=2, 6=1}

        //mapping collector that lets us go down another level and add another collector e.g. we wanted to
        //get the first letter of the first animal alphabetically of each length.
//        Map<Integer, Optional<Character>> hecticMap = stringStream.collect(
//                Collectors.groupingBy(String::length, Collectors.mapping(s-> s.charAt(0),
//                        Collectors.minBy(Comparator.naturalOrder())))
//        );

    }

    private void testException() {
//        Supplier<List<String>> s = AdvancedStreamPipelineConcepts::throwCheckedException;
        //proper way
//        Supplier<List<String>> s = () -> {
//            try {
//                return AdvancedStreamPipelineConcepts::throwCheckedException;
//            } catch (IOException e) {
//                throw new RuntimeException();
//            }
//        };

    }

    private static List<String> throwCheckedException() throws IOException {
        throw new IOException();
    }
}
