package de.pkro;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        //MyLinkedList list = myLinkedListFrom("4 5 2 9 6 1 3 1 4 0");
        MyLinkedList list = myLinkedListFrom("Offenbach Aschaffenburg Hamburg Köln");
        //MyLinkedList list = myLinkedListFrom("5 3 4 2 1"); // works
        //MyLinkedList list = myLinkedListFrom("1 2 3 4 5"); // works
        list.traverse(list.getRoot());
        System.out.println("================");
        list.removeItem(new Node("Hamburg"));
        list.removeItem(new Node("0"));
        list.traverse(list.getRoot());

        SearchTree tree = mySearchTreeFrom("Offenbach Aschaffenburg Hamburg Köln");
        tree.traverse(tree.getRoot());
    }

    public static MyLinkedList myLinkedListFrom(String spaceSeparatedItems) {
        String[] items = spaceSeparatedItems.split(" ");
        MyLinkedList list = new MyLinkedList(null);
        for (String item : items) {
            list.addItem(new Node(item));
        }
        return list;
    }

    public static SearchTree mySearchTreeFrom(String spaceSeparatedItems) {
        String[] items = spaceSeparatedItems.split(" ");
        SearchTree list = new SearchTree(null);
        for (String item : items) {
            list.addItem(new Node(item));
        }
        return list;
    }
}
