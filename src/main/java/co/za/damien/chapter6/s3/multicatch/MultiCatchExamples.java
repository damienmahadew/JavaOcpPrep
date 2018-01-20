package co.za.damien.chapter6.s3.multicatch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * EXAM : for multi catch the name of the variable is at the end of the statement and there is only one variable
 *
 * Note you cannot use a subclass in the list e.g. catch FileNotFoundException | IOException - java does not allow it
 *
 * Multi catch is effectively final - you cannot reassign the exception
 */
public class MultiCatchExamples {

    private void singleCatchExceptionExample() {
        try {
            //doSomething
            throw new IOException();
        } catch (DateTimeParseException d) {
        } catch (IOException e) {
//            e = new FileNotFoundException(); - allowed but bad practice
        }
    }

    private void multiCatchExceptionExample() {
        try {
            throw new IOException();
        } catch (DateTimeParseException | IOException e) {
//            e = new RuntimeException(); -- this is not allowed
        }
    }

}
