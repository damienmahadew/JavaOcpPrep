package co.za.damien.chapter8.users;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

/**
 * {@link java.io.Console}
 * More evolved class from System.in and System.out - introduced in java 6
 */
public class InteractingWithUsersExamples {

    private void oldWay() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userInput = reader.readLine();
        System.out.println("you entered the following : " + userInput);
    }

    private void newWay() throws Exception {
        /**
         * Singleton - accessed by calling System.console()
         *  may return null in environments where text interactions are not supported
         */
        Console console = System.console();
        if (console != null) {
            String userInput = console.readLine(); //terminated by Enter
            console.writer().println("you entered the following = " + userInput);
        }
    }

    private void consoleWithReaderAndWriter() {
        Console console = System.console();
        Reader reader = console.reader();
        Writer writer = console.writer();
    }

    private void printfAndFormatExamples() {


        // Console only has a format without Local - uses default system locale
        //can use custom local
        Console console = System.console();
        console.writer().format(new Locale("fr", "CA"), "Hello world");

        //can also use format or printf(same just the name difference)
        console.format("some string");
        console.printf("some other string");
    }

    private void flushExample() {
        //forces any buffered output to be written immediately.
        //Recommended calling flush() prior to calling any readLine() or readPassword()
    }

    private void readLineExample() throws Exception {
        //normal readline() terminated by Enter
        //can add text to be printed before user is asked to input
        Console console = System.console();
        if(console == null) {
            throw new RuntimeException("Console not available");
        } else {
            console.writer().print("How excited are you about your trip today? ");
            console.flush();
            String excitementAnswer = console.readLine();
            String name = console.readLine("Please enter your name: ");
            Integer age = null;
            console.writer().print("What is your age? ");
            console.flush();
            BufferedReader reader = new BufferedReader(console.reader());
            String value = reader.readLine();
            age = Integer.valueOf(value);
            console.writer().println();
            console.format("Your name is "+name);
            console.writer().println();
            console.format("Your age is "+age);
            console.printf("Your excitement level is: "+excitementAnswer);
        }
    }

    private void readPasswordExample() {
        //Similar to readLine but echo is disabled - user does not see text
        //Has same overloaded method to accept a text to display
        //Returns array of chars instead of String - security reasons - if stored as a string - gets saved in a pool

        char[] password = new char[50];

        //you can use the below to erase the password
        Arrays.fill(password, 'X');
    }

    public static void main(String[] args) throws Exception{
        InteractingWithUsersExamples ex = new InteractingWithUsersExamples();
//        ex.newWay();
        ex.readLineExample();
    }
}
