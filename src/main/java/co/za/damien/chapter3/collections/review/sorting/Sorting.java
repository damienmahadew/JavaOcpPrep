package co.za.damien.chapter3.collections.review.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Damien on 2017-03-26.
 */
public class Sorting {

    public static void sortArray() {
        int[] intArr = {1, 6, 2, 18, 5};
        printArray("Unsorted array", intArr);
        Arrays.sort(intArr);
        printArray("Sorted array", intArr);
    }

    public static void sortList() {
        List<Integer> integerList = Arrays.asList(1,6,18,5);
        System.out.println("Unsorted List : " + integerList);
        Collections.sort(integerList);
        System.out.println("Sorted List : " + integerList);
    }

    private static void printArray(String msg, int[] objects) {
        System.out.println(msg);
        for (int object : objects) {
            System.out.print(object + " - ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        sortArray();
        sortList();
    }
}
