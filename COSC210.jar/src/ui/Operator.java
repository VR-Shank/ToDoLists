package ui;

import Model.*;


import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static Model.Exceptions.*;
import static Model.ToDo.*;
import static Model.WriteFile.writeFile;
import static java.lang.System.*;
import static ui.InitializeOrManage.*;

public class Operator {
    static Scanner scanner = new Scanner(in);
    static String item;
    //User Input constants
    static String prompt;
    static String prompt2;
    static String operation;
    static ToDo toDaLoo;
    static FileManager fileManager;
    static boolean construct;
    static TaskList tasks;
    static ShoppingList shopping;
    static SavedLists s;
    static ArrayList <String> holderKeys = new ArrayList<>();
    static ArrayList <String> save;
    static String saved =  "You have too many todo lists!\n" +
            " To save your current toDoList you will have to replace an existing one!:\n" +
            "Please choose one or quit :";


    //initialization prompts
    static String initialization = "Which type of list would you like to initialize?  a) \"Make a Task List\", "+
            "b) \"Make a Shopping List\", c)\"Choose a Saved File\", or d) \"Weekly Challenge\" to e)\"Exit\"";
    static String initializationException = "Sorry, please respond with \"a\", \"b\", \"c\", or \"d\"!";
    public static final String loadSavedFilesPrompt ="Which saved file would you like to load? ";


    //initialization2 prompts
    private static final String initialization2Exception = "Sorry, please respond with \"a\" or \"b\"!";

    //InitializeOrManage fields
    private JButton initializeListButton;
    private JButton manageListsButton;
    private JButton exitButton;
    private JLabel SystemConsole1;

    //InputOutputConstructor fields
    private JButton addButton;
    private JButton checkButton;
    private JButton renameButton;
    private JButton removeButton;
    private JButton saveAndExitButton;
    private JButton showButton;

    //
    private JButton newShoppingListButton;
    private JButton newTaskListButton;
    private JButton loadSavedFileButton;
    //Frame3
    static InitializeOrManage iom;
    Initialization i;
    private static String initialization2(String a) {
        return String.format("How would you like to initialize the %1$s: a)\"New %1$s\" or b) \"Load Default\"?", a);
    }

    // InputOutputConstructor prompts
    private static String add(String a, String b){
        return String.format("Add %s to the %s", a, b);
    }
    private static String remove(String a, String b){
        return String.format("Remove %s from the %s", a, b);
    }
    private static String check(String a){
        return String.format("Mark %s as completed: ", a);
    }
    private static String current(String a){
        return String.format("Here is your current %s:", a);
    }
    private static final String inputOutputConstructorError = "\"Sorry, please respond with \"a\", \"b\", \"c\", \"d\","
            + " \"e\", \"f\", or \"g\"!";
    private static String inputOutputConstructor(String a){
        return String.format("Please select an  option: a) \"Add %1$s\", b) \"Remove %1$s\", c) \"Check %1$s\", " +
                "d)\"Rename file\" e)\"Show %1$s\", f) \"Save & Quit\", or g) \"Quit\".", a);
    }


    //InitializeOrManage() String literals
    private static final String Message1 = "Welcome, what would you Like to do today?";
    private static final String Message2 = "a) \"Initialize/manage a to-do list\", b) \"Manage To-Do Lists\" or c) \"Exit\".";
    private static final String closingMessage = "Closing operations. It has been a pleasure, I look forward to assisting you again!";
    Operator() throws InputException, IOException, InvalidSelectionException, loadException {
        s = new SavedLists();
        InitializeOrManage();
    }

    private void InitializeOrManage() throws InputException, IOException, InvalidSelectionException, loadException {
        out.println(Message1);
        out.println(Message2);
        //SystemConsole1 = new JLabel("Message1");
        prompt= scanner.nextLine();;
        //iom=new InitializeOrManage();
        //Process.WaitForExit
        //if(prompt){
            try{
                switch(prompt){
                    case "a":{
                        //disableIoMGUI();
                        Initialization();
                        break;
                    }
                    case "b": {
                        //disableIoMGUI();
                        fileManager=new FileManager(s);
                        break;
                    }
                    case "c":{
                        System.out.println(closingMessage);
                        //closing(closingMessage);
                        //disableIoMGUI();
                        System.exit(0);
                    }
                    default: throw new InvalidSelectionException(initializationException);
                }
            } catch (InvalidSelectionException e) {
                //closing(String.valueOf(e));
                //disableIoMGUI();
            } finally {
                new Operator();
            }
        //}
    }

