package de.pkro;

public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem item) {
        this.root = item;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem item) {
        // addItem(), takes a ListItem and returns true if it was added successfully or false otherwise. If the item is already present, it doesn't get added. Use compareTo() to place the item in its proper order.
        ListItem current = root;
        if (current == null) {
            root = item;
            return true;
        }

        while (current != null) {
            if (current.compareTo(item) == 0) {
                return false;
            }

            if (current.compareTo(item) > 0) {// current > item
                if (current == root) {
                    root = item;
                }
                item.setPrevious(current.previous());
                item.setNext(current);
                current.setPrevious(item);
                return true;
            }

            if (current.compareTo(item) < 0 && (current.next() == null || current.next().compareTo(item) > 0)) {
                item.setNext(current.next());
                item.setPrevious(current);
                current.setNext(item);
                return true;
            }

            current = current.next();
        }

        return true; // it should never come to this but intellij complains otherwise
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem current = root;
        while (current != null) {
            if (current.compareTo(item) == 0) {
                if (current == root) {
                    root = current.next();
                    return true;
                }
                if (current.next() != null) {
                    current.next().setPrevious(current.previous());
                }
                if (current.previous() != null) {
                    current.previous().setNext(current.next());
                }
                return true;
            }
            current = current.next();
        }
        return false;
    }

    @Override
    public void traverse(ListItem item) {
        if(item == null) {
            System.out.println("The list is empty");
            return;
        }
        while (item != null) {
            System.out.println(item.getValue());
            item = item.next(); // don't do in production, will overflow stack
        }
    }
}
