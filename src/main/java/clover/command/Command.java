package clover.command;
import clover.util.TaskList;
import clover.util.Ui;
import clover.util.Storage;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
