package co.za.damien.chapter6.s1.review;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Possibilities for program failure
 * 1. try to read a file that does not exist
 * 2. Access db but connection is not available
 * 3. invalid SQL statement
 * 4. Wrong format for DateTimeFormatter
 *
 * Happy path - when nothing goes wrong in code
 *
 * RunTimeException or unchecked may be caught but isn't required to be caught
 *
 * Checked exception - any class that extends Exception but is not a RuntimeException
 *  - must follow the handle or declare rule -either caught or thrown back to the caller
 *
 * Error - Fatal and should not be caught by the program. It is possible to catch but not good practice
 *
 *                              Object
 *                                 |
 *                        java.lang.Throwable
 *                        |                 |
 *      java.lang.Exception                 java.lang.Error
 *              |
 *      java.lang.RuntimeException
 *
 * EXAM : IO , Parsing and SQL exceptions are checked
 */
public class ExceptionsReview {

    private void ocaExceptions() {
        /**
         * ArithmeticException - divide by zero
         *
         * ArrayIndexOutOfBoundsException - using illegal index
         *
         * ClassCastException - incorrect casting
         *
         * IllegalArgumentException - method has been passed an illegal or inappropriate argument
         *
         * NullPointerException - Null reference when object is required
         *
         * NumberFormatException - attempt to convert a string to numeric
         *
         * IOException - CheckedException
         */
    }

    private void ocpExceptions() {
        //Converting a string to a number
        java.text.ParseException parseException;

        //dealing with IO and NIO.2 exceptions
        //IOException is the parent class - can assume any java.io exception is checked
        java.io.IOException ioException;
        //Subclass of IOException
        java.io.FileNotFoundException fileNotFoundException;
        java.io.NotSerializableException notSerializableException;

        //Database issues SQLException is parent class
        //Assume any java.sql Exceptions are checked
        java.sql.SQLException sqlException;

        //trying to store the wrong datatype in an array
        java.lang.ArrayStoreException arrayStoreException;

        //invalid date format string
        java.time.DateTimeException dateTimeException;

        //trying to access a key or resource that does not exist
        java.util.MissingResourceException missingResourceException;

        //invalid operation in collections or concurrency
        java.lang.IllegalStateException illegalStateException;
        java.lang.UnsupportedOperationException unsupportedOperationException;
    }

    private void tryCatchExamples() {
        /**
         * try consists of mandatory try clause,
         * can include one or more catch clauses,
         * can also include a finally clause which runs regardless of whether an exception is thrown
         *
         * This is for both try and try with resources
         *
         * IMPORTANT : try needs either or both catch and finally clauses
         * try with resources is different
         * Remember that throw is to throw an exception, and throws is in a method declaration
         *
         * Java checks the catch blocks in order in which they appear. It is illegal to declare a subclass
         * exception lower in the list as it will be unreachable code
         *
         * Java will not allow you to catch an exception that is never thrown in the try block
         */

        try {
            throw new FileNotFoundException();
        } catch (IOException e) {

        }
    }
}
