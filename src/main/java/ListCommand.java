public class ListCommand extends Command {

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ui.showList(allTasks);
    }
}
