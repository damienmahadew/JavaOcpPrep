package co.za.damien.chapter2.design.patterns.factory;

/**
 * Created by Damien on 2017-03-26.
 */
public abstract class Food {

    private int quantity;

    public Food(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract void consumed();
}
