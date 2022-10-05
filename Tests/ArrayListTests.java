package Tests;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import ArrayList.ArrayList;

public class ArrayListTests {

    ArrayList<Integer> myList;

    /**
     * Initialize a array list with content ordered Integers 1 - 5 inclusive before every test
     */
    @Before
    public void setupArrayList() {
        myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
    }

    /**
     * Empty the array list after every test
     */
    @After
    public void takedownLinkedList() {
        myList = null;
    }

    /**
     * Check that the initialization of the array list was successful using the get() method
     */
    @Test
    public void testSetupSuccessful() {

        // Check list is not null
        assertNotNull(myList);

        // Check that each entry in the list is equal to its index + 1
        for(int i = 0; i < myList.size(); i++) {
            assertEquals((int)myList.get(i), (long)i + 1);
        }

        // Check that the length of the list is equal to 5
        assertEquals(myList.size(), 5);

    }

    /**
     * Check that the implementation for the array list iterable and iterator is working properly
     * Tests the get method because it is used under the hood by the iterator
     */
    @Test
    public void testArrayListIterator() {

        // For confirming the contents of the array list
        int check = 1;

        // Use a for:each loop, which is unlocked by implementing iterator, to check the same data as the test above
        for(Object o: myList) {
            assertEquals((int)o, check++);
        }

    }

    /**
     * Check that the array list is dynamically sizing when trying to insert into a full underlying array
     * Tests the add() and copyArray() functions
     */
    @Test
    public void testUnderlyingArrayDynamicityViaAddAndCopyArray() {

        // Expected size of the underlying array
        int expectedSize = 1;

        // Re-initialize an empty array list
        myList = new ArrayList<>();

        // Show that the initial underlying is length 1
        assertEquals(myList.sizeUnderlying(), expectedSize);
        myList.add(1);

        // Show that underlying changes only when trying to insert into a full array
        assertEquals(myList.sizeUnderlying(), expectedSize);

        // Insert values 2 - 10 inclusive
        for(int i = 2; i < 11; i++) {

            // Add i to the list
            myList.add(i);

            // If at a duplication point, increase the expected size by a power of 2
            if(i == 2 || i == 3 || i == 5 || i == 9) {
                expectedSize = expectedSize << 1;
            }

            // Check that the underlying size increases at the expected intervals
            assertEquals(myList.sizeUnderlying(), expectedSize);

        }

    }

    /**
     * Tests array list set method which changes the value at a particular index
     */
    @Test
    public void testSetAtValidIndex() {

        // To verify the initial values of the array list
        int check = 1;

        // Use a for:each loop to verify the initial state of the list
        for(Object o: myList) {
            assertEquals((int)o, check++);
        }

        // Verify the list is of length 5
        assertEquals(myList.size(), 5);

        // Save the success status of setting the new value at the specified index
        boolean success = myList.set(Integer.MAX_VALUE, 2);
        
        // Verify the length of the array has not changed
        assertEquals(myList.size(), 5);

        // Verify the set operation was succesful
        assertTrue(success);

        // Expected contents of my:ist
        int[] vals = {1, 2, Integer.MAX_VALUE, 4, 5};

        // Verify the contents of myList match the expected contents
        for(int i = 0; i < myList.size(); i++) {
            assertEquals(vals[i], (int)myList.get(i));
        }

    }

    /**
     * Tests array list set method with 2 indices that should produces failures
     */
    @Test
    public void testSetAtInvalidIndex() {

        // Save the success status of setting the new value at a negative index
        boolean success = myList.set(Integer.MAX_VALUE, -2);

        // Verify the set operation failed
        assertFalse(success);

        // Save the success status of setting the new value at an out-of-bounds index
        success = myList.set(Integer.MAX_VALUE, 5);

        // Verify the operation failed
        assertFalse(success);

    }

    /**
     * Tests that normal case for deleting by object and by index operates properly
     */
    @Test
    public void testDeleteFunction() {

        // Check that each entry in the list is equal to its index + 1
        for(int i = 0; i < myList.size(); i++) {
            assertEquals((int)myList.get(i), (long)i + 1);
        }

        // Delete and store the value at index 2
        int number = (int)myList.delete(2);
        assertEquals(number, 3);
        assertEquals(4, myList.size());

        // Delete and check for status on deleting object 2
        boolean status = myList.delete((Object)2);
        assertTrue(status);
        assertEquals(3, myList.size());

        // Create an array that should mirror the current state of the array list
        int[] expectedArray = {1, 4, 5};

        // Check that the values of the array list equal the values of the above array
        for(int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], (int)myList.get(i));
        }

    }

    /**
     * Test that the delete function gracefully handles indices and elements that are not in the array list
     */
    @Test
    public void testDeleteFunctionOnEdgeCases() {

        // Try to delete an index that doesn't exist
        assertNull(myList.delete(5));

        // Try to delete an object from the list that is not present
        assertFalse(myList.delete((Object)6));

    }

    
}
