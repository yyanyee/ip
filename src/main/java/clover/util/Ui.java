package clover.util;

import clover.task.Task;

import java.util.Scanner;

/**
 * Handles the interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object that initialises the scanner for reading the user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command entered by the user.
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the horizontal line used by the chatbot.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the loading error used by the chatbot.
     */
    public void showLoadingError() {
        System.out.println("loading error... :(");
    }

    /**
     * Displays the greeting message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm clover.Clover");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of all tasks to the user.
     *
     * @param allTasks The TaskList of all tasks of the user.
     */
    public void showList(TaskList allTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + allTasks.getTask(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message of the task that was marked as done.
     * @param task The task to be marked as done.
     */
    public void showMark(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message of the task that was marked as undone.
     *
     * @param task the task to be marked as undone.
     */
    public void showUnmark(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task to be added to the task list.
     * @param size The current size of the task list.
     */
    public void showAddTask(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param removed The task that was removed from the task list.
     * @param size The current size of the task list.
     */
    public void showDeleteTask(Task removed, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removed);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param msg The error message to be displayed.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }
}
