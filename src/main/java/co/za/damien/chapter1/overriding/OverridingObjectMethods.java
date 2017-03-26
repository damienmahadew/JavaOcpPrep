package co.za.damien.chapter1.overriding;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by Damien on 2017-03-25.
 */
public class OverridingObjectMethods {

    public String name;

    public int age;

    public OverridingObjectMethods(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Using reflection
     * @return
     */
    @Override
    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
        //The line below only prints fields and not name of class and mem location
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * When overriding equals then you should generally override hashCode
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        OverridingObjectMethods overridingObjectMethods = (OverridingObjectMethods) obj;
//        Default way
//        return super.equals(obj);
//            or
//          return this.name.equals((overridingObjectMethods.name);
//        Using apache lang

        //The below compares all fields
//        return EqualsBuilder.reflectionEquals(this, obj);

        //The below does null checks as well, but you can specify which fields to compare
        return new EqualsBuilder().appendSuper(super.equals(obj))
                .append(this.name, overridingObjectMethods.name)
                .append(this.age, overridingObjectMethods.age)
                .isEquals();
    }

    /**
     * Why Override hashCode?
     *      Java stores objects in a map, creating a unique hashCode puts similar objects closer to each other
     *      This results in fast lookups
     * @return
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
