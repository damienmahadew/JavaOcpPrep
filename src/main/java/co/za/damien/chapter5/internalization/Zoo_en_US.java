package co.za.damien.chapter5.internalization;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * property files only allow string values,
 * java resource bundles allow any types to be  the values , the key is still a string
 */
public class Zoo_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"hello", "Hello"},
                {"open", "The zoo is open"},
                {"someClass", new Object()}
        };
    }

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("co.za.damien.chapter5.internalization.Zoo", Locale.US);
        System.out.println(resourceBundle.getObject("someClass"));
    }
}
