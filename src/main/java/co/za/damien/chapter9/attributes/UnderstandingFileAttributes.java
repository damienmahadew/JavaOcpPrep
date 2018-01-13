package co.za.damien.chapter9.attributes;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

/**
 * The methods available to read file attributes are available to all operating systems , however some
 * methods may be limited meaning in some of them
 */
public class UnderstandingFileAttributes {

    private void readingAttributesExamples() {
        Path path = Paths.get("c:\\temp\\test.txt");

        isDirectoryFileSymbolicLink(path);

        isHidden(path);

        isReadable(path);

        isExecutable(path);

        fileSize(path);

        modifiedDateExamples(path);

        managingOwnership(path);

        readingAllAttributes(path);

        /**
         * Modifying attributes
         * use Files.getFileAttributeView(Path, Class<V>) - returns a view object that can be used to update
         * the file system dependent attributes
         *
         * Can also use the view to read the associate file system attributes, by readAttributes
         *
         * Can only modify date/time values, cannot change the other file system properties because
         * this would change the file system object. e.g. cannot set a property to change a directory to a file,
         * also we cannot change a file size
         */
        try {
            //this line does not throw an exception
            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            BasicFileAttributes attributes = view.readAttributes();

            FileTime lastModifiedTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 10000);
            view.setTimes(lastModifiedTime, null, null); //lastModified, lastAccess, lastCreated, if you pass null it ignores it
        } catch (IOException e) {

        }

    }

    private void readingAllAttributes(Path path) {
        /**
         * Improving access with views
         * More efficient to access all meta data with one single call, also some attributes are file system
         * specific and cannot be generalized for all file systems
         *
         * You can construct views for various file systems in a single method call.
         * View = group of related attributes for a particular file system, a file may support multiple views
         * allowing you to retrieve and update various sets of information about the file
         *
         * To request a view - pass through a path and class to say which type of view
         * Files.readAttributes() - read only
         * Files.getFileAttributeView() - can modify information
         *
         * Throw IOException
         *
         * Attribute Class                      View Class                  Description
         * BasicFileAttributes                  BasicFileAttributeView      basic set of attributes supported by the file system
         * DosFileAttributes (NOT IN EXAM)      DosFileAttributeView        Attributes supported by DOS/Windows based systems
         * PosixFileAttributes (NOT IN EXAM)    PosixFileAttributeView      Attributes supported by POSIX systems e.g. UNIX, LINUX, MAC
         *
         * the last two inherit from the first attribute class
         */
        try {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            boolean isDirectory = basicFileAttributes.isDirectory();
            boolean isRegularFile = basicFileAttributes.isRegularFile();
            boolean isSymbolicLink = basicFileAttributes.isSymbolicLink();
            //not a file, directory or symbolic link
            boolean isOther = basicFileAttributes.isOther();
            long size = basicFileAttributes.size();
            FileTime creationTime = basicFileAttributes.creationTime();
            FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();
            FileTime lastAccessedTime = basicFileAttributes.lastAccessTime();
            //unique file identifier if available, or null
            Object uniqueFileIdentifier = basicFileAttributes.fileKey();
        } catch (IOException e) {
            //handle error
        }
    }

    private void managingOwnership(Path path) {
        /**
         * Managing ownership
         *
         * To set ownership, NIO.2 API provides a UserPrincipalLookupService helper class
         * First need to obtain an instance of the FileSystem object
         */
        try {
            UserPrincipal userPrincipal = Files.getOwner(path);
            System.out.println(userPrincipal.getName());

            //when setting a new owner, the OS may intervene and block as you do not have permissions
            UserPrincipal newUserPrincipal = FileSystems
                    .getDefault()
                    .getUserPrincipalLookupService()
                    .lookupPrincipalByName("damien");
            //or
            UserPrincipal anotherUserPrincipal = path
                    .getFileSystem()
                    .getUserPrincipalLookupService()
                    .lookupPrincipalByName("damien");

            Files.setOwner(path, newUserPrincipal);
        } catch (IOException e) {
            //handle exception
        }
    }

    private void modifiedDateExamples(Path path) {
        /**
         * Most OS tracks last modified date time,
         *
         * Only modify the last modified date time when you actually change content, otherwise it could
         * impact applications that access this file regularly
         */
        try {
            //File time stores created, modified, and accessed
            FileTime fileTime = Files.getLastModifiedTime(path);
            Long epochTime = fileTime.toMillis();
            //setting the time
            FileTime newFileTime = FileTime.fromMillis(System.currentTimeMillis());
            Files.setLastModifiedTime(path, newFileTime);
        } catch (IOException e) {
            //handle Exception
        }
    }

    private void isDirectoryFileSymbolicLink(Path path) {
        //note that directories can have extensions e.g. fur.jpg,
        //if a symbolic is present that links to an existing directory then returns true
        boolean isDirectory = Files.isDirectory(path);

        //a regular file is one that contains content, if the path is a symbolic link, java will perform
        //checks on the target of the symbolic link
        boolean isRegularFile = Files.isRegularFile(path);

        //returns true regardless if the target directory or file exists
        boolean isSymbolicLink = Files.isSymbolicLink(path);

        //note the following is redundant
        if (Files.exists(path) && Files.isDirectory(path)) {
            //you only need isDirectory()
        }
    }

    private void isHidden(Path path) {
        //isHidden() returns if the file is hidden or not
        //in linux/mac pc's the files normally start with a .
        //whereas in windows the hidden attribute is set
        //may throw a IOException as there may be errors reading the file information
        try {
            //file must be available and hidden
            boolean isHidden = Files.isHidden(path);
        } catch (IOException e) {
            //handle error
        }
    }

    private void isReadable(Path path) {
        //returns true if exists and readable
        boolean isReadable = Files.isReadable(path);
    }

    private void isExecutable(Path path) {
        //returns true if marked executable, returns false if the file does not exist
        boolean isExecutable = Files.isExecutable(path);
    }

    private void fileSize(Path path) {
        //returns size of file in bytes - conceptual size of data and may differ from actual size, due
        //to file system compression and organization. Throws IO Exception if file does not exist, or
        //process is unable to read information
        //NOTE: size is only defined on files, calling it on directory is system dependent and undefined,
        //if you want to determine the size of the directory then you will have to walk through the directory tree
        try {
            //no of bytes in the file expressed as long value
            long size = Files.size(path);
        } catch (IOException e) {
            //handle exception
        }
    }
}