    //Modifies: savedList and todoList.
    //Effects: Decides how to initialize todolist
    private void Initialization() throws InvalidSelectionException, InputException, IOException {
        out.println(initialization);
        prompt = scanner.nextLine();
        //i=new Initialization();
        //Initialization.main();
        try{
            switch (prompt) {
                case "a" : {
                    //i.setEnabled(false);
                    tasks = new TaskList();
                    Initialization2(tasks);
                    break;
                    //Calls Initializtion2() with an empty taskList
                }
                case "b": {
                   // i.setEnabled(false);
                    shopping = new ShoppingList();
                    Initialization2(shopping);
                    break;
                    //Calls Initializtion2() with an empty ShoppingList
                }
                case "c": {
                  //  i.setEnabled(false);
                    loadSavedFiles(); //check if this is problematic (only task lists)
                    break;

                }
                case "d": {
                    //i.setEnabled(false);
                    InputOutputConstructor(readWebFile());
                    ///////
                    break;
                }
                case "e":{
                    //i.closingInitialization("Closing initialization... Returning to home.");
                    //i.setEnabled(false);
                    break;
                }
                default: {
                    throw new InvalidSelectionException(initializationException);
                }
            }
        } catch (InputException | IOException | InvalidSelectionException | loadException e){
           // i.closingInitialization("Input/Output error");
          //  i.setEnabled(false);
            System.out.println(e);
            Initialization();
        }
    }

    //Requires: ToDoList to be initialized
    //Modifies: ToDoList
    //Effects: Choose whether to manage a new toDoList or load a SavedFile.
    private void Initialization2(ToDo abc) throws InvalidSelectionException, IOException, InputException, loadException {
        out.println(initialization2(listType));
        prompt2 = scanner.nextLine();
        try {
            switch (prompt2) {
                case "a":
                    InputOutputConstructor(abc);
                    break;
                case "b": {
                    abc.load(srcInput);
                    InputOutputConstructor(abc);
                    break;
                }
                default: {
                    throw new InvalidSelectionException(initialization2Exception);
                }
            }
        } catch (IOException | InvalidSelectionException e){
            out.println(e);
            Initialization2(abc);

        } finally {
            printList();
        }
    }

    //Requires: ToDoList to be initialized
    //Modifies: ToDoList
    //Effects: Manages the tasks/items saved in the todolist and if there is no name, function asks the user for a name.
    private void InputOutputConstructor(ToDo abc) {
        nameToDo(abc);
        construct=true;
        while (construct) {
            out.println(inputOutputConstructor(listItem));
            operation = scanner.nextLine();
            try {
                switch (operation) {
                    case "a": {
                        out.println(add(listItem,listType));
                        item = scanner.nextLine();
                        out.println(abc.taskAdded(item));
                        break;
                    }
                    case "b": {
                        if (!todos.isEmpty()) {
                            out.println(remove(listItem, listType));
                            item = scanner.nextLine();
                            out.println(abc.taskRemoved(item));
                            break;

                        } else {
                            throw new ToDoEmptyException();
                        }
                    }
                    case "c": {
                        if(!todos.isEmpty()) {
                            out.println(check(listItem));
                            item = scanner.nextLine();
                            out.println(abc.taskChecked(item));
                            break;
                        } else {
                            throw new ToDoEmptyException();
                        }
                    }
                    case "d":{
                        out.println("What would you like to rename the file to?");
                        item=scanner.nextLine();
                        abc.renameFile(item);
                        break;
                    }
                    case "e": {
                        out.println(current(listType) + todos);
                        break;
                    }
                    case "f": {
                        saveFile(abc);
                    }
                    case "g":{
                        throw new RuntimeException("To-do operations has been closed!");
                    }
                    default: {
                        throw new InputException(inputOutputConstructorError);
                    }
                }
            } catch (ToDoEmptyException | InputException | InvalidSelectionException | loadException | IOException e) {
                out.println(e);
                InputOutputConstructor(abc);
            }
        }

    }



