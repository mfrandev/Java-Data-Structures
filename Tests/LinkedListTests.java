package Tests;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import LinkedList.LinkedList;
import LinkedList.Node;

public class LinkedListTests {

    LinkedList<Integer> myList;

    /**
     * Initialize a linked list with content ordered Integers 1 - 5 inclusive before every test
     */
    @Before
    public void setupLinkedList() {
        myList = new LinkedList<>();
        myList.addNode(1);
        myList.addNode(2);
        myList.addNode(3);
        myList.addNode(4);
        myList.addNode(5);
    }

    /**
     * Empty the linked list after every test
     */
    @After
    public void takedownLinkedList() {
        myList.clearList();
    }

    /**
     * Test the the intended initial state of the linked list exists
     */
    @Test
    public void testSetupSuccessful() {

        // Assert that the linked list is not null
        assertNotNull(myList);

        // Check the length of the list is 5
        assertEquals(myList.getLength(), 5);

        // Get the first element of the list
        Node<Integer> head = myList.getHead();

        // Assert the first node is 1
        assertEquals((int)head.getContent(), 1);

        // Check the rest of the nodes and that they contain the content 2-5 inclusive
        for(int i = 2; i < 6; i++) {
            head = head.getNextNode();
            assertEquals((int)head.getContent(), i);
        }
    }

    /**
     * Test that the linked list's addNode function adds a new node to the end of the list
     */
    @Test
    public void testAddNewNode() {

        // Add a new node to the end of the linked list
        myList.addNode(6);

        // Assert the list is 6 elements long (5 from initialization + 1 new node)
        assertEquals(myList.getLength(), 6);

        // Get the final node of the list
        Node<Integer> end = myList.getTail();

        // Check the content of the last node is equal to 6
        assertEquals((int)end.getContent(), 6);

    }

    /**
     * Test that the linked list's addNode function works correctly when the list is empty
     */
    @Test
    public void testAddNewNodeToEmptyList() {

        // Clear the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        // Add a new node to the end of the linked list
        myList.addNode(1);

        // Assert the list is 6 elements long (5 from initialization + 1 new node)
        assertEquals(myList.getLength(), 1);

        // Get the head and tail of the list
        Node<Integer> start = myList.getHead();
        Node<Integer> end = myList.getTail();
        

        // Check the content of the last node is equal to 6
        assertEquals((int)end.getContent(), (int)start.getContent());

    }

    /**
     * Test that the linked list's remove function can successfully remove a node from the middle of the list
     */
    @Test
    public void testRemoveMiddleNode() {

        // Delete the last node of the linked list by entering its content
        boolean success = myList.remove(3);

        // Assert the list is 4 elements long (5 from initialization - 1 deleted nodes)
        assertEquals(myList.getLength(), 4);

        // Verify return value from remove
        assertTrue(success);

        // Check the content of the first node is equal to 1
        assertEquals((int)myList.at(0), 1);

        // Check the content of the second node is equal to 2
        assertEquals((int)myList.at(1), 2);

        // Check the content of the third node is equal to 4
        assertEquals((int)myList.at(2), 4);
        
        // Check the content of the fourth node is equal to 5
        assertEquals((int)myList.at(3), 5);

    }

    /**
     * Test that the linked list's remove function removes all nodes with the parameterized content
     */
    @Test
    public void testRemoveFirstNode() {

        // Delete the last node of the linked list by entering its content
        boolean success = myList.remove(1);

        // Assert the list is 4 elements long (5 from initialization - 1 deleted nodes)
        assertEquals(myList.getLength(), 4);

        // Verify return value from remove
        assertTrue(success);

        // Check the content of the first node is equal to 2
        assertEquals((int)myList.at(0), 2);

        // Check the content of the second node is equal to 3
        assertEquals((int)myList.at(1), 3);

        // Check the content of the third node is equal to 4
        assertEquals((int)myList.at(2), 4);
        
        // Check the content of the fourth node is equal to 5
        assertEquals((int)myList.at(3), 5);

    }

    /**
     * Test that the linked list's remove function removes the last node
     */
    @Test
    public void testRemoveLastNode() {

        // Delete the last node of the linked list by entering its content
        boolean success = myList.remove(5);

        // Assert the list is 4 elements long (5 from initialization - 1 deleted nodes)
        assertEquals(myList.getLength(), 4);

        // Verify return value from remove
        assertTrue(success);

        // Check the content of the first node is equal to 1
        assertEquals((int)myList.at(0), 1);

        // Check the content of the second node is equal to 2
        assertEquals((int)myList.at(1), 2);

        // Check the content of the third node is equal to 3
        assertEquals((int)myList.at(2), 3);
        
        // Check the content of the fourth node is equal to 4
        assertEquals((int)myList.at(3), 4);

    }

