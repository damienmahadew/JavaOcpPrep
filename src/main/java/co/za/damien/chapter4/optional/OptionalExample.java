package co.za.damien.chapter4.optional;

import java.util.Optional;

/**
 * Created by damien.mahadew on 2017-10-17.
 */
public class OptionalExample {

    public static void main(String[] args) {
        Optional<Double> doubleOptional = getAverage(90, 5);

        if (doubleOptional.isPresent()) System.out.println(" if result exists (should) then print ->" + doubleOptional.get());

        Optional<Double> doubleOptional2 = getAverage();

        if (doubleOptional2.isPresent()) System.out.println(" if result exists (shouldn't) then print ->" + doubleOptional2.get());
        else System.out.println("this should print because optional is not present");

        // if result exists (should) then print ->47.5
        //this should print because optional is not present
    }

    private static Optional<Double> getAverage(int... ints) {
        if (ints.length == 0) return Optional.empty();

        int sum = 0;
        for (int i: ints) sum+=i;
        return Optional.of((double) sum / ints.length);
    }
}
