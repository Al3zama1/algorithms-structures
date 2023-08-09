package com.abranlezama.practice.graph;

import java.util.*;

public class UndirectedHasPath {
    public static void main(String[] args) {
        // convert edges list to an adjacency list
        char[][] edges = {
                {'i', 'j'},
                {'k', 'i'},
                {'m', 'k'},
                {'k', 'l'},
                {'o', 'n'}
        };
        Map<Character, List<Character>> aList = buildAdjacencyList(edges);
        System.out.println(aList.toString());
        boolean existsPath = hasPathDepth(aList, 'j', 'm');
        System.out.println(existsPath);

        boolean recursiveExistsPath = recursiveHasPath(aList, 'j', 'm', new HashSet<>());
        System.out.println(recursiveExistsPath);
    }

    private static boolean recursiveHasPath(Map<Character, List<Character>> alyst, char src, char dest, Set<Character> set) {
        if (src == dest) return true;
        set.add(src);

        for(char c : alyst.get(src)) {
            if (set.contains(c)) continue;
            if (recursiveHasPath(alyst, c, dest, set)) return true;

        }
        return false;
    }
    private static boolean hasPathDepth(Map<Character, List<Character>> aList, char src, char dest) {
        Set<Character> set = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            char node = stack.pop();
            set.add(node);

            for (char c : aList.get(node)) {
                if (c == dest) return true;
                if (!set.contains(c)) stack.push(c);
            }
        }
        return false;
    }


    private static Map<Character, List<Character>> buildAdjacencyList(char[][] edges) {
        Map<Character, List<Character>> alyst = new HashMap<>();

        for (char[] edge : edges) {
            char one = edge[0];
            char two = edge[1];

            alyst.computeIfAbsent(one, k -> new ArrayList<>());
            alyst.get(one).add(two);
            alyst.computeIfAbsent(two, k -> new ArrayList<>());
            alyst.get(two).add(one);

        }
        return alyst;
    }
}
