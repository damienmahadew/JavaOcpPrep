package co.za.damien.chapter2.s4.design.principles.composition;

/**
 * Super class Animal
 */
public class Animal {

    private int legs;

    private String name;

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
