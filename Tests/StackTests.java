package Tests;

import Stack.Stack;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class StackTests {
    
    Stack<Integer> myStack = new Stack<>();

    /**
     * Add integers 1 - 5 before each test
     */
    @Before
    public void setupStack() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
    }

    /**
     * Clear the stack after each test
     */
    @After
    public void teardownStack() {
        myStack.emptyStack();
    }

    /**
     * Check that the stack is correctly initialized
     */
    @Test
    public void testStackInitialState() {

        // Check that the stack is not null
        assertNotNull(myStack);

        // Show that the stack is not empty and there are 5 elements
        assertFalse(myStack.isEmpty());
        assertEquals(myStack.getLength(), 5);

        // Remove each element from the stack and check the length has been decreased by 1 after each pop
        for(int i = 1; i < 6; i++) {

            // Check the correct element is always popped (integers 5 - 1)
            assertEquals((long)6 - i, (int)myStack.pop());
            assertEquals(myStack.getLength(), (long)5 - i);
        }

        // Show that the stack is empty
        assertTrue(myStack.isEmpty());
        assertEquals(myStack.getLength(), 0);

    }

    /**
     * Test that elements can successfully be added to an empty stack
     */
    @Test
    public void testPushOnEmptyStack() {

        // Empty the stack
        myStack.emptyStack();

        // Verify that it's empty
        assertTrue(myStack.isEmpty());
        assertEquals(myStack.getLength(), 0);

        // Add an element
        myStack.push(100);

        // Verify the element is present and that the stack is no longer empty
        assertFalse(myStack.isEmpty());
        assertEquals(myStack.getLength(), 1);

        // Verify that the value of the element added is correct
        assertEquals((int)myStack.peek(), 100);
        
    }

    /**
     * Test that an element can be successfully added when there are already elements in the stack
     */
    @Test
    public void testPushOnNonEmptyStack() {

        // Validate the there are 5 elements in the stack
        assertEquals(myStack.getLength(), 5);
        
        // Add a new element
        myStack.push(100);

        // Validate there are 6 values in the stack
        assertEquals(myStack.getLength(), 6);

        // Validate the top element of the stack was equal to the element previously added
        assertEquals((int)myStack.pop(), 100);

        // Validate that the new top element is equal to the last element added during setup
        assertEquals((int)myStack.peek(), 5);

    }

    /**
     * Test that the pop method returns null when there are no elements in the stack
     */
    @Test
    public void testPopOnEmptyStack() {

        // Empty the stack
        myStack.emptyStack();

        // Make sure that there pop returns null
        assertNull(myStack.pop());
    }

    /**
     * Test that the stack returns the element most recently added when invoking pop
     */
    @Test
    public void testPopOnNonEmptyStack() {

        // Pop all elements from the stack (integers 5 - 1)
        for(int i = 5; i > 0; i--) {

            // Verify that the values returned by pop equal the integers 5 - 1
            assertEquals(i, (int)myStack.pop());
        }
    }

    /**
     * Test that checking the contents of the stack returns null if the stack is empty
     */
    @Test
    public void testPeekOnEmptyStack() {
        
        // Empty the stack
        myStack.emptyStack();

        // Validate the stack is empty
        assertTrue(myStack.isEmpty());

        // Validate that the top element is null because length 0
        assertNull(myStack.peek());
    }

    /**
     * Test that peek returns the element most recently added to the stack
     */
    @Test
    public void testPeekOnNonEmptyStack() {

        // Check that the top of the stack is equal to 5 (last element added in setup)
        assertEquals((int)myStack.peek(), 5);

        // Push the element 100 to stack
        myStack.push(100);

        // Check that the top element of the stack is equal to 100
        assertEquals((int)myStack.peek(), 100);

    }

}
