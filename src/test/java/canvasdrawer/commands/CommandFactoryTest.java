package canvasdrawer.commands;

import org.canvasdrawer.commands.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandFactoryTest {

    @Test
    @DisplayName("When command doesn't exist should return null")
    public void invalidCommand() {
        assertNull(CommandFactory.getCommand("X"));
    }

    @Test
    @DisplayName("When command is C returns create canvas Command")
    public void createCanvasCommand() {
        assertInstanceOf(CreateCanvasCommand.class, CommandFactory.getCommand("C"));
    }

    @Test
    @DisplayName("When command is L returns add line command")
    public void addLineCommand() {
        assertInstanceOf(AddLineCommand.class, CommandFactory.getCommand("L"));
    }

    @Test
    @DisplayName("When command is R returns add rectangle command")
    public void addRectangleCommand() {
        assertInstanceOf(AddRectangleCommand.class, CommandFactory.getCommand("R"));
    }

    @Test
    @DisplayName("When command is Q returns quit command")
    public void quitCommand() {
        assertInstanceOf(QuitCommand.class, CommandFactory.getCommand("Q"));
    }
}
