package barry.util;

import barry.command.*;

/**
 * Represents a parser that interprets user input and returns the corresponding command.
 *
 */
public class Parser {

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

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new MarkCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case "unmark":
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new UnmarkCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case "todo":
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    return new UnknownCommand("Unknown command!! I do not know this :( Description seems to be empty...");
                }
                return new TodoCommand(split[1]);
            case "deadline":
                try {
                    String[] parts = split[1].split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        return new UnknownCommand("Unknown command!! I do not know this :( Description seems to be empty...");
                    }
                    return new DeadlineCommand(parts[0].trim(), parts[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case "event":
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
            case "delete":
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    return new DeleteCommand(i);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
            case "find":
                if (split.length < 2 || split[1].trim().isEmpty()) {
                    return new UnknownCommand("Unknown command!! I do not know this :(");
                }
                return new FindCommand(split[1].trim());
            default:
                return new UnknownCommand("Unknown command!! I do not know this :(");
        }
    }
}

