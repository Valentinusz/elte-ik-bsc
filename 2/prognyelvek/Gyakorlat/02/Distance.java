public class Distance {
    public static void main(String[] args) {
        double sum = 0;
        for (int i=0; i<args.length-2; i+=2) {
            Point current = new Point(Double.parseDouble(args[i]), Double.parseDouble(args[i+1]));
            Point neighbour = new Point(Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]));
            sum += current.distance(neighbour);
        }
        System.out.println(sum);
    }
}