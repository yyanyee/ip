package clover.command;

import clover.task.Task;
import clover.util.TaskList;
import clover.util.Ui;
import clover.util.Storage;

import java.io.IOException;

public abstract class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        allTasks.addTask(task);
        ui.showAddTask(task, allTasks.size());
        storage.save(allTasks.getAllTasks());
    }
}
