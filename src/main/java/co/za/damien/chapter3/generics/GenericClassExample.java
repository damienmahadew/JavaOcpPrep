package co.za.damien.chapter3.generics;

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
