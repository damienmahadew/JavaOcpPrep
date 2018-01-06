package co.za.damien.chapter8.streams;

import java.io.Serializable;

/**
 * Created by damien.mahadew on 2018-01-06.
 */
class Animal implements Serializable {
    private static final long serialVersionUID = 2l;
    private transient String name;
    private transient int age = 10;
    private static char type = 'C';
    private String testVariable;

    {this.age = 14;}

    public Animal() {
        this.name = "Unknown";
        this.age = 12;
        this.type = 'Q';
    }

    public Animal(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTestVariable() {
        return testVariable;
    }

    public void setTestVariable(String testVariable) {
        this.testVariable = testVariable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                ", testVariable='" + testVariable + '\'' +
                '}';
    }
}