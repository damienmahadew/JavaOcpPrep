package co.za.damien.chapter4.lambdas;

/**
 * Lambdas use the same access rules as inner classes
 *  can access static variables, instance variables, effectively final local variables and eff final
 *  method parameters
 */

interface SomeInterface {
    String doSomething();
}

public class LambdasExample {

    private String instanceVariable = "instanceVariable";

    private static final String staticVariable = "staticVariable";

    public void testAll(boolean methodParam) {
        String effectivelyFinalLocalVariable = "effectivelyFinalLocalVariable";

        useInterface(() -> instanceVariable);
        useInterface(() -> staticVariable);
        useInterface(() -> methodParam ? "methodParamWorks" : "methodParamDoesNotWork");
        useInterface(() -> effectivelyFinalLocalVariable);
    }

    public void useInterface(SomeInterface someInterface) {
        System.out.println(someInterface.doSomething());
    }

    public static void main(String[] args) {
        LambdasExample lambdasExample = new LambdasExample();
        lambdasExample.testAll(true);
    }
}
