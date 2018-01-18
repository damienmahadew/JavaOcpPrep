package co.za.damien.chapter3.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien.mahadew on 2018-01-17.
 */
public class PuttingItAllTogether {
    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }

    public static void main(String[] args) {
        // creates an arraylist of type A, stored in a variable with an unbounded wildcard
        //any generic type can be reference from here making this okay
        List<?> list1 = new ArrayList<A>();

        //Fine declaration
        List<? extends A> list2 = new ArrayList<A>();
        //the below does not compile as java does not know which class it is
//        list2.add(new A());
//        list2.add(new B());
//        list2.add(new C());

        //Fine declaration
        List<? super A> list3 = new ArrayList<A>();
        list3.add(new A());
        list3.add(new B());
        list3.add(new C());

        // upper bound of B, but you are trying to declare it as A
//        List<? extends B> list4 = new ArrayList<A>();

        //lower bound wildcard which allows reference to B, A and Object
        List<? super B> list5 = new ArrayList<A>();
        list5.add(new B());
        //below does not compile
//        list5.add(new A());
//        list5.add(new Object());

        //Below does not compile - you have to have a solid class in the instantiation of the array
//        List<?> list6 = new ArrayList<? extends A>();

    }

    /**
     *Compiles fine - method specific type parameter, takes in a parameter of List<T> or some subclass
     * and returns a single object
     * Can call it with List<String> or List<Number>
     */
    public static <T> T method1 (List<? extends T> list) {
        return list.get(0);
    }

    /**
     * Does not compile - return type isnt actually a type - dont get to specify a wildcard in the return type
     */
//    public static <T> <? extends T> method2(List<? extends T> list) {
//
//    }

    /**
     * Does not compile - TRICKY - B is also a class, but here its used as a generic type, you cannot instantiate
     * a generic type
     *
     */
//    <B extends A> B method3(List<B> list) {
//        return new B();
//    }

    /**
     * Normal use of generics - pass in List<B> List<A> List<Object>
     */
    static void method4(List<? super B> list) {

    }

    /**
     * Does not compile - tries to use a method type generic with a wildcard - you must have ? as the wildcard   !!!!
     */
//    <X> void method5(List<X super B> list) { }
}