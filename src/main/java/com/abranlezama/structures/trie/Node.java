package com.abranlezama.structures.trie;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private final char character;
    private boolean isEndOfWord;
    private final Map<Character, Node> children = new HashMap<>();

    public Node(char character) {
        this.character = character;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }
}
