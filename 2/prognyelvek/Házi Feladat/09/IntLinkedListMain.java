import java.util.LinkedList;

public class IntLinkedListMain {
    public static void main(String[] args) {
        IntLinkedList intLinkedList1 = new IntLinkedList();
        intLinkedList1.add(1);
        intLinkedList1.add(2);
        intLinkedList1.add(3);
        intLinkedList1.add(4);

        IntLinkedList intLinkedList2 = new IntLinkedList();
        intLinkedList2.add(5);
        intLinkedList2.add(6);
        intLinkedList2.add(7);

        System.out.println("intLinkedList1: " + intLinkedList1);
        System.out.println("intLinkedList2: " + intLinkedList2);

        LinkedList<Integer> linkedList = intLinkedList1.getData();

        intLinkedList1.concat(intLinkedList2);
        System.out.println("result of concatenation in intLinkedList1: " + intLinkedList1);
        System.out.println("result of getData before concatenation: " + linkedList);

        intLinkedList2.removeItemsGreaterThan(6);
        System.out.println("result of removing items greater than 6: " + intLinkedList2);

        int[] array = {1,2,3,4,5};
        IntLinkedList intLinkedList3 = new IntLinkedList(array);
        System.out.println("intLinkedList3: " + intLinkedList3);
        array[3] = 9;
        System.out.println("array[3] = " + array[3]);
        System.out.println("data of intLinkedList3 is a copy: " + intLinkedList3);
    }    
}
