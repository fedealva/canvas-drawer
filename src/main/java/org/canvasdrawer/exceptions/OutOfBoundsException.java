package org.canvasdrawer.exceptions;

import org.canvasdrawer.entities.Point;

public class OutOfBoundsException extends RuntimeException {

    public OutOfBoundsException(final Point p) {
        super(String.format("Point %s out of bounds", p));
    }
}
