package com.abranlezama.algorithms.graph.kruskal;

import java.util.ArrayList;
import java.util.List;

// minimum spanning tree algorithm
public class Kruskal {
    public static void main(String[] args) {
        List<Graph.Edge> edges = new ArrayList<>();


        edges.add(new Graph.Edge('A', 'B', 5));
        edges.add(new Graph.Edge('A', 'D', 4));
        edges.add(new Graph.Edge('A', 'E', 1));

        edges.add(new Graph.Edge('B', 'D', 2));
        edges.add(new Graph.Edge('B', 'C', 4));

        edges.add(new Graph.Edge('D', 'E', 2));
        edges.add(new Graph.Edge('D', 'F', 5));
        edges.add(new Graph.Edge('D', 'G', 11));
        edges.add(new Graph.Edge('D', 'H', 2));


        edges.add(new Graph.Edge('E', 'F', 1));

        edges.add(new Graph.Edge('F', 'G', 7));

        edges.add(new Graph.Edge('C', 'H', 4));
        edges.add(new Graph.Edge('C', 'I', 1));
        edges.add(new Graph.Edge('C', 'J', 2));

        edges.add(new Graph.Edge('I', 'J', 0));
        edges.add(new Graph.Edge('I', 'G', 4));
        edges.add(new Graph.Edge('I', 'H', 6));

        edges.add(new Graph.Edge('G', 'H', 1));

        Graph graph = new Graph(edges);
    }
}
