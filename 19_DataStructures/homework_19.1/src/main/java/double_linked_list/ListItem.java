package double_linked_list;

import java.util.Objects;

public class ListItem<T> {
    private final T data;
    ListItem<T> prev;
    ListItem<T> next;

    public ListItem(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ListItem<T> getPrev() {
        return prev;
    }

    public void setPrev(ListItem<T> prev) {
        this.prev = prev;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListItem<T> listItem = (ListItem<T>) o;
        return data.equals(listItem.data) && Objects.equals(prev, listItem.prev) && Objects.equals(next, listItem.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, prev, next);
    }



    @Override
    public String toString() {
        return "ListItem{data='" + data + "'}";
    }


}