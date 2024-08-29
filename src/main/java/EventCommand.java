public class EventCommand extends AddCommand {
    public EventCommand(String description, String from, String to) {
        super(new Event(description, from, to));
    }
}
