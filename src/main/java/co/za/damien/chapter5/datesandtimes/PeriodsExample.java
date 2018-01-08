package co.za.damien.chapter5.datesandtimes;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class PeriodsExample {

    public static void main(String[] args) {
        periodExample();
    }

    private static void periodExample() {
        System.out.println("Period example - 1 month");
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        Period period = Period.ofMonths(1);

        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            System.out.println("time before adding period " + upTo);
            upTo = upTo.plus(period); // adds the period
        }

        Period annually = Period.ofYears(1); // every 1 year
        Period quarterly = Period.ofMonths(3); // every 3 months
        Period everyThreeWeeks = Period.ofWeeks(3); // every 3 weeks
        Period everyOtherDay = Period.ofDays(2); // every 2 days
        Period everyYearAndAWeek = Period.of(1, 0, 7); // every year and 7 days

        //period methods are static therefore
        Period p = Period.ofYears(1).ofWeeks(1); // = Period.ofWeeks(1);

        System.out.println("Printing periodOf" + Period.of(1,2,3)); //only prints non zero values

        System.out.println("period of 3 weeks prints = " + Period.ofWeeks(3));

    }
}
