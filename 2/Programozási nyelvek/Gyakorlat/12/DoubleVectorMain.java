import java.util.HashSet;

public class DoubleVectorMain {
    public static void main(String[] args)
    {
        System.out.println(new DoubleVector(2, 3).equals(new DoubleVector(2, 3)));
        System.out.println(new DoubleVector(2, 3).equals(new DoubleVector(2, 2)));

        HashSet<DoubleVector> exampleSet = new HashSet<DoubleVector>();
        exampleSet.add(new DoubleVector(0, 0));
        exampleSet.add(new DoubleVector(3, -7));
        exampleSet.add(new DoubleVector(3, -7));
        System.out.println( "size of HashSet: " + exampleSet.size());
        System.out.println( "items of HashSet: " + exampleSet);
    }
}
