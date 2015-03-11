
/**
 * Node that contains an int value
 * @author Gerard McDevitt
 */
public class Node {

    //The value of the node
    private int value;
    //Left child of the node
    private Node leftChild;
    //Right child of the node
    private Node rightChild;

    /**
     * Create a node with an int value
     * @param value
     */
    public Node(int value) {
        this.value = value;
    }

    //Print out the node
    public String toString() {
        return String.valueOf(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}

