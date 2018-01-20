package co.za.damien.chapter2.s2.functional.programming.lambdas;

/**
 * This is an example of using Lambda expressions with functional programming
 *      Use a variable e.g. b
 *          b -> b.canHop();
 *
 *          this means that you are passing b.canHop() as the body of the function 'test' in the LambdaFunctionalExampleInterface
 */
public class LambdaFunctionalExampleMain {

    private static void print(Animal animal, LambdaFunctionalExampleInterface trait) {
        if(trait.test(animal)) {
            System.out.println(animal);
        }
    }

    public static void main(String[] args) {
        /**
         * a and b has to be Animal, as the LambdaFunctionalExampleInterface expects an animal
         *
         * the next two lines are equivalent
         */
        print(new Animal("fish", false, true), b -> b.canHop() && b.canSwim());

        /**
         * Parenthesis can be omitted if there is only one input and the data type is not specified
         *
         * If one data type is specified then all must be specified
         *
         * Cannot redeclare a variable of the same name, but a new variable of a dif name can be used
         *
         * When you add braces in the body of the function, each statement must be terminated by a ;
         *
         * Example with 0 params
         *
         *  () -> true;
         *  takes in 0 params and always returns true
         */
        print(new Animal("kangaroo", true, false), (Animal a) -> {return  a.canHop();});


        /**
         * Left of the -> is the input parameters
         *
         * Right of the -> is the body of the lambda function
         */
    }
}
