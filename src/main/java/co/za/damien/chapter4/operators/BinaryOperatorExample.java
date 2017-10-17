package co.za.damien.chapter4.operators;

import java.util.function.BinaryOperator;

/**
 * Special function
 *  merges two values into the same type - e.g. adding two numbers
 * Extends BiFunction
 */
public class BinaryOperatorExample {

    public static void main(String[] args) {
        BinaryOperator<String> binaryOperator1 = String::concat;
        BinaryOperator<String> binaryOperator2 = (string, toAdd) -> string.concat(toAdd);

        System.out.println("BinaryOperator1<String> on 'a' and 'b' ->" + binaryOperator1.apply("a", "b"));
        System.out.println("BinaryOperator2<String> on 'a' and 'b' ->" + binaryOperator2.apply("a", "b"));

        //BinaryOperator1<String> on 'a' and 'b' ->ab
        //BinaryOperator2<String> on 'a' and 'b' ->ab
    }
}
