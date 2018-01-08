package co.za.damien.chapter5.stringclass;

public class ReviewStringClass {

    /**
     * String = sequence of characters
     *
     * Final and immutable
     *  -- optimizes java by storing string literals in String pool (meaning you can compare strings using ==)
     *      however it is preferred to use .equals because string objects created via constructor may yield incorrect
     *      results - using the new keyword intentionally creates new reference in the pool
     */

    public static void main(String[] args) {
        stringPoolExamples();
        concatenationExamples();
    }

    private static void concatenationExamples() {
        String stringFirstConcat = "1" + 2 + 3;
        String stringLastConcat = 1 + 2 + "3";

        System.out.println("stringFirstConcat (\"1\" + 2 + 3)= " + stringFirstConcat);
        System.out.println("stringLastConcat (1 + 2 + \"3\") = " + stringLastConcat);
    }

    private static void stringPoolExamples() {
        String s1 = "bunny";
        String s2 = "bunny";
        String s3 = new String("bunny");

        System.out.println("s1 == s2 ->" + (s1 == s2)); // true
        System.out.println("s1 == s3 -> " + (s1 == s3)); // false
        System.out.println("s1.eqauls(s3)" + s1.equals(s3)); // true
    }
}
