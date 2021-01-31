package double_linked_list;

public class Load {

    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList1 = new DoubleLinkedList<>();
        doubleLinkedList1.addToHead(35);
        doubleLinkedList1.addToTail(67);
        doubleLinkedList1.addToTail(68);
        System.out.println(doubleLinkedList1);
        System.out.println(doubleLinkedList1.getHeadElement());
        System.out.println(doubleLinkedList1.getTailElement());

        DoubleLinkedList<Integer> doubleLinkedList2 = new DoubleLinkedList<>();
        doubleLinkedList2.addToHead(35);
        doubleLinkedList2.addToTail(67);
        doubleLinkedList2.addToTail(68);
        System.out.println(doubleLinkedList2);
        System.out.println(doubleLinkedList2.getHeadElement());
        System.out.println(doubleLinkedList2.getTailElement());

        System.out.println(doubleLinkedList1);
        System.out.println(doubleLinkedList2.toString().equals(doubleLinkedList1.toString()));
        System.out.println("Сравнение int - " + doubleLinkedList1.equals(doubleLinkedList2));

        ListItem<Integer>listItem = new ListItem<>(5);
        ListItem<Integer>listItem1 = new ListItem<>(5);

        System.out.println("Сравнение head - " + doubleLinkedList1.getHeadElement().equals(doubleLinkedList2.getHeadElement()));
        ListItem listItem2 = doubleLinkedList1.getTailElement();
        ListItem listItem3 = doubleLinkedList2.getTailElement();
        System.out.println("Сравнение tail - " + listItem2.equals(listItem3));
        System.out.println(listItem1.equals(listItem));

        DoubleLinkedList <String>doubleLinkedList5 = new DoubleLinkedList<>();
        doubleLinkedList5.addToTail("first");
        doubleLinkedList5.addToTail("second");
        doubleLinkedList5.addToTail("third");

        DoubleLinkedList <String>doubleLinkedList6 = new DoubleLinkedList<>();
        doubleLinkedList6.addToTail("first");
        doubleLinkedList6.addToTail("second");
        doubleLinkedList6.addToTail("third");
        System.out.println("Сравнение String - " + doubleLinkedList5.equals(doubleLinkedList6));
    }
}
