package Heap;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> implements Heap<T> {

    // Allow for 16 elements in heap since index 0 is not used
    private static final int INITIAL_HEAP_SIZE = 17;

    // Store the heap data as an array
    private T[] data;

    // Track the number of elements in the heap
    private int length;

    // Unlocks some methods used in unit testing
    private boolean testMode;

    /**
     * Constructor for the min heap, start off with no elements
     */
    public MinHeap() {
        data = (T[]) new Comparable[INITIAL_HEAP_SIZE];
        length = 0;
        this.testMode = false;
    }

    // =============== Test Methods ===============

    /**
     * Constructor for the min heap, start off with no elements
     */
    public MinHeap(boolean testMode) {
        data = (T[]) new Comparable[INITIAL_HEAP_SIZE];
        length = 0;
        this.testMode = testMode;
    }

    /**
     * Return the array of data if in test mode. Otherwise an empty array
     * @return T[]: the array containing the heap data
     */
    public T[] getData() {
        if(!testMode) return (T[]) new Comparable[0];
        return data;
    }

    /**
     * Determines if the heap is a min-heap or not
     * @param index int: index from where to start scanning the heap
     * @return boolean: true if heap is a valid min heap, otherwise false
     */
    public boolean isMinBinaryTree(int index) {
        
        boolean valid = true;

        if(hasLeftChild(index)) {
            valid = isMinBinaryTree(leftChildIndex(index));
            valid = valid && (data[index].compareTo(data[leftChildIndex(index)]) <= 0);
        }
        if(!valid) return valid;
        if(hasRightChild(index)) {
            valid = isMinBinaryTree(rightChildIndex(index));
            valid = valid && (data[index].compareTo(data[rightChildIndex(index)]) <= 0);
        }
        return valid;
    }

    /**
     * Determines if the heap is a max-heap or not
     * @param index int: index from where to start scanning the heap
     * @return boolean: true if heap is a valid max heap, otherwise false
     */
    public boolean isMaxBinaryTree(int index) {
        
        boolean valid = true;

        if(hasLeftChild(index)) {
            valid = isMaxBinaryTree(leftChildIndex(index));
            valid = valid && (data[index].compareTo(data[leftChildIndex(index)]) >= 0);
        }
        if(!valid) return valid;
        if(hasRightChild(index)) {
            valid = isMaxBinaryTree(rightChildIndex(index));
            valid = valid && (data[index].compareTo(data[rightChildIndex(index)]) >= 0);
        }
        return valid;
    }

    // =============== Public Methods ===============

    /**
     * Add an element to the heap while maintaining the min heap's properties
     */
    @Override
    public void offer(T item) {

        // If max capacity, add more space to heap
        if(length >= data.length - 1) addMoreSpaceToHeap();

        // Add the new item to the bottom of the heap
        data[++length] = item;

        // Put the new element in the appropriate position
        propagateUp();
        
    }

    /**
     * Return and remove the top element of the heap
     */
    @Override
    public T poll() {

        // Save top element
        T min = peek();

        // Put it to the bottom-rightmost position in the heap
        swap(1, length);

        // Set it to null and decrement the length of the heap
        data[length--] = null;
        
        // Propagate the previously largest element to a valid position
        propagateDown();

        // Return min value
        return min;
    }

    /**
     * Return the top element of the heap without removing it
     */
    @Override
    public T peek() {
        if(isEmpty()) return null;
        return data[1];
    }

    /**
     * Return the number of elements in the heap
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * Return true if the heap is empty, otherwise false;
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Reset the heap to its initial state
     * Identical to the constructor
     */
    @Override
    public void clear() {
        data = (T[]) new Comparable[INITIAL_HEAP_SIZE];
        length = 0;
    }

    // =============== Private Methods ===============

    /**
     * Move a small element up the heap to restore the min-heap property
     */
    public void propagateUp() {

        // Element to propagate is always at the end 
        int index = length;
        
        // While the min-heap property does not hold
        while(hasParent(index) && data[parentIndex(index)].compareTo(data[index]) > 0) {

            // Move the bad element further up the heap
            swap(parentIndex(index), index);

            // Save the index of the bad element
            index = parentIndex(index);
        }
    }

    /**
     * Move the root element down the heap to restore the min-heap property
     */
    public void propagateDown() {
        
        // Save the root index
        int index = 1;

        // Always fill left to right, so while a left index exists
        while(hasLeftChild(index)) {

            // Save the index of the smaller value between the left and right children
            int smallest = leftChildIndex(index);

            // Determine if the right child is smaller
            if(hasRightChild(index)) {
                smallest = data[smallest].compareTo(data[rightChildIndex(index)]) < 0 ? smallest : rightChildIndex(index);
            }
            
            // If the min-heap property is violated between the smallest and index, swap them
            if(data[index].compareTo(data[smallest]) > 0) swap(index, smallest);

            // Otherwise min-heap is valid
            else break;
            
            // Set the index to the position where its value is post-swap 
            index = smallest;
        }
    }

    /**
     * Return the index for the parent of a given node
     * @param index int: index for which node to get the parent of
     * @return int: index of the parameter's parent
     */
    private int parentIndex(int index) {
        return index / 2;
    }

    /**
     * Return the index for the left child of any given node
     * @param index int: index of parent
     * @return int: index of left child (may not exist if length < leftChildIndex)
     */
    private int leftChildIndex(int index) {
        return index * 2;
    }

    /**
     * Return the index for the right child of any given node
     * @param index int: index of parent
     * @return int: index of right child (may not exist if length < rightChildIndex)
     */
    private int rightChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * Return true if the parameterized index has a parent node in the heap
     * @param index int: node to determine if a parent exists
     * @return boolean: true if has parent, false otherwise
     */
    private boolean hasParent(int index) {
        return index > 1;
    }

    /**
     * Return true if the parameterized index has a left child in the heap
     * @param index int: node to determine if a left child exists
     * @return boolean: true if has a left child, false otherwise
     */
    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) <= length;
    }

    /**
     * Return true if the parameterized index has a right child in the heap
     * @param index int: node to determine if a right child exists
     * @return boolean: true if has a right child, false otherwise
     */
    private boolean hasRightChild(int index) {
        return rightChildIndex(index) <= length;
    }

    /**
     * Swap two elements in the data array
     * @param i int: index of first object
     * @param j int: index of second object
     */
    private void swap(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Double the amount of allocated space for the heap
     */
    private void addMoreSpaceToHeap() {
        data = Arrays.copyOf(data, 2 * data.length);
    }
    
}
