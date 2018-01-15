package co.za.damien.chapter5.internalization;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Unlike the LocalDateTime class, DateTimeFormatter can be used to format any type of date or time
 * java.time.format
 */
public class FormattingDates {

    private void dateTimeFormattingExamples() {
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); //2020-01-20
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME)); //11:12:34
        System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //2020-01-20T11:12:34

        /**
         * Reasonable way for computers to communicate , but probably not the way you want to output the
         * date and time
         * See below for predefined formats
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println("date time formatted =" + formatter.format(dateTime));
        System.out.println("date formatted =" + formatter.format(date));
//        System.out.println("time formatted =" + formatter.format(time));//throws UnsupportedTemporalTypeException

        //exact same thing as above
        System.out.println("date time formatted =" + dateTime.format(formatter));
        System.out.println("date formatted =" + date.format(formatter));
//        System.out.println("time formatted =" + time.format(formatter));//throws UnsupportedTemporalTypeException

        /**
         * 2 predefined formats that can show up in the exam SHORT and MEDIUM - the others have time zones
         */
        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time1 = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        DateTimeFormatter shortF = DateTimeFormatter   .ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter  .ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(shortF.format(dateTime));      // 1/20/20 11:12 AM
        System.out.println(mediumF.format(dateTime));     // Jan 20, 2020 11:12:34 AM

        //custom
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMMM yy dd HH:mm:ss");
        /**
         * M = 1, MM = 01, MMM = JAN, MMMM = JANUARY
         * use , if you want  to output a comma
         * dd = day
         * y = year, yy = 2 digit year, yyyy 4 digit year
         * hh = 12 hour clock, HH = 24 hour clock
         * mm = 2 digits, m = 1
         */
    }

    private void parsingDatesExamples() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date = LocalDate.parse("01 02 2015", f);
        LocalTime time = LocalTime.parse("11:22");
        System.out.println(date);          // 2015–01–02
        System.out.println(time);          // 11:22
    }

    public static void main(String[] args) {
        FormattingDates formattingDates = new FormattingDates();
        formattingDates.dateTimeFormattingExamples();
    }
}