    private void saveFile(ToDo abc) throws InvalidSelectionException, loadException, IOException, InputException {
        out.println("Would you like to, a) Save the file or b) Quit?");
        prompt = scanner.nextLine();
        switch(prompt) {
            case "a": {
                save(abc);
                writeFile(abc, false);
                break;
            }
            case "b": break;
            default: throw new InvalidSelectionException("Please select \"a\" or \"b\"");
        }
    }
    private void save(ToDo abc) throws InvalidSelectionException, loadException, IOException, InputException {
        // save = new ArrayList<>();
        if(s.ContainsThis(name)){
            out.println("There is another To-Do that is called "+ name + "!\n" +
                    " Would you like to a) overwrite the existing,\n" +
                    " b) Rename the current file,"+
                    " or c) quit without saving");
            prompt = scanner.nextLine();
            try {
                switch (prompt){
                    case "a": {
                        SavedListsOperations.replaceSavedToDo(abc, false);
                        break;
                    }
                    case "b": {
                        nameToDo(abc);
                        saveFile(abc);
                        break;
                    }
                    case "c": exit(0);
                    default: throw new InvalidSelectionException("Sorry, please select \"a\" or \"b\"!");
                }
            } catch (InvalidSelectionException e){
                out.println(e);
                save(abc);
            }
        } else {
            if (s.getSize() >= 3) {
                SavedListsOperations.replaceSavedToDo(abc, true);
            } else if (s.getSize() < 3) {
                s.saveToHolder(name, listType);
                out.println(name + " has been saved to your files!");
            } else throw new InvalidSelectionException("Big Error!");
        }
    }

    //Requires: toDoList to be initialized.
    //Modifies: toDoList name
    private void nameToDo(ToDo abc){
        if(name == null) {
            out.println("What would you like to call this To-Do List?");
            prompt = scanner.nextLine();
            abc.renameFile(prompt);
        } else {
            out.println(name);
        }
    }
    private ToDo readWebFile() throws IOException, InvalidSelectionException, loadException {
        return ReadFile.ReadWebFile("https://storage.googleapis.com/theories_and_conspiracies/Weekly%20Challenges.txt");
    }

    private void printList() {
        int i = 1;
        out.println(incompleteTitle(listItem, listType, finished));
        for (String task: todos.keySet()) {
            if(todos.get(task)=="Incomplete") {
                out.println(i + ")" + task);
                i++;
            }
        }

        i = 1;
        out.println(completedTitle(listItem, listType, finished));
        for (String task: todos.keySet()) {
            if(todos.get(task)=="Complete") {
                out.println(i + ") " + task);
                i++;
            }

        }
        out.println("Date & Time: " + getDateTime());
    }

    //Requires: SavedFiles to not be null or else throws loadException.
    //Modifies: Initialization2.
    //Effects: Acts as an adapter to load Saved text files (hold incomplete & complete tasks/items) to be accessed and modified.
   private void loadSavedFiles() throws Exceptions.loadException, Exceptions.InvalidSelectionException, IOException, Exceptions.InputException {
        if(!s.getValue().isEmpty()){
            System.out.println("Here are the saved list files: ");
            for (String x : SavedLists.keys){
                System.out.println("Name: "+ x + ", Type: " + s.getListType(x));
            }
            System.out.println(s.savedListsNames());
            System.out.println(loadSavedFilesPrompt + SavedListsOperations.load() + ("Please type the name:"));
            String saveFileSelected = scanner.nextLine();
            System.out.println(s.getListType(saveFileSelected));
            if(s.ContainsThis(saveFileSelected)) {
                fileLoader(saveFileSelected);
            } else throw new Exceptions.InvalidSelectionException("Sorry, the file your requested was not found!");
        }else throw new Exceptions.loadException();
    }
    private void fileLoader (String xyz) throws InvalidSelectionException, IOException {
            switch (s.getListType(xyz)) {
                case "Task List": {
                    tasks = new TaskList();
                    out.println(s.getSrcFromSrcMap(xyz));
                    tasks.load(s.getSrcFromSrcMap(xyz));
                    InputOutputConstructor(tasks);
                }
                case "Shopping List": {
                    shopping = new ShoppingList();
                    shopping.load(s.getSrcFromSrcMap(xyz));
                    InputOutputConstructor(shopping);
                }
                default:
                    throw new Exceptions.InvalidSelectionException("Major malfunction in loadSavedFiles()!");
            }
    }
    private static Date getDateTime() {
        return new Date();
    }

}
