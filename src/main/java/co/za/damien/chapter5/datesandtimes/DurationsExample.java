package co.za.damien.chapter5.datesandtimes;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DurationsExample {

    //duration is intended for smaller durations of time, < day (periods > day)
    //can pass through days, hours minutes, seconds and nanoseconds
    //period output is P1Y... but duration is PT1D...
    //can only work with objects with time

    public static void main(String[] args) {
        durationsExample();
        chronoUnitExample();
    }

    private static void chronoUnitExample() {
        //chrono unit can tell differences e.g.
        LocalTime time1 = LocalTime.of(12, 20);
        LocalTime time2 = LocalTime.of(10, 12);

        System.out.println("ChronoUnit.HOURS.between(time1, time2)" + ChronoUnit.HOURS.between(time1, time2));
        System.out.println("ChronoUnit.MINUTES.between(time1, time2)" + ChronoUnit.MINUTES.between(time1, time2));

        //mixing date and time objects will throw DateTimeExceptions
    }

    private static void durationsExample() {
        Duration durationDays = Duration.ofDays(12);
        Duration durationHours = Duration.ofHours(12);
        Duration durationMinutes = Duration.ofMinutes(12);
        Duration durationSeconds = Duration.ofSeconds(12);
        Duration durationNanoSeconds = Duration.ofNanos(12);

        System.out.println("durationDays 12 = " + durationDays);
        System.out.println("durationHours 12 = " + durationHours);
        System.out.println("durationMinutes 12 = " + durationMinutes);
        System.out.println("durationSeconds 12 = " + durationSeconds);
        System.out.println("durationNanoseconds 12 = " + durationNanoSeconds);

        //or
        Duration daily = Duration.of(1, ChronoUnit.DAYS);
        Duration hourly = Duration.of(1, ChronoUnit.HOURS);
        Duration everyMinute = Duration.of(1, ChronoUnit.MINUTES);
        Duration everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS);
        Duration everyMilli = Duration.of(1, ChronoUnit.MILLIS);
        Duration everyNano = Duration.of(1, ChronoUnit.NANOS);
        //also contains ChronoUnit.HALF_DAYS
    }
}
