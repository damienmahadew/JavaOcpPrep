package co.za.damien.chapter4.streams;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Streams also support int, double, long primitives
 *
 *  *
 */
public class PrimitivesExamples {

    private void introductionExamples() {
        /**
         * Reason for primitive streams ->
         */
        //adding all numbers
        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.reduce(0, (s, n) -> s + n));

        //another way - convert stream to IntStream
        System.out.println(stream.mapToInt(x-> x).sum());

        //get average
        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble avg = intStream.average();
        System.out.println(avg.getAsDouble());
    }

    private void creatingPrimitiveStreams() {
        /**
         * Three types of primitive streams
         * IntStream - used for primitive types - int, short, byte, char
         * LongStream - long
         * DoubleStream - double and float
         *
         * Pay attention to the word stream, upper case = object, lower case - might be a Stream, DoubleStream, IntStream
         * and LongStream
         */

        //create an empty stream
        DoubleStream doubleStream = DoubleStream.empty();
        //create a normal stream
        DoubleStream anotherDoubleStream = DoubleStream.of(3.14);
        //creating infinite streams
        DoubleStream infiniteDoubleStream = DoubleStream.generate(Math::random);
        DoubleStream anotherInfiniteDoubleStream = DoubleStream.iterate(.5, d -> d/ 2);

        /**
         * Not in exam - Random class provides a method to get primitive streams of random objects directly
         */
//        IntStream someIntStream = IntStream.

        /**
         * Suppose you want a stream from 1-5
         */
        IntStream stream = IntStream.iterate(1, n -> n+1);
        //or
        stream = IntStream.range(1, 6); //inclusive then exclusive
        stream = IntStream.rangeClosed(1, 5); //inclusive then inclusive
    }

    private void mappingStreams() {
        /**
         * Mapping methods between types of streams
         * Source Stream Class      To Create Stream        To create DoubleStream      To Create IntStream     To create LongStream
         * Stream                   map                     mapToDouble                 mapToInt                mapToLong
         * DoubleStream             mapToObj                map                         mapToInt                mapToLong
         * IntStream                mapToObj                mapToDouble                 map                     mapToLong
         * LongStream                mapToObj                mapToDouble                 mapToInt                map
         * java requires you to pass in a mapping function
         */
        Stream<String> objStream = Stream.of("damien", "mahadew");
        IntStream intStream = objStream.mapToInt(s -> s.length());
    }

    private void usingOptionalWithPrimitiveStreams() {
        //example get average
        IntStream stream = IntStream.rangeClosed(1,10);
        OptionalDouble avg = stream.average(); //OptionalDouble is for primitive and Option<Double> is for the wrapper
        avg.ifPresent(System.out::println);
        System.out.println(avg.getAsDouble());
        System.out.println(avg.orElseGet(() -> Double.NaN));// takes a DoubleSupplier instead of a Supplier

        //Primitive Stream methods add two new methods added to min(), max(), findAny() which return Optional values.
        //sum() and avg()
        int intSum = stream.sum();
        DoubleStream d  = stream.mapToDouble(Double::new);
        double sum = d.sum();
        /**
         * Optional types for primitives
         *                              OptionalDouble          OptionalInt             OptionalLong
         * Getting as primitive         getAsDouble()           getAsInt()              getAsLong()
         * orElseGet(parameter type)    DoubleSupplier          IntSupplier             LongSupplier
         * return type of max()         OptionalDouble          OptionalInt             OptionalLong
         * return type of min()         OptionalDouble          OptionalInt             OptionalLong
         * return type of sum           double                  int                     long
         * return type of avg           OptionalDouble          OptionalDouble          OptionalDouble
         */
    }
}
