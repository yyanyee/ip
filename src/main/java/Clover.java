import java.util.Scanner;
public class Clover {
    public static void main(String[] args) {
        //greet
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Clover");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String command;

        Task[] allTasks = new Task[100];
        int counter = 0;

        while (true) {
            command = scanner.nextLine();

            if (command.equals("bye")) {
                //exit if user types the command bye
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (command.equals("list")) {
                // display tasks back
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < counter; i ++) {
                    System.out.println(" " + (i + 1) + "." + allTasks[i]);
                }
                System.out.println("____________________________________________________________");

            } else if (command.startsWith("mark ")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                allTasks[index - 1].markAsDone(); // [ ] -> [X]
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + allTasks[index - 1]);
                System.out.println("____________________________________________________________");

            } else if (command.startsWith("unmark ")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                allTasks[index - 1].markAsUndone(); // [X] -> [ ]
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + allTasks[index - 1]);
                System.out.println("____________________________________________________________");
                
            } else {
                // else, echo the command & show it is added
                allTasks[counter] = new Task(command);
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}