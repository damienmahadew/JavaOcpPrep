package co.za.damien.chapter4.functional.interfaces;

import java.util.function.Function;

/**
 * A function is responsible for turning one parameter into a value of a possibly different type
 * and returning it
 * Bifunction takes in two and returns two
 */
public class FunctionAndBiFunctionExample {

    public static void main(String[] args) {
        functionExample();

    }

    private static void functionExample() {
        Function<String, Integer> function1 = String::length;
        Function<String, Integer> function2 = s -> s.length();

        System.out.println("function1 - return length of abc =" + function1.apply("abc"));
        System.out.println("function2 - return length of abc =" + function2.apply("abc"));

        /**
         * function1 - return length of abc =3
         * function2 - return length of abc =3
         */}
}
