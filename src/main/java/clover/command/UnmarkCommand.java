package clover.command;

import clover.task.Task;
import clover.util.TaskList;
import clover.util.Ui;
import clover.util.Storage;
import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        try {
            Task task = allTasks.getTask(index);
            task.markAsUndone();
            ui.showUnmark(task);
            storage.save(allTasks.getAllTasks());
        } catch (IOException e) {
            ui.showError(" Oh no... This is an invalid task number!");;
        }
    }
}
