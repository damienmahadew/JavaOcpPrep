package co.za.damien.chapter2.s4.design.patterns.immutable;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable - read only objects that can be shared and used by multiple classes
 *      Values of the object must not be modified
 *  The state of an immutable object should not change after it has been created
 *
 *  Thread safe
 *
 *  Go hand in hand with encapsulation
 *          But no setter methods
 *
 *  Common strategy in creating immutable objects for exam
 *      1. use a constructor to set all properties of an object
 *      2. mark all the instance variables private and final
 *      3. do not define any setter methods
 *      4. dont allow referenced mutable objects to be modified and accessed directly
 *          e.g. having a list as a field- rule -> never have mutable objects in an immutable class
 *      5. prevent methods from being overriden
 */
public final class ImmutableExample {

    private final String name;
    private final int age;

    /**
     * Do not use lists etc.
     *      when getList called then you have access to add/remove from the list
     * If you are using lists then never return the reference to that list -- this class has an example of how to use lists
     */
    private final List<String> list;

    public ImmutableExample(String name, int age, List<String> list) {
        this.name = name;
        this.age = age;
        if (list == null) {
            throw new RuntimeException("List is null");
        }
        /**
         * creates a new object so calling method does not have access to the reference
         */
        this.list = new ArrayList<String>(list);
    }

    public int getListSize() {
        return list.size();
    }

    public String getIndex(int index) {
        return list.get(index);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
