public class IntListMain {
    public static void main(String[] args) {
        IntList intList1 = new IntList(7);
        intList1.add(1);
        intList1.add(2);
        intList1.add(3);
        intList1.add(4);

        IntList intList2 = new IntList(4);
        intList2.add(5);
        intList2.add(6);
        intList2.add(7);

        System.out.println(intList1);
        System.out.println(intList2);

        intList1.concat(intList2);
        System.out.println(intList1);

        intList1.removeItemsGreaterThan(4);
        System.out.println(intList1);
    }
}
