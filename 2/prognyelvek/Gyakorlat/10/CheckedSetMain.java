public class CheckedSetMain {
    public static void main(String[] args) {
        CheckedSet<Integer> checkedSet = new CheckedSet<>();
        try {
            checkedSet.add(1);

            System.out.println(checkedSet.size());

            checkedSet.add(2);

            System.out.println(checkedSet.size());
            System.out.println(checkedSet.contains(1));
            System.out.println(checkedSet.contains(3));

            checkedSet.add(1);
        } catch (AlreadyContainedException e) {
            System.out.println("Element already in set.");
        }

    }
}
