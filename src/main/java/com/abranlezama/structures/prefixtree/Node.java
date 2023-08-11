package com.abranlezama.structures.prefixtree;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private final char character;
    private boolean isEndOfWord;
    private final Map<Character, Node> children = new HashMap<>();

    public Node(char character, boolean isEndOfWord) {
        this.character = character;
        this.isEndOfWord = isEndOfWord;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public Map<Character, Node> getChildren() {
        return children;
    }
}
