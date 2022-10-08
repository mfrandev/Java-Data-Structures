package Stack;

import LinkedList.LinkedList;

/**
 * Alternative Stack implementation using my LinkedList implementation
 */
public class Stack<T> extends LinkedList<T> {

    public Stack() {
        super();
    }

    /**
     * Linked List addNode method analagous to push()
     * i.e., add content to the end of the linked list
     * @param content T: element to add to list
     */
    public void push(T content) {
       addNode(content);
    }

    /**
     * Remove and return the element from the end of the stack
     * @return T: element at the end of the stack
     */
    public T pop() {

        // Nothing to pop
        if(isEmpty()) return null;

        // Save the node to remove
        T toRemove = dummyTail.getPreviousNode().getContent();

        // Delete the node at the end of the list (or top of the stack)
        deleteNodeAtIndex(getLength() - 1);

        // Return the deleted element
        return toRemove;

    }

    /**
     * Return the element at the end of the stack without removing it
     * @return T: the element at the end of the stack
     */
    public T peek() {

        // Return the element at the end of the list
        return dummyTail.getPreviousNode().getContent();
    }

    /**
     * Return true if there are no elements in the stack, otherwise false
     * @return boolean: whether or not the stack is empty
     */
    public boolean isEmpty() {
        return getLength() == 0;
    }

    /**
     * Remove all elements from the stack by linking the dummy head and tail
     */
    public void emptyStack() {
        dummyHead.setNextNode(dummyTail);
        dummyTail.setPreviousNode(dummyHead);
        length = 0;
    }
    
}
