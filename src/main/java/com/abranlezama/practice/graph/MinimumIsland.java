package com.abranlezama.practice.graph;

import java.util.HashSet;
import java.util.Set;

public class MinimumIsland {
    public static void main(String[] args) {
        Set<Coordinate> visited = new HashSet<>();
        char[][] grid = {
                {'W', 'L', 'W', 'W', 'W'},
                {'W', 'L', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'L', 'W'},
                {'W', 'W', 'L', 'L', 'W'},
                {'L', 'W', 'W', 'L', 'L'},
                {'L', 'L', 'W', 'W', 'W'}
        };

        int minimumIsland = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int islandSize = explore(grid, row, col, visited);
                if (islandSize > 0 && islandSize < minimumIsland) minimumIsland = islandSize;
            }
        }

        System.out.println(minimumIsland);
    }

    private static int explore(char[][] grid, int row, int col, Set<Coordinate> visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) return 0;
        Coordinate coordinate = new Coordinate(row, col);
        if (grid[row][col] == 'W' || visited.contains(coordinate)) return 0;
        visited.add(coordinate);

        int size = 1;
        size += explore(grid, row - 1, col, visited);
        size += explore(grid, row, col + 1, visited);
        size += explore(grid, row + 1, col, visited);
        size += explore(grid, row, col - 1, visited);

        return size;
    }

    private record Coordinate(int row, int col) {};
}
