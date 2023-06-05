package Model;

import java.util.ArrayList;
import java.util.HashMap;


public class TaskList extends ToDo{
    public TaskList() {
        setUpToDo();

    }

    @Override
    void setUpToDo() {
        todos = new HashMap<>();
        prefixIncomplete = "TLI";
        prefixComplete = "TLC";
        notFound = "Sorry, task was not found";
        listItem= "Task";
        listType= "Task List";
        finished="completed";
    }
}


