package clover.util;

import clover.task.Task;

import java.util.Scanner;
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("loading error... :(");
    }
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm clover.Clover");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public void showList(TaskList allTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + allTasks.getTask(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showMark(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public void showUnmark(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public void showAddTask(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showDeleteTask(Task removed, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removed);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    public void showError(String msg) {
        System.out.println(msg);
    }




}
