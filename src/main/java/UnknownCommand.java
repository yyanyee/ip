public class UnknownCommand extends Command {
    private String msg;

    public UnknownCommand(String msg) {
        this.msg = msg;
    }

    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) {
        ui.showError(msg);
    }
}
