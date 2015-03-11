/**
 * Binary Search tree that holds a node
 * @author Gerard McDevitt
 */
public class BinarySearchTree {
    protected Node root;
    protected int size = 0;
    protected int height = 0;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(int[] array) {
        for(int i = 0; i < array.length; i++) {
            this.addNode(array[i]);
        }
    }

    public void addNode(int value) {
        // Create a new Node and initialize it
        Node newNode = new Node(value);

        // If there is no root this becomes root
        if (root == null) {
            root = newNode;
            //Update size
            size++;

        } else {
            // Set root as the Node we will start
            // with as we traverse the tree
            Node focusNode = root;
            // Future parent for our new Node
            Node parent;

            while (true) {
                // root is the top parent so we start there
                parent = focusNode;
                // Check if the new node should go on
                // the left side of the parent node
                if (value < focusNode.getValue()) {
                    // Switch focus to the left child
                    focusNode = focusNode.getLeftChild();
                    // If the left child has no children
                    if (focusNode == null) {
                        // then place the new node on the left of it
                        parent.setLeftChild(newNode);
                        //Update size and height
                        size++;
                        if (findDepth(newNode.getValue()) > height) {
                            height = findDepth(newNode.getValue());
                        }
                        return; // All Done
                    }

                } else {
                    // If we get here put the node on the right
                    focusNode = focusNode.getRightChild();
                    // If the right child has no children
                    if (focusNode == null) {
                        // then place the new node on the right of it
                        parent.setRightChild(newNode);
                        //Update size and height
                        size++;
                        if (findDepth(newNode.getValue()) > height) {
                            height = findDepth(newNode.getValue());
                        }
                        // All Done
                        return;
                    }
                }
            }
        }
    }

    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth
    public void inOrderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            // Traverse the left node
            preorderTraverseTree(focusNode.getLeftChild());
            // Visit the currently focused on node
            System.out.println(focusNode);
            // Traverse the right node
            preorderTraverseTree(focusNode.getRightChild());
        }
    }

    public void preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            //Visit the node
            System.out.println(focusNode);
            //Traverse the left node
            preorderTraverseTree(focusNode.getLeftChild());
            //Traverse the right node
            preorderTraverseTree(focusNode.getRightChild());
        }
    }

    public void postOrderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            //Traverse the left node
            preorderTraverseTree(focusNode.getLeftChild());
            //Traverse the right node
            preorderTraverseTree(focusNode.getRightChild());
            //Visit the node
            System.out.println(focusNode);
        }
    }

    public Node findNode(int value) {
        // Start at the top of the tree
        Node focusNode = root;
        // While we haven't found the Node
        // keep looking
        while (focusNode.getValue() != value) {
            // If we should search to the left
            if (value < focusNode.getValue()) {
                // Shift the focus Node to the left child
                focusNode = focusNode.getLeftChild();
            } else {
                // Shift the focus Node to the right child
                focusNode = focusNode.getRightChild();
            }
            // The node wasn't found
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }

    public boolean remove(int value) {
        // Start at the top of the tree
        Node focusNode = root;
        Node parent = root;
        // When searching for a Node this will
        // tell us whether to search to the
        // right or left
        boolean isItALeftChild = true;
        // While we haven't found the Node
        // keep looking
        while (focusNode.getValue() != value) {
            parent = focusNode;
            // If we should search to the left
            if (value < focusNode.getValue()) {
                isItALeftChild = true;
                // Shift the focus Node to the left child
                focusNode = focusNode.getLeftChild();
            } else {
                // Greater than focus node so go to the right
                isItALeftChild = false;
                // Shift the focus Node to the right child
                focusNode = focusNode.getRightChild();
            }
            // The node wasn't found
            if (focusNode == null) {
                return false;
            }
        }
        // If Node doesn't have children delete it
        if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
            // If root delete it
            if (focusNode == root) {
                root = null;
            }
            // If it was marked as a left child
            // of the parent delete it in its parent
            else if (isItALeftChild) {
                parent.setLeftChild(null);
                size--;
            }
            // Vice versa for the right child
            else
                parent.setRightChild(null);
        }
        // If no right child
        else if (focusNode.getRightChild() == null) {
            if (focusNode == root) {
                root = focusNode.getLeftChild();
            }
            // If focus Node was on the left of parent
            // move the focus Node's left child up to the
            // parent node
            else if (isItALeftChild) {
                parent.setLeftChild(focusNode.getLeftChild());
            }
            // Vice versa for the right child
            else {
                parent.setRightChild(focusNode.getLeftChild());
            }
        }

        // If no left child
        else if (focusNode.getLeftChild() == null) {
            if (focusNode == root) {
                root = focusNode.getRightChild();
            }
            // If focus Node was on the left of parent
            // move the focus Nodes right child up to the
            // parent node
            else if (isItALeftChild) {
                parent.setLeftChild(focusNode.getRightChild());
            }
            // Vice versa for the left child
            else {
                parent.setRightChild(focusNode.getRightChild());
            }
        }
        // Two children so I need to find the deleted nodes
        // replacement
        else {
            Node replacement = getReplacementNode(focusNode);
            // If the focusNode is root replace root
            // with the replacement
            if (focusNode == root) {
                root = replacement;
            }
            // If the deleted node was a left child
            // make the replacement the left child
            else if (isItALeftChild) {
                parent.setLeftChild(replacement);
            }
            // Vice versa if it was a right child
            else {
                parent.setRightChild(replacement);
            }
            replacement.setLeftChild(focusNode.getLeftChild());
        }
        size--;
        updateHeight(this.root);
        return true;
    }

    public Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node focusNode = replacedNode.getRightChild();
        // While there are no more left children
        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.getLeftChild();
        }

        /*
         * If the replacement isn't the right child
         * move the replacement into the parents
         * leftChild slot and move the replaced nodes
         * right child into the replacements rightChild
         */
        if (replacement != replacedNode.getRightChild()) {
            replacementParent.setLeftChild(replacement.getRightChild());
            replacement.setRightChild(replacedNode.getRightChild());

        }
        return replacement;
    }

    /**
     * Finds the maximum value in the tree (just returns the right most node)
     * @return
     */
    public int getMaxValue() {
        // Start at the top of the tree
        Node focusNode = root;
        // While we haven't found the Node
        // keep looking
        while (focusNode.getRightChild() != null) {
            focusNode = focusNode.getRightChild();
        }
        return focusNode.getValue();
    }

    /**
     * Finds the minimum value in the tree (just returns the left most node)
     * @return
     */
    public int getMinValue() {
        // Start at the top of the tree
        Node focusNode = root;
        // While we haven't found the Node
        // keep looking
        while (focusNode.getLeftChild() != null) {
            focusNode = focusNode.getLeftChild();
        }
        return focusNode.getValue();
    }

    /**
     * Finds the depth of a specific node.
     * Returns -1 if it was not found.
     * @param value
     * @return
     */
    public int findDepth(int value) {
        // Start at the top of the tree
        Node focusNode = root;
        int depth = 0;
        // While we haven't found the Node
        // keep looking
        while (focusNode.getValue() != value) {
            // If we should search to the left
            if (value < focusNode.getValue()) {
                // Shift the focus Node to the left child
                focusNode = focusNode.getLeftChild();
                depth++;
            } else {
                // Shift the focus Node to the right child
                focusNode = focusNode.getRightChild();
                depth++;
            }
            // The node wasn't found
            if (focusNode == null) {
                return -1;
            }
        }
        return depth;
    }

    private int updateHeight(Node focusNode) {

        int height = 0;

        if (focusNode != null) {

            // Traverse the left node
            updateHeight(focusNode.getLeftChild());

            // Visit the currently focused on node
            if(findDepth(focusNode.getValue()) > height) {
                height = findDepth(focusNode.getValue());
            }

            // Traverse the right node
            updateHeight(focusNode.getRightChild());
        }
        return height;

    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }


}
