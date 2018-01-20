package co.za.damien.chapter2.s4.design.patterns.singleton;

/**
 * Singleton pattern
 *      Only one instance of this is created in memory, shareable by all classes and threads in the application
 *   May improve performance, by loading data once
 */
public class SingletonExample {

    private int someVariable;

    /**
     * Private constructor does not allow any other class to create a new instance of this object
     *      This also implicitly marks the class as final because subclasses cannot call super() when constucting itself as
     *      the superclass is marked private
     */
    private SingletonExample() {

    }

    private static final SingletonExample instance = new SingletonExample();

    /**
     * This method will be called to get a reference to the one instance of this object
     * @return
     */
    public static SingletonExample getInstance() {
        return instance;
    }

    /**
     * Lazy initialization - not thread safe
     *      To ensure thread safety then you would add synchronized
     * May introduce a time delay when this resource is needed

            public static SingletonExample getInstance() {
                if (instance == null) {
                    instance = new SingletonExample();
                }
                return instance;
            }
     */

    /**
     * A complete thread safe usage in real world applications
     *
     *
        private static volatile SingletonExample instance;
         public static SingletonExample getInstance() {
             if (instance == null) {
                 synchronized (SingletonExample.class) {
                 if (instance == null) {
                     instance = new SingletonExample();
                     }
                 }
             }
             return instance;
         }
     */



    public synchronized void addToSomeVariable(int someNumber) {
        someVariable += someNumber;
    }

    public synchronized int getSomeVariable() {
        return someVariable;
    }
}
