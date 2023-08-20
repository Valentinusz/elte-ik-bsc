public class Point implements Comparable<Point> {
    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point that) {
        if (this.x > that.x) {
            return 1;
        }

        if (this.x == that.x) {
            if (this.y > that.y) {
                return 1;
            }

            if (that.y > this.y) {
                return -1;
            }

            return 0;
        }

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(this.x);
        sb.append(",");
        sb.append(this.y);
        sb.append(")");
        return sb.toString();
    }
}
