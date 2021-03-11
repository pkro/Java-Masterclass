package de.pkro;

public abstract  class ListItem {
    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object obj) {
        value = obj;
    }

    abstract ListItem next();

    abstract ListItem previous();

    abstract ListItem setNext(ListItem item);

    abstract ListItem setPrevious(ListItem item);

    abstract int compareTo(ListItem item);
}
