package com.abranlezama.algorithms.graph.kruskal;

import java.util.*;

public class Graph {
    public static class Edge implements Comparable<Edge> {
        char src, dest;
        int weight;

        public Edge(char src, char dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return src + "---" + weight + "---" + dest;
        }



        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }

    private static class Vertex {
        char name;
        int parent;

        Vertex(char name, int parent) {
            this.name = name;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return parent == vertex.parent;
        }

        @Override
        public int hashCode() {
            return Objects.hash(parent);
        }

        public char getName() {
            return name;
        }

        public void setName(char name) {
            this.name = name;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
    }

    private final Map<Character, Integer> bijection = new HashMap<>();
    private final Queue<Edge> priorityQueue;
    private Set<Edge> minimumSpanningTree = new HashSet<>();
    private Vertex[] id;
    private int[] compSize;
    private int numComponents;

    public Graph(List<Edge> edges) {
        createBijection(edges);
        priorityQueue = new PriorityQueue<>(edges);

        while (!priorityQueue.isEmpty() || numComponents > 1) {
            Edge edge = priorityQueue.poll();

            if (edge == null) break;

            char src = edge.src;
            char dest = edge.dest;

            if (unify(bijection.get(src), bijection.get(dest))) minimumSpanningTree.add(edge);
        }

        System.out.println(minimumSpanningTree.toString());
        System.out.println(Arrays.toString(compSize));
    }

    private void createBijection(List<Edge> edges) {
        int index = 0;
        for (Edge edge : edges) {
            if (!bijection.containsKey(edge.src)) bijection.put(edge.src, index++);
            if (!bijection.containsKey(edge.dest)) bijection.put(edge.dest, index++);
        }
        id = new Vertex[bijection.size()];
        compSize = new int[id.length];
        Arrays.fill(compSize, 1);
        numComponents = bijection.size();

        for (Map.Entry<Character, Integer> entry : bijection.entrySet()) {
            id[entry.getValue()] = new Vertex(entry.getKey(), entry.getValue());
        }

    }

    private int find(int p) {
        Vertex root = id[p];

        while (!id[root.parent].equals(root)) root = id[root.parent];

        Vertex temp = id[p];

        while (!temp.equals(root)) {
            temp.setParent(root.parent);
            bijection.put(temp.name, root.parent);
            temp = id[temp.parent];
        }

        return root.parent;
    }

    private boolean unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if (root1 == root2) return false;

        if (compSize[root1] < compSize[root2]) {
            compSize[root2] += compSize[root1];
            Vertex temp = id[root1];
            temp.setParent(root2);
            compSize[root1] = 0;
        } else {
            compSize[root1] += compSize[root2];
            Vertex temp = id[root2];
            temp.setParent(root1);
            compSize[root2] = 0;
        }

        numComponents--;
        return true;
    }
}
