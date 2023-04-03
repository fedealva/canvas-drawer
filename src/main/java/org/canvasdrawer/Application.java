package org.canvasdrawer;

import org.canvasdrawer.commands.CanvasCommand;
import org.canvasdrawer.commands.Command;
import org.canvasdrawer.commands.CommandFactory;
import org.canvasdrawer.commands.QuitCommand;
import org.canvasdrawer.entities.Canvas;
import org.canvasdrawer.exceptions.InvalidParametersException;

import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private static Scanner scanner;
    private static Canvas canvas;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        canvas = new Canvas();

        CommandFactory.printHelp();

        while(true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            processInput(input);
        }
    }

    private static void processInput(final String input) {
        final String[] inputArray = input.split(" ");
        final String userCommand = inputArray[0];
        final String[] params = Arrays.copyOfRange(inputArray, 1, inputArray.length);

        final Command command = CommandFactory.getCommand(userCommand);

        if (command == null) {
            System.out.println("Invalid command\n");
            CommandFactory.printHelp();
            return;
        }

        if (command instanceof QuitCommand) {
            exit();
        }

        if (command instanceof CanvasCommand) {
            try {
                ((CanvasCommand) command).execute(canvas, params);

                if (canvas.isInitialized()) System.out.println(canvas);
            } catch (InvalidParametersException invalidParametersException) {
                System.out.println(invalidParametersException.getMessage());
                System.out.println(command.help());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void exit() {
        scanner.close();
        System.out.println("Good bye");
        System.exit(0);
    }
}