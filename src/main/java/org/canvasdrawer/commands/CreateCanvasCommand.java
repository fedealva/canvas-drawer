package org.canvasdrawer.commands;

import org.canvasdrawer.entities.Canvas;
import org.canvasdrawer.exceptions.InvalidParametersException;

import java.util.stream.Stream;

public class CreateCanvasCommand implements CanvasCommand {
    @Override
    public void execute(final Canvas canvas, final String[] params) {

        validate(params);

        canvas.initialize(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
    }

    @Override
    public String help() {
        return "Creates and initializes a canvas." + System.lineSeparator() +
                "C w h" + System.lineSeparator() +
                "w" + "\t\t" + "Width of the canvas. Must be a number greater than 0." + System.lineSeparator() +
                "h" + "\t\t" + "Heigt of the canvas. Must be a number greater than 0." + System.lineSeparator() +
                System.lineSeparator() +
                "e.g.: C 20 10";
    }

    private void validate(final String[] params) {
        if (params.length != 2) {
            throw new InvalidParametersException();
        }

        if (!Stream.of(params).allMatch(p -> p.matches("^[0-9]+$")))
            throw new InvalidParametersException();
    }
}
