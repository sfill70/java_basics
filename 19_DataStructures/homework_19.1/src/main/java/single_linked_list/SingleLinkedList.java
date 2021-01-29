package single_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList {
    private ListItem top;

    public void push(ListItem item) {
        if (top != null) {
            item.setNext(top);
        }
        top = item;
    }

    public ListItem pop() {
        ListItem item = top;
        if (top != null) {
            top = top.getNext();
            item.setNext(null);
        }
        return item;
    }

    public void removeTop() {
        if (top != null) {
            top = top.getNext();
        }
    }
//IndexOutOfBoundsException
//NoSuchElementException
/*
    public void removeLast() {
        ListItem previous;
        ListItem item;
        if(top == null)
        {
            throw new NoSuchElementException("Empty list.");
        }
        else if(top.getNext() == null)
        {
            top = null;
        }
        else
        {
            previous = top;
            item = top.getNext();

            while(item.getNext() != null)
            {
                previous = item;
                item = item.getNext();
            }
            previous.setNext(null);
        }
    }
*/

    public void removeLast() {
        if (top != null) {
            if (top.getNext() != null) {
                ListItem previous = top;
                ListItem item = top.getNext();
                while (item.getNext() != null) {
                    previous = item;
                    item = item.getNext();
                }
                previous.setNext(null);
            } else {
                top = null;
            }
        } else {
            throw new NoSuchElementException("Empty list.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleLinkedList that = (SingleLinkedList) o;
        return Objects.equals(top, that.top);
    }

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "top=" + top +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(top);
    }
}