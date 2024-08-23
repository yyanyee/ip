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

        String[] allTasks = new String[100];
        int counter = 0;

        while (true) {
            command = scanner.nextLine();

            if (command.equals("bye")) {
                //exit if user types the command bye
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (command.equals("list")) {
                // display tasks back to user
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i ++) {
                    System.out.println(" " + (i + 1) + "." + allTasks[i]);
                }
                System.out.println("____________________________________________________________");
            }

            // else, echo the command & show it is added
            allTasks[counter] = command;
            counter++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + command);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}