package Tests;

import Heap.MinHeap;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MinHeapTests {

    MinHeap<Integer> heap = new MinHeap<>(true);

    /**
     * Add integers 1 - 5 before each test
     */
    @Before
    public void setupStack() {
        heap.offer(4);
        heap.offer(3);
        heap.offer(1);
        heap.offer(5);
        heap.offer(2);
    }

    /**
     * Clear the heap after each test
     */
    @After
    public void teardownStack() {
        heap.clear();
    }

    /**
     * Check the heap is not null and that there are 5 elements inside 
     */
    @Test
    public void testHeapInitialState() {
        assertNotNull(heap);
        assertEquals(heap.size(), 5);
    }

    /**
     * This test demonstrates that the heap always returns the minimum element on poll and peek
     */
    @Test
    public void testHeapAlwaysRemovesSmallestElement() {
        assertEquals((int)heap.peek(), 1);
        assertEquals((int)heap.poll(), 1);
        assertEquals((int)heap.peek(), 2);
        assertEquals((int)heap.poll(), 2);
        assertEquals((int)heap.peek(), 3);
        assertEquals((int)heap.poll(), 3);
        assertEquals((int)heap.peek(), 4);
        assertEquals((int)heap.poll(), 4);
        assertEquals((int)heap.peek(), 5);
        assertEquals((int)heap.poll(), 5);
    }

    /**
     * Test that isMinBinaryTree returns true
     * Test that isMaxBinaryTree returns false
     */
    @Test
    public void testHeapIsAMinHeap() {
        assertTrue(heap.isMinBinaryTree(1));
        assertFalse(heap.isMaxBinaryTree(1));
    }

    /**
     * Test that a heap operates properly when all but one of the elements are equal
     */
    @Test
    public void testHeapWithEqualValues() {
        heap.clear();
        heap.offer(42);
        heap.offer(42);
        heap.offer(42);
        heap.offer(42);
        heap.offer(41);
        heap.offer(42);
        assertTrue(heap.isMinBinaryTree(1));
        assertFalse(heap.isMaxBinaryTree(1));
        assertEquals((int)heap.poll(), 41);
        assertEquals((int)heap.poll(), 42);
        assertEquals(heap.size(), 4);
    }
    
}
