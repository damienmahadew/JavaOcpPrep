package co.za.damien.chapter3.collections.review.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Damien on 2017-03-26.
 */
public class ArraysAndArrayLists {

    public static void main(String[] args) {
        /**
         * An object that contains other objects
         *      cannot contain primitives
         */
        List<String> arrayList = new ArrayList<String>();

        arrayList.add("String1");
        arrayList.add("String2");

        /**
         * Built in data structure that contains other objects or primitives
         */
        String[] arrayString = new String[arrayList.size()];
        arrayString[0] = arrayList.get(0);
        arrayString[1] = arrayList.get(1);

        /**
         * Other ways of defining arrays
         */

        String[] arrayDecTwo = {"Value1", "Value2"};
        int lengthOfArrayDecTwo = arrayDecTwo.length;

        /**
         * Converting an array to an List
         *      NOTE: ARRAYLIST will not work here
         */
        List<String> arrayListFromArray = Arrays.asList(arrayDecTwo);

        /**
         * The line below changes the value in the array and the list
         */
        arrayListFromArray.set(1, "Value3");
        //THe line below will not work - as the asList cannot be resized
//        arrayListFromArray.add(2, "Value4");

        System.out.println("ArrayDecTwo : [" + arrayDecTwo[0] + "], [" + arrayDecTwo[1] + "]");
        System.out.println("ArrayListDecTwo :" + arrayListFromArray);



    }


}
