package Heap;

/**
 * Minimal interface for implementing a heap
 */
public interface Heap<T extends Comparable<T>> {

	// =========== Public Methods ===========

	/**
	 * Add an item to the heap
	 * @param item T: item to add
	 */
	public void offer(T item);

	/**
	 * Removes and returns the element from the top of the heap
	 * @return T: element at the top
	 */
	public T poll();

	/**
	 * Returns the element from the top of the heap without removal
	 * @return T: element at the top
	 */
	public T peek();

	/**
	 * Returns the number of elements in the heap
	 * @return int
	 */
	public int size();

	/**
	 * Determines if heap is empty
	 * @return boolean: true if heap is empty, else false
	 */
	public boolean isEmpty();

	/**
	 * Deletes the elements inside the heap and returns a new heap with the initial max length
	 */
	public void clear();
    
}
