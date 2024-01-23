package com.abranlezama.algorithms.graph.dijkstras;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addAdjacentNode(nodeB, 2);
        nodeA.addAdjacentNode(nodeC, 4);

        nodeB.addAdjacentNode(nodeC, 3);
        nodeB.addAdjacentNode(nodeD, 1);
        nodeB.addAdjacentNode(nodeE, 5);

        nodeC.addAdjacentNode(nodeD, 2);

        nodeD.addAdjacentNode(nodeE, 1);
        nodeD.addAdjacentNode(nodeF, 4);

        nodeE.addAdjacentNode(nodeF, 2);

        Dijkstras dijkstras = new Dijkstras();
        dijkstras.calculateShortestPath(nodeA);
        printPaths(nodeA);
        printPaths(nodeD);
        printPaths(nodeC);
        printPaths(nodeF);
    }

    private static void printPaths(Node node) {
        String path = node.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        System.out.println(path.isBlank() ? "%s : %s".formatted(node.getName(), node.getDistance())
                : "%s -> %s : %s".formatted(path, node.getName(), node.getDistance()));
    }
}
