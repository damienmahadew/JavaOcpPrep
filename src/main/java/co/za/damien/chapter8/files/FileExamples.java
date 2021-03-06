package co.za.damien.chapter8.files;

import java.io.File;

/**
 * read information about existing files/directories, list contents of a directory and create/delete files
 * and directories
 * <p>
 * Instance of a file - represents the pathname
 * Cannot read or write data but can be passed as a reference to stream classes
 */
public class FileExamples {

    private void createFileObject() {

        // created using a string containing absolute or relative file path
        File fileAbsolute = new File("c:\\temp\\anotherFolder\\someDoc.txt");
        File fileRelative = new File("anotherFolder\\someDoc.txt");

        System.out.println("Does fileAbsolute exist = " + fileAbsolute.exists());
        System.out.println("Does fileRelative exist = " + fileRelative.exists());

        //other constructors - join child file paths with parent

        File parent = new File("c:\\temp\\parent");
        File child = new File(parent, "folderInParent\\childFile.txt");
    }

    private void getPathFormatOfPc() {
        String usingSystemProperty = System.getProperty("file.separator");
        String usingFileClass = File.separator;

        System.out.println("usingSystemProperty = " + usingSystemProperty);
        System.out.println("usingFileClass = " + usingFileClass);
    }

    private void fileOperationsExamples() {
        File testFile = new File("c:\\temp\\parent\\child\\file.txt");
        //exists() -
        boolean exists = testFile.exists();
        System.out.println("exists = " + exists); //exists = true

        //getName()
        String name = testFile.getName();
        System.out.println("name = " + name); //name = file.txt

        //getAbosultePath()
        String absolutePath = testFile.getAbsolutePath();
        System.out.println("absolutePath = " + absolutePath); //absolutePath = c:\temp\parent\child\file.txt

        //isDirectory()
        boolean isDirectory = testFile.isDirectory();
        System.out.println("isDirectory = " + isDirectory); //isDirectory = true

        boolean isFile = testFile.isFile(); //isFile = false
        System.out.println("isFile = " + isFile);

        long length = testFile.length();  //length = 0
        System.out.println("length = " + length);

        long lastModified = testFile.lastModified();
        System.out.println("lastModified = " + lastModified); //lastModified = 1515232753590

        boolean delete = testFile.delete();
        System.out.println("delete = " + delete); //delete = true

        boolean renameTo = testFile.renameTo(new File("newTestFile.txt"));
        System.out.println("renameTo = " + renameTo);  //renameTo = false

        //creates a directory named by this path
        boolean mkdir = testFile.mkdir();
        System.out.println("mkdir = " + mkdir); //mkdir = true

        //creates the directory named by this path including non existing parent directories
        boolean mkdirs = testFile.mkdirs();
        System.out.println("mkdirs = " + mkdirs); //mkdirs = false

        //abstract pathname of this abstract pathnames parent or null if this pathname does not
        //name a parent directory
        String parent = testFile.getParent();
        System.out.println("parent = " + parent); //parent = c:\temp\parent\child

        File[] files = testFile.listFiles();
        System.out.println("list of files");

        for (File temp : files) {
            System.out.println("File = " + temp.getName());
        }
    }

    public static void main(String[] args) {
        FileExamples fileExamples = new FileExamples();
        fileExamples.fileOperationsExamples();
    }

}
