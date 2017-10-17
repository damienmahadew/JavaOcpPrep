package co.za.damien.chapter4.operators;

import java.util.function.UnaryOperator;

/**
 * Special functions
 *  require all params to be of the same type - transforms its value to the same type
 * Extends function
 *  e.g. incrementing a value by one
 */
public class UnaryOperatorExample {

    public static void main(String[] args) {
        unaryOperatorExample();
    }

    private static void unaryOperatorExample() {
        UnaryOperator<String> unaryOperator1 = String::toUpperCase;
        UnaryOperator<String> unaryOperator2 = x -> x.toUpperCase();

        System.out.println("UnaryOperator1 example - toUpperCase abc ->" + unaryOperator1.apply("abc"));
        System.out.println("UnaryOperator2 example - toUpperCase abc ->" + unaryOperator2.apply("abc"));
        //UnaryOperator1 example - toUpperCase abc ->ABC
        //UnaryOperator2 example - toUpperCase abc ->ABC
    }
}
