package co.za.damien.chapter7.parallel;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Streams API has built in concurrency support
 * All streams worked with thus far are serial streams
 *  - results are ordered with only one entry being processed at a time
 *
 * Parallel stream - capable of processing results concurrently using multiple threads.
 * e.g. use parallel stream and the stream map() method to operate concurrently on the elements in the
 * stream improving performance over processing a single element at a time
 *
 * By default - no of threads in parallel stream = no of available CPU's in environment, to increase
 * you will have to create a custom class
 *
 * two ways of creating parallel streams - see below
 *
 * NOTE : anytime you use a parallelStream use concurrent collections
 *
 * parallel streams can impact how you write your application.
 * Parallel reductions - reducing operations
 *
 * if you use findAny(), findFirst() or limit() it can reduce the performance on parallel streams,
 * however applying these operations yield the same results on serial streams
 */
public class ParallelStreamsExample {

    private void waysOfCreatingParallelStreams() {
        /**
         * First way - from an existing stream, just call parallel()
         */
        Stream<Integer> stream1 = Arrays.asList(1,2,3,4,5,6).stream();
        Stream<Integer> parallelStream1 = stream1.parallel();
        // parallel() is an intermediate operation that works on the original stream

        /**
         * Second way - from Collections class
         */
        Stream<Integer> stream2 = Arrays.asList(1,2,3,4,5,6).parallelStream();

        /**
         * You can use isParallel() to check if a stream supports parallel processing.
         * Some operations on streams preserve parallel attribute e.g. Stream.concat(Stream s1, Stream s2)
         * but others like flatMap() creates a new stream that is not parallel
         */
    }

    private void parallelStreamsExamples() {
        //normal stream - output should be 1 2 3 4 5 6
        Arrays.asList(1,2,3,4,5,6)
                .stream()
                .forEach(s-> System.out.println(s + " "));

        //parallel stream - output is not like above , can be 5 4 1 2 6 3
        Arrays.asList(1,2,3,4,5,6)
                .parallelStream()
                .forEach(s-> System.out.println(s + " "));

        /**
         * you can use forEachOrdered() - but this comes at a performance cost
         * avoid stateful expressions
         */
    }

    private void parallelReductionExamples() {
        /**
         * stream object reduce() combines a stream into a single object, first object = identity,
         * second = accumulator, and third is combiner
         */

        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
                .stream()
                .reduce("",
                        (c, s1)-> c + s1,
                        (s2, s3) -> s2 + s3));
        //output = wolf

        /**
         * in parallel streams the reduce method works by applying the reduction to paris of elements
         * within the stream to create intermediate values, then combining those values to produce the final
         * result
         *
         * A serial stream , wolf was build one character at a time, in a parallel stream the intermediate strings
         * wo and lf could have been created then combined.
         *
         * With parallel streams we have to be concerned about the order. The Streams API
         * prevents this problem while still allowing streams to be processed in parallel, as along
         * as the arguments to reduce() adhere to certain principles
         *
         * Requirements for reduce()
         */
    }

    public static void main(String[] args) {
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        parallelStreamsExample.parallelReductionExamples();
    }
}
