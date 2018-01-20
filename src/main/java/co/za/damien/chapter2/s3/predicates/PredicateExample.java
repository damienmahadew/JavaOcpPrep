package co.za.damien.chapter2.s3.predicates;

import co.za.damien.chapter2.s2.functional.programming.lambdas.Animal;

import java.util.function.Predicate;

/**
 * Using predicates
 *      Same thing as a functional interface, but a Predicate is java's instance
 *      Takes in a generic value
 *
 *
 */
public class PredicateExample {

    private static void print(Animal animal, Predicate<Animal> trait) {
        if (trait.test(animal)) {
            System.out.println(animal);
        }
    }

    public static void main(String[] args) {
        print(new Animal("fish", false, true), a -> a.canHop());
        print(new Animal("kangaroo", true, false), a -> a.canHop());
    }
}
