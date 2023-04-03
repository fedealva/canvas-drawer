package canvasdrawer.entities;

import org.canvasdrawer.entities.Canvas;
import org.canvasdrawer.entities.Point;
import org.canvasdrawer.exceptions.OutOfBoundsException;
import org.canvasdrawer.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasTest {

    private Canvas canvas;

    @BeforeEach
    public void setup() {
        canvas = new Canvas();
        canvas.initialize(20, 4);
    }

    @Test
    @DisplayName("Canvas initialize")
    public void initializeCanvas() {
        assertEquals(canvas.toString(), String.join(System.lineSeparator(),
                "----------------------",
                "|                    |",
                "|                    |",
                "|                    |",
                "|                    |",
                "----------------------"));
    }

    @Test
    @DisplayName("When canvas is initialize should return true")
    public void isInitialized() {
        assertTrue(canvas.isInitialized());
    }

    @Test
    @DisplayName("When canvas is not initialize should return false")
    public void inNotInitialized() {
        Canvas canvas = new Canvas();
        assertFalse(canvas.isInitialized());
    }

    @Test
    @DisplayName("Clean canvas")
    public void cleanCanvas() {
        canvas.addLine(new Point(1, 1), new Point(1, 3));
        canvas.clean();
        assertEquals(canvas.toString(), String.join(System.lineSeparator(),
                "----------------------",
                "|                    |",
                "|                    |",
                "|                    |",
                "|                    |",
                "----------------------"));
    }

    @Test
    @DisplayName("Add horizontal line")
    public void addHorizontalLine() {
        final Point p1 = new Point(1, 2);
        final Point p2 = new Point(6, 2);
        String result = String.join(System.lineSeparator(),
                "----------------------",
                "|                    |",
                "|xxxxxx              |",
                "|                    |",
                "|                    |",
                "----------------------");

        canvas.addLine(p1, p2);
        assertEquals(result, canvas.toString());
    }

    @Test
    @DisplayName("Add reversed horizontal line")
    public void addReversedHorizontalLine() {
        Point p1 = new Point(6, 2);
        Point p2 = new Point(1, 2);
        String result = String.join(System.lineSeparator(),
                "----------------------",
                "|                    |",
                "|xxxxxx              |",
                "|                    |",
                "|                    |",
                "----------------------");

        canvas.addLine(p1, p2);
        assertEquals(result, canvas.toString());
    }

    @Test
    @DisplayName("Add vertical line")
    public void addVerticalLine() {
        Point p1 = new Point(6, 3);
        Point p2 = new Point(6, 4);
        String result = String.join(System.lineSeparator(),
                "----------------------",
                "|                    |",
                "|                    |",
                "|     x              |",
                "|     x              |",
                "----------------------");

        canvas.addLine(p1, p2);
        assertEquals(result, canvas.toString());
    }

    @Test
    @DisplayName("Add reversed vertical line")
    public void addReversedVerticalLine() {
        Point p1 = new Point(6, 4);
        Point p2 = new Point(6, 3);
        String result = String.join(System.lineSeparator(),
                "----------------------",
                "|                    |",
                "|                    |",
                "|     x              |",
                "|     x              |",
                "----------------------");

        canvas.addLine(p1, p2);
        assertEquals(result, canvas.toString());
    }

    @Test
    @DisplayName("Add rectangle")
    public void addRectangle() {
        Point p1 = new Point(16, 1);
        Point p2 = new Point(20, 3);

        String result = String.join(System.lineSeparator(),
                "----------------------",
                "|               xxxxx|",
                "|               x   x|",
                "|               xxxxx|",
                "|                    |",
                "----------------------");

        canvas.addRectangle(p1, p2);
        assertEquals(result, canvas.toString());
    }

    @Test
    @DisplayName("When line out of bounds throws exception")
    public void addLineOutOfBoundaries() {
        assertThrows(OutOfBoundsException.class,
                () -> canvas.addLine(new Point(1, 2), new Point(1, 5)));

        assertThrows(OutOfBoundsException.class,
                () -> canvas.addLine(new Point(0, 3), new Point(10, 3)));
    }

    @Test
    @DisplayName("When line is diagonal throws exception")
    public void addDiagonalLine() {
        assertThrows(ValidationException.class,
                () -> canvas.addLine(new Point(1, 2), new Point(4, 4)));
    }

    @Test
    @DisplayName("When rect out of bounds throws exception")
    public void addRectOutOfBoundaries() {
        assertThrows(OutOfBoundsException.class,
                () -> canvas.addRectangle(new Point(1, 2), new Point(1, 5)));

        assertThrows(OutOfBoundsException.class,
                () -> canvas.addRectangle(new Point(0, 3), new Point(10, 3)));
    }

    @Test
    @DisplayName("When p1 is lower left corner of rectangle throws exception")
    public void rectP1IsLowerLeft() {
        assertThrows(ValidationException.class,
                () -> canvas.addRectangle(new Point(2, 2), new Point(3, 1)));

    }

    @Test
    @DisplayName("When p1 is lower right corner of rectangle throws exception")
    public void rectP1IsLowerRight() {
        assertThrows(ValidationException.class,
                () -> canvas.addRectangle(new Point(2, 2), new Point(1, 1)));
    }

    @Test
    @DisplayName("When p1 is upper right corner of rectangle throws exception")
    public void rectP1IsUpperRight() {
        assertThrows(ValidationException.class,
                () -> canvas.addRectangle(new Point(2, 2), new Point(1, 3)));
    }
}
