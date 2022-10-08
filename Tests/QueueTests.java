package Tests;

import Queue.Queue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class QueueTests {
    
    Queue<Integer> myQueue = new Queue<>();

    /**
     * Add integers 1 - 5 before each test
     */
    @Before
    public void setupQueue() {
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        myQueue.offer(4);
        myQueue.offer(5);
    }

    /**
     * Clear the Queue after each test
     */
    @After
    public void teardownQueue() {
        myQueue.emptyQueue();
    }

    /**
     * Check that the Queue is correctly initialized
     */
    @Test
    public void testQueueInitialState() {

        // Check that the Queue is not null
        assertNotNull(myQueue);

        // Show that the Queue is not empty and there are 5 elements
        assertFalse(myQueue.isEmpty());
        assertEquals(myQueue.getLength(), 5);

        // Remove each element from the Queue and check the length has been decreased by 1 after each poll
        for(int i = 1; i < 6; i++) {

            // Check the correct element is always polled (integers 5 - 1)
            assertEquals(i, (int)myQueue.poll());
            assertEquals(myQueue.getLength(), (long)5 - i);
        }

        // Show that the Queue is empty
        assertTrue(myQueue.isEmpty());
        assertEquals(myQueue.getLength(), 0);

    }

    /**
     * Test that elements can successfully be added to an empty Queue
     */
    @Test
    public void testofferOnEmptyQueue() {

        // Empty the Queue
        myQueue.emptyQueue();

        // Verify that it's empty
        assertTrue(myQueue.isEmpty());
        assertEquals(myQueue.getLength(), 0);

        // Add an element
        myQueue.offer(100);

        // Verify the element is present and that the Queue is no longer empty
        assertFalse(myQueue.isEmpty());
        assertEquals(myQueue.getLength(), 1);

        // Verify that the value of the element added is correct
        assertEquals((int)myQueue.peek(), 100);
        
    }

    /**
     * Test that an element can be successfully added when there are already elements in the Queue
     */
    @Test
    public void testofferOnNonEmptyQueue() {

        // Validate the there are 5 elements in the Queue
        assertEquals(myQueue.getLength(), 5);
        
        // Add a new element
        myQueue.offer(100);

        // Validate there are 6 values in the Queue
        assertEquals(myQueue.getLength(), 6);

        // Remove all of the elements from setup
        for(int i = 1; i < 6; i++) {
            assertEquals((int)myQueue.poll(), i);
        }

        // Validate there is only 1 element left in the queue, the one previously added in this test
        assertEquals(myQueue.getLength(), 1);
        assertEquals((int)myQueue.peek(), 100);

    }

    /**
     * Test that the poll method returns null when there are no elements in the Queue
     */
    @Test
    public void testpollOnEmptyQueue() {

        // Empty the Queue
        myQueue.emptyQueue();

        // Make sure that there poll returns null
        assertNull(myQueue.poll());
    }

    /**
     * Test that the Queue returns the element most recently added when invoking poll
     */
    @Test
    public void testpollOnNonEmptyQueue() {

        // Poll all elements from the Queue (integers 1 - 5)
        for(int i = 1; i < 6; i++) {

            // Verify that the values returned by poll equal the integers 1 - 5
            assertEquals(i, (int)myQueue.poll());
        }
    }

    /**
     * Test that checking the contents of the Queue returns null if the Queue is empty
     */
    @Test
    public void testPeekOnEmptyQueue() {
        
        // Empty the Queue
        myQueue.emptyQueue();

        // Validate the Queue is empty
        assertTrue(myQueue.isEmpty());

        // Validate that the top element is null because length 0
        assertNull(myQueue.peek());
    }

    /**
     * Test that peek returns the element most recently added to the Queue
     */
    @Test
    public void testPeekOnNonEmptyQueue() {

        // Offer the element 100 to Queue
        myQueue.offer(100);


        for(int i = 1;  i < 6; i++) {

            // Check that peek returns the element at the front of the queue
            assertEquals((int)myQueue.peek(), i);
            assertEquals((int)myQueue.poll(), i);
        }

        // Check that the top element of the Queue is equal to 100
        assertEquals((int)myQueue.peek(), 100);

    }

}
