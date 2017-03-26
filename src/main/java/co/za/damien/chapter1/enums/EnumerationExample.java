package co.za.damien.chapter1.enums;

/**
 * Contains a fixed set of constants
 */
public enum  EnumerationExample {
    WINTER {
        void print() {
            System.out.print("WINTER");
        }
    };

    abstract void print();
}
