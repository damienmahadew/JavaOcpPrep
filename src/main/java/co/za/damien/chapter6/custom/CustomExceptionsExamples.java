package co.za.damien.chapter6.custom;

/**
 * Most common to extend Exception (checked) or RuntimeException(unchecked)
 */
public class CustomExceptionsExamples extends Exception {

    public CustomExceptionsExamples() {
    }

    public CustomExceptionsExamples(String message) {
        super(message);
    }

    public CustomExceptionsExamples(Exception cause) {
        super(cause);
    }

    public static void main(String[] args) throws Exception {
//        throw new CustomExceptionsExamples("hellow");
        throw new CustomExceptionsExamples(new RuntimeException());

    }
}
