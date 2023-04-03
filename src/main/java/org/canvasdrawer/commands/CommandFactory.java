package org.canvasdrawer.commands;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CommandFactory {

    private static final String SEPARATOR = "\t\t";

    private static final HashMap<String, Command> commands = (HashMap<String, Command>) Stream.of(
            new AbstractMap.SimpleEntry<String, Command>("C", new CreateCanvasCommand()),
            new AbstractMap.SimpleEntry<String, Command>("L", new AddLineCommand()),
            new AbstractMap.SimpleEntry<String, Command>("R", new AddRectangleCommand()),
            new AbstractMap.SimpleEntry<String, Command>("Q", new QuitCommand())
    ).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    public static Command getCommand(final String command) {
        return commands.get(command);
    }

    public static void printHelp() {
        final StringBuilder sb = new StringBuilder("Available commands: ").append(System.lineSeparator());
        for (String command : commands.keySet()) {
            sb.append(command);

            String[] help = commands.get(command).help().split(System.lineSeparator());
            for (String line : help) {
                sb.append(SEPARATOR).append(line).append(System.lineSeparator());
            }

            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
