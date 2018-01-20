package co.za.damien.chapter2.s4.design.patterns.builder;

/**
 * Pattern to set numerous values of an object upon creation
 *
 * Motivation:
 *      Having fields in a constructor works until, more variables are introduced and then the classes
 *      using this constructor has to make the changes as well.
 *      Using multiple constructors could work but then the class becomes over crowded.
 *      Having 50 variables in a constructor is also not ideal
 *          This is called - 'telescoping constructor anti-pattern'
 *          An anti pattern is a common solution to a reoccurring problem that tends to lead to unmanageable code
 *          Minor damages are done each time a small solution is implemented to do a fix
 *      Can use setter methods but these do not work well with immutable objects
 *
 * The builder pattern is often used with immutable objects as it has no setters, but can also be used with mutable ones
 * Advantage - leads to far more maintainable code
 *
 * This builder class can either be a class in the same package, or created as a static inner class
 */
public class BuilderPatternExample {
    private int age;
    private String name;
    private String gender;

    public BuilderPatternExample setAge(int age) {
        this.age = age;
        return this;
    }

    public BuilderPatternExample setName(String name) {
        this.name = name;
        return this;
    }

    public BuilderPatternExample setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public BuilderImmutablePojoExample build() {
        /**
         * You can validate all objects before creating the actual object
         */
        return new BuilderImmutablePojoExample(age, name, gender);
    }

    public static void main(String[] args) {
        BuilderImmutablePojoExample example = new BuilderPatternExample()
                .setAge(1)
                .setName("Name")
                .setGender("M")
                .build();
    }
}
