package co.za.damien.chapter9.interacting;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * NIO.2 API contains many methods that allow you to operate on the Path
 * <p>
 * Note : Path is not a file, but a representation of a location on the file system i.e.
 * most operations on the path object can be called even if the file does not exist
 * <p>
 * A handful of methods do require the file to actually exist e.g.  Path.toRealPath(), if the file
 * does not exist it will throw a checked exception
 * <p>
 * Note : relativize() and resolve() do not clean up path symbols - normalize() does this
 */
public class InteractingWithPathsAndFilesExamples {

    private void providingOptionalArguments() {
        /**
         * Many of the methods dealing with paths take in optional flags in the form of varargs
         * Table below applies to both files and directories
         *
         * Enum Value           Usage                       Description
         * NO_FOLLOW_LINKS      Test file existing          If provided, symbolic links when encountered will not be traversed
         *                      Read file data
         *                      Copy file
         *                      Move file
         * FOLLOW_LINKS         Traverse a directory tree   If provided, symbolic links when encountered will be traversed
         * COPY_ATTRIBUTES      Copy file                   If provided, all meta data about a file will be copied with it
         * REPLACE_EXISTING     Copy file                   If provided and the target file exists, it will be replaced, otherwise if not provided
         *                      Move File                   and the file exists, then an exception will be thrown
         * ATOMIC_MOVE          Move file                   Operation is performed in an atomic manner within the file system
         *                                                  ensuring that any process using a file sees only a complete
         *                                                  record. Method using it may throw an exception if the file system
         *                                                  does not support it
         *
         * An atomic operation is any operation that is performed as a single indivisible unit of execution
         * which appears to the rest of the system as appearing instantaneously. Atomic move, the process
         * watching the file system never sees an incomplete or partial written file
         * AtomicMoveNotSupportedException can be thrown
         *
         *
         */
    }

    private void usingPathObjects() {
        /**
         * Many methods transforms the path object in some way and returning a new instance of Path
         * allowing you to chain methods
         */
        Paths.get("/zoo/damien").getParent().normalize().toAbsolutePath();
        /**
         * Path contains three methods to obtain basic information about the path
         *
         * The path name is : \land\hippo\happy.harry
         element 0 is land
         element 1 is hippo
         element 2 is happy.harry
         */
        Path path = Paths.get("/land/hippo/happy.harry");
        System.out.println("The path name is : " + path);
        //EXAM : note the getName(int index) is 0 indexed
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println(String.format("element %d is %s", i, path.getName(i)));
        }

        /**
         * Path contains many methods to get specific sub elements of a path, returned as Path objects
         */
        Path fileName = path.getFileName(); //path instance representing the file name
        Path parent = path.getParent(); //returns the parent path, if nothing exists, then null, if this is a relative path,
        //it will stop at the top most level that was used to create the Path object
        Path root = path.getRoot(); //returns the root element, or null if the path was relative

        printPathInformation(Paths.get("/zoo/armadillo/shells.txt"));
        System.out.println();
        printPathInformation(Paths.get("armadillo/shells.txt"));
    }

    private void checkingPathTypes() {
        /**
         * Path interface contains two methods to assist with relative and absolute paths
         */
        Path path = Paths.get("/home/text.txt");
        boolean isAbsolute = path.isAbsolute();

        Path asbolutePath = path.toAbsolutePath();//converts a relative to absolute path, or if absolute already, returns a copy
    }

    private void creatingPathsWithSubpaths() {
        /**
         * subpath(int, int) returns a relative subpath of a path, referenced by an inclusive start index
         * and en exclusive end index
         * Subpath does not include the root of the file
         *
         * subpath(0, 4) and subpath(1,1) throw illegalArgumentException
         */
        Path path = Paths.get("/mammal/carnivore/racoon.image");
        System.out.println("PAth is : " + path);
        System.out.println("Subpath from 0 to 3 is: " + path.subpath(0, 3));
        System.out.println("Subpath from 1 to 3 is: " + path.subpath(1, 3));
        System.out.println("Subpath from 1 to 2 is: " + path.subpath(1, 2));
        /**
         * PAth is : \mammal\carnivore\racoon.image
         Subpath from 0 to 3 is: mammal\carnivore\racoon.image
         Subpath from 1 to 3 is: carnivore\racoon.image
         Subpath from 1 to 2 is: carnivore
         */
    }

    private void usingPathSymbols() {
        /**
         * file systems support paths that contain path information in the form of path symbols
         *
         * .. = parent directory
         * . = current directory
         */
    }

    private void derivingAPathWithRelativize() {
        /**
         * relativize(Path path) method available to construct a relative path from one path object to another
         *
         * requires both paths to be relative or both to be absolute, throws an illegalargumentexception
         * On windows if you are using absolute paths, then both paths must be on the same drive
         */
        //relative  example
        Path path1 = Paths.get("fish.txt");
        Path path2 = Paths.get("birds.txt");
        System.out.println(path1.relativize(path2));
        System.out.println(path2.relativize(path1));
        /**
         * ..\birds.txt
         ..\fish.txt
         */

        //absolute example
        Path path3 = Paths.get("E:\\habitat");
        Path path4 = Paths.get("E:\\sanctuary\\raven");
        System.out.println(path3.relativize(path4));
        System.out.println(path4.relativize(path3));
        /**
         *..\sanctuary\raven
         ..\..\habitat
         */
    }

    private void joinPathObjectsWithResolve() {
        /**
         * resolve(Path) creates a new path by joining an existing path to a current path
         *
         */
        final Path path1 = Paths.get("/cats/../panther");
        final Path path2 = Paths.get("food");
        System.out.println(path1.resolve(path2));
        /**
         * /cats/../panther/food
         */

        /**
         * EXAM: be aware : if the second path is absolute then the result of resolve is equivalent to the second path
         * e.g.
         */
        final Path path3 = Paths.get("/turkey/food");
        final Path path4 = Paths.get("/tiger/cage");
        System.out.println(path3.resolve(path4));
        /**
         * /tiger/cage
         */
    }

    private void cleaningUpWithNormalize() {
        Path path3 = Paths.get("E:\\data");
        Path path4 = Paths.get("E:\\user\\home");
        Path relativePath = path3.relativize(path4);
        System.out.println(path3.resolve(relativePath)); //output is E:\data\..\user\home
        System.out.println(path3.resolve(relativePath).normalize()); //output is E:\user\home
    }

    private void toRealPathExamples() throws IOException {
        /**
         * toRealPath(Path) takes a path object that may or may not point to a real file and returns a reference
         * to the real path of the file, it also verifies that the file exists and may throw an IOException if it doesnt
         * Only path method to support the NOFOLLOW_LINKS - but disabled by default
         *
         * Implicitly calls normalize()
         */

        //can get the current working directory
        System.out.println(Paths.get(".").toRealPath());
//        System.out.println(Paths.get(".").toRealPath(LinkOption.NOFOLLOW_LINKS));
    }

    private void printPathInformation(Path path) {
        System.out.println("Filename is :" + path.getFileName());
        System.out.println("root is : " + path.getRoot());

        Path currentPath = path;
        while ((currentPath = currentPath.getParent()) != null) {
            System.out.println("current parent path is :" + currentPath);
        }
    }

    public static void main(String[] args) {
        InteractingWithPathsAndFilesExamples examples = new InteractingWithPathsAndFilesExamples();
        examples.usingPathObjects();
        examples.creatingPathsWithSubpaths();
        examples.derivingAPathWithRelativize();
    }
}
