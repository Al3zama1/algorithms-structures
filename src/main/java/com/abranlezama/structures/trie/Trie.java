package com.abranlezama.structures.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private final Node root;
    public Trie() {
        root = new Node(' ');
    }

    public Trie insert(String word) {
        Node currentNode = root;
        Map<Character, Node> children = root.getChildren();
        for (char c : word.toCharArray()) {
            if (children.containsKey(c)) currentNode = children.get(c);
            else {
                currentNode = new Node(c);
                children.put(c, currentNode);
            }
            children = currentNode.getChildren();
        }
        currentNode.setEndOfWord(true);
        return this;
    }

    // checks if trie contains a word
    public boolean contains(String word) {
        Node lastPresentNode = search(word);
        return lastPresentNode != null && lastPresentNode.isEndOfWord();
    }

    public boolean containsPrefix(String prefix) {
        return search(prefix) != null;
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

    public List<String> wordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        Node prefixNode = search(prefix);
        if (prefixNode != null) {
            addWords(prefixNode, prefix, words);
        }

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
            if (!children.containsKey(c)) return;
            Node currentNode = children.get(c);
            children = currentNode.getChildren();
            list.add(currentNode);
        }
        if (list.isEmpty() || list.size() != word.length()) return;
        list.get(list.size() - 1).setEndOfWord(false);

        // remove not needed nodes that do not have children from bottom up
        for (int i = list.size() - 1; i > 0; i--) {
            Node current = list.get(i);
            if (current.isEndOfWord()) break;
            else if (current.getChildren().isEmpty()) {
                list.get(i - 1).getChildren().remove(current.getCharacter());
            }
        }
    }

}
