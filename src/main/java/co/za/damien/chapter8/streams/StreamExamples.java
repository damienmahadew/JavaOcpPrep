package co.za.damien.chapter8.streams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Streams IO not lambda streams
 *  used to read/write to files
 *  reads one chunk of data at a time
 *  some streams can read/write bytes at a time, while others may use characters or strings
 *      others may use groups of the above
 *
 * Note : all underlying streams use bytes - new api's created for convenience and performance
 *  e.g. bufferedoutputstream - writes large chunks at a time
 *
 * Used for files but more commonly used for reading/writing of a sequential data source
 *
 * Important :
 *  Byte Streams Vs Character Streams
 *  Byte Streams have streams in their name
 *  Character Streams have Reader/Writer in their name
 *      e.g. FileInputStream -- FileReader
 *  Stream classes - all types of binary or byte data
 *  Reader/Writer classes - inputting/outputting only character and string data
 *      ** Reader/Writers are still streams
 *
 * You can use Writer to output a string to a file without worrying about the underlying byte encoding
 *
 * All Input classes have corresponding output classes e.g. FileReader/FileWriter
 * except PrintWriter and PrintStream
 *
 * Low level vs high level streams
 *  Low level - connects directly with the source of the data
 *  High level - built on top of low level streams using wrapping - pass the low level stream to the
 *  high level stream
 *
 * NOTE : Exam - only know low level stream classes that operate on files
 *
 */
public class StreamExamples {

