package Queue;

import LinkedList.LinkedList;

public class Queue<T> extends LinkedList<T> {

    public Queue() {
        super();
    }

    /**
     * Add the element offered to the end of the queue
     * @param content T: item to add 
     */
    public void offer(T content) {
        addNode(content);
    }

    /**
     * Remove and return the element at the front of the queue
     * @return
     */
    public T poll() {

        // Nothing to pop
        if(isEmpty()) return null;

        // Content at the position to delete
        T toRemove = dummyHead.getNextNode().getContent();

        // Delete the node at the front of the queue
        deleteNodeAtIndex(0);

        // Return the deleted element
        return toRemove;
    }

    /**
     * Return the element at the front of the queue without removing it
     * @return T: the element at the end of the queue
     */
    public T peek() {

        // Return the element at the front of the queue
        return dummyHead.getNextNode().getContent();
    }

    /**
     * Determines if the queue is empty or not
     * @return boolean: true if empty, otherwise false
     */
    public boolean isEmpty() {
        return getLength() == 0;
    }

    /**
     * Link the dummy head and tail together, effectively removing every node from the queue
     */
    public void emptyQueue() {
        dummyHead.setNextNode(dummyTail);
        dummyTail.setPreviousNode(dummyHead);

        // Set length to 0 now since queue is empty
        length = 0;
    }
    
}
