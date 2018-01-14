package co.za.damien.chapter4.streams;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream in java is a sequence of data - stream pipeline is the operations
 * that run on a stream to produce a result
 * <p>
 * With streams data is created when needed
 * Stream operations occur in a pipeline.
 * Three parts to a stream pipeline:
 * 1. Source - where the data comes from
 * 2. Intermediate operations - transforms the stream into another one. Streams use lazy evaluation, the intermediate
 * operations do not run until the terminal operation runs
 * 3. Terminal operations - actually produces a result. Since streams can only be used once, the
 * stream is no longer valid after the terminal operation completes
 * <p>
 * EXAM - need to know the difference between intermediate operations and terminal
 * Scenario                             For Intermediate Operations?            For Terminal operations?
 * Required part of a useful            No                                      Yes
 * pipeline?
 * Can exist multiple times in a        Yes                                     No
 * pipeline?
 * Return type is a stream type?        Yes                                     No
 * Executed upon method call?           No                                      Yes
 * Stream valid after call?             Yes                                     No
 */
public class StreamsExamples {

    private void createStreamsExamples() {
        //few ways to create finite streams
        Stream<String> empty = Stream.empty(); //count = 0
        Stream<Integer> singleElement = Stream.of(1); //count = 1
        Stream<Integer> fromArray = Stream.of(1, 2, 3);

        //converting list to streams
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> fromList = list.stream();
        Stream<String> fromListParallel = list.parallelStream();

        //problem with lists and existing collections - you cant create infinite lists
        //the line below generates as many random numbers as you want
        Stream<Double> randoms = Stream.generate(Math::random);
        //iterate takes a seed or starting value as the first parameter, the second parameter
        //takes in a lambda expression
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
    }

    private void commonTerminalOperations() {
        /**
         * You can perform terminal operations without any intermediate operations, but
         * not the other way around
         * Reductions are a special type of terminal operation where all of the contents of the
         * stream are combined into a single primitive or Object e.g. an int or a collection
         *
         * Method       What happens for infinite streams           Return Value        Reduction
         * allMatch()   Sometimes terminates                        boolean             No
         * anyMatch()
         * noneMatch()
         *
         * collect()    Does not terminate                          varies              Yes
         * count()      Does not terminate                          long                yes
         *
         * findAny()    Terminates                                  Optional<T>         No
         * findFirst()
         *
         * forEach()    Does not terminate                          void                No
         *
         * min()        Does not terminate                          Optional<T>         Yes
         * max()
         *
         * reduce()     Does not terminate                          Varies              Yes
         */

        Stream<String> s = Stream.of("monkey", "gorilla", "baboon");

        countOperation(s);

        minMaxOperation(s);

        findAnyFirstOperation(s);

        anyAllNoneMatchOperation();

        forEachOperation(s);

        reduceOperation();

        collectOperation();
    }

    private void commonIntermediateOperations() {
        /**
         * Deals with infinite stream simply by returning an infinite stream. Since elements are produced when only needed
         * this works fine.
         *
         * All intermediate operations return streams
         */

        Stream<String> s = Stream.of("monkey", "gorilla", "baboon");

        filterOperation(s);

        distinctOperation(s);

        skipLimitOperation();

        mapOperation(s);

        flatMapOperation();

        sortedOperation(s);

        /**
         * peek() - allows us to peform stream oepration without actually changing the stream
         * Stream<T> peek(Consumer<? super T> action)
         * Most common use is to output the contents of the stream as it goes by
         */
        s.filter(st-> st.startsWith("b")).peek(System.out::println).count();
        /**
         * Note peek is intended to perform an operation without changing the result
         * Bad practice to modify state
         */


    }

    private void sortedOperation(Stream<String> s) {
        /**
         *sorted() - returns a stream with the elements sorted, java uses natural ordering unless you specify
         * a comparator
         * Stream<T> sorted()
         * Stream<T> sorted(Comparator<? super T> comparator)
         */
        s.sorted().forEach(System.out::println);// uses default sort order - baboon, gorilla, monkey
        //using comparator and lambda expression
        s.sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //note the following does not compile - reverse order takes zero parameters and returns a comparator
//        s.sorted(Comparator::reverseOrder);
    }

