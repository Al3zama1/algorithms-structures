package com.abranlezama.structures.trie;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("develop").insert("developing").insert("developer").insert("development")
                .insert("device").insert("apple").insert("application").insert("applicative")
                .insert("appletv").insert("ape").insert("band");

        System.out.println(trie.wordsWithPrefix("ap"));
    }
}
