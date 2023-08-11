package com.abranlezama.structures.prefixtree;

public class Main {
    public static void main(String[] args) {
        PrefixTree trie = new PrefixTree();
        trie.insert("develop").insert("developing").insert("developer").insert("development")
                .insert("device").insert("apple").insert("application").insert("applicative")
                .insert("appletv").insert("ape").insert("band");

        System.out.println(trie.wordsWithPrefix("dev"));
    }
}
