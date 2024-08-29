import java.io.IOException;

public class Clover {
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;
    public Clover(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            allTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            allTasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(allTasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Clover("data/tasks.txt").run();
    }
}