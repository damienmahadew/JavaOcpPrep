package co.za.damien.chapter5.stringclass;

public class ReviewStringClass {

    /**
     * String = sequence of characters
     *
     * Final and immutable
     *  -- optimizes java by storing string literals in String pool (meaning you can compare strings using ==)
     *      however it is preferred to use .equals because string objects created via constructor may yield incorrect
     *      results - using the new keyword intentionally creates new reference in the pool
     *
     * Since string is such a fundamental class - java allows + to add them
     * Remember - java concatenates from left to right and a string concatenated with anything else is a string
     *
     * Since string is immutable it is inefficient to update the value in a loop - use StringBuilder
     * If multiple threads are using the same object, use StringBuffer
     *
     * Differences between String, StringBuilder and StringBuffer
     *
     * Characteristic               String              StringBuilder               StringBuffer
     * Immutable?                   Yes                 No                          No
     * Pooled?                      Yes                 No                          No
     * Threadsafe?                  Yes                 No                          Yes
     * Can change size?             No                  Yes                         Yes
     */

    public static void main(String[] args) {
        stringPoolExamples();
        concatenationExamples();
    }

    private static void concatenationExamples() {
        String stringFirstConcat = "1" + 2 + 3; // 123
        String stringLastConcat = 1 + 2 + "3"; //33

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

    private void commonStringMethods() {
        String s = "abcde";
        System.out.println(s.trim().length()); // 5
        System.out.println(s.charAt(4)); //e - starts at 0
        System.out.println(s.indexOf('e')); //4
        System.out.println(s.indexOf("de")); //3
        System.out.println(s.substring(2, 4).toCharArray()); //CD
        System.out.println(s.replace('a', '1')); //1bcde
        System.out.println(s.contains("DE")); //false
        System.out.println(s.startsWith("a")); //true
    }

    private void stringBuilderExamples() {
        StringBuilder b = new StringBuilder();
        b.append(12345).append('-');
        System.out.println(b.length()); //6
        System.out.println(b.indexOf("-")); // 5
        System.out.println(b.charAt(2));   // 3

        StringBuilder b2 = b.reverse();
        System.out.println(b.toString());      // -54321
        System.out.println(b == b2);           // true

        StringBuilder s = new StringBuilder("abcde");
        s.insert(1, '-').delete(3, 4);
        System. out. println(s);                 //a-bde
        System. out. println(s.substring(2, 4)); // bd
    }
}