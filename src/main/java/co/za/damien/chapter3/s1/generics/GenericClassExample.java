package co.za.damien.chapter3.s1.generics;

/**
 * Example of a generic class
 *      Naming convention:
 *          1. use single upper case letters to differentiate from a class name
 *          2. E for element
 *          3. K for map key
 *          4. V for map value
 *          5. N for number
 *          6. T for type
 *          7. S, U, V for multiple generic types
 *
 * What you cant do with generic types:
 * Reifiable - types can do anything that java allows - whose information is fully available at runtime
 * 1. Call the constructer .new T() - not allowed becauase at runtime it would be new Object()
 * 2. Create an array of that static type. You would be creating an array of objects
 * 3. Call instanceOf because List<Integer> and List<String> look the same
 * 4. Use primitive types as the generic parameter
 * 5. Create a static variable as a generic type
 */
public class GenericClassExample<T, S> {

    /**
     * The compiler removes all generics when the class is compiled and replaces it with Object
     *      This is called type erasure - allows your code to be compatible with older versions of java that do
     *      not support generics
     */
    private T otherGeneric;
    private S generic;

    public S getGeneric() {
        return generic;
    }

    public void setGeneric(S generic) {
        this.generic = generic;
    }

    public T getOtherGeneric() {
        return otherGeneric;
    }

    public void setOtherGeneric(T otherGeneric) {
        this.otherGeneric = otherGeneric;
    }
}
