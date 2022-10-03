package LinkedList;

/**
 * Class implementing a Linked List node
 */
public class Node<T> {

    // Next node in the list
    private Node<T> next;

    // Previous node in the list
    private Node<T> previous;

    // Content held within the current node
    private T content;

    /**
     * Constructor taking new nodes along with the content for the current node
     * @param next Node<T>: the node in the list
     * @param previous Node<T>: the previous node in the list
     * @param content T: content to be stored in this node
     */
    public Node(Node<T> next, Node<T> previous, T content) {
        setNextNode(next);
        setPreviousNode(previous);
        setContent(content);
    }

    /**
     * Creates an independent, isolated node 
     * @param content T: content to be stored in this node
     */
    public Node(T content) {
        setContent(content);
    }

    /**
     * Sets the next nde in the list
     * @param next Node<T>: the next node in the list
     */
    public void setNextNode(Node<T> next) {
        this.next = next;
    }

    /**
     * Sets the previous node in the list
     * @param previous Node<T>: the previous node in the list
     */
    public void setPreviousNode(Node<T> previous) {
        this.previous = previous;
    }

    /**
     * Sets the content for the current node
     * @param content T: the content for the current node
     */
    public void setContent(T content) {
        this.content = content;
    }

    /**
     * Gets the next node in the list
     * @return Node<T>: next node in the list
     */
    public Node<T> getNextNode() {
        return next;
    }

    /**
     * Gets the previous node in the list
     * @return Node<T>: previous node in the list
     */
    public Node<T> getPreviousNode() {
        return previous;
    }

    /**
     * Gets the content for the current node
     * @return T: content for the current node
     */
    public T getContent() {
        return content;
    }

}