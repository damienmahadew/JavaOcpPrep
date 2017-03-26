package co.za.damien.chapter2.design.patterns.factory;

/**
 * Created by Damien on 2017-03-26.
 */
public class Pellets extends Food {

    public Pellets(int quantity) {
        super(quantity);
    }

    public void consumed() {
        System.out.println("Pellets eaten: " + getQuantity());
    }
}
