package com.abranlezama.algorithms.graph.dijkstras;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {
    private final String name;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    private final Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
