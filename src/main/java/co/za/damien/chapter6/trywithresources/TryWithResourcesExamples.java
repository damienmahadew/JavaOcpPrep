package co.za.damien.chapter6.trywithresources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Multicatch allows you to write code without duplication, another problem arises with duplicate code
 * in finally blocks i.e. important to close resources when you are finished with them
 *
 * try with resources introduced in Java 7
 *
 * dont require finally or catch clause, finally is there implicitly
 *
 * You can still have catch or finally, but the implicit finally block is run before the programmers code
 *
 * Cannot have multiple finally blocks!
 *
 * IMPORTANT : resources created in try with resources block is only accessible to the try block
 * not even the catch or finally block
 *
 * You cannot put any class in the try with resources . A class must implement java.lang.AutoCloseable
 */
public class TryWithResourcesExamples implements AutoCloseable {

    private void oldWayOfClosingResources(Path path1, Path path2) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = Files.newBufferedReader(path1);
            writer = Files.newBufferedWriter(path2);
            writer.write(reader.readLine());
        } finally {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        }
    }

    private void newWayOfClosingResources(Path path1, Path path2) throws IOException {
        //also known as automatic resource management
        try (BufferedReader reader = Files.newBufferedReader(path1);
        BufferedWriter writer = Files.newBufferedWriter(path2)) {
            writer.write(reader.readLine());
        }
    }

    private void closeableVsAutocloseable() {
        /**
         * Autocloseable was introduced in 7, but Closeable was around before that.
         *
         * Difference:
         *  Closeable restricts the type of exception thrown to IOException
         *  Closeable requires implementations to be idempotent
         *
         * Autocloseable is less strict
         */
    }

    @Override
    public void close() throws Exception {
        //Something to do when this object is closed using try with resources
        //dont have to throw an exception
        //becareful when this method throws a checked exception. you need to either catch it
        //in the try catch calling this or change the method signature to throw the exception

        //JAVA recommends that this method does not throw Exception but rather a specific exception
        // , also recommends that this method is idempotent - method can be called multiple times
        // without any side effects e.g. must not throw exception only the second time or change state
        // , this is allowed but discouraged,
        throw new Exception();
    }

    public static void main(String[] args) {
        try (TryWithResourcesExamples examples = new TryWithResourcesExamples()) {
            //doSomething
        } catch (Exception e) {

        }
    }
}
