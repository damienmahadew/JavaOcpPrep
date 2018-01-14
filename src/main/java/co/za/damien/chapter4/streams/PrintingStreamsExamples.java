package co.za.damien.chapter4.streams;

/**
 * Since intermediate operations dont run until needed, new techniques are needed to print streams
 *
 * Option                                               Works For Infinite Streams?         Destructive to Stream?
 * s.forEach(System.out::println)                       No                                  Yes
 * System.out.println(s.collect(Collectors.toList())    No                                  Yes
 * s.peek(System.out::println).count()                  No                                  No
 * s.limit(5).forEach(System.out::println)              Yes                                 Yes
 */
public class PrintingStreamsExamples {
}
