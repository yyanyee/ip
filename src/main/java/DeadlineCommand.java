public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String description, String by) {
        super(new Deadline(description, by));
    }
}
