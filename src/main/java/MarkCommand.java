import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        try {
            Task task = allTasks.getTask(index);
            task.markAsDone();
            ui.showMark(task);
            storage.save(allTasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError(" Oh no... This is an invalid task number!");;
        }
    }
}
