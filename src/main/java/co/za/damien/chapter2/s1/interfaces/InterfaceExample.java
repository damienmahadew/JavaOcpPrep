package co.za.damien.chapter2.s1.interfaces;

/**
 * Interfaces can have more than just abstract methods:
 *      public static final fields
 *      default methods
 *      static methods
 * Interfaces can extend multiple interfaces
 */
public interface InterfaceExample {

    public final static int MAX_SPEED = 100;

    public int getWingSpan() throws Exception;

    public default void land() {
        System.out.println("Animal is landing");
    }

    public static double calculateSpeed(float distance, double time) {
        return distance/time;
    }



}
