package co.za.damien.chapter5.internalization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Exam: Two ways for getting a resource bundle
 * ResourceBundle.getBundle("name") --uses default locale
 * ResourceBundle.getBundle("name", locale)
 *
 * Java handles the logic for picking up the best resource bundle for a given key
 * tries to find the most specific value, when there is a tie, bundles are given preference
 * 1. Always lok for the property file after the matching java class
 * 2. Drop one thing at a time if there are no matches, first drop the country
 * 3. Look at the default locale and the default resource bundle last
 *
 * Java isnt required to get all of the keys from the same resource bundle. It can get them from any parent
 * of the matching resource bundle. A parent resource bundle in the hierarchy just removes components
 * of the name until it gets to the top
 * e.g.
 * Zoo_fr_FR.java will first go to Zoo_fr_FR.java, then Zoo_fr.java, then Zoo.java
 *
 * In real world applications, it is common to substitute variables in the middle of a
 * resource bundle with a string e.g. helloByName=Hello, {0}
 *
 * String format = rb.getString("helloByName"
 * String formatted = MessageFormat.format(format, "Tommy")
 *
 * if property is missing - MissingResourceException
 *
 */
public class ResourceBundles {

    private void resourceBundleExample() {
        Locale locale = new Locale("en", "CA");
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", locale);
        System.out.print(rb.getString("hello"));
        System.out.print(". ");
        System.out.print(rb.getString("name"));
        System.out.print(" ");
        System.out.print(rb.getString("open"));
        System.out.print(" ");
        System.out.print(rb.getString("visitor"));
        System.out.print(" ");
//        System.out.print(rb.getString("damien"));
    }

    public static void main(String[] args) {
        ResourceBundles resourceBundle = new ResourceBundles();
        resourceBundle.resourceBundleExample();;
    }
}
