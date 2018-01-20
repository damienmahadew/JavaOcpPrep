package co.za.damien.chapter3.s2.collections.complete;

/**
 * A map is a collection that maps keys to values, with no duplicate keys allowed
 *
 * Map is not a subclass of the Collection interface, it is considered part of the java collections framework
 * THey are treated differently because they need different options as they work with key value pairs
 *
 * 4 implementations
 * HashMap - stores keys in a hash table -uses the hashCode - adding elements and retrieving elements = constant time
 * But lose order, you can use LinkedHashMap if you want to keep the order
 *
 * TreeMap - sorted always - but checking if an element is present and adding are O(logn)
 *
 * Hashtable - vector - old and thread safe and wont be expected to use it - old because it has a lower t
 *
 * ArraysList is to Vectors as HashMap is to Hashtable
 *
 * Method                                   Description
 * void clear()                             removes all the keys and values from the map
 * boolean isEmpty()
 * int size()
 * V get(Object key)                        returns value or null
 * V put(K key, V value)                    Adds or replaces a key value pair, returns previous value or null
 * V remove(Object key)                     removes and returns value or null
 * boolean containsKey(Object key)          returns whether a key is in a map
 * boolean containsValue(Object value)      returns whether value is in the map
 * Set<K> keySet()                          returns set of all the keys
 * Collection<V> values()                   returns a collection of all the values
 *
 */
public class MapExamples {
}
