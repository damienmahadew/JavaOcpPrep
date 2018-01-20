package co.za.damien.chapter4.streams;

import java.util.stream.Stream;

/**
 * Streams allow you to use chaining and express what you want to accomplish rather than how to do so.
 */
public class PipelineExamples {

    private void pipelineExample() {
        //tricky streams

        //the following hangs:
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .sorted() //sorted waits for all the elements to come before sending it to the next operation
                .limit(2)
                .forEach(System.out::println);

        //prints Elsa twice
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);

        //hangs, waits for 2 inputs from an infinite stream but gets no data as the filter removes all the elements
        Stream.generate(() -> "Olaf Lazisson")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);

        //following prints 11233455
        Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
        infinite.limit(5)
                .peek(System.out::print)
                .filter(x -> x % 2 == 1)
                .forEach(System.out::print);

        //prints 13579
        infinite.filter(x -> x % 2 == 1)
                .limit(5)
                .forEach(System.out::print); // 13579

        //prints 1133557799
        infinite.filter(x -> x % 2 == 1)
                .peek(System.out::print)
                .limit(5)
                .forEach(System.out::print);
    }
}
