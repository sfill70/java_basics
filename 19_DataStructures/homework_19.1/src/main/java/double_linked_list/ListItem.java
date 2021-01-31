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

 /*   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListItem<T> listItem = (ListItem<T>) o;
        return data.equals(listItem.data) && Objects.equals(prev, listItem.prev) && Objects.equals(next, listItem.next);
    }*/

    /*  @Override
      public int hashCode() {
          return Objects.hash(data, prev, next);
      }*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItem<?> listItem = (ListItem<?>) o;

        if (data != null ? !data.equals(listItem.data) : listItem.data != null) return false;
        if (prev != null ? !prev.data.equals(listItem.prev.data) : listItem.prev != null) return false;
        return next != null ? next.data.equals(listItem.next.data) : listItem.next == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (prev != null ? prev.data.hashCode() : 0);
        result = 31 * result + (next != null ? next.data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ListItem{data='" + data + "'}" + ", perv = " + (prev != null ? prev.data : 0) + ", next = " + (next != null ? next.data : 0);
    }


}