    private void highLevelStreamExample() throws Exception {
        //High level streams add new methods and performance enhancements
        //e.g of wrapping
        BufferedReader bufferedReader = new BufferedReader(new FileReader("c:\\temp\\someFile.txt"));
        System.out.println(bufferedReader.readLine());

        //Nested high level wrapping
        //Wrapped in BufferedInputStream to improve performance
        //Wrapped as ObjectInputStream to filter the data as Java Objects
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("c:\\temp\\someFile.txt")));
    }

    private void streamBaseClassExample() {
        //4 abstract classes - inputStream, OutputStream, Reader, Writer
        //most classes follow naming convention except PrintStream - OutputStream
        //cannot mix input/output streams with readers/writers

        /**
         * IMPORTANT:
         *  1. class with InputStream/OutputStream - reading/writing binary data
         *  2. class with Reader/Writer - reading/writing characters or strings
         *  3. most but not all classes have corresponding output class
         *  4. low level stream connects directly with source
         *  5. high level stream built on low level with wrapping
         *  6. class with buffered reads/writes data in chunks - improves performance in sequential file systems
         */

        /**
         * Common streams for exam
         * 1. Reader
         * 2. Writer
         * 3. InputStream
         * 4. OutputStream
         * 5. FileInputStream
         * 6. FileOutputStream
         * 7. FileReader
         * 8. FileWriter
         * 9. BufferedReader
         * 10. BufferedWriter
         * 11. ObjectInputStream - deserializes primitive java data types to input stream
         * 12. ObjectOutputStream - serializes  primitive java data types to output stream
         * 13. InputStreamReader - Reads characters from existing InputStream -- not in exam
         * 14. OutputStreamWriter - Writes characters to existing OutputStream -- not in exam
         * 15. PrintStream - Writes formatted representations of Java Objects to binary stream
         * 16. PrintWriter - Writes formatted representations of java objects to text based output stream
         * 17. BufferedInputStream
         * 18. BufferedOutputStream
         * 19. DataInputStream - tailored to read only primitive and string values -- not in exam
         * 20. DataOutputStream - tailored to write only primitive and string values -- not in exam
         */
    }

    private void commonStreamOperations() {
        /**
         * Streams must be closed in finally or try with resources - if not closed
         *  it could lead to memory leaks, locks on the file
         *
         *  Flush - since data is not always written immediately and waits for a cache to be full
         *  before written, you can use flush to persist data
         *      Comes with a cost - noticeable delay in application when called
         *  Do not need to call flush explicitly - close() does this as well
         *
         *  mark(int) and reset() - move stream back to an earlier position,
         *      first call markSupported() to see if it is allowed
         *      e.g. call mark with read ahead limit, read data, then call reset to take you back
         *      to point where you called mark
         *      If you call reset after your mark limit, exception may be thrown(except if buffer is used
         *      which is difficult to give an exact answer for)
         *
         *  skip(long) skips bytes - returns a long - if long is 0 or negative it is the end of the file
         */
    }

    private void fileInputOutputStreamExamples() throws Exception {
        InputStream isWithFilePath = new FileInputStream("c:\\temp\\testFile.txt");
        InputStream isWithFile = new FileInputStream(new File("c:\\temp\\testFile.txt"));
        // call read() method and is terminated when value = -1
        //note read and writes use int instead of bytes

        //output stream is the same as input stream
        //read and write can take byte arrays

    }

    private void bufferedInputOutputStreamExamples() throws Exception {
        //Common to use buffered classes for reading/writing data with byte arrays
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("c:\\temp\\testFile.txt"));
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("c:\\temp\\copy.txt"))
        ) {
            byte[] bytes = new byte[1024];
            int lengthRead;
            //read returns number of bytes read
            //if less than 1024 bytes available, byte array is filled with data from previous read,
            //important to read correct number of bytes
            while((lengthRead = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, lengthRead);
                outputStream.flush();
            }
        }

        // buffered classes allow you to set a value for the buffer - but defaults to a value of the power of 2
    }

    private void fileReaderWriterExamples() throws Exception {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("c:\\temp\\testFile.txt"))) {
            String s;
            while((s = reader.readLine()) != null) {
                data.add(s);
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("c:\\temp\\newFile.txt"))) {
            for (String s : data) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        }
    }

    private void objectInputOutputStreamExamples() throws Exception {
        /**
         * to convert objects to binary data, classes and nested classes must implement Serializable
         * You may mark a field as transient if you do not require it to be serialized
         * Static class members will also be ignored
         * If you use serialVersionUid -it helps with serializing and deserializing as it stores
         * a version - if you update the class then update the ID
         *
         * NOTE : null objects are supported
         */
        class tempClass implements Serializable{
            private static final long serialVersionUID = 1l;
        }

        List<tempClass> tempClasses = new ArrayList<tempClass>();
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("c:\\temp\\testFile.txt")))) {
            while(true) {
                Object object = inputStream.readObject();
                if (object instanceof tempClass) {
                    tempClasses.add((tempClass) object);
                }
            }
        } catch (EOFException e) {
            //file end reached
            //you can use is.available() >0 but this tells you the number of blocks that can be
            //read without blocking the next caller, i.e. it can return 0 if there are still more
            //data to be read
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("c:\\temp\\newFile.txt")))) {
            for (tempClass temp : tempClasses) {
                outputStream.writeObject(temp);
            }
        }
    }

    private void understandingObjectCreation() throws Exception {
        /**
         * EXAM - understand how deserialized object is created
         *  Constructor is not called
         *  Java first calls the first no-arg constructor for the first nonserializable parent class
         *  skipping constructors of any serialized class inbetween.
         *  Static variables are ignored
         */
        Animal animal = new Animal();
        animal.setTestVariable("testVariable");

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("c:\\temp\\testAnimal.data"))
        )) {
            outputStream.writeObject(animal);
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("c:\\temp\\testAnimal.data"))
        )) {
            Object object = inputStream.readObject();
            if (object instanceof Animal) {
                System.out.println(animal);
            }
        }
    }

    private void printStreamWriterExamples() throws Exception {
        /**
         * High level stream classes that write formatted representations of java objects to a text based output
         * stream
         * PrintStream operates on OutputStream
         * PrintWriter operates on Writer
         *
         * Both classes include constructors that can open and write to files directly
         *
         * PrintWriter also allows you pass through an OutputStream in the constructor
         *
         * Mainly convenience classes
         *
         * System.out and System.err uses a PrintStream object
         *
         * EXAM - need to know print(), println(), format() and printf(), checkError() - used to check if there
         * are errors after writing to output
         * These methods do not throw checked exceptions
         */

        PrintWriter printWriter = new PrintWriter("c:\\temp\\printWriter.txt");
        printWriter.print(5); //printwriter method
        printWriter.write(String.valueOf(5)); //writer method

        boolean checkError = printWriter.checkError();

        Animal animal = new Animal();
        animal.setTestVariable("testVar");

        printWriter.print(animal);
        printWriter.println(animal);

        printWriter.format("some string", animal);
        printWriter.printf("some string", animal); // both methods are identical & does not include line breaks

    }

    public static void main(String[] args) throws Exception {
        StreamExamples streamExamples = new StreamExamples();
//        streamExamples.understandingObjectCreation();
        streamExamples.printStreamWriterExamples();
    }
}
