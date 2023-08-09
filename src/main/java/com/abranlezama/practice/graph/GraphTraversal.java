package com.abranlezama.practice.graph;

import java.util.*;

public class GraphTraversal {
    public static void main(String[] args) {
        Map<Character, List<Character>> alist = Map.of(
                'a', List.of('b', 'c'),
                'b', List.of('d'),
                'c', List.of('e'),
                'd', List.of('f'),
                'e', List.of(),
                'f', List.of()
        );
        System.out.println("Recursive Traversal");
        recursiveTraversal(alist, 'a');

        System.out.println("Depth First Traversal");
        depthFirstTraversal(alist);

        System.out.println("Breath First Traversal");
        breathFirstTraversal(alist);
    }

    private static void recursiveTraversal(Map<Character, List<Character>> aList, char node) {
        System.out.println(node);
        for (char value : aList.get(node)) recursiveTraversal(aList, value);
    }

    private static void depthFirstTraversal(Map<Character, List<Character>> alist) {
        Stack<Character> stack = new Stack<>();
        stack.push('a');

        while (!stack.isEmpty()) {
            char node = stack.pop();
            for (char c : alist.get(node)) stack.push(c);
            System.out.println(node);
        }
    }

    private static void breathFirstTraversal(Map<Character, List<Character>> alist) {
        Queue<Character> queue = new LinkedList<>();
        queue.offer('a');

        while (!queue.isEmpty()) {
            char node = queue.poll();
            for (char c : alist.get(node)) queue.offer(c);
            System.out.println(node);
        }
    }
}
