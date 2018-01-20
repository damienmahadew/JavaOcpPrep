package co.za.damien.chapter3.s2.collections.complete;

/**
 * Comparison between collection types
 * Type     Duplicates?         Ordered?            Keys/Value?         Must add/remove in specific order?
 * List     Yes                 Yes                 No                  No
 * Map      Yes(values)         No                  Yes                 No
 * Queue    Yes                 Yes(retrieved       No                  Yes
 *                              in defined order)
 * Set      No                  No                  No                  No
 *
 *
 * Type         Interface               Sorted          Calls hashCode?         Calls CompareTo?
 * ArrayList    List                    No              No                      No
 * ArrayDeque   Queue                   No              No                      No
 * HashMap      Map                     No              Yes                     No
 * HashSet      Set                     No              Yes                     No
 * Hashtable    Map                     No              Yes                     No
 * LinkedList   List, Queue             No              No                      No
 * Stack        List                    No              No                      No
 * TreeMap      Map                     Yes             No                      Yes
 * TreeSet      Set                     Yes             No                      Yes
 * Vector       List                    No              No                      No
 *
 * Data structures that sort do not allow nulls, TreeSet, TreeMap( null values are OK, not keys)
 *
 * ArrayDeque - you cannot use null, as poll uses null as a special return type
 *
 * Hashtable does not allow nulls - too old to give a reason
 *
 */
public class ComparingCollectionTypes {
}
