import java.util.ArrayList;

public class IntArrayListMain {
    public static void main(String[] args) {
        IntArrayList IntArrayList1 = new IntArrayList();
        IntArrayList1.add(1);
        IntArrayList1.add(2);
        IntArrayList1.add(3);
        IntArrayList1.add(4);

        IntArrayList IntArrayList2 = new IntArrayList();
        IntArrayList2.add(5);
        IntArrayList2.add(6);
        IntArrayList2.add(7);

        System.out.println("IntArrayList1: " + IntArrayList1);
        System.out.println("IntArrayList2: " + IntArrayList2);

        ArrayList<Integer> linkedList = IntArrayList1.getData();

        IntArrayList1.concat(IntArrayList2);
        System.out.println("result of concatenation in IntArrayList1: " + IntArrayList1);
        System.out.println("result of getData before concatenation: " + linkedList);

        IntArrayList2.removeItemsGreaterThan(6);
        System.out.println("result of removing items greater than 6: " + IntArrayList2);

        int[] array = {1,2,3,4,5};
        IntArrayList IntArrayList3 = new IntArrayList(array);
        System.out.println("IntArrayList3: " + IntArrayList3);
        array[3] = 9;
        System.out.println("array[3] = " + array[3]);
        System.out.println("data of IntArrayList3 is a copy: " + IntArrayList3);
    }    
}
