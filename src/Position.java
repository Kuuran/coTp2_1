public class Position {
    private double x, y;
    private static Position defaultPosition = new Position(0.0, 0.0);

    public static Position positionDefaut () {
	return defaultPosition;
    }

    public Position() {
        this.x = defaultPosition.x;
        this.y = defaultPosition.y;
    }

    public Position(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public double x() { return x; }

    public double y() { return y; }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String toString()  { return "(" + x + ", " + y +")"; }

}
