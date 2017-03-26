package co.za.damien.chapter2.design.patterns.factory;

/**
 * Created by Damien on 2017-03-26.
 */
public class Hay extends Food {

    public Hay(int quantity) {
        super(quantity);
    }

    @Override
    public void consumed() {
        System.out.println("Hay consumed :" + getQuantity());
    }
}