    private void flatMapOperation() {
        /**
         * flatMap - takes each element in the stream and makes any elements it contains a top level elements
         * in a single stream. Helpful when you want to remove empty elements in a stream or want to combine a
         * stream of lists
         * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
         * -basically says that ir returns a Stream of the type that the function contains at a lower level
         */
        //example - gets allof the animals into the same level along with getting rid of the empty list

        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);
        animals.flatMap(l -> l.stream()).forEach(System.out::println);
        /**
         * Output:
         * Bonobo
         * Mama Gorilla
         * Baby Gorilla
         */}

    private void mapOperation(Stream<String> s) {
        /**
         * creates a one-to-one mapping from elements in the stream to elements of the next step in the stream
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper)
         * It uses lambda expression to figure out the type passed to that function and the one returned
         * The returned type is the stream that gets returned
         * Transforms data
         */
        //Example - converts a list of String objects to a list of Integers representing their lengths
        s.map(String::length).forEach(System.out::println);
        s.map(x -> x.length()).forEach(System.out::println);
    }

    private void skipLimitOperation() {
        /**
         * limit() and skip()
         * makes the stream smaller - make a finite stream smaller or make an infinite stream finite
         * Stream<T> limit(int maxSize)
         * Stream<T> skip(int n)
         */
        Stream<Integer> integerStream = Stream.iterate(1, n -> n + 1);
        Stream<Integer> anotherStream = integerStream.skip(5).limit(2);
        System.out.println(anotherStream); //prints 67
    }

    private void distinctOperation(Stream<String> s) {
        /**
         * distinct() returns a stream with duplicate values removed, they do not need to be adjacent to be removed
         * Java calls equals
         */
        Stream<String> distinctStream = s.distinct(); //removes duplicates
    }

    private void filterOperation(Stream<String> s) {
        /**
         * filter(Predicate<? super T> predicate
         */
        s.filter(x -> x.startsWith("m")).forEach(System.out::println);
    }

    private void collectOperation() {
        /**
         * collect() - special type of reduction - mutable reduction
         * more efficient than regular reduction because we use the same mutable object while accumulating
         * common mutable objects are StringBuilder, ArrayList
         * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
         * <R, A> R collect(Collector<? super T, A, R> collector)
         */
        Stream<String> stringStream = Stream.of("w", "o", "l", "f");
        StringBuilder word = stringStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        //First argument - supplier - creates the object
        //Second argument - BiConsumer - takes two parameters and does not return anything. Responsible for
        //adding one or more elements to the data collection - appends the next string to the StringBuilder
        //Final parameter is another BiConsumer, responsible for taking the two data collections and merging them
        //useful in parallel streams
        //order of the letters may not be correct

        //another example
        TreeSet<String> set = stringStream.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        //the last parameter is mainly for things done in parallel

        //since these are common collectors, java provides an interface with common collectors
        TreeSet<String> anotherTreeSet = stringStream.collect(Collectors.toCollection(TreeSet::new));
        Set<String> anotherSet = stringStream.collect(Collectors.toSet()); //no guarantee of which implementation of set
    }

    private void reduceOperation() {
        /**
         * reduce combines a stream into a single object
         * T reduce(T identity, BinaryOperator<T> accumulator)
         * Optional<T> reduce(BinaryOperator<T> accumulator)
         * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) -- used when
         * processing collections in parallel
         */
        //basic reduction - start with an initial value and keep merging it with the next value
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        //initial value of empty string is the identity
        String word = stream.reduce("", (s1, c) -> s1 + c);
        //or
        String anotherWord = stream.reduce("", String::concat);
        System.out.println(word); //wolf

        //if you dont specify an identity, an optional is returned

        //third option - allows java to create intermediate reductions and then combine then at the end
        //parallel safe
        BinaryOperator<Integer> op = (a, b) -> a * b;
//        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a *b;
        Stream<Integer> intStream = Stream.of(3, 5, 6);
        System.out.println(intStream.reduce(1, op, op));
    }

    private void forEachOperation(Stream<String> s) {
        /**
         * forEach()
         * does not terminate on an infinite loop
         * not a reduction
         * think about the problem before using loops, e.g. a loop with an if statement should be a filter
         * void forEach(Consumer<? super T> action)
         * only terminal operation with a return type of void
         * EXAM: remember you can call forEach on a collection and on a stream- dont get confused
         */
        s.forEach(System.out::println);
        //the below does not compile
//        for (String someString : s) {
//        }
    }

    private void anyAllNoneMatchOperation() {
        /**
         * allMatch(), anyMatch(), noneMatch() - returns boolean
         * May or may not terminate
         * Not reductions
         * boolean any/all/noneMatch(Predicate<? super T> predicate)
         */
        List<String> list = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> predicate = x -> Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(predicate)); //true
        System.out.println(list.stream().allMatch(predicate)); //false
        System.out.println(list.stream().noneMatch(predicate)); //false
        System.out.println(infinite.anyMatch(predicate)); //true
    }

    private void findAnyFirstOperation(Stream<String> s) {
        /**
         * findAny() and findFirst()
         * Returns an element of the stream unless the stream is empty, if empty the Optional is empty
         * Works with infinite streams
         * findAny() is useful for parallel streams
         * Not reductions as they sometimes terminate without processing all elements i.e. return value
         * based on a stream but not reduce the entire stream
         */
        Stream<String> infinite = Stream.generate(() -> "chimp");
        s.findAny().ifPresent(System.out::println);
        infinite.findAny().ifPresent(System.out::println);
    }

    private void minMaxOperation(Stream<String> s) {
        /**
         * min() and max() - finite stream, hangs on infinite streams
         * returns -> Optional<T> min/max(<? super T> comparator)
         * isPresent on the optional is false if it cant find a min/max
         * If the stream is empty, the comparator is never called
         */
        Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println);
    }

    private void countOperation(Stream<String> s) {
        /**
         * count() determines the number of elements in a finite stream, hangs for an infinite stream
         */
        System.out.println(s.count());
    }
}
