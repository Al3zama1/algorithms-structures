package com.abranlezama.structures.linkedlist;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> doubly = new DoublyLinkedList<>();
        SinglyLinkedList<Integer> singly = new SinglyLinkedList<>();

        doubly.add(3);
        doubly.add(2);
        doubly.add(1);
        doubly.removeFirst();
        doubly.removeLast();
        doubly.remove(2);
        System.out.println(doubly);

        singly.add(3);
        singly.add(2);
        singly.add(1);
        singly.removeFirst();
        singly.removeLast();
        singly.remove(2);
        System.out.println(singly);
    }
}
