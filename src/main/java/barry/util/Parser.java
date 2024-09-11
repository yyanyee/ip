package barry.util;

import barry.command.*;

/**
 * Represents a parser that interprets user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Enumeration for the possible commands.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, HELP, UNKNOWN
    }

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param input The user's input.
     * @return The command corresponding to the user's input.
     */
    public static Command parse(String input) {
        assert input != null : "command cannot be null!";
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.UNKNOWN;
        }

        switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new MarkCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case UNMARK:
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new UnmarkCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case TODO:
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    return new UnknownCommand("Unknown command!! I do not know this :( Description seems to be empty...");
                }
                return new TodoCommand(split[1]);
            case DEADLINE:
                try {
                    String[] parts = split[1].split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        return new UnknownCommand("Unknown command!! I do not know this :( Description seems to be empty...");
                    }
                    return new DeadlineCommand(parts[0].trim(), parts[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case EVENT:
                try {
                    String[] parts = split[1].split(" /from ");
                    if (parts.length < 2) {
                        return new UnknownCommand("Unknown command!! I do not know this :( Description seems to be empty...");
                    }
                    String[] times = parts[1].split(" /to ");
                    if (times.length < 2) {
                        return new UnknownCommand("Unknown command!! I do not know this :( Time seems to be empty...");
                    }
                    return new EventCommand(parts[0].trim(), times[0].trim(), times[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case DELETE:
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new DeleteCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case FIND:
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
                return new FindCommand(split[1].trim());
            case HELP:
                return new HelpCommand();
            default:
                return new UnknownCommand("Unknown command!! I do not know this :(");
        }
    }
}
