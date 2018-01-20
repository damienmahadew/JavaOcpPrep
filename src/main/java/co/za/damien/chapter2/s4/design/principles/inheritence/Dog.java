package co.za.damien.chapter2.s4.design.principles.inheritence;

/**
 * Sub class Dog
 * Example of an is-a relationship
 *      Dos is a animal
 *          Inherits all properties of animal
 */
public class Dog extends Animal {

    public void bark() {
        System.out.println("Woof");
    }
}
