package double_linked_list;

public class Main {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        System.out.println(doubleLinkedList.getSize());
        doubleLinkedList.addToTail(12);
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());
        doubleLinkedList.addToHead(72);
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());
        doubleLinkedList.addToTail(24);
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());
        System.out.println(doubleLinkedList.popTailElement());
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());
        doubleLinkedList.removeTailElement();
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());
//        System.out.println(doubleLinkedList.popHeadElement());
//        System.out.println(doubleLinkedList.popTailElement());
        doubleLinkedList.removeTailElement();
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());
       /* try {
            doubleLinkedList.removeHeadElement();
            doubleLinkedList.removeTailElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            doubleLinkedList.popTailElement();
            doubleLinkedList.popHeadElement();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        for (int i = 4; i < 400 ; i=i+4) {
            doubleLinkedList.addToHead(i);
            doubleLinkedList.addToTail(i+5);
        }

        ListItem<Integer> item = doubleLinkedList.getHeadElement();
        while (item.next != null) {
            System.out.print(item + " / ");
            item = item.next;
        }
        System.out.println();
        doubleLinkedList.getSize();

        DoubleLinkedList<Integer> doubleLinkedList1 = new DoubleLinkedList<>();
        doubleLinkedList1.addToHead(35);
        doubleLinkedList1.addToTail(67);
        doubleLinkedList1.addToTail(68);
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());

        DoubleLinkedList<Integer> doubleLinkedList2 = new DoubleLinkedList<>();
        doubleLinkedList2.addToHead(35);
        doubleLinkedList2.addToTail(67);
        doubleLinkedList2.addToTail(68);
        System.out.println(doubleLinkedList);
        System.out.println(doubleLinkedList.getHeadElement());
        System.out.println(doubleLinkedList.getTailElement());

        System.out.println(doubleLinkedList1);
        System.out.println(doubleLinkedList2.toString().equals(doubleLinkedList1.toString()));
//        System.out.println(doubleLinkedList1.equals(doubleLinkedList2));

        DoubleLinkedList<String> doubleLinkedList4 = new DoubleLinkedList<>();
        doubleLinkedList4.addToTail("first");
        doubleLinkedList4.addToTail("second");
        doubleLinkedList4.addToTail("third");
        doubleLinkedList4.removeTailElement();
        System.out.println(doubleLinkedList4.getTailElement().getData());
        System.out.println(doubleLinkedList4);
        System.out.println(doubleLinkedList4.getHeadElement());
        System.out.println(doubleLinkedList4.getTailElement());

    }
}
