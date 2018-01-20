package co.za.damien.chapter6.s5.suppressed;

/**
 * Suppressed Exceptions - when multiple exceptions are thrown e.g. from try block and the close() function
 * in an autocloseable class, then all but the first exception are the suppressed exceptions
 * see below
 */
public class SuppressedExceptionsExample implements AutoCloseable {

    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException("exception thrown from close");
    }

    private void suppressedExceptionsExamples() {
        try(SuppressedExceptionsExample suppressedExceptionsExample = new SuppressedExceptionsExample()) {
            throw new IllegalStateException("exception thrown from try");
            //Bear in mind that the catch block looks for matches on the primary exception, java does not enforce it
        } catch (IllegalStateException e) {
            System.out.println("caught : " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("suppressed exceptions : " + t.getMessage());
            }
        }
    }

    private void multipleSuppressedExceptionsExamples() {
        //NOTE : java will try to close the resources in reverse order!
        try(SuppressedExceptionsExample suppressedExceptionsExample = new SuppressedExceptionsExample();
        SuppressedExceptionsExample suppressedExceptionsExample1 = new SuppressedExceptionsExample()) {
            System.out.println("try block");
            //Bear in mind that the catch block looks for matches on the primary exception, java does not enforce it
        } catch (IllegalStateException e) {
            System.out.println("caught : " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("suppressed exceptions : " + t.getMessage());
            }
        }
    }

    private void lostExceptionsExamples() {
        //NOTE : suppressed exceptions apply only to exceptions thrown in the try block!!
        //if the below happens, the exception from the try block and the close() will be lost
        try(SuppressedExceptionsExample suppressedExceptionsExample = new SuppressedExceptionsExample()) {
            throw new IllegalStateException("try block");
            //Bear in mind that the catch block looks for matches on the primary exception, java does not enforce it
        } finally {
            throw new RuntimeException("all other exceptions are lost");
        }
    }

    public static void main(String[] args) {
        SuppressedExceptionsExample exceptionsExample = new SuppressedExceptionsExample();
        System.out.println("Suppressed Exceptions examples");
//        exceptionsExample.suppressedExceptionsExamples();
        System.out.println("Multiple Suppressed Exceptions examples");
//        exceptionsExample.multipleSuppressedExceptionsExamples();
        System.out.println("Lost Exceptions examples");
        exceptionsExample.lostExceptionsExamples();
    }
}
