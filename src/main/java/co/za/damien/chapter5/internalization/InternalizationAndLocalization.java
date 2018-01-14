package co.za.damien.chapter5.internalization;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Internalization is the process of designing your pogram so it can be adapted
 * This involves placing strings in a property file and using classes like DateFormat so
 * the right formats are used. You do not need to support more than one language or country, it just means that you can
 *
 * Localization means actually supporting multiple locales
 * Locale - a specific geographical, political, or cultural region e.g. a language and country pair
 * Includes outputting dates and numbers in the correct format
 *
 * AKA - i18n, and l10n
 */
public class InternalizationAndLocalization {

    private void pickingALocale() {
        /**
         * Locales - you will only see languages and countries in the exam
         * !! en_ZA - lower case language code, then underscore, following by an uppercase country code,
         *
         * underscore and country code are optional and it is valid for a locale to only be a language
         *
         * Java will let you create a locale with an invalid language or country code but your program will
         * not behave as expected
         */
        //get current locale
        java.util.Locale locale = Locale.getDefault();
        System.out.println(locale); //en_ZA -- english and South Africa

        //3 ways of creating Locales
        //using constants from Locale
        System.out.println(Locale.GERMAN); //de
        System.out.println(Locale.GERMANY); //de_DE

        //using constructors
        System.out.println(new Locale("fr"));
        System.out.println(new Locale("hi", "IN"));

        //using the builder pattern, does the string formatting for you, builder also lets you create blank locales- dont!
        Locale builderLocale = new Locale.Builder()
                .setLanguage("en")
                .setRegion("ZA")
                .build();
    }

    private void setDefautLocale() {
        System.out.println(Locale.getDefault());
        Locale locale = Locale.US;
        Locale.setDefault(locale);
    }

    private void resourceBundles() {
        /**
         * Contains local specific objects to be used by a program - map with keys and values
         * Can be in a property file or a java class
         * Localization requires externalizing your property files
         * Resource bundles help in supporting multiple locales,
         *
         * If you dont have a country specific resource bundle, java will use a language specific one
         *
         * examples of properties
         * hello=Hello
         * animal:dolphin
         * animal dolphin
         *
         * if the property  starts with # or ! it is a comment
         * spaces before or after the separator character are ignored
         * spaces at the beginning of a line are ignored
         * spaces at the end of a line are ignored
         * end the line with a backslash if you want to break the line for readability
         * you can use normal java escape characters like \t and \n
         */
        Locale americanLocale = new Locale("en", "US");
        Locale franceLocale = new Locale("fr", "FR");
        Locale englishCanadalocale = new Locale("en", "CA");
        Locale frenchCanadalocale = new Locale("fr", "CA");

        printProperties(americanLocale);
        System.out.println();
        printProperties(franceLocale);

        //looping through all properties
        ResourceBundle resourceBundle = ResourceBundle.getBundle("zoo", americanLocale);
        Set<String> keyset = resourceBundle.keySet();
        keyset.stream().map(k -> k + " " + resourceBundle.getString(k)).forEach(System.out::println);
    }

    private void propertyFiles() {
        Properties properties = new Properties();
        //looping through all properties
        ResourceBundle resourceBundle = ResourceBundle.getBundle("zoo", new Locale("en", "US"));
        Set<String> keyset = resourceBundle.keySet();
        keyset.stream().forEach(k -> properties.put(k, resourceBundle.getString(k)));

        System.out.println(properties.getProperty("notARealProperty")); //null
        //using default values
        System.out.println(properties.getProperty("notARealProperty", "123"));//uses default value 123
    }

    private void printProperties(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("zoo", locale);
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("open"));
    }

    public static void main(String[] args) {
        InternalizationAndLocalization localization = new InternalizationAndLocalization();
        localization.pickingALocale();
        localization.resourceBundles();
    }
}
