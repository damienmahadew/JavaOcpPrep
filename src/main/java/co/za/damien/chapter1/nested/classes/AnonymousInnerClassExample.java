package co.za.damien.chapter1.nested.classes;

/**
 * Anonymous Inner class
 *      Local inner class that does not have a name
 *      Declared and instantiated all in one line using 'new'
 *      Used to extend an existing class or implement an interface
 *      Useful when you have a small implementation
 *      You can define them where they are needed even if it is an argument to another method
 */
public class AnonymousInnerClassExample {

    abstract class SaleTodayOnly {
        abstract int dollarsOff();
    }

    public int admission(int basePrice) {
        SaleTodayOnly saleTodayOnly = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 5;
            }
        };
        return basePrice - saleTodayOnly.dollarsOff();
    }

    public static void main(String[] args) {
        AnonymousInnerClassExample innerClassExample = new AnonymousInnerClassExample();
        int temp = innerClassExample.admission(6);
        System.out.println(temp);
    }
}
