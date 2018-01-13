package co.za.damien.chapter9.interacting;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Many of the operations available for java.io.File are available for path via a helper class java.nio.file.Files
 * Most of the options in the files class throw exceptions if the file does not exist IOException
 */
public class InteractingWithFiles {

    private void methodsInFilesClass() throws IOException {
        Path path = fileExistsExamples();

        Path path2 = isSameFileExample(path);

        makeDirectoriesExamples(path, path2);

        fileCopyExamples(path, path2);

        movingFilesExamples();

        deleteFilesExamples();

        readingWritingExamples(path);

        /**
         * Reading files with read all lines
         * Files.readAllLines() reads all the lines of a text file and returns it as an ordered List of String values
         * API includes an overloaded method that takes in a charset as well
         *
         * You may encounter an OutOfMemoryError
         */
        Path path1 = Paths.get("c:\\temp\\test.txt");
        try {
            final List<String> lines = Files.readAllLines(path1);
            for (String string : lines) {
                System.out.println(string);
            }
        } catch (IOException e) {
            //handle exception
        }

    }

    private void readingWritingExamples(Path path) {
        /**
         * NIO.2 API includes methods for reading and writing file contents using java.io streams
         *
         * Files.newBufferedReader(Path, Charset) reads the file specified at the location using the java.io.BufferedReader
         * it also requires a charset value to determine what character encoding to use to read the file
         * You can encode in a variety of ways, as a default use Charset.defaultCharset()
         */
        Path path1 = Paths.get("c:\\temp\\test.txt");
        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("US-ASCII"))) {
            String currentLine = null;
            while((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            //handle exception
        }

        /**
         * Files.newBufferedWriter(Path, Charset) - same like reader
         * Creates a new file, overwriting the file if it exists
         * Supports optional varargs such as appending to an existing file
         */
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, Charset.forName("UTF-16"))) {
            bufferedWriter.write("Hello world");
        } catch (IOException e) {
            //handle io exception
        }
    }

    private void deleteFilesExamples() {
        /**
         * Files.delete(Path) deletes a file or empty directory within the file system.
         * Throws IOException,
         * If Directory not empty - DirectoryNotEmptyException
         * If the target path is a symbolic link, it will delete the symbolic link and not the directory
         *
         * You can use deleteIfExists(Path) to first check if the path exists , it will return false if the path
         * does not exist. It will still throw an exception if the file exists but fails to delete
         */
        try {
            Files.delete(Paths.get("c:\\temp\\test.txt"));
            Files.deleteIfExists(Paths.get("c:\\temp\\someOtherTest.txt"));
        } catch (IOException e) {

        }
    }

    private void movingFilesExamples() {
        /**
         * Files.move(Path, Path) moves or renames a file or directory within a system. Throws IOException
         * Keeps all of the original contents of the directory
         *
         * Follows links, throws exceptions if file exits, and not perform an atomic move
         * Can change these options by specifying NOFOLLOW_LINKS, REPLACE_EXISTING, ATOMIC_MOVE
         *
         * NOTE: the move can be applied to non-empty directories only if they are on the same drive
         * Otherwise you will get a DirectoryNotEmptyException
         */
        try {
            Files.move(Paths.get("c:\\zoo"), Paths.get("c:\\zoo-new"));
            Files.move(Paths.get("c:\\user\\addresses.txt"), Paths.get("c:\\zoo-new\\addresses.txt"));
        } catch (IOException e) {
            //handle exception
        }
    }

    private Path fileExistsExamples() {
        //the below does not throw an Exception
        Path path = Paths.get("somePath");
        boolean exists = Files.exists(path);
        return path;
    }

    private void fileCopyExamples(Path path, Path path2) throws IOException {
        //duplicating file contents
        //copies a file or directory from one location to the other - throws IOException when directory does not exist,
        //or cannot be read
        //*Directory copies are shallow and not deep(does not copy subdirectories and files)
        // the second argument is a new directory
        Files.copy(path, path2);
        //copy is also overloaded to take  in an InputStream and OutputStream
        try (InputStream is = new FileInputStream("c:\\temp\\test.txt");
             OutputStream out = new FileOutputStream("c:\\temp\\test2.txt")) {
            Files.copy(is, Paths.get("c:\\temp\\anotherTest.txt")); // supports varargs options - because you are copying to a path

            Files.copy(Paths.get("c:\\temp\\sometest.txt"), out); // does not support varargs options
        } catch (IOException e) {
            //handle exception
        }
    }

    private void makeDirectoriesExamples(Path path, Path path2) throws IOException {
        //making directories -- can throw IOException if directory cannot be created or already exists
        //equivalent to mkdir()
        Files.createDirectory(path);
        //equivalent to mkdirs() - creates all parent directories as well
        Files.createDirectories(path2);
    }

    private Path isSameFileExample(Path path) throws IOException {
        //isSameFile(Path path1, Path path2) checks if the files are the same,
        //works on directories as well
        //first checks if paths are equal in terms of equal() i.e. if path is the same and if equals it returns true
        //without checking if the file exists. If the paths are not equal then it checks the actual file, throwing
        //a checked exception
        //EXAM: it does not compare the contents only the location!
        Path path2 = Paths.get("anotherPath");
        boolean isSameFile = Files.isSameFile(path, path2);
        return path2;
    }
}
