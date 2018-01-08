package co.za.damien.chapter5.datesandtimes;

import java.time.*;

public class DatesAndTimesExample {

    //GMT - greenwich mean time
    //UCT - coordinated universal time - can be expressed in different ways - +02:00, GMT+2, and UTC+2
    //NOTE:- java exam uses MM/dd/YYYY
    //NOTE: there are no constructors!
    //NOTE : these classes are immutable
    //epoch - 1 Jan 1970 (GMT)- Java used it because Unix used it
    //If you pass invalid parameters to .of(), you get a DateTimeException

    //Four type of dates and times
    private LocalDate localDate; //Just a date, no time and no time zone e.g. your birthday

    private LocalTime localTime; //Contains just a time, no date and no timezone

    private LocalDateTime localDateTime; //contains a date and time, no timezone

    private ZonedDateTime zonedDateTime; //contains date, time and timezone


    public static void main(String[] args) {
        printAllDatesAndTimes();
        createDates();
        createTimes();
        createDateAndTimes();
        createZonedDateTimes();
        printZones();
        manipulatingDates();
        epochExamples();
    }



    private static void epochExamples() {
        LocalDate localDate = LocalDate.now();
        Long epochLocalDate = localDate.toEpochDay();
        System.out.println("Days since 1 Jan 1970 = " + epochLocalDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        Long epochLocalDateTime = localDateTime.toEpochSecond(ZoneOffset.UTC);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Long epochZonedDateTime = zonedDateTime.toEpochSecond();
    }

    private static void manipulatingDates() {
        //Note : date and time classes are immutable - so you will need to assign a new object if you are manipulating
        LocalDate localDate = LocalDate.now();
        System.out.println("localdate = " + localDate);
        LocalDate days = localDate.plusDays(2);
        System.out.println("localdate + 2 days =" + days);
        LocalDate weeks = localDate.plusWeeks(2);
        System.out.println("localdate + 2 weeks =" + weeks);
        LocalDate months = localDate.plusMonths(2);
        System.out.println("localdate + 2 months =" + months);
        LocalDate years = localDate.plusYears(2);
        System.out.println("localdate + 2 years =" + years);

        //the below is common
        LocalDate localDate1 = LocalDate.of(2017, Month.APRIL, 5);
        LocalTime localTime = LocalTime.of(12,12);
        LocalDateTime localDateTime = LocalDateTime.of(localDate1, localTime).minusDays(3).minusMinutes(5);

    }

    private static void printZones() {
        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(z-> z.contains("Africa") && z.contains("Johannesburg"))
                .sorted()
                .forEach(System.out::println);

    }

    private static void createZonedDateTimes() {
        System.out.println("::createZonedDateTimes()");
        ZoneId zoneId = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2015, 1, 20, 12, 12, 12, 12, zoneId);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), zoneId);
        ZonedDateTime zonedDateTime3 = ZonedDateTime.of(LocalDateTime.now(), zoneId);
        System.out.println("zonedDateTime1 = " + zonedDateTime1);
        System.out.println("zonedDateTime2 = " + zonedDateTime2);
        System.out.println("zonedDateTime3 = " + zonedDateTime3);
        //Note there is no option to pass through the Month Enum
    }

    private static void createDateAndTimes() {
        System.out.println("::createDateAndTimes()");
        LocalDateTime localDateTime1 = LocalDateTime.of(2015, Month.APRIL, 20, 12, 12, 12);
        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("localDateTime1 = " + localDateTime1);
        System.out.println("localDateTime2 = " + localDateTime2);
    }

    private static void printAllDatesAndTimes() {
        System.out.println("Localdate = " + LocalDate.now());
        System.out.println("LocalTime = " + LocalTime.now());
        System.out.println("LocaldateTime = " + LocalDateTime.now());
        System.out.println("ZonedDateTime = " + ZonedDateTime.now());
    }

    private static void createDates() {
        System.out.println("::CreateDates()");
        LocalDate date1 = LocalDate.of(2017, Month.JANUARY, 20);
        LocalDate date2 = LocalDate.of(2017, 1, 20); //both are the same
        System.out.println("Date1 :" + date1);
        System.out.println("Date2 :" + date2);
        //Note : Month is an enum!
    }

    private static void createTimes() {
        System.out.println("::CreateTimes()");
        LocalTime time1 = LocalTime.of(6, 15); // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
        System.out.println("time1 = " + time1);
        System.out.println("time2 = " + time2);
        System.out.println("time3 = " + time3);
    }


}
