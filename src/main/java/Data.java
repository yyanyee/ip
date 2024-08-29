import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    private String filePath;

    public Data(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> allTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) { //if file doesn't exist at the start
            createFile(file);
            return allTasks;
        }

        //else, file does exist
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            /*
            [0]: Task, Deadline or Event
            [1]: 1 or 0
            [2]: task
            [3]: due date
            [4]: time
             */
            String[] split = line.split(" \\| "); //split up line at even "|"
            Task task;

            switch (split[0]) {
                case "T": // todo
                    task = new Todo(split[2]);
                    break;

                case "D": // deadline
                    task = new Deadline(split[2], split[3]);
                    break;

                case "E": // event
                    task = new Event(split[2], split[3], split[4]);
                    break;

                default: // none of the above cases
                    throw new IOException("data file is corrupt");
            }

            if (split[1].equals("0")) {
                task.markAsUndone();
            } else if (split[1].equals("1")) {
                task.markAsDone();
            }

            allTasks.add(task);
        }
        scanner.close();
        return allTasks;
    }

    public void createFile(File file) throws IOException {
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.saveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}