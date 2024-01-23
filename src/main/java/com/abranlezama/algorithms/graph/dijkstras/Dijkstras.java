package com.abranlezama.algorithms.graph.dijkstras;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstras {

    public void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes()
                    .entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey())) // help avoid cycles for undirected weighted graphs
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        int newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);

            adjacentNode.setShortestPath(
                    Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
            );
        }
    }
}
