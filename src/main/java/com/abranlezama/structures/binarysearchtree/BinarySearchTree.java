package com.abranlezama.structures.binarysearchtree;

import org.w3c.dom.Node;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {
    private int nodeCount = 0;
    private Node<T> root = null;

    public static class Node<T> {
        T data;
        Node<T> left, right;

        public Node(Node<T> left, Node<T> right, T elem) {
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    // check if binary search tree is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of nodes in binary search tree
    public int size() {
        return nodeCount;
    }

    public boolean add(T elem) {
        // check if value already exists in tree, if it does ignore adding it
        if (!contains(elem)) return false;
        else { // otherwise add element to the binary tree
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    // recursively add a value to the tree
    private Node<T> add(Node<T> node, T elem) {
        // base case: found a leaf node
        if (node == null) node = new Node<>(null, null, elem);
        else {
            // pick a subtree to insert element
            if (elem.compareTo(node.data) < 0) node.left = add(node.left, elem);
            else node.right = add(node.right, elem);
        }
        return node;
    }

    // remove value from binary tree if it exists, O(n)
    public boolean remove(T elem) {
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node<T> remove(Node<T> node, T elem) {
        if (node ==  null) return null;

        int cmp = elem.compareTo(node.data);
        if (cmp < 0) node.left = remove(node.left, elem);
        else if (cmp > 0) node.right = remove(node.right, elem);
        else { // found node we wish to remove
            // case with right subtree or no subtree at all
            if (node.left == null) return node.right;
            // case with left subtree or no subtree at all
            else if (node.right == null) return node.left;
            else { // case with both subtrees
                /*
                find the largest value in left subtree or smallest value in right subtree
                 */
                Node<T> tmp = findMin(node.right);
                node.data = tmp.data; // swap the data
                node.right = remove(node.right, tmp.data);
            }
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node<T> findMax(Node<T> node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private boolean contains(Node<T> node, T elem) {
        if (node == null) return false;

        int cmp = elem.compareTo(node.data);
        if (cmp < 0) return contains(node.left, elem);
        else if (cmp > 0) return contains(node.right, elem);
        else return true;
    }

    public Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node<T> node = stack.pop();
                if (node.right !=  null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }
        };
    }

    public Iterator<T> inOrderTraversal() {
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
                while (trav != null && trav.left !=  null) {
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

    public Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node<T>> stack1 = new Stack<>();
        final Stack<Node<T>> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node<T> node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left !=  null) stack1.push(node.left);
                if (node.right !=  null) stack1.push(node.right);
            }
        }

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return stack2.pop().data;
            }
        };
    }

    public Iterator<T> levelOrderTraversal() {
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
                if (node != null && node.left != null) queue.offer(node.left);
                if (node != null && node.right != null) queue.offer(node.right);
                assert node != null;
                return node.data;
            }
        };
    }
}
