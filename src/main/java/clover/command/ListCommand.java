package clover.command;

import clover.util.TaskList;
import clover.util.Ui;
import clover.util.Storage;
public class ListCommand extends Command {

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ui.showList(allTasks);
    }
}
