package co.za.damien.chapter2.design.patterns.factory;

/**
 * Factory Pattern example
 * factory method pattern - creational pattern based on a factory class to produce objects based on a set of input params
 * Similar to the builder pattern but focused on supporting class polymorphism
 * <p>
 * Good practice to post fix the name of the class with 'factory'
 */
public class FoodFactory {

    /**
     * Something to note:
     *      You can change the constructors of all the Food subclasses to private to only allow the factory to create it
     *      but that means you would not be able to instantiate it in the factory.
     *
     *      You could leave the access modifier as default and ensure the factory is in the same package
     */

    public static Food getFood(String animalName) {
        switch (animalName) {
            case "zebra":
                return new Hay(100);
            case "rabbit":
                return new Pellets(5);
            case "goat":
                return new Pellets(30);
            case "polar bear":
                return new Fish(10);
        }
        // Good practice to throw an exception if no matching subclass could be found
        throw new UnsupportedOperationException("Unsupported animal: " + animalName);
    }
}
