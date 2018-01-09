package co.za.damien.chapter6.assertions;

/**
 * Assertion is a boolean expression that you place at a point in your code where you
 * want something to be true
 *
 * An assert contains the true statement along with an optional String message
 *
 * Allows for detecting for defects
 *
 * Can turn on assertions for testing an debugging while leaving them off while in production
 *
 * Assertion throws an AssertionError if it is false
 *
 * three possible outcomes of an assert statement:
 * 1. if assertions are disabled, java skips the assertion and goes on in the code
 * 2. if assertions are enabled, and assertion is true, nothing happens
 * 3. if assertions are enabled, assertion is false, then a java.lang.AssertionError is thrown
 *
 * Assertions are ignored by default by JVM, to enable use the -enableassertions or -ea flag
 * e.g. java -ea Rectangle
 *
 * Enables assertions in all classes. System classes are part of the program
 *
 * Enable assertions for a specific class e.g. enable for classes in com.damien.mahadew package
 * and subpackages
 *
 * java -ea:com.damien.mahadew... my.program.Main
 * or a specif class
 * java -ea:com.damien.mahadew.SpecificClass my.program.Main
 *
 * you can enable and disable at the same time (-disableassertions or -da)
 * java -ea:com.damien.mahadew... -da:com.damien.mahadew.DisableAssertions my.program.Main
 *
 * EXAM : assert without assertions enabled
 *
 * BEcause assertions are turned off in production, your assertions should not contain any business
 * logic that affects the outcome of your code
 * e.g. assert ++x > 10
 *
 * Do not use assertions to validate arguments passed in to a method. Use IllegalArgumentException
 */
public class AssertionsExamples {

    private void assertStatementExamples() {
        //example of assert
        assert true;
        //or
        assert (true);
        //example of assert with message
        assert true: "Asserting true";
        //this message will be thrown if you assert false
        assert false: "Expecting an asserting";
    }

    public static void main(String[] args) {
        AssertionsExamples assertionsExamples = new AssertionsExamples();
        assertionsExamples.assertStatementExamples();
    }
}
