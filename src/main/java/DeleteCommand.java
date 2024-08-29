import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        try {
            Task removed = allTasks.removeTask(index);
            ui.showDeleteTask(removed, allTasks.size());
            storage.save(allTasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError(" Oh no... This is an invalid task number!");;
        }
    }
}
