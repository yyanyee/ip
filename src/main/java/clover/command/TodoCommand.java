package clover.command;

import clover.task.Todo;
public class TodoCommand extends AddCommand {
   public TodoCommand(String description) {
       super(new Todo(description));
   }
}
