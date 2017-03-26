package co.za.damien.chapter2.functional.programming;

/**
 * A functional interface has only one abstract method
 *
 * The annotation @FunctionalInterface is not mandatory, java will know its a functional interface if there is only one
 * abstract method, (can have static methods and default methods)
 *  however if you mark an interface and you have multiple or no methods then the compiler will complain
 */
@FunctionalInterface
public interface FunctionalInterfaceExample {

    //Can have only one abstract method
    public void sprint() ;

    public default void print() {
        System.out.println("Default methods still work");
    }

    public static void printStatic() {
        System.out.println("Static methods still work");
    }

}
