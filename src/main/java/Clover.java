import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Clover {
    private static Data data;
    private static ArrayList<Task> allTasks;
    public static void main(String[] args) {
        data = new Data("./data/Clover.txt");

        try {
            allTasks = data.load();
        } catch (IOException e) {
            System.out.println("Corrupt file: " + e.getMessage());
            allTasks = new ArrayList<>();
        }

        //greet
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Clover");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String command;

       //ArrayList<Task> allTasks = new ArrayList<>();

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
                    save();

                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + allTasks.get(index - 1));
                    System.out.println("____________________________________________________________");


                } else if (command.startsWith("unmark ")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    allTasks.get(index - 1).markAsUndone(); // [X] -> [ ]
                    save();

                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + allTasks.get(index - 1));
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("todo ")) {
                    String description = command.substring(5).trim();

                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("description cannot be empty >:( ");
                    }

                    allTasks.add(new Todo(description));
                    save();

                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + allTasks.get(allTasks.size() - 1));
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");

                } else if (command.startsWith("deadline ")) {
                    String[] split = command.substring(9).split(" /by ");

                    if (split.length < 2 || split[0].trim().isEmpty()) {
                        throw new EmptyDescriptionException("description cannot be empty >:( ");
                    }
                    String description = split[0].trim();
                    String date = split[1].trim();

                    try {
                        allTasks.add(new Deadline(description, date));
                    } catch (DateTimeParseException e) {
                        System.out.println(" Date is in the incorrect format :( Re-enter please <3 ");
                        System.out.println("____________________________________________________________");
                        return;
                    }
                    save();

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
                    save();

                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + allTasks.get(allTasks.size() - 1));
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (command.startsWith("delete ")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task removed = allTasks.remove(index);
                    save();

                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + allTasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                // stretch goal: new command "due" to print deadlines/events occurring on a specific date
                } else if (command.startsWith("due ")) {
                    String dateString = command.substring(4).trim();
                    try {
                        LocalDate dateDate = LocalDate.parse(dateString);
                        tasksDue(dateDate);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date :(");
                        System.out.println("____________________________________________________________");
                    }
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

    private static void save() {
        try {
            data.save(allTasks);
        } catch (IOException e) {
            System.out.println("Corrupt file: " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    private static void tasksDue(LocalDate date) {
        DateTimeFormatter MMMddyyy = DateTimeFormatter.ofPattern("MMM dd yyyy");
        System.out.println(" Here are the tasks due on " + date.format(MMMddyyy) + ": ");
        for (Task task : allTasks) {
            if (task instanceof Deadline) {
                Deadline due = (Deadline) task;
                if (due.by.equals(date)) {
                    System.out.println(" " + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String fromString = event.from;
                String toString = event.to;
                LocalDate start = LocalDate.parse(fromString);
                LocalDate end = LocalDate.parse(toString);

                if (start.equals(date) || end.equals(date)) {
                    System.out.println(" " + task);
                }
            }
        }
        System.out.println("____________________________________________________________");
    }
}