    /**
     * Test that the linked list's remove function removes the first occurance of a specified parameter
     */
    @Test
    public void testRemoveFirstOccurance() {

        // Add a node with the content 2
        myList.addNode(2);

        // Check there are 6 elements in the list
        assertEquals(myList.getLength(), 6);

        // Delete the first occurance of 2 in the linked list
        boolean success = myList.remove(2);

        // Assert the list is 5 elements long (5 from initialization + 1 node added - 1 deleted nodes)
        assertEquals(myList.getLength(), 5);

        // Verify return value from remove
        assertTrue(success);

        // Check the content of the first node is equal to 1
        assertEquals((int)myList.at(0), 1);

        // Check the content of the second node is equal to 3
        assertEquals((int)myList.at(1), 3);

        // Check the content of the third node is equal to 4
        assertEquals((int)myList.at(2), 4);
        
        // Check the content of the fourth node is equal to 5
        assertEquals((int)myList.at(3), 5);

        // Check the content of the fifth node is equal to 2
        assertEquals((int)myList.at(4), 2);

        // Check the content of the sixth node is null
        assertNull(myList.at(5));

    }

    /**
     * Test that the linked list's remove function does nothing if the parameterized value is not in the list
     */
    @Test
    public void testRemoveWhenContentIsNotInList() {

        // Delete the last two nodes of the linked list by entering its content
        boolean success = myList.remove(6);

        // Assert the list is 5 elements long (5 from initialization - 0 deleted nodes)
        assertEquals(myList.getLength(), 5);

        // Verify return value from remove
        assertFalse(success);

        // Get the final node of the list
        Node<Integer> end = myList.getTail();

        // Check the content of the last node is equal to 4
        assertEquals((int)end.getContent(), 5);

    }

    /**
     * Test that the linked list's remove function does not give null pointer exception when list is empty
     */
    @Test
    public void testRemoveOnEmptyList() {

        // Empty the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        boolean success = myList.remove(1);

        // Check the that the operation failed
        assertFalse(success);

    }

    /**
     * Tests that list adequately handles deleting a node from the middle of the list (non-negative index)
     */
    @Test
    public void testDeleteNodeAtIndexForward() {

        // Delete the node at the middle of the list
        boolean success = myList.deleteNodeAtIndex(2);

        // Check the status code returned is success
        assertTrue(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 4);

        // Check first element is same
        assertEquals((int)myList.at(0), 1);

        // Check second element is same
        assertEquals((int)myList.at(1), 2);

        // Check third element now contains 4
        assertEquals((int)myList.at(2), 4);

        // Check fourth element now contains 5
        assertEquals((int)myList.at(3), 5);

        // Check accessing index 4 returns null
        assertNull(myList.at(4));
    }

    /**
     * Tests that list adequately handles deleting a node from the start of the list (non-negative index)
     */
    @Test
    public void testDeleteNodeAtIndexZeroForward() {

        // Delete the node at the start of the list
        boolean success = myList.deleteNodeAtIndex(0);

        // Check the status code returned is success
        assertTrue(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 4);

        // Check first element is now 2
        assertEquals((int)myList.at(0), 2);

        // Check second element is now 3
        assertEquals((int)myList.at(1), 3);

        // Check third element is now 4
        assertEquals((int)myList.at(2), 4);

        // Check fourth element is now 5
        assertEquals((int)myList.at(3), 5);

        // Check accessing index 4 returns null
        assertNull(myList.at(4));
    }

    /**
     * Tests that list adequately handles deleting a node from the end of the list (non-negative index)
     */
    @Test
    public void testDeleteNodeAtIndexLengthMinusOneForward() {

        // Delete the node at the end of the list
        boolean success = myList.deleteNodeAtIndex(myList.getLength() - 1);

        // Check the status code returned is success
        assertTrue(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 4);

        // Check first element is the same
        assertEquals((int)myList.at(0), 1);

        // Check second element is the same
        assertEquals((int)myList.at(1), 2);

        // Check third element is the same
        assertEquals((int)myList.at(2), 3);

        // Check fourth element is the same
        assertEquals((int)myList.at(3), 4);

        // Check accessing index 4 returns null
        assertNull(myList.at(4));
    }

    /**
     * Tests that list adequately handles deleting a node from an empty list (non-negative index)
     */
    @Test
    public void testDeleteNodeAtIndexEmptyListForward() {

        // Empty the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        // Delete the node at the start of the list
        boolean success = myList.deleteNodeAtIndex(0);

        // Check the status code returned is success
        assertFalse(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 0);

        // Check accessing index 4 returns null
        assertNull(myList.at(0));
    }

    /**
     * Tests that list adequately handles an index that is too large (non-negative index)
     */
    @Test
    public void testDeleteNodeAtIndexBadIndexForward() {

        // Delete a node at non-existent index
        boolean success = myList.deleteNodeAtIndex(6);

        // Check the status code returned is success
        assertFalse(success);

        // Check that the length of the list is 5
        assertEquals(myList.getLength(), 5);

        // Check accessing index 6 returns null
        assertNull(myList.at(6));
    }

