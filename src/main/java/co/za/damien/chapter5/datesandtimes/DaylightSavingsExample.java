package co.za.damien.chapter5.datesandtimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DaylightSavingsExample {

    /**
     * Clock is moved by 1 hour twice a year to make better use of sunlight.
     * The countries that do participate in this do so on different weekends
     *
     *  e.g. US - moved forward in March and moved back in November
     *
     *  Done at 2am on a Sunday Morning
     *
     *  Spring forward and Fall back - terms used for adding/subtracting the hour in the US
     */

    public static void main(String[] args) {
        springForwardExample();
        fallBackExample();
        createTimeThatDoesNotExist();
    }

    private static void createTimeThatDoesNotExist() {
        LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time = LocalTime.of(2, 30);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zone);

        System.out.println("Time that does not exist - 20160313 02:30 - rolls forward - dateTime =" + dateTime ); // 2016–03–13T03:30–04:00[US/Eastern]
    }

    private static void fallBackExample() {
        LocalDate date = LocalDate.of(2016, Month.NOVEMBER, 6);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zone);

        System.out.println("Fall back - before adding 1 hour - zonedDateTime= " + dateTime); // 2016–11–06T01:30–04:00[US/Eastern]

        dateTime = dateTime.plusHours(1);

        System.out.println("Fall back - after adding 1 hour - zonedDateTime= " + dateTime); // 2016–11–06T01:30–05:00[US/Eastern]

        dateTime = dateTime.plusHours(1);

        System.out.println("Fall back  - after adding another 1 hour - zonedDateTime= " + dateTime); // 2016–11–06T02:30–05:00[US/Eastern]
    }

    private static void springForwardExample() {
        LocalDate localDate = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime localTime = LocalTime.of(1, 30);
        ZoneId zoneId = ZoneId.of("US/Eastern");

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);

        System.out.println("Spring forward - before adding 1 hour - zonedDateTime= " + zonedDateTime);

        zonedDateTime = zonedDateTime.plusHours(1);

        System.out.println("Spring forward - after adding 1 hour - zonedDateTime= " + zonedDateTime);
    }
}
