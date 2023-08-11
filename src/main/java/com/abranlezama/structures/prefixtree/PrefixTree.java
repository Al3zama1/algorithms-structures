package com.abranlezama.structures.prefixtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixTree {

    private final Node root;

    public PrefixTree() {
        root = new Node(' ', false);
    }

    public PrefixTree insert(String word) {
        Node currentNode = root;
        Map<Character, Node> children = root.getChildren();

        for (char c : word.toCharArray()) {
            if (children.containsKey(c)) currentNode = children.get(c);
            else {
                currentNode = new Node(c, false);
                children.put(c, currentNode);
            }
            children = currentNode.getChildren();
        }
        currentNode.setEndOfWord(true);
        return this;
    }

    public boolean contains(String word) {
        Node lastPresentNode = search(word);
        return lastPresentNode != null && lastPresentNode.isEndOfWord();
    }

    public boolean containsPrefix(String prefix) {
        return search(prefix) != null;
    }

    public List<String> wordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        Node prefixNode = search(prefix);
        if (prefixNode !=  null) addWords(prefixNode, prefix, words);

        return words;
    }

    private void addWords(Node node, String word, List<String> wordList) {
        if (node.isEndOfWord()) wordList.add(word);
        node.getChildren().values().forEach(child -> {
            String character = String.valueOf(child.getCharacter());
            addWords(child, word.concat(character), wordList);
        });
    }

    public void delete(String word) {
        List<Node> list = new ArrayList<>();
        Map<Character, Node> children = root.getChildren();
        for (char c : word.toCharArray()) {
            if (!children.containsKey(c)) break;
            Node currentNode = children.get(c);
            children = currentNode.getChildren();
            list.add(currentNode);
        }
        if (list.isEmpty() || list.size() != word.length()) return;
        list.get(list.size() - 1).setEndOfWord(false);

        for (int i = list.size() - 1; i > 0; i--) {
            Node currentNode = list.get(i);
            if (currentNode.isEndOfWord()) break;
            else if (currentNode.getChildren().isEmpty()) {
                list.get(i - 1).getChildren().remove(currentNode.getCharacter());
            }
        }
    }

    private Node search(String str) {
        Node currentNode = null;
        Map<Character, Node> children = root.getChildren();
        for (char c : str.toCharArray()) {
            if (!children.containsKey(c)) return null;
            currentNode = children.get(c);
            children = currentNode.getChildren();
        }
        return currentNode;
    }
}
