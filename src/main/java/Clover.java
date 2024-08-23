import java.util.ArrayList;
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

        ArrayList<Task> allTasks = new ArrayList<>();

        while (true) {
            command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            try {
                if (command.equals("bye")) {
                    //exit if user types the command bye
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                } else if (command.equals("list")) {
                    // display tasks back
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < allTasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + allTasks.get(i));
                    }
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("mark ")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    allTasks.get(index - 1).markAsDone(); // [ ] -> [X]
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + allTasks.get(index - 1));
                    System.out.println("____________________________________________________________");


                } else if (command.startsWith("unmark ")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    allTasks.get(index - 1).markAsUndone(); // [X] -> [ ]
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + allTasks.get(index - 1));
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("todo ")) {
                    String description = command.substring(5).trim();

                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("description cannot be empty >:( ");
                    }

                    allTasks.add(new Todo(description));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + allTasks.get(allTasks.size() - 1));
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("deadline ")) {
                    String[] split = command.substring(9).split(" /by ");

                    if (split.length < 2 || split[0].trim().isEmpty()) {
                        throw new EmptyDescriptionException("description cannot be empty >:( ");
                    }
                    String description = split[0].trim();
                    String date = split[1].trim();
                    allTasks.add(new Deadline(description, date));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + allTasks.get(allTasks.size() - 1));
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("event ")) {
                    String[] split = command.substring(6).split(" /from ");
                    String description = split[0].trim();
                    String[] times = split[1].split(" /to ");
                    String from = times[0].trim();
                    String to = times[1].trim();
                    allTasks.add(new Event(description, from, to));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + allTasks.get(allTasks.size() - 1));
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("delete ")) {
                    int index = Integer.parseInt(command.split(" ")[1])- 1;
                    Task removed = allTasks.remove(index);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else {
                    // else, unknown command
                    throw new UnknownCommandException("Unknown command!! I do not know this :(");
                }
            } catch (EmptyDescriptionException e) {
                System.out.println(" ERM... " + e.getMessage());
                System.out.println("____________________________________________________________");

            } catch (UnknownCommandException e) {
                System.out.println(" Hmm... " + e.getMessage());
                System.out.println("____________________________________________________________");

            } catch (IndexOutOfBoundsException e) {
                System.out.println(" Oh no... This is an invalid task number!");
                System.out.println("____________________________________________________________");

            } catch (Exception e) {
                System.out.println(" Unknown error! ");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}