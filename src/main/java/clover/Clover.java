package clover;

import clover.command.Command;
import clover.util.Parser;
import clover.util.Storage;
import clover.util.TaskList;
import clover.util.Ui;
import java.io.IOException;

/**
 * The main class for the Clover chatbot
 */
public class Clover {
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructs a Clover object and initialises the necessary components.
     *
     * @param filePath The file path where tasks are stored.
     */
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

    /**
     * Runs the main loop of the application.
     * Reads the user input, parses it into commands and executes the command.
     */
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

    /**
     * The main entry point for the Clover chatbot.
     * Creates a new Clover object and starts the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Clover("data/tasks.txt").run();
    }
}