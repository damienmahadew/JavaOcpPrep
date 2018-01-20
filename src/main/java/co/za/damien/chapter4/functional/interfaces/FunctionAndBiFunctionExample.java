package co.za.damien.chapter4.functional.interfaces;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A function is responsible for turning one parameter into a value of a possibly different type
 * and returning it
 * Bifunction takes in two and returns one
 */
public class FunctionAndBiFunctionExample {

    public static void main(String[] args) {
        functionExample();
        biFunctionExample();
    }

    private static void biFunctionExample() {
        // First two types are inputs and the third is the result

        //first param is used as the object concat is called on, and the second is the string added
        BiFunction<String, String, String> bi1 = String::concat;
        BiFunction<String, String, String> bi2 = (string, toAdd) -> string.concat(toAdd);

        System.out.println("bifunction1 - concat string a and string b and return a string ->" + bi1.apply("a", "b"));
        System.out.println("bifunction2 - concat string a and string b and return a string ->" + bi1.apply("c", "d"));
    }

    private static void functionExample() {
        Function<String, Integer> function1 = String::length;
        Function<String, Integer> function2 = s -> s.length();

        System.out.println("function1 - return length of abc =" + function1.apply("abc"));
        System.out.println("function2 - return length of abc =" + function2.apply("abc"));

        /**
         * function1 - return length of abc =3
         * function2 - return length of abc =3
         */
    }
}
