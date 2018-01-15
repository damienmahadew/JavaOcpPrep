package co.za.damien.chapter5.internalization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Format numbers, currency and dates
 * <p>
 * Regardless of whether you want to format or parse, the first step is the same, you need to
 * create a NumberFormat
 * <p>
 * Description                              Using Default Locale, and a Specified Locale
 * A general purpose formatter              NumberFormat.getInstance()
 * NumberFormat.getInstance(Locale locale)
 * <p>
 * Same as getInstance                      NumberFormat.getNumberInstance()
 * NumberFormat.getNumberInstance(locale)
 * <p>
 * For formatting monetary amounts          NumberFormat.getCurrencyInstance()
 * NumberFormat.getCurrencyInstance(locale)
 * <p>
 * Percentages                              NumberFormat.getPercentageInstance()
 * NumberFormat.getPercentageInstance(locale)
 * <p>
 * NOT IN EXAM:
 * rounds decimal values before             NumberFormat.getIntegerInstance()
 * displaying them                          NumberFormat.getIntegerInstance(locale)
 * <p>
 * format classes are not thread safe! do not store them in instance variables or static variables
 */
public class FormattingNumbers {

    private void formattingNumbersExamples() {
        int integer = 3_200_000;
        int anotherInt = integer / 12;

        NumberFormat us = NumberFormat.getInstance(Locale.US);
        System.out.println("us format = " + us.format(anotherInt)); //266,666

        NumberFormat germany = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("germany format = " + germany.format(anotherInt)); //266.666

        NumberFormat canada = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println("canada format = " + canada.format(anotherInt)); //266 666

        //Real world use int or BigDecimal for money
        NumberFormat usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("us currency format = " + usCurrency.format(integer)); //266 666
    }

    private void parsingNumbersExamples() throws ParseException {
        /**
         * Throws ParseException if fail to parse
         * If you see parsing logic inside a method, make sure that ParseException or Exception is handled or declared
         *
         * NOTE : java parses from the beginning of a string, after it reaches a character that
         * cannot be parsed, the parsing stops and the value is returned
         */
        NumberFormat en = NumberFormat.getInstance(Locale.US);
        NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
        String s = "40.45";

        Number usParse = en.parse(s);
        System.out.println("US parse 40.45 = " + usParse); //40.45
        Number frParse = fr.parse(s);
        System.out.println("France parse 40.45 = " + frParse); //40

        NumberFormat nf = NumberFormat.getInstance();
        String three = "x85.3";
//        System.out.println(nf.parse(three));// throws ParseException

        //Parsing currency
        String amt = "$92,807.99";
        NumberFormat cf = NumberFormat.getCurrencyInstance();
        double value = (Double) cf.parse(amt);
        System.out.println(value); // 92807.99
    }

    public static void main(String[] args) throws ParseException {
        FormattingNumbers formattingNumbers = new FormattingNumbers();
        formattingNumbers.formattingNumbersExamples();
        formattingNumbers.parsingNumbersExamples();
    }
}
