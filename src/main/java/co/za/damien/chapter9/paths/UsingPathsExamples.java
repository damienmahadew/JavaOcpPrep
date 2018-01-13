package co.za.damien.chapter9.paths;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path is an interface - need a factory class to create an instance of it
 *
 * EXAM: be vigilant of Path vs Paths
 */
public class UsingPathsExamples {

    private void createPathUsingPaths() throws URISyntaxException {
        /**
         * Easiest way to create the Path interface is to use the Paths factory
         */
        Path somePathInWindows = Paths.get("c:\\temp\\sometextFile.txt"); //windows
        Path somePathInLinux = Paths.get("home/damien/"); //linux
        Path someRelativePath = Paths.get("innerFolder/someTextFile.txt");

        /**
         * Use the following rules to determine if the path is absolute or relative
         * 1. If the file starts with a forward slash - absolute
         * 2. If the file starts with a driver letter - absolute
         * 3. Otherwise it is a relative path
         */
        //another way of using Paths.getPath(String path, String... paths) - allows you to build a path
        // and the path separator is automatically included
        Path varargPaths = Paths.get("c:", "temp", "someRandomFolder", "someFile.txt");
        /**
         * Another way to construct paths is with a URI -uniform resource identifier - string of
         * characters that identify a resource, begins with a schema that identifies a resource type,
         * followed by a path value e.g. file:// http:// ftp://
         * You may only use absolute paths!
         */
        Path uriPath1 = Paths.get(new URI("file://c:/temp/someTempFile.txt"));
        Path uriPath2 = Paths.get(new URI("file:///home/damien/someTempFile.txt"));
        Path uriPathRuntimeException = Paths.get(new URI("file://innerFolder/someTempFile.txt")); //throws runtime exception

        //you can convert a path back to URI
        URI uri = uriPath1.toUri();
    }

    private void longerSyntaxToGetFilePath() throws URISyntaxException {
        /**
         * Paths.getPath() is actually a shorthand for java.nio.file.FileSystem.getPath() method
         * FileSystem has a protected constructor so use FileSystems to get an instance of it
         */
        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath("some path");

        /**
         * Accessing remote file systems
         */
        FileSystem remoteFileSystem = FileSystems.getFileSystem(new URI("http://www.website.com"));
        Path remotePath = remoteFileSystem.getPath("someFileOnRemotePath.txt");
    }

    private void workingWithTheLegacyFile() {
        /**
         * In Java 7 , File class was modified to add the toPath() method
         */
        File file = new File("c:\\temp\\testFile.txt");
        Path pathToFile = file.toPath();
        /**
         * For backward compatibility, Path as a toFile() method as well
         */
        File anotherFile = pathToFile.toFile();
    }
}
