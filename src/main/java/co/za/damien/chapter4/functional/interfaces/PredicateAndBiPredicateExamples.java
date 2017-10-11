package co.za.damien.chapter4.functional.interfaces;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Often used when filtering or matching
 * BiPredicate - takes two inputs
 *
 * Contains default methods - and, negate, or
 */
public class PredicateAndBiPredicateExamples {
    public static void main(String[] args) {
        usingPredicateToTestCondition();
        biPredicateExample();
        andExample();
        negateExample();
        orExample();
        isEqualExample();
    }

    private static void isEqualExample() {
        Predicate<String> isEqual = Predicate.isEqual("isEqual");

        System.out.println("isEqual = isEqual ->" + isEqual.test("isEqual"));
        //isEqual = isEqual ->true
    }

    private static void orExample() {
        Predicate<String> predicate1 = s->s.length() > 5;
        Predicate<String> predicate2 = s->s.length() < 2;

        System.out.println("is abcdfffff >5 or < 2 = " + predicate1.or(predicate2).test("abcdfffff"));
        //is abcdfffff >5 or < 2 = true
    }

    private static void negateExample() {
        Predicate<String> predicate = s->s.contains("eggs") && s.contains("brown");

        System.out.println("negate =" + predicate.negate().test("eggsbrown"));
        //negate =false
    }

    private static void andExample() {
        //instead of this
        Predicate<String> predicate = s->s.contains("eggs") && s.contains("brown");
        //we could say
        Predicate<String> eggPredicate = s -> s.contains("egg");
        Predicate<String> brownPredicate = s -> s.contains("brown");

        Predicate<String> combinedPredicate = eggPredicate.and(brownPredicate);
        System.out.println("combined predicate = " + combinedPredicate.test("eggbrown"));
        //combined predicate = true
    }

    private static void biPredicateExample() {
        //note - startswith is an instance method. this method is used in the first param
        //and the second value is the param for the method
        BiPredicate<String, String> biPredicate1 = String::startsWith;
        BiPredicate<String, String> biPredicate2 = (string1, string2) -> string1.startsWith(string2);

        System.out.println("bipredicate1 - does \"chicken\" start with chick = " + biPredicate1.test("chicken", "chick"));
        System.out.println("bipredicate2 - does \"chicken\" start with chick = " + biPredicate2.test("chicken", "chick"));

        /**
         * bipredicate1 - does "chicken" start with chick = true
         * bipredicate2 - does "chicken" start with chick = true
         */}

    private static void usingPredicateToTestCondition() {
        Predicate<String> predicate1 = String::isEmpty;
        Predicate<String> predicate2 = s -> s.isEmpty();

        System.out.println("is \"\" empty from predicate 1 = " + predicate1.test(""));
        System.out.println("is \"\" empty from predicate 2 = " + predicate2.test(""));

        /**
         * is "" empty from predicate 1 = true
         * is "" empty from predicate 2 = true
         */
    }


}
