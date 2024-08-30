package clover.command;

import clover.util.TaskList;
import clover.util.Ui;
import clover.util.Storage;

import java.io.IOException;

/**
 * Represents a command that exits the chatbot
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit.
     * Displays a goodbye message to the user.
     *
     *
     * @param allTasks The list of all tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
