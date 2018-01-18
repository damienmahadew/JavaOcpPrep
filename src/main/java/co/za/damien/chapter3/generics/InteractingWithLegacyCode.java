package co.za.damien.chapter3.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Legacy code - java 1.4 or lower
 * Collections written without generics - raw collections
 *
 * Java presents a compiler warning when using raw collections e.g. List list = new ArrayList()
 *
 * EXAM: need to know how to identify when a compiler warning will occur
 * Not in exam - know how to run the command to check : javac -Xlint:unchecked *.java
 */
public class InteractingWithLegacyCode {

    private void unboxingExamples() {
        /**
         * Unboxing fails at compile time - java does not know that the list contains integers,
         * it just knows we have an object and an object cannot be unboxed to int
         */
        List list = new ArrayList();
//        int result = list.get(0);
    }
}
