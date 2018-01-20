package co.za.damien.chapter3.s1.generics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generics are treated as objects, and dont have many methods available. Bounded wildcards solve
 * this by restricting what types can be used in that wildcard position
 * <p>
 * A bounded parameter type is a generic type that specifies a bound for generic.
 * <p>
 * A wildcard generic type is an unknown generic type represented with a question mark.
 * You can use generic wildcards in 3 ways
 * <p>
 * Type of bound                    Syntax                  Example
 * unbounded wildcard               ?                       List<?> l = new ArrayList<String>;
 * wildcard with an upper bound     ? extends type          List<? extends Exception> l = new ArrayList<RuntimeException>
 * wildcard with a lower bound      ? super type            List<? super Exception> l = new ArrayList<Object>
 */
public class BoundsExamples {

    /**
     * Represents any data type.
     * e.g. a method that looks through a list of any type
     * the following method does not compile
     */
    private void unboundedExamples(List<Object> list) {
    }


    private void unboundedExamplesCorrectWay(List<?> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    private void upperBoundedWildcards(List<? extends Number> numberList) {
        numberList.stream().mapToLong(x -> x.longValue()).sum();
        /**
         * THe below does not compile - making the list immutable
         */
//        numberList.add(new Integer(2));
    }

    /**
     * This tells java that the list will be a list of String objects or a super class of String, either way it is
     * safe to add a string to that list
     * @param list
     */
    private void lowerBoundsExample(List<? super String> list) {
        list.add("quack");

        /**
         * Becareful of the below
         */
        List<? super IOException> exceptions = new ArrayList<Exception>();
//        exceptions.add(new Exception());  // DOES NOT COMPILE - we could have a list IOException so adding an Exception wouldn't be fine
        exceptions.add(new IOException());
        exceptions.add(new FileNotFoundException());

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        BoundsExamples boundsExamples = new BoundsExamples();
        //the following line does not compile
//        boundsExamples.unboundedExamples(list);
        //Note that the following code does compile
        Object[] objects = new String[0];
        //but the following does compile but throws ArrayStoreException
        Integer[] numbers = {new Integer(1)};
        Object[] anotherArray = objects;
        anotherArray[0] = "asa";

        //The proper way to do the above
        boundsExamples.unboundedExamplesCorrectWay(list);
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        boundsExamples.unboundedExamplesCorrectWay(integerList);

        /**
         * the below fails -
         */
        List<? extends Bird> birds = new ArrayList<Bird>();
//        birds.add(new Sparrow());         // DOES NOT COMPILE
//        birds.add(new Bird());// DOES NOT COMPILE

        //the below is valid
        List<Flyer> flyers = new ArrayList<>();
        flyers.add(new HangGlider());
        flyers.add(new Goose());
        boundsExamples.anyFlyer(flyers);
        boundsExamples.groupOfFlyers(flyers);

        List<Goose> gooseList = new ArrayList<>();
        gooseList.add(new Goose());
        //doesnt compile
//        boundsExamples.anyFlyer(gooseList);
        boundsExamples.groupOfFlyers(gooseList);

        /**
         * Unbounded example
         */
        List<String> strings = new ArrayList<String>();
        strings.add("tweet");
        List<Object> objects2 = new ArrayList<Object>(strings);
        boundsExamples.lowerBoundsExample(strings);
        boundsExamples.lowerBoundsExample(objects2);


    }

    static class Sparrow extends Bird { }
    static class Bird { }


    interface Flyer { void fly(); }
    static class HangGlider implements Flyer { public void fly() {} }
    static class Goose implements Flyer { public void fly() {} }

    private void anyFlyer(List<Flyer> flyer) {}
    private void groupOfFlyers(List<? extends Flyer> flyer) {}

}
