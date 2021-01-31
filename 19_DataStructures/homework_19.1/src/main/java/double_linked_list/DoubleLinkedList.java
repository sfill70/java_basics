package double_linked_list;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoubleLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    private int size;

    public ListItem<T> getHeadElement() {
        return head;
    }

    public ListItem<T> getTailElement() {
        return tail;
    }

    public ListItem<T> popHeadElement() {
        if (head != null) {
            if (head.getNext() == null) {
                return returnExtremeItem();
            } else {
                ListItem<T> listItem = head;
                head = listItem.getNext();
                head.setPrev(null);
                size--;
                return listItem;
            }
        } else {
            throw new NoSuchElementException("Empty list.");
        }
    }


    public ListItem<T> popTailElement() {
        if (tail.getPrev() == null) {
            return returnExtremeItem();
        } else {
            ListItem<T> listItem = tail;
            tail = listItem.getPrev();
            tail.setNext(null);
            size--;
            return listItem;
        }
    }

    public void removeHeadElement() {
        if (head != null) {
            if (head.getNext() == null) {
                removeExtremeItem();
            } else {
                head = head.getNext();
                head.setPrev(null);
                size--;
            }
        } else {
            throw new NoSuchElementException("Empty list.");
        }
    }

    public void removeTailElement() {
        if (tail.getPrev() == null) {
            removeHeadElement();
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
        }
    }

    public void addToHead(T data) {
        if (data != null) {
            if (head == null) {
                head = new ListItem<T>(data);
                tail = head;
            } else {
                ListItem<T> listItem = new ListItem<>(data);
                head.setPrev(listItem);
                listItem.setNext(head);
                head = listItem;
            }
            size++;
        } else {
            throw new NullPointerException("data can't be null ");
        }
    }

    public void addToTail(T data) {
        if (data != null) {
            if (head == null) {
                addToHead(data);
            } else {
                ListItem<T> listItem = new ListItem<>(data);
                listItem.setPrev(tail);
                tail.setNext(listItem);
                tail = listItem;
                size++;
            }
        } else {
            throw new NullPointerException("data can't be null ");
        }
    }

    public int getSize() {
        return size;
    }

    private ListItem<T> returnExtremeItem() {
        ListItem<T> listItem = new ListItem<>(head.getData());
        removeExtremeItem();
        return listItem;
    }

    private void removeExtremeItem() {
        head = null;
        tail = null;
        size--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<T> that = (DoubleLinkedList<T>) o;
        return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }


    @Override
    public String toString() {
        if (head == null) {
            return "DoubleLinkedList is empty size = " + size;
        }
        StringBuilder stringBuilder = new StringBuilder(head.toString());
        ListItem<T> item = head;
        while (item.next != null) {
            if (item.next.prev == item) {
                stringBuilder.append("<-");
            }
            stringBuilder.append(" -> ").append(item.next);
            item = item.next;
        }
        return "DoubleLinkedList{size=" + size + "\n" + stringBuilder.toString() + "}";
    }
}