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

        while (true) {
            command = scanner.nextLine();

            if (command.equals("bye")) {
                //exit if user types the command bye
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // else, echo the command
            System.out.println("____________________________________________________________");
            System.out.println(" " + command);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}