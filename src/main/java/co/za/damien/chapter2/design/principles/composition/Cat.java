package co.za.damien.chapter2.design.principles.composition;

/**
 * Sub class cat
 *      demonstrates inheritance
 *          a cat is-a animal
 * The cat object contains an instance of Tail
 *      This is an example of composition
 *          a cat has-a tail
 */
public class Cat extends Animal {

    /**
     * Composition
     */
    private Tail tail;

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }
}
