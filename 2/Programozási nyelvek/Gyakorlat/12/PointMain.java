import java.util.ArrayList;
import java.util.Collections;

public class PointMain {
    public static void main(String[] args) {
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(1, 5));
        list.add(new Point(2, 5));
        list.add(new Point(5, 5));
        list.add(new Point(5, 7));
        list.add(new Point(5, 5));
        list.add(new Point(1, 7));
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
