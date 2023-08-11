package com.abranlezama.structures;

import java.util.*;

public class BinarySearchTree <T extends Comparable<T>> {

    // track number of nodes in the BST
    private int nodeCount = 0;
    // this BST is a rooted tree, therefore maintain a handle on the root node
    private Node<T> root = null;

    private static class Node<T> {
        T data;
        Node<T> left, right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // check if the BST is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of nodes in BST
    public int size() {
        return nodeCount;
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    // private recursive method to find element in the BST
    private boolean contains(Node<T> node, T element) {
        // bse case: reached bottom, value not found
        if (node == null) return false;

        int cmp = element.compareTo(node.data);
        if (cmp < 0) return contains(node.left, element);
        else if (cmp > 0) return contains(node.right, element);
        else return true;
    }

    //ad element to BST. Returns tru if successful
    public boolean add(T element) {
        // add value if it does not already exist
        if (contains(element)) return false;
        else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    private Node<T> add(Node<T> node, T element) {
        if (node == null)  {
            node = new Node<>(element, null, null);
        } else {
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return  node;
    }

    public boolean remove(T element) {
        if (!contains(element)) return false;
        root = remove(root, element);
        nodeCount--;
        return true;
    }

    private Node<T> remove(Node<T> node, T element) {
        if (node == null) return null;

        int cmp = element.compareTo(node.data);

        if (cmp < 0) node.left = remove(node.left, element);
        else if (cmp > 0) node.right = remove(node.right, element);
        else {
            /*
            case with only a right subtree or not subtree at all. In this situation
            just swap the node we wish to remove with its right child
             */
            if (node.left == null) {
                Node<T> rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            /*
            case with only a left subtree or no subtree at all. In this situation
            just swap the node we wish to remove with its left child
             */
            } else if (node.right == null) {
                Node<T> leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;

            /*
            when removing a node from a binary tree with two links, the successor of the node being removed can either
            be the largest value in the left subtree or the smallest value in the right subtree.
            This implementation find the smallest value in the right subtree. It can be found by traversing as
            far left as possible in the right subtree
             */
            } else {
                Node<T> tmp = findMin(node.right);
                // swap the data
                node.data = tmp.data;

                /*
                Go into the right subtree and remove the leftmost node we found and swapped data with.
                This prevents us from having two nodes in our tree with the same value
                 */
                node.right = remove(node.right, tmp.data);
            }
        }
        return node;
    }

    // helper method to find the leftmost node, which has the smallest value
    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // helper method to find the rightmost node, which has the largest value
    private Node<T> findMax(Node<T> node) {
        while (node.right != null) node = node.right;
        return node;
    }

    // compute the height of the tree
    public int height() {
        return height(root);
    }

    // recursive helper method to find the height of the tree
    public int height(Node<T> node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public enum BSTTraversalOrder{ PRE_ORDER, IN_ORDER, POST_ORDER, LEVER_ORDER }
    public Iterator<T> traversal(BSTTraversalOrder order) {
        return switch (order) {
            case PRE_ORDER -> preOrderTraversal();
            default -> null;
        };
    }

    private Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node<T> node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            Node<T> trav = root;

            @Override
            public boolean hasNext() {
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();

                // dig left
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node<T> node = stack.pop();

                // try moving down right once
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node<T>> stack1 = new Stack<>();
        final Stack<Node<T>> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node<T> node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.right != null) stack1.push(node.right);
                if (node.left != null) stack1.push(node.left);
            }
        }

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return stack2.pop().data;
            }

        };
    }

    private Iterator<T> levelOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        return new Iterator<T>() {


            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node<T> node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }
        };
    }

}
