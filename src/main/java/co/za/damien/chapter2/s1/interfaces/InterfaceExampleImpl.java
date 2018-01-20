package co.za.damien.chapter2.s1.interfaces;

/**
 * Created by Damien on 2017-03-25.
 */
public class InterfaceExampleImpl implements InterfaceExample {
    public int getWingSpan() throws Exception {
        return 0;
    }

    public void land() {
        System.out.print("Implementation method");
    }

    public static void main(String[] args) throws Exception {
        InterfaceExample example = new InterfaceExampleImpl();
        example.land();
        System.out.print(example.getWingSpan());
    }
}
