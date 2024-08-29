package clover.command;

import clover.task.Deadline;
public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String description, String by) {
        super(new Deadline(description, by));
    }
}
