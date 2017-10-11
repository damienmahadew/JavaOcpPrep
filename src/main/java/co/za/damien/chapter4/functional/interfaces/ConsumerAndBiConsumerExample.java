package co.za.damien.chapter4.functional.interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Consumers are used when you want to manipulate an object but not return anything
 * Biconsumer does the same thing except takes two params - same types can be used
 *  **Contains default methods -- andThen - to nest operations
 */
public class ConsumerAndBiConsumerExample {

    public static void main(String[] args) {
        consumerExample();
        biconsumerExample();
    }

    private static void biconsumerExample() {
        Map<String, Integer> someMap = new HashMap<>();
        BiConsumer<String, Integer> biConsumer1 = someMap::put;
        BiConsumer<String, Integer> biConsumer2 = (k, v) -> someMap.put(k, v); //this value must be effectively final

        biConsumer1.accept("chicken", 1);
        biConsumer2.accept("beef", 0);
        System.out.println("biconsumer1 + biconsumer2 map =" + someMap);
        //biconsumer1 + biconsumer2 map ={chicken=1, beef=0}
    }

    private static void consumerExample() {
        Consumer<String> consumer1 = System.out::println; //java uses the context of input to determine
        //which overloaded method to use
        Consumer<String> consumer2 = s -> System.out.println(s);

        consumer1.accept("testing consumer 1");
        consumer2.accept("testing consumer 2");
    }
}
