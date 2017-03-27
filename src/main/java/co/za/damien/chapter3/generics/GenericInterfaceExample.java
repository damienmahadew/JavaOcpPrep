package co.za.damien.chapter3.generics;

/**
 * An example of a generic interface
 * Three types of ways to use this interface
 *      1. Declare the type in the implementing class
 *          public class GenericInterfaceExampleImpl implements GenericInterfaceExample<String> {}
*       2. THe next way is to create a generic class
 *          class GenericInterfaceExampleImpl<T> implements GenericInterfaceExample<T>
 *      3. Do not use generics at all
 *              Compiler warns you about the raw type but still compiles
 *              see {@link GenericClassExampleImpl}
 *
 * Oracle refers to types whos information is fully avaialable at runtime as reifiable. Non-reifiable types have some limitations:
 *  1. Call the constructor - new T() is not allowed because at runtime it would be new Object()
 *  2. create an array of static type - will create an array of objects
 *  3. call instance of - List<String> and List<Integer> will be of the same type
 *  4. Use primitive types as the generic
 */
public interface GenericInterfaceExample<T> {

    void genericMethod(T t);
}
