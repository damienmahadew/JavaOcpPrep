package co.za.damien.chapter9.streams;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Prior to Java 8, searching for a file within a directory tree were verbose and often required you to define
 * an entire class to perform a single task, Java 8 introduced code to allow you to do this in a single line of code
 */
public class FileStreamsExamples {

    private void directoryWalkingExamples() {
        /**
         * File systems are organized in a hierarchical manner
         * Walking or traversing a directory is the process by which you start with the parent directory
         * and iterate over all its descendants until some condition is met or there are no more elements left
         *
         * Two common search strategies
         * Depth first search - traverses the structure from the rot to an arbitrary leaf then navigates back up toward
         * the root, traversing fully down any paths it skipped along the way
         * Search depth - distance from the root to the current node, for performance reasons, some processes
         * have a maximum limit on how many levels deep the search goes before stopping
         *
         * Breadth first search - starts at the root and processes all elements of each particular depth or distance from the
         * root before proceeding to the next depth level. The results are ordered by depth with all nodes at depth 1 read
         * before all nodes at depth 2
         *
         * Do not need to understand the search strategies for the exam, except that the stream api uses depth first
         * search with a default maximum depth of Integer.MAX_VALUE
         *
         * Files.walk(Path) returns a Stream<Path> object that traverses the directory in a depth first , lazy manner
         * Keep in mind, when you create the Stream using Files.walk the contents of the directory have not yet been traversed
         *
         * There is also Files.newDirectoryStream() which returns a DirectoryStream<Path> but is not actually a stream
         * - not in exam
         *
         * Walk will not traverse symbolic links by default, could lead to a cycle - an infinite circular dependency
         * in which an entry in a directory is an ancestor of that directory -
         * if you wish to traverse symbolic links use Files.walk(path, FileVisitOption.FOLLOW_LINKS) - recommended to use
         * an appropriate depth level when this is used
         * A FileSystemLoopException will be thrown if a cycle is detected
         */
        Path path = Paths.get("c:\\temp\\test.txt");
        try {
            Files.walk(path) //overloaded version of walk(path, int) which takes in a max depth level, 0 will be the root
                    //you will need to set it to 1 to print any child elements
                    .filter(path1 -> path.toString().endsWith(".java"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            //handle error - could be a problem reading the underlying file system
        }
    }

    private void searchingADirectoryExamples() {
        /**
         * Files.find(Path, int, BiPredicate) method behaves in a similar manner to filter on the stream mentioned above
         * but requires the depth to be set explicitly
         * Like walk(), find also supports the FOLLOW_LINK vararg option
         *
         * BiPredicate takes two options, Path and BasicFileAttributes
         */
        Path path = Paths.get("c:\\temp\\test.txt");
        long dateFilter = 1420070400000L;
        try {
            Stream<Path> stream = Files.find(path, 10,
                    (path1, basicFileAttributes) ->
                        path1.toString().endsWith(".java") && basicFileAttributes.lastModifiedTime().toMillis() > dateFilter
                    );
            stream.forEach(System.out::println);
        } catch (IOException e) {
            //handle exception
        }
    }

    private void listingDirectoryContents() {
        /**
         * Similar to listFiles() of java.io,
         *
         * You could use the walk() method with a depth of 1 but there is a method to do this
         * Files.list(Path)
         *
         * Only searches one level deep and is the same as java.io.File.listFiles()
         */
        Path path = Paths.get("c:\\temp\\test");
        try {
         Files.list(path)
                 .filter(path1 -> !Files.isDirectory(path1))
                 .map(path1 -> path1.toAbsolutePath())
                 .forEach(System.out::println);
        } catch (IOException e) {
            //handle exception
        }
    }

    private void printFileContents() {
        /**
         * Remember Files.readAllLines() - reading a large data set could result in an OutOfMemoryError
         * Files.lines(path) returns a Stream<String> that does not suffer from this issue
         * File is read and processed lazily
         */
        Path path = Paths.get("c:\\temp\\test.txt");
        try {
            Files.lines(path)
                    .forEach(System.out::println);
        } catch (IOException e) {
            //handle exception
        }

        /**
         * EXAM : familiar with both readAllLines (returns list) and lines (returns stream)
         * difficult as forEach can be called on streams and collections
         */
    }
}