    /**
     * Tests that list adequately handles deleting a node from the middle of the list (negative index)
     */
    @Test
    public void testDeleteNodeAtIndexBackwards() {

        // Delete the node at the middle of the list
        boolean success = myList.deleteNodeAtIndex(-3);

        // Check the status code returned is success
        assertTrue(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 4);

        // Check first element is same
        assertEquals((int)myList.at(0), 1);

        // Check second element is same
        assertEquals((int)myList.at(1), 2);

        // Check third element now contains 4
        assertEquals((int)myList.at(2), 4);

        // Check fourth element now contains 5
        assertEquals((int)myList.at(3), 5);

        // Check accessing index 4 returns null
        assertNull(myList.at(4));
    }

    /**
     * Tests that list adequately handles deleting a node from the end of the list (negative index)
     */
    @Test
    public void testDeleteNodeAtIndexNegative1Backward() {

        // Delete the node at the end of the list
        boolean success = myList.deleteNodeAtIndex(-1);

        // Check the status code returned is success
        assertTrue(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 4);

        // Check first element is the same
        assertEquals((int)myList.at(0), 1);

        // Check second element is the same
        assertEquals((int)myList.at(1), 2);

        // Check third element is the same
        assertEquals((int)myList.at(2), 3);

        // Check fourth element is the same
        assertEquals((int)myList.at(3), 4);

        // Check accessing index 4 returns null
        assertNull(myList.at(4));
    }

    /**
     * Tests that list adequately handles deleting a node from the start of the list (negative index)
     */
    @Test
    public void testDeleteNodeAtIndexNegativeLengthBackward() {

        // Delete the node at the start of the list
        boolean success = myList.deleteNodeAtIndex(-myList.getLength());

        // Check the status code returned is success
        assertTrue(success);

        // Check that the length of the list is 4
        assertEquals(myList.getLength(), 4);

        // Check first element is now 2
        assertEquals((int)myList.at(0), 2);

        // Check second element is now 3
        assertEquals((int)myList.at(1), 3);

        // Check third element is now 4
        assertEquals((int)myList.at(2), 4);

        // Check fourth element is now 5
        assertEquals((int)myList.at(3), 5);

        // Check accessing index 4 returns null
        assertNull(myList.at(4));
    }

    /**
     * Tests that list adequately handles deleting a node from an empty list (negative index)
     */
    @Test
    public void testDeleteNodeAtIndexEmptyListBackward() {

        // Empty the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        // Delete the node at the end of the list
        boolean success = myList.deleteNodeAtIndex(-1);

        // Check the status code returned is failure
        assertFalse(success);

        // Check accessing index -1 returns null
        assertNull(myList.at(-1));
    }

    /**
     * Tests that list adequately handles an index that is too small (negative index)
     */
    @Test
    public void testDeleteNodeAtIndexBadIndexBackward() {

        // Delete node at a non-exstent index
        boolean success = myList.deleteNodeAtIndex(-6);

        // Check the status code returned is success
        assertFalse(success);

        // Check that the length of the list is 5
        assertEquals(myList.getLength(), 5);

        // Check accessing index -6 returns null
        assertNull(myList.at(-6));
    }

    /**
     * Test that the linked list can detect whether or not it contains a specific element
     */
    @Test
    public void testContains() {

        // Check that list contains all numbers 1 through length of the list inclusive
        for(int i = 1; i < myList.getLength() + 1; i++) {
            assertTrue(myList.contains(i));
        }

        // Check that the list does not contain one number higher than the length of the list
        assertFalse(myList.contains(myList.getLength() + 1));
    }

    /**
     * Test that the linked list gracefully handles an empty list
     */
    @Test
    public void testContainsEmptyList() {

        // Empty the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        // Check that the list does not contain the number 0
        assertFalse(myList.contains(0));
    }

    /**
     * Test that linked list can retrieve element at nth index (from 0)
     */
    @Test 
    public void testAtGoingForward() {

        // Check that the content in each index is equal to the values 1 - 5 inclusive
        for(int i = 0; i < myList.getLength(); i++) {
            assertEquals((int)myList.at(i), (long)i + 1);
        }

        // Assert at returns null if index does not exist
        assertNull(myList.at(5));
    }

    /**
     * Test that the linked list can retrieve elements from the end of the list using negative indices (from -1)
     */
    @Test 
    public void testAtGoingBackward() {

        // Iterate from the back of the linked list to the front with negative indices
        for(int i = -1; i >= myList.getLength(); i--) {
            assertEquals((int)myList.at(i), myList.getLength() - (long)i + 1);
        }

        // Assert at returns null if index does not exist
        assertNull(myList.at(-6));
    }

    /**
     * Test that linked list returns null when trying to get element from empty list (non-negative index)
     */
    @Test 
    public void testAtGoingForwardOnEmptyList() {

        // Empty the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        // Should return null for checking at for index 0 
        assertNull(myList.at(0));
    }

    /**
     * Test that linked list returns null when trying to get element from empty list (negative index)
     */
    @Test 
    public void testAtGoingBackwardOnEmptyList() {

        // Empty the list
        myList.clearList();

        // Confirm the list is empty
        assertEquals(myList.getLength(), 0);

        // Should return null for checking at for index 0 
        assertNull(myList.at(-1));
    }

}
