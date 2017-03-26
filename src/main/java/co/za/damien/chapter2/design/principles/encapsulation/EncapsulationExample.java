package co.za.damien.chapter2.design.principles.encapsulation;

/**
 * Encapsulation is the idea of having fields and methods in a class where the methods
 * operate on the data rather than accessing the fields directly
 *
 * It is considered good practice to always use encapsulation in case validation needs to be introduced,
 * then the not much has to change
 */
public class EncapsulationExample {

    private int age;

    private boolean bird;

    private Long entityNo;


    /**
     * Getter for non boolean - add 'get' to name
     */
    public int getAge() {
        return age;
    }

    /**
     * Setters all start with 'set'
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getters for boolean - start with 'is' or 'get'
     * ** NOTE: if you are using a wrapper class - Boolean, then it can only be a 'get'
     */
    public boolean isBird() {
        return bird;
    }

    public void setBird(boolean bird) {
        this.bird = bird;
    }

    /**
     * If the var name is multiple words then you should use the below format
     * @return
     */
    public Long getEntityNo() {
        return entityNo;
    }

    public void setEntityNo(Long entityNo) {
        this.entityNo = entityNo;
    }
}
