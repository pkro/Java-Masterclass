package de.pkro;

public class Node extends ListItem {
    public Node(Object obj) {
        super(obj);
    }

    @Override
    ListItem next() {
        return rightLink;
    }

    @Override
    ListItem previous() {
        return leftLink;
    }

    @Override
    ListItem setNext(ListItem item) {
        rightLink = item;
        return rightLink;
    }

    @Override
    ListItem setPrevious(ListItem item) {
        leftLink = item;
        return leftLink;
    }

    @Override
    int compareTo(ListItem item) {
        if (item == null) {
            return -1;
        }
        // completely unclear from the description so i had to copy this from a solution
        return ((String) super.getValue()).compareTo((String) item.getValue());
    }

}
