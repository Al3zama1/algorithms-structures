package com.abranlezama.practice.graph;

import java.util.*;

// # of edges between src and dest will be used for calculating distance
public class ShortestPath {
    public static void main(String[] args) {
        char[][] edgeList = {
                {'w', 'x'},
                {'x', 'y'},
                {'z', 'y'},
                {'z', 'v'},
                {'w', 'v'}
        };

        Map<Character, List<Character>> aList = createAdjacencyList(edgeList);
        System.out.println(aList.toString());
        int shortestDistance = explore(aList, 'w', 'z', new HashSet<>());
        System.out.println(shortestDistance);
    }

    private static int explore(Map<Character, List<Character>> aList, char src, char dest, Set<Character> visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(src, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node.value);

            for (char c : aList.get(node.value)) {
                if (c == dest) return node.distance + 1;
                if (visited.contains(c)) continue;
                queue.offer(new Node(c, node.distance + 1));
            }
        }

        return -1;
    }

    private record Node(char value, int distance){}

    private static Map<Character, List<Character>> createAdjacencyList(char[][] edgeList) {
        Map<Character, List<Character>> aList = new HashMap<>();
        for (char[] row : edgeList) {
            char a = row[0];
            char b = row[1];

            aList.computeIfAbsent(a, k -> new ArrayList<>());
            aList.computeIfAbsent(b, k -> new ArrayList<>());
            aList.get(a).add(b);
            aList.get(b).add(a);
        }
        return aList;
    }
}
