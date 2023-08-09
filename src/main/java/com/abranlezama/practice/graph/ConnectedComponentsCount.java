package com.abranlezama.practice.graph;

import java.util.*;

public class ConnectedComponentsCount {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> aList = Map.of(
                3, List.of(),
                4, List.of(6),
                6, List.of(4, 5, 7, 8),
                8, List.of(6),
                7, List.of(6),
                5, List.of(6),
                1, List.of(2),
                2, List.of(1)
        );

        int countStack = connectedStack(aList);
        System.out.println(countStack);

        // performed using recursion
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int key : aList.keySet()) {
            count += connectedRecursion(aList, key, visited);
        }
        System.out.println(count);
    }

    private static int connectedRecursion(Map<Integer, List<Integer>> aList, int current, Set<Integer> visited) {
        if (visited.contains(current)) return 0;
        visited.add(current);

        for (int value : aList.get(current)) connectedRecursion(aList, value, visited);

        return 1;
    }

    private static int connectedStack(Map<Integer, List<Integer>> alyst) {
        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int key : alyst.keySet()) {
            stack.add(key);
            if (!set.contains(key)) count++;

            while (!stack.isEmpty()) {
                for (int i : alyst.get(stack.pop())) {
                    if (!set.contains(i)) stack.push(i);
                    set.add(i);
                }
            }
        }
        return count;
    }

}
