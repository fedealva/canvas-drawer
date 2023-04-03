package org.canvasdrawer.commands;

import org.canvasdrawer.entities.Canvas;
import org.canvasdrawer.entities.Point;
import org.canvasdrawer.exceptions.InvalidParametersException;

import java.util.stream.Stream;

public class AddRectangleCommand implements CanvasCommand {
    @Override
    public void execute(final Canvas canvas, final String[] params) {
        validate(params);

        final Point p1 = new Point(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        final Point p2 = new Point(Integer.parseInt(params[2]), Integer.parseInt(params[3]));

        canvas.addRectangle(p1, p2);
    }

    @Override
    public String help() {
        return "Adds a rectangle to a canvas." + System.lineSeparator() +
                "R x1 y1 x2 y2" + System.lineSeparator() +
                "x1" + "\t\t" + "Position on the x axis for the upper left corner of the rectangle. Must be a number greater than 0." + System.lineSeparator() +
                "y1" + "\t\t" + "Position on the y axis for the upper left corner of the rectangle. Must be a number greater than 0." + System.lineSeparator() +
                "x2" + "\t\t" + "Position on the x axis for the lower right corner of the rectangle. Must be a number greater than 0." + System.lineSeparator() +
                "y2" + "\t\t" + "Position on the y axis for the lower right corner of the rectangle. Must be a number greater than 0." + System.lineSeparator() +
                System.lineSeparator() +
                "e.g.: R 1 2 4 10";
    }

    private void validate(final String[] params) {
        if (params.length != 4) {
            throw new InvalidParametersException();
        }

        if (!Stream.of(params).allMatch(p -> p.matches("^[0-9]+$")))
            throw new InvalidParametersException();
    }
}
