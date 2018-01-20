package co.za.damien.chapter2.s4.design.patterns.builder;

/**
 * Created by Damien on 2017-03-26.
 */
public final class BuilderImmutablePojoExample {

    private final int age;
    private final String name;
    private final String gender;

    public BuilderImmutablePojoExample(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
