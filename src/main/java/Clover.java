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

            } else if (command.startsWith("todo ")) {
                String description = command.substring(5).trim();
                allTasks[counter] = new Todo(description);
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + allTasks[counter - 1]);
                System.out.println(" Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (command.startsWith("deadline ")) {
                String[] split = command.substring(9).split(" /by ");
                String description = split[0].trim();
                String date = split[1].trim();
                allTasks[counter] = new Deadline(description, date);
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + allTasks[counter - 1]);
                System.out.println(" Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (command.startsWith("event ")) {
                String[] split = command.substring(6).split(" /from ");
                String description = split[0].trim();
                String[] times = split[1].split(" /to ");
                String from = times[0].trim();
                String to = times[1].trim();
                allTasks[counter] = new Event(description, from, to);
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + allTasks[counter - 1]);
                System.out.println(" Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else {
                // else, unknown command
                System.out.println("____________________________________________________________");
                System.out.println(" Unknown command :( ");
                System.out.println("____________________________________________________________");

            }
        }
        scanner.close();
    }
}