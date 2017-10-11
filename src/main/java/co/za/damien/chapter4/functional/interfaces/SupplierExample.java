package co.za.damien.chapter4.functional.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * supplier used when you want to generate or supply values without providing any input
 * <p>
 * supplier often used when constructing new objects
 */
public class SupplierExample {

    public static void main(String[] args) {
        staticFunctionExample();
        constructorExample();
        printToStringOnLambda();
    }

    private static void printToStringOnLambda() {
        Supplier<ArrayList<String>> arraySupplier = ArrayList<String>::new;

        System.out.println("printing out lambda to string" + arraySupplier);

        /**
         * printing out lambda to string
         * co.za.damien.chapter4.functional.interfaces.SupplierExample$$Lambda$8/363771819@7b23ec81
         *
         * $$ - means class does not exist in a file system but only in memory
         */

    }

    private static void constructorExample() {
        Supplier<StringBuilder> constructor1 = StringBuilder::new;
        Supplier<StringBuilder> constructor2 = () -> new StringBuilder();

        System.out.println("constructor1 = " + constructor1.get());
        System.out.println("constructor2 = " + constructor2.get());
    }

    private static void staticFunctionExample() {
        Supplier<LocalDate> staticF1 = LocalDate::now; //LocalDate::now - used to create a supplier
        //to assign to s1
        Supplier<LocalDate> staticF2 = () -> LocalDate.now();

        System.out.println("staticF1 = " + staticF1.get());
        System.out.println("staticF2 = " + staticF2.get());

        /**
         * s1 = 2017-10-11
         * s2 = 2017-10-11
         */
    }
}
