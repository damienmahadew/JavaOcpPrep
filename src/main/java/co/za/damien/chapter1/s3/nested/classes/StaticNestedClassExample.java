package co.za.damien.chapter1.s3.nested.classes;

/**
 * A static nested class is a static class defined at the member level
 * It can be instantiated without the instantiation of the outer class therefore it cant access the local variables
 *      of the outer class unless it creates a new object
 * It is like a regular class except the following:
 *      the nesting class creates a namespace because the enclosing class name must be used to refer to it
 *      Can be made private or use other modifiers
 *      The enclosing class can refer to the fields and methods of the inner class
 *
 * Importing static classses:
 *      way 1:
 *          import StaticNestedClassExample.Nested;
 *      Or
 *      way 2:
 *          import static StaticNestedClassExample.Nested;
 */
public class StaticNestedClassExample {

    static class Nested {
        private int price = 6;
    }

    public static void main(String[] args) {
        Nested nested = new Nested();
        System.out.print(nested.price);
    }
}
