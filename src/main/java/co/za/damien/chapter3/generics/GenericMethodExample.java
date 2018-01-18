package co.za.damien.chapter3.generics;

/**
 * Also possible to have generic methods, often used for static methods since they aren't part of the instance
 * that can declare the type, however it is also allowed on non-static methods as well
 */
public class GenericMethodExample {

    /**
     * Generic is specific immediately before the return type
     */
    public static <T> GenericInterfaceExample<T> genericStaticMethod(T t) {
        return null;
    }

    public static <T> void genericStaticMethod2(T t) {
    }

    public static <T> T genericStaticMethod3(T t) {
        return null;
    }

    public static void main(String[] args) {
        //be aware that this syntax exists
        GenericMethodExample.<String>genericStaticMethod("test");
        GenericMethodExample.<String[]>genericStaticMethod(args);
    }
}
