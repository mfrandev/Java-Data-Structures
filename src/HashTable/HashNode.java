package HashTable;

public class HashNode<K, V> {

    // Node key
    private K key;

    // Node value
    private V value;

    // Hash code for this node
    private int hashCode;

    // Address for the next node in the list
    private HashNode<K, V> nextNode;
    
    /**
     * Constructor for a node in the HashTable bucket list
     * @param key K: identifier for this node
     * @param value V: data this node contains
     * @param hashCode int: integer value for the index in the bucket list
     */
    public HashNode(K key, V value, int hashCode) {
       setKey(key);
       setValue(value);
       setHashCode(hashCode);
    }

    /**
     * Return this node's key
     * @return K: key of this node
     */
    public K getKey() {
        return key;
    }

    /**
     * Set the key for this node
     * @param key K: key for this node
     */
    private void setKey(K key) {
        this.key = key;
    }

    /**
     * Return the data this node contains
     * @return V: data this node stores
     */
    public V getValue() {
        return value;
    }

    /**
     * Set the data this node contains
     * @param value V: value for this node
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Return the index for this node
     * @return int: index for this node
     */
    public int getHashCode() {
        return hashCode;
    }

    /**
     * Save the index for this node in the bucket list
     * @param hashCode int: index for this node
     */
    private void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * Return the next node in the linked list
     * @return HashNode<K, V>: next node in the next list
     */
    public HashNode<K, V> getNextNode() {
        return nextNode;
    }

    /**
     * Set the next node in the bucket (public becuase not done at node construction)
     * @param nextNode HashNode<K, V>: next node in the list
     */
    public void setNextNode(HashNode<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    

}
