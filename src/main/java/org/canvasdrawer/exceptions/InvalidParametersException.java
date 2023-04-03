package org.canvasdrawer.exceptions;

public class InvalidParametersException extends RuntimeException {

    public InvalidParametersException() {
        super("Invalid command parameters");
    }
}
