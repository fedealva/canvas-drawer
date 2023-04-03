package org.canvasdrawer.entities;

import org.canvasdrawer.exceptions.OutOfBoundsException;
import org.canvasdrawer.exceptions.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Canvas {

    private static final String EMPTY_CHAR = " ";
    private static final String LINE_CHAR = "x";
    private static final String HORIZONTAL_CHAR = "-";
    private static final String VERTICAL_CHAR = "|";

    private int width;
    private int height;
    private String[][] canvas;

    public void initialize(final int width, final int height) {
        this.width = width;
        this.height = height;

        canvas = new String[height][width];

        clean();
    }

    public void clean() {
        if (canvas == null) {
            throw new RuntimeException("Canvas not initialized");
        }

        Stream.of(canvas).forEach(r -> Arrays.fill(r, EMPTY_CHAR));
    }

    public void addLine(final Point p1, final Point p2) {
        validateCanvas();
        validateLine(p1, p2);

        // Calculates the direction in which we need to move to reach the target point
        // It will only be vertical or horizontal since it doesn't allow diagonal lines.
        final Point direction = new Point(
                Integer.signum(p2.x - p1.x),
                Integer.signum(p2.y - p1.y));

        Point currentPoint;
        Point nextPoint = p1;

        do {
            currentPoint = nextPoint;

            // Move to the next point towards the target point
            nextPoint = new Point(currentPoint.x + direction.x, currentPoint.y + direction.y);

            addPoint(currentPoint);
        } while (!currentPoint.equals(p2));
    }

    public void addRectangle(final Point p1, final Point p2) {
        validateCanvas();
        validateRectangle(p1, p2);

        final Point p3 = new Point(p2.x, p1.y);
        final Point p4 = new Point(p1.x, p2.y);

        addLine(p1, p3);
        addLine(p3, p2);
        addLine(p2, p4);
        addLine(p4, p1);
    }

    private void validateCanvas() {
        if (!isInitialized()) {
            throw new ValidationException("Canvas is not initialized");
        }
    }

    private void validateLine(final Point p1, final Point p2) {
        if (isOutsideCanvas(p1)) {
            throw new OutOfBoundsException(p1);
        }

        if (isOutsideCanvas(p2)) {
            throw new OutOfBoundsException(p2);
        }

        if (isDiagonal(p1, p2)) {
            throw new ValidationException(String.format("Diagonal lines are not supported %s -> %s", p1, p2));
        }
    }

    private void validateRectangle(final Point p1, final Point p2) {
        if (isOutsideCanvas(p1)) {
            throw new OutOfBoundsException(p1);
        }

        if (isOutsideCanvas(p2)) {
            throw new OutOfBoundsException(p2);
        }

        if (p1.x > p2.x || p1.y > p2.y) {
            throw new ValidationException(String.format("Point 1 %s needs to be the upper left corner of the rectangle: %s -> %s", p1, p1, p2));
        }
    }

    public boolean isInitialized() {
        return canvas != null;
    }

    private void addPoint(final Point p)
    {
        canvas[p.y - 1][p.x - 1] = LINE_CHAR;
    }

    private boolean isOutsideCanvas(final Point point) {
        return point.x <= 0 || point.x - 1>= width ||
                point.y <= 0 || point.y - 1 >= height;
    }

    private boolean isDiagonal(final Point p1, final Point p2) {
        return p1.x != p2.x &&
                p1.y != p2.y;
    }

    @Override
    public String toString() {
        final String horizontalBoundary = String.join("", Collections.nCopies(width + 2, HORIZONTAL_CHAR));

        StringBuilder sb = new StringBuilder(horizontalBoundary).append(System.lineSeparator());

        sb.append(Stream.of(canvas)
                .map(r -> VERTICAL_CHAR + String.join("", r) + VERTICAL_CHAR + System.lineSeparator())
                .collect(Collectors.joining()));

        sb.append(horizontalBoundary);

        return sb.toString();
    }
}
