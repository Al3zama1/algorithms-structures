package com.abranlezama.structures.avltree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

public class AVLRecursiveTree<T extends Comparable<T>> implements Iterable<T> {


    public static class Node<T> {
        // bf is short for balance factor
        public int bf;

        // the value/data contained in the node
        public T value;

        // the height of this node in the tree
        public int height;

        // the left and right children of this node
        Node<T> left, right;

        public Node(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    // the root node of the AVL tree
    public Node<T> root;

    //track the number of nodes inside the tree
    public int nodeCount = 0;

    /*
    the height of a rooted tree is the number of edges between the tree's
    root and its furthest leaf node. This means that a tree containing
    a single node has a height of 0.
     */
    public int height() {
        if (root == null) return 0;
        return root.height;
    }

    // returns the number of nodes in the tree
    public int size() {
        return nodeCount;
    }

    // returns whether or not the tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // return true/false depending on whether a value exists in the tree
    public boolean contains(T value) {
        return contains(root, value);
    }

    // recursive contains helper method
    private boolean contains(Node<T> node, T value) {
        if (node == null) return false;

        // compare current value to the value in the node
        int cmp = value.compareTo(node.value);

        // dig into left subtree
        if (cmp < 0) return contains(node.left, value);
        // dig into right subtree
        else if (cmp > 0) return contains(node.right, value);
        // found value
        else return true;
    }

    // insert/add a value to the AVL tree. The value must not be null, O(log(n))
    public boolean insert(T value) {
        if (value == null) return false;
        if (contains(value)) return false;
        root = insert(root, value);
        nodeCount++;
        return true;
    }

    // recursive insert helper  method
    private Node<T> insert(Node<T> node, T value) {
        // Base case
        if (node == null) return new Node<>(value);

        // compare current value to the value in the node
        int cmp = value.compareTo(node.value);

        // insert node in the left subtree
        if (cmp < 0) node.left = insert(node.left, value);
        // insert node in the right subtree
        else node.right = insert(node.right, value);

        // update balance factor and height value
        update(node);

        // re-balance tree
        return balance(node);
    }

    // update a node's height and balance factor
    private void update(Node<T> node) {
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

        // update this node's height
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

        // update balance factor
        node.bf = rightNodeHeight - leftNodeHeight;
    }

    // re-balance a node if its balance factor is +2 or -2. |balance| <= 1 is good
    private Node<T> balance(Node<T> node) {
        // left heavy subtree
        if (node.bf == -2) {

            // left-left case
            if (node.left.bf <= 0) return leftLeftCase(node);

            // left-right case
             else return leftRightCase(node);
            // right heavy subtree
        } else if (node.bf == +2) {

            // right-right case
            if (node.right.bf >= 0) return rightRightCase(node);
            // right-left case
            else return rightLeftCase(node);
        }

        // Node either has a balance factor of 0, +1, or -1 which is fine
    return node;
    }

    private Node<T> leftLeftCase(Node<T> node) {
        return rightRotation(node);
    }

    private Node<T> leftRightCase(Node<T> node) {
        node.left = leftRotation(node.left);
        return leftLeftCase(node);
    }

    private Node<T> rightRightCase(Node<T> node) {
        return leftRotation(node);
    }

    private Node<T> rightLeftCase(Node<T> node) {
        node.right = rightRotation(node.right);
        return rightRightCase(node);
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }

    // remove a value from binary tree if it exists, O(log(n))
    public boolean remove(T elem) {
        if (elem == null || !contains(root, elem)) return false;

        root = remove(root, elem);
        nodeCount--;
        return true;
    }

    // remove a value from the AVL tree
    private Node<T> remove(Node<T> node, T elem) {
        if (node == null) return null;

        int cmp = elem.compareTo(node.value);

        // dig into left subtree if value is less than current node value
        if (cmp < 0) node.left = remove(node.left, elem);
        // dig into right subtree if value greater than current node value
        else if (cmp > 0) node.right = remove(node.right, elem);
        // found the node to remove
        else {
            // case with right subtree or no subtree at all
            if (node.left == null) return node.right;
            // case with left subtree or no subtree at all
            else if (node.right == null) return node.left;
            // case with left and right subtrees
            else {
                // choose to remove from left subtree
                if (node.left.height > node.right.height) {

                    // swap node value with the largest value of left subtree
                    T successorValue = findMax(node.left);
                    node.value = successorValue;

                    // remove largest value in left subtree
                    node.left = remove(node.left, successorValue);
                } else {
                    // swap node value with the smallest value of right subtree
                    T successorValue = findMin(node.right);
                    node.value = successorValue;

                    // remove smallest value in right subtree
                    node.right = remove(node.right, successorValue);
                }
            }
        }
        // update balance factor and height values
        update(node);
        // re-balance tree
        return balance(node);
    }

    // helper method to find the leftmost node (which is the smallest value)
    private T findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node.value;
    }

    // helper method t find rightmost node (which has the largest value)
    private T findMax(Node<T> node) {
        while (node.right != null) node = node.right;
        return node.value;
    }

    // In Order Traversal
    @Override
    public Iterator<T> iterator() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node<T>> stack = new Stack<>();
        stack.push(root);


        return new Iterator<T>() {
            Node<T> trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();

                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node<T> node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.value;
            }
        };
    }
 }
