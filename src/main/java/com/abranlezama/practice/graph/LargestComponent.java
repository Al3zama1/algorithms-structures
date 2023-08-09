package com.abranlezama.practice.graph;

import java.util.*;

public class LargestComponent {
    public static void main(String[] args) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> aList = Map.of(
                0, List.of(8, 1, 5),
                1, List.of(0),
                5, List.of(0, 8),
                8, List.of(0, 5),
                2, List.of(3, 4),
                3, List.of(2, 4),
                4, List.of(3, 2)
        );

        int largest = 0;
        for (int key : aList.keySet()) {
            int size = count(aList, key, visited);
            if (size > largest) largest = size;
        }
        System.out.println(largest);
    }

    private static int count(Map<Integer, List<Integer>> aList, int current, Set<Integer> visited) {
        if (visited.contains(current)) return 0;
        visited.add(current);

        int size = 1;
        for (int value : aList.get(current)) {
            size += count(aList, value, visited);
        }

        return size;
    }
}
