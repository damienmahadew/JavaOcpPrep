package co.za.damien.chapter9.introduction;

/**
 * NIO.2 - Java API version 2 for IO.
 * Non Blocking IO
 *
 * Java created NIO in version 4 - introduced bufferes and channels in place of streams,
 * load data from file into a temporary buffer, that unlike byte streams can be read forward
 * and backward without blocking on the underlying source
 *
 * Java NIO.2 was released in 7- actually a replacement for the File class
 *
 */
public class NioIntroduction {

    private void pathIntroduction() {
        java.nio.file.Path path ;
        /** primary entry point when working with NIO.2
         *
         * represents a hierarchical path on a storage system to a file or directory
         * Direct replacement for File
         *
         * Path has more features:
         * 1. Support for symbolic links - creating, detecting and navigating
         *
         * Created from a Paths factory class, Path is an interface and not a class
         * because creating a file or directory is considered a file system dependent task in NIO.2
         * When you obtain a path object from a default file system, the JVM gives you back an object
         * that unlike File transparently handles system specific details for the current platform
         *
         * If you dont use the factory then you would have to know what the underlying file system was
         * and use it in every instance
         *
         * java also includes java.nio.file.Files - helper class to operate on instances of Path
         */
    }
}
