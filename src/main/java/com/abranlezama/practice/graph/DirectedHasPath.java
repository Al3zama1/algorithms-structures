package com.abranlezama.practice.graph;

import java.util.*;

public class DirectedHasPath {
    public static void main(String[] args) {
        // acyclic graph (graph with no cycles)
        Map<Character, List<Character>> alyst = Map.of(
                'f', List.of('g', 'i'),
                'g', List.of('h'),
                'h', List.of(),
                'i', List.of('g', 'k'),
                'j', List.of('i'),
                'k', List.of()
        );

        System.out.println("Has path recursive");
        boolean existsPathRecursive = hasPathRecursive(alyst, 'j', 'k');
        System.out.println(existsPathRecursive);

        System.out.println("Has path with stack");
        boolean existsPathDepth = hasPathDepth(alyst, 'j', 'k');
        System.out.println(existsPathDepth);

        System.out.println("Has path breath first search");
        boolean existsPathBreath = hasPathBreath(alyst, 'j', 'k');
        System.out.println(existsPathBreath);

    }

    private static boolean hasPathRecursive(Map<Character, List<Character>> aList, char src, char dest) {
        if (src == dest) return true;

        for (char neighbor : aList.get(src)) {
            if (hasPathRecursive(aList, neighbor, dest)) return true;
        }

        return false;
    }

    // time complexity O(e), e --> edges
    // space complexity O(v), v --> nodes
    private static boolean hasPathDepth(Map<Character, List<Character>> alyst, char src, char dest) {
        Stack<Character> stack = new Stack<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            char node = stack.pop();
            for (char c : alyst.get(node)) {
                if (c == dest) return true;
                stack.push(c);
            }
        }
        return false;
    }

    private static boolean hasPathBreath(Map<Character, List<Character>> alyst, char src, char dest) {
        Queue<Character> queue = new LinkedList<>();
        queue.offer(src);

        while (!queue.isEmpty()) {
            char node = queue.poll();
            for (char c : alyst.get(node)) {
                if (c == dest) return true;
                queue.offer(c);
            }
        }
        return false;
    }
}
