package co.za.damien.chapter4.optional;

import java.util.Optional;

/**
 * Optional instance methods
 * Method                   When Empty                  Contains Value
 * get()                    throws exception            Returns value
 * ifPresent(Consumer c)    does Nothing                Calls consumer c with value
 * isPresent()              returns false               returns true
 * orElse(T other)          returns other parameter     returns value
 * orElseGet(Supplier s)    returns result of calling   returns value
 *                          supplier
 * orElseThrow(Supplier s)  throws exception created    returns value
 *                          by calling supplier
 */
public class OptionalExample {

    public static void main(String[] args) {
        Optional<Double> doubleOptional = getAverage(90, 5);

        if (doubleOptional.isPresent()) System.out.println(" if result exists (should) then print ->" + doubleOptional.get());

        Optional<Double> doubleOptional2 = getAverage();

        if (doubleOptional2.isPresent()) System.out.println(" if result exists (shouldn't) then print ->" + doubleOptional2.get());
        else System.out.println("this should print because optional is not present");

        //if you do a get() without checking ifPresent then you may end up with a NoSuchElementException

        // if result exists (should) then print ->47.5
        //this should print because optional is not present
    }

    private static Optional<Double> getAverage(int... ints) {
        if (ints.length == 0) return Optional.empty();

        int sum = 0;
        for (int i: ints) sum+=i;
        return Optional.of((double) sum / ints.length);
    }

    private static Optional<Double> OfNullableExamples(Double value) {
        //since the below is such a common pattern
        Optional<Double> someOption =  (value == null) ? Optional.empty() : Optional.of(value);

        //java created the following which does the same thing
        Optional<Double> someOption1 = Optional.ofNullable(value);
        return someOption;
    }

    private void methodsOfOptionalExamples() {
        Optional<Double> optional = Optional.of(12d);

        optional.ifPresent(System.out::println);
        //chooses other option if result is empty
        optional.orElse(Double.NaN);

//        optional.orElseGet(() -> Math.random());
        optional.orElseGet(Math::random);

        optional.orElseThrow(() -> new IllegalStateException());
        optional.orElseThrow(IllegalStateException::new);

        //the line below does not compile, expects a double but throws an exception instead
//        System.out.println(optional.orElseGet(() -> new IllegalStateException()));
    }

}
