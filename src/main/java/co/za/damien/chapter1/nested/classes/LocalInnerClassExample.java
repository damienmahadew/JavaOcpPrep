package co.za.damien.chapter1.nested.classes;

/**
 * Nested inner class defined within a method
 *      Does not exist until the method is invoked
 */
public class LocalInnerClassExample {

    private int length = 5;

    public void testingLocalInnerClasses() {
        //This has to be final - referred to as effectively final
        int width = 10;

        /**
         * Cannot have an access modifier
         * Cannot be declared static or cannot access static fields or methods
         * Have access to all fields and methods of the enclosing class
         * Do not have access to local fields of a method unless those fields are final
         */
        class LocalInner {
            private void multiply() {
                print(width * length);
            }
        }
        LocalInner inner = new LocalInner();
        inner.multiply();
    }

    public void print(int value) {
        System.out.println("The value = " + value);
    }

    public static void main(String[] args) {
        LocalInnerClassExample example1 = new LocalInnerClassExample();
        example1.testingLocalInnerClasses();
    }
}
