package co.za.damien.chapter3.collections.review.searching;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Damien on 2017-03-26.
 */
public class Searching {

    public static void searchArray() {
        int[] intArr = {1, 6, 18, 5};
        printArray("Unsorted array", intArr);
        Arrays.sort(intArr);
        printArray("Sorted array", intArr);

        System.out.println();
        System.out.println("Arrays.binSearch(6) = " + Arrays.binarySearch(intArr, 6));
        /**
         * If bin search cannot find the number, it returns the negated position of where the number should be and adds (-1)
         */
        System.out.println("Arrays.binSearch(12) = " + Arrays.binarySearch(intArr, 12));
    }

    private static void searchList() {
        List<Integer> integerList = Arrays.asList(1,6,18,5);
        System.out.println("Unsorted List : " + integerList);
        Collections.sort(integerList);
        System.out.println("Sorted List : " + integerList);

        System.out.println("Collections.binarySearch(integerList, 6) -" + Collections.binarySearch(integerList, 6)); // 0
        System.out.println("Collections.binarySearch(integerList, 2) -" + Collections.binarySearch(integerList, 2)); // -1
    }


    private static void printArray(String msg, int[] objects) {
        System.out.println(msg);
        for (int object : objects) {
            System.out.print(object + " - ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        searchArray();
        searchList();
    }

}
