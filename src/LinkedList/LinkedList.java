package LinkedList;

/**
 * Alternative linked list implementation
 */
public class LinkedList<T> {

    protected Node<T> dummyHead;
    protected Node<T> dummyTail;
    protected int length;
    
    /**
     * Initialize an empty linked list container
     */
    public LinkedList() {
        dummyHead = new Node<>(null);
        dummyTail = new Node<>(null);
        length = 0;
    }

    /**
     * Add a new node to the end of the linked list
     * @param content T: Content to add in a new node
     * @return a success indicator
     */
    public void addNode(T content) {

        // If the list is empty, create and link the first element
        if(dummyHead.getNextNode() == null) {
            dummyHead.setNextNode(new Node<>(content));
            dummyHead.getNextNode().setPreviousNode(dummyHead);
            dummyTail.setPreviousNode(dummyHead.getNextNode());
            dummyTail.getPreviousNode().setNextNode(dummyTail);
            length++;
            return;
        }

        // If the list is not empty
        // Create a new node
        Node<T> newTail = new Node<>(content);

        // Set the previous link of the new tail to the old tail
        newTail.setPreviousNode(dummyTail.getPreviousNode());

        // Set the node after the last equal to the dummy tail
        newTail.setNextNode(dummyTail);

        // Set the next link of the old tail to the new tail
        dummyTail.getPreviousNode().setNextNode(newTail);

        // The old tail is now the new tail
        dummyTail.setPreviousNode(newTail);

        // Increment the length of the list
        length++;
    }

    /**
     * Delete first occurance (positive indices) of the parameterized object.
     * Modeled after Java collections .remove() method
     * @param content T: remove first node with this value
     * @return boolean: success or failure
     */
    public boolean remove(T content) {

        // Trying to delete from empty list
        if(dummyHead.getNextNode() == null) return false;

        Node<T> iterHead = dummyHead.getNextNode();

        // For iteration termination condition
        int numIter = 0;

         
        //Check every node to see if it contains the content specified
        while(numIter < length) {

            // Check iterHead during forward iteration
            if(iterHead.getContent().equals(content)) {

                // Decrement the length of the list
                length--;

                // Set the previous node's next link to the next node
                iterHead.getPreviousNode().setNextNode(iterHead.getNextNode());

                // Set the next node's previous link to the previous node
                iterHead.getNextNode().setPreviousNode(iterHead.getPreviousNode());

                // Return success
                return true;

            }

            // Iterate head forward
            iterHead = iterHead.getNextNode();
            numIter++;

        }

        // Return failure
        return false;
    }

    /**
     * Delete a node from the list at index N.
     * Accepts non-negative indices to iterate from the front and negative indices to iterate from the back
     * @return boolean: success status
     */
    public boolean deleteNodeAtIndex(int n) {

        // Check that the the index provided is less than the length of the list
        if(n >= 0 && length <= n) return false;
        else if(n < 0 && length < Math.abs(n)) return false;


        // If a positive index entered, iterate forward
        if(n >= 0) {

            // Save the head of the list
            Node<T> iter = dummyHead.getNextNode();

            // Iterate forward until reaching the specified index
            int i = 0;
            while(i != n) {
                iter = iter.getNextNode();
                i++;
            }    

            // Set the previous node's next link to the next node
            iter.getPreviousNode().setNextNode(iter.getNextNode());

            // Set the next node's previous link to the previous node
            iter.getNextNode().setPreviousNode(iter.getPreviousNode());

            // Decrement the length of the list
            length--;

            // Return success
            return true;

        } 
        
        else {

            // Save the tail of the list
            Node<T> iter = dummyTail.getPreviousNode();

            // Iterate down from the end until finding the specified index
            int i = -1;
            while(i != n) {
                iter = iter.getPreviousNode();
                i--;
            }

            // Set the previous node's next link to the next node
            iter.getPreviousNode().setNextNode(iter.getNextNode());

            // Set the next node's previous link to the previous node
            iter.getNextNode().setPreviousNode(iter.getPreviousNode());

            // Decrement the length of the list
            length--;

            // Return success
            return true;

        }

    }

    /**
     * Determines if a specific value is contained in the list
     * @param content T: the content within 
     * @return boolean: if the parameterized value is contained in the list
     */
    public boolean contains(T content) {

        // Trying to delete from empty list
        if(dummyHead.getNextNode() == null) return false;

        // Iteration temp variable
        Node<T> iterHead = dummyHead.getNextNode();
        int numIter = 0;

        // Check every node
        while(numIter < length) {

            // If the content is found, return true 
            if(iterHead.getContent().equals(content)) return true;

            // Otherwise go to the next node
            iterHead = iterHead.getNextNode();

            // Count iterations for termination condition
            numIter++;
        }

        // Content was not found, return false
        return false;

    }

    /**
     * Retrive the content from node at index N in the linked list.
     * Accepts non-negative indices to iterate from the front and negative indices to iterate from the back
     * @return T: content in node at index n
     */
    public T at(int n) {

        // Check that the absolute value of the index provided is less than the length of the list
        if(length <= Math.abs(n)) return null;

        // If a positive index entered, iterate forward
        if(n >= 0) {

            // Save the head of the list
            Node<T> iter = dummyHead.getNextNode();

            // Iterate forward until reaching the specified index
            int i = 0;
            while(i != n) {
                iter = iter.getNextNode();
                i++;
            }    

            // Return the content at node index n (iterating forward)
            return iter.getContent();

        } 
        
        else {

            // Save the tail of the list
            Node<T> iter = dummyTail.getPreviousNode();

            // Iterate down from the end until finding the specified index
            int i = -1;
            while(i != n) {
                iter = iter.getPreviousNode();
                i--;
            }

            // Return the content at node index n (iterating backward)
            return iter.getContent();

        }

    }

    /**
     * Erase all nodes from the list
     */
    public void clearList() {
        dummyHead.setNextNode(null);
        dummyTail.setPreviousNode(null);
        length = 0;
    }

    /**
     * Print the contents of the list
     */
    public void printList() {

        // Save the number of iterations
        int numIter = 0;

        // Iteration variable
        Node<T> iter = dummyHead.getNextNode();

        // Iterate for only the number of items in the list
        while(numIter < length) {

            // If statements for pretty printing
            if(numIter == 0) {
                System.out.print(iter.getContent());
            } else {
                System.out.print(" --> " + iter.getContent());
            }

            // Move to the next node
            iter = iter.getNextNode();

            // Increment iteration var
            numIter++;
        }

        // Print an extra line at the end
        System.out.println();

    }

    // ============================== Getters ==============================

    /**
     * Return the length of the linked list at any point in time
     * @return int: Length of the linked list
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the first element of the linked list
     * @return Node<T>: the first element in the linked list
     */
    public Node<T> getHead() {
        return dummyHead.getNextNode();
    }

    /**
     * Returns the last element of the linked list
     * @return Node<T>: the last element in the linked list
     */
    public Node<T> getTail() {
        return dummyTail.getPreviousNode();
    }

}
