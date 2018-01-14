package co.za.damien.chapter4.streams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * Summary Statistics used when you need to do complex operations on primitive streams
 * e.g. get the difference between the min and max of a list of numbers
 */
public class StatisticsExamples {

    private int statisticExamples() {
        IntStream intStream = IntStream.rangeClosed(1,10);
        IntSummaryStatistics statistics = intStream.summaryStatistics();
        if (statistics.getCount() == 0) throw new RuntimeException();
        return statistics.getMax() - statistics.getMin();
    }
}
