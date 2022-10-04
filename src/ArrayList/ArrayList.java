package ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * Alternative ArrayList implementation
 */
public class ArrayList<E> implements Iterable<Object>{

    // Underlying list that stores the data
    private Object[] data; 

    // Length of the underlying list
    private int arrMaxCapacity;

    // Number of elements in the underlying list
    private int length;

    /**
     * Initialize an array list with 
     */
    public ArrayList() {
        arrMaxCapacity = 1;
        length = 0;
        data = new Object[arrMaxCapacity];
    }

    /**
     * Add a new element to the array list
     * @param element E: New element of generic type to put into the array
     */
    public void add(E element) {

        // If the current array is full, increase the size and make a copy
        if(length == arrMaxCapacity) {
            arrMaxCapacity = arrMaxCapacity << 1;
            copyArray(arrMaxCapacity);
        }

        // Add the element specified to the array and increment the length by 1
        data[length++] = element;

    }

    /**
     * Create a new array double the size of the existing array and copy the old elements in order
     * @param newSize int: Equal to double the size of the old array
     */
    public void copyArray(int newSize) {
        data = Arrays.copyOf(data, newSize);
    }

    /**
     * Get the object at the given index
     * @param n int: index at which to grab the object from
     * @return Object: object at index n in the array
     */
    public Object get(int n) {
        if(n < length || n >= 0) {
            return data[n];
        }
        return null;
    }

    /**
     * Remove the object from index n and return it
     * @param n int: index of the element to remove
     * @return Object: value at index n
     */
    public Object delete(int n) {

        // If a valid index was entered
        if(n >= 0 && n < length) {

            // Grab the object at n
            Object o = data[n];

            // Nullify the spot
            data[n] = null;

            // Shift down the following elements by 1 space
            for(int i = n; i < length - 1; i++) {
                data[i] = data[i + 1];
                data[i + 1] = null;
            }

            // Decrement the length of the list by 1
            length--;

            // Return the deleted object
            return o;
        }

        // Return null if invalid index is entered
        else return null;
    }

    /**
     * Remove the first occurance of the object from the array list
     * @param o Object: object to remove
     * @return boolean: true if successful, false if failure
     */
    public boolean delete(Object o) {

        // Iterate through the whole list and delete the first occurance of the parameterized object
        for(int i = 0; i < length; i++) {

            // If an instance of the parameterized object is found
            if(data[i].equals(o)) {

                // Shift every element after the found object back one position
                for(int j = i; j < length; j++) {
                    data[j] = data[j + 1];
                    data[j + 1] = null;
                }
                
                // Decrement the length of the list by 1
                length--;

                // Return success
                return true;
            }
        }

        // Return failure becuase no equivalent element was found
        return false;
        
    }

    /**
     * Return the amount of elements in the array
     * @return int: number of elements in the array
     */
    public int size() {
        return length;
    }

    /**
     * Return the length of the underlying array
     * @return int: length of the underlying array
     */
    public int sizeUnderlying() {
        return arrMaxCapacity;
    }

    /**
     * Iterator implementation to unlock for:each for array list and other benefits
     */
    class ArrayListIterator implements Iterator<Object> {

        // Imagine this is initialized to iterate from the beginning of the array
        int current = 0;

        // If not at the end of the array, return true
        @Override
        public boolean hasNext() {
            return current < ArrayList.this.length;
        }

        // If not at the end of the array, return the current element
        @Override
        public Object next() {
            if(hasNext()) {
                return ArrayList.this.get(current++);
            }

            // If at the end of the array, return this exception as required by Java
            throw new NoSuchElementException();
        }
        
    }

    // Return an instance of the iterator class defined above
    @Override
    public Iterator<Object> iterator() {
        return new ArrayListIterator();
    }

}
