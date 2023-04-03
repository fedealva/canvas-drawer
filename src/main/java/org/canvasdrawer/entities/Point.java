package org.canvasdrawer.entities;

public class Point {
    public final int x;
    public final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            return (x == ((Point) o).x && y == ((Point) o).y);
        }

        return false;
    }
}
