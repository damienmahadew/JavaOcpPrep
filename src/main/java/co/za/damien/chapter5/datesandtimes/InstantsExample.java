package co.za.damien.chapter5.datesandtimes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantsExample {

    //Instants represent and instant moment in time
    //instant allows you to add any day and smaller unit  - Instant nextDay = instant.plus(1, ChronoUnit.DAYS);

    public static void main(String[] args) throws InterruptedException {
        instantExample();
        instantWithZonedDateTime();
        epochExample();
    }

    private static void epochExample() {
        Instant instant = Instant.ofEpochMilli(1222222);
        System.out.println("instant of epoch = " + instant);
    }

    private static void instantWithZonedDateTime() {
        LocalDate date = LocalDate.of(2015, 5, 25);
        LocalTime time = LocalTime.of(11, 55, 00);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zone);
        Instant instant = zonedDateTime.toInstant(); // 2015–05–25T15:55:00Z
        System.out.println(zonedDateTime); // 2015–05–25T11:55–04:00[US/Eastern]
        System.out.println(instant); // 2015–05–25T15:55:00Z
    }

    private static void instantExample() throws InterruptedException {
        Instant now = Instant.now();
        //do something here
        Thread.sleep(122);
        Instant later = Instant.now();

        Duration durations = Duration.between(now, later);
        System.out.println("Durations between later and now = " + durations);
        System.out.println("Durations between later and now in milliseconds = " + durations.toMillis());
    }
}
