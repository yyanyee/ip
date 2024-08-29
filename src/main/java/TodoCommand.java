import java.io.IOException;

public class TodoCommand extends AddCommand {
   public TodoCommand(String description) {
       super(new Todo(description));
   }
}
