package co.za.damien.chapter2.s4.design.patterns.factory;

/**
 * Created by Damien on 2017-03-26.
 */
public class Fish extends Food {

    public Fish(int quantity) {
        super(quantity);
    }

    public void consumed() {
        System.out.println("Fish eaten: " + getQuantity());
    }
}

