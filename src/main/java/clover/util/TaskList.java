package clover.util;

import clover.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> allTasks;

    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public Task removeTask(int i) {
        return allTasks.remove(i);
    }

    public Task getTask(int i) {
        return allTasks.get(i);
    }

    public int size() {
        return allTasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return this.allTasks;
    }

}
