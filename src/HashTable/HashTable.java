package HashTable;

import java.util.Objects;
import ArrayList.ArrayList;

// Concepts learned from: https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/

/**
 * Alternative implementation of the HashTable data structures
 */
public class HashTable<K, V> {

    // Total number of buckets
    int numBuckets;

    // Number of buckets with at least one element
    int size;

    // List of buckets uses my own array list implementation
    ArrayList<HashNode<K, V>> buckets;

    // For testing purposes, may disable the load factor to verify delete only deletes the desired node from a bucket
    boolean disableLoadFactor;

    /**
     * Initialize the bucket list and peripheral values
     */
    public HashTable() {

        // Initialize the buckets
        buckets = new ArrayList<>();

        // Number of elements in the table
        size = 0;

        // Total number of buckets
        numBuckets = 10;

        // Initialize each bucket as null
        for(int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }

        // Default usage should use load factor when resizing table
        disableLoadFactor = false;

    }

    /**
     * Initialize the bucket list and peripheral values
     */
    public HashTable(boolean disableLoadFactor) {

        // Initialize the buckets
        buckets = new ArrayList<>();

        // Number of elements in the table
        size = 0;

        // Total number of buckets
        numBuckets = 10;

        // Initialize each bucket as null
        for(int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }

        // Set whether the load factor bucket multiplication is enabled or not
        this.disableLoadFactor = disableLoadFactor;

    }

    /**
     * Return the number of elements contained in the table
     * @return int: number of elements in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Return whether or not there is an element placed in the table
     * @return boolean: whether or not the table is empty
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Clears the hash table buckets
     * Identical to the constructor
     */
    public void clear() {
        // Initialize the buckets
        buckets = new ArrayList<>();

        // Number of elements in the table
        size = 0;

        // Total number of buckets
        numBuckets = 10;

        // Initialize each bucket as null
        for(int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }
    }

    /**
     * Return the number of buckets available at a given time (for testing only)
     * @return int: number of buckets in the table
     */
    public int getNumberOfBuckets() {
        return numBuckets;
    }

    public void add(K key, V value) {

        // Do not permit null keys or values
        if(key == null || value == null) return;

        // Get the hash code for the key
        int hashCode = getHashCode(key);

        // Get the bucket index this key belongs in
        int index = getIndex(key);

        // Get the first node of the bucket this key belongs to
        HashNode<K, V> head = buckets.get(index);

        // Iterate through the bucket looking for the key provided
        while(head != null) {

            // Once found...
            if(head.getHashCode() == hashCode && head.getKey().equals(key)) {

                // Set a new value associated with the key
                head.setValue(value);

                // Task is done
                return;
            }

            // Iterate to the next node
            head = head.getNextNode();
        }

        // Note another node will be added to the table
        size++;

        // If the node was never found, make it
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);

        // Put it at the beginning of the bucket list
        newNode.setNextNode(buckets.get(index));

        // Set the new node at the proper index of the bucket list
        buckets.set(newNode, index);

        // If the load factor is too large now, remake the bucket list 
        if(getLoadFactor() >= 0.75 && !disableLoadFactor) {

            // Save the current list of buckets in a temporary list
            ArrayList<HashNode<K, V>> temp = buckets;

            // Create a new bucket list that's twice the size of the old one
            buckets = new ArrayList<>();
            int numOldBuckets = numBuckets;
            numBuckets = numBuckets * 2;

            // Must reset the size of the table to prevent load factor from triggering deep recursion
            size = 0;

            // Set each new bucket equal to null
            for(int i = 0; i < numBuckets; i++) {
                buckets.add(null);
            }
             
            // Re-add all of the items previously in the table
            for(int i = 0; i < numOldBuckets; i++) {

                // Head of the bucket
                head = temp.get(i);

                // Re-add each item in the current bucket list
                while(head != null) {

                    // Recursive add call, but this call is guaranteed to be a leaf call 
                    // because the size of the table has not changed
                    add(head.getKey(), head.getValue());
                    head = head.getNextNode();
                }   

            }

        }

    }

    /**
     * Remove the node with a particular key from the table
     * @param key K: key for the node to remove
     * @return V: value contained by the deleted node, or null on failure
     */
    public V remove(K key) {

        // Get the hash code for future checking 
        int hashCode = getHashCode(key);

        // Get the index of the bucket this key would be placed in
        int index = getIndex(key);

        HashNode<K, V> head = buckets.get(index);
        HashNode<K, V> prev = null;

        // Look for the element to remove
        while(head != null) {

            // If the element was found, leave the loop
            if(head.getHashCode() == hashCode && head.getKey().equals(key)) break;

            // Go to the next node in the list
            prev = head;
            head = head.getNextNode();
        }

        // If the node to remove was not found, return null
        if(head == null) return null;

        // If the node to remove was found, save its value to return
        V value = head.getValue();

        // If the node to remove was found at the start of the list
        if(prev == null) {

            // Set the start of the list equal to the next node
            buckets.set(head.getNextNode(), index);

        } 
        
        // Otherwise, set the previous node's next link equal to the node to delete's next link
        else {
            prev.setNextNode(head.getNextNode());
        }

        // Decrement the size of the table by 1 because an element was removed
        size--;

        // Return the value of the deleted node
        return value;

    }

    /**
     * Return the value associated with the parameterized key
     * @param key K: key to search for in the table
     * @return V: value associated with parameterized key
     */
    public V get(K key) {
        
        // Get the hash code for future checking 
        int hashCode = getHashCode(key);

        // Get the index of the bucket this key would be placed in
        int index = getIndex(key);

        // Get the first element of the bucket
        HashNode<K, V> head = buckets.get(index);

        // Iterate through the bucket list...
        while(head != null) {

            // If the key with correct hash code is found, return the value
            if(head.getKey().equals(key) && head.getHashCode() == hashCode) return head.getValue();

            // Otherwise continue iterating
            head = head.getNextNode();
        }

        // Key does not exist in the table, so return null
        return null;

    }

    // ========================== Helper Functions ==========================

    /**
     * Use the java Objects hash code method to get the parameter's hash code
     * @param key K: an object to get the hash code for
     * @return int: the parameter's hash code
     */
    private int getHashCode(K key) {
        return Objects.hashCode(key);
    }

    /**
     * Get the index for this key in the bucket list
     * @param key K: key to calculate the bucket list index for
     * @return int: index in the bucket list for the parameterized key
     */
    private int getIndex(K key) {
        int hashCode = getHashCode(key);
        return Math.abs(hashCode % numBuckets);
    }

    /**
     * Calculate the load factor: number of elements in the table / number of buckets
     * @return double: the load factor
     */
    private double getLoadFactor() {
        return (1.0 * size) / numBuckets;
    }
    
}
