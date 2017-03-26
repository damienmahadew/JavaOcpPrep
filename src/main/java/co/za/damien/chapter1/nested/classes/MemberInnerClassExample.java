package co.za.damien.chapter1.nested.classes;

/**
 * Created by Damien on 2017-03-25.
 */
public class MemberInnerClassExample {
    private String greetings = "Outer";

    /**
     * Calling inner class
     */
    public void callInner() {
        Inner inner = new Inner();
        inner.go();
    }

    /**
     * Creating Inner class
     *      Has access to private variables of outer class
     *      Can be declared private, public, protected or default
     *      Can extend any class or implement interfaces
     *      Can be abstract or final
     *      Cannot declare static fields or methods
     *      When compiled you will get:
     *          MemberInnerClassExample.class
     *          MemberInnerClassExample$Inner.class
     *
     *      Can have the same variable names as outer classes
     *
     */
    protected class Inner implements InnerInterface {
        public String greetings = "Inner";

        // cannot have static fields or methods
        //private static int test = 1;

        public void go() {
            System.out.println("greetings : " + greetings);
            System.out.println("this.greetings : " + this.greetings);
            System.out.println("MemberInnerClassExample.this.greetings : " + MemberInnerClassExample.this.greetings);
        }
    }

    private interface InnerInterface {
        public void go() ;
    }

    public static void main(String[] args) {
        //Since Inner not static, class must be used with an instance of the outerclass

        //Two ways of doing this
        //1.
        MemberInnerClassExample example1 = new MemberInnerClassExample();
        example1.callInner();

        //2.
        MemberInnerClassExample example2 = new MemberInnerClassExample();
        MemberInnerClassExample.Inner inner = example2.new Inner();
        inner.go();

    }
}
