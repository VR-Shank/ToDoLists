package Model;


import java.util.ArrayList;
import java.util.HashMap;

import static Model.Exceptions.*;

//The abstract todolist class that helps build task lists and grocery lists.
public abstract class ToDo implements WriteFile, ReadFile {
        public static String COMPLETE = "Complete";
        public static String INCOMPLETE = "Incomplete";
        public static String name;
        public static String srcInput = "src/InputOutput/Input.txt";
        public static String srcTestInput = "src/InputOutput/testInput.txt";
        public static String srcTestOutput = "src/InputOutput/TestOutput.txt";
        public static String srcOutput1 = "src/InputOutput/Output1.txt";
        public static String srcOutput2 = "src/InputOutput/Output2.txt";
        public static String srcOutput3 = "src/InputOutput/Output3.txt";
        public static HashMap<String,String> todos;
        static String prefixIncomplete;
        static String prefixComplete;
        static String y;
        public static String notFound;
        public static String duplicationError1(){
                return "Sorry, the " + listItem + " already exists and is yet to be completed!";
        }
        public static String duplicationError2(){
                return "Sorry, the " + listItem + " has already been completed!";
        }

        // String constants
        public static String finished;
        public static String operand;

        public static String incompleteTitle(String a, String b,String c){
                return String.format("Here are the %s from your %s that haven't been %s:", a + "s", b, c);
        }

        public static String completedTitle(String a, String b, String c){
                return String.format("Here are the %s from your %s that have been %s:", a + "s", b, c);
        };
        public static String listType;
        public static String listItem;

        private static String operationsCompleted(String a, String b, String c) {
                return String.format("%s has been %s the %s", a, b, c);
        }


        //operator prompts;

        abstract void setUpToDo();

        public String taskAdded(String a) throws InvalidSelectionException {
                if(!(todos.containsKey(a))) {
                        todos.put(a, INCOMPLETE);
                        operand="added to";
                        return operationsCompleted(listItem, operand, listType);
                } else if(todos.containsKey(a)) {
                        if(todos.get(a)== INCOMPLETE) {
                                throw new InvalidSelectionException(duplicationError1());
                        } else if(todos.get(a)== COMPLETE){
                                todos.replace(a, INCOMPLETE);
                                throw new InvalidSelectionException(duplicationError2() + " So, it's status has been reverted!");
                        } else throw new RuntimeException("Major malfunction!");
                } else {
                        throw new InvalidSelectionException(duplicationError2());
                }
        }

        //user has chosen to remove an item from the to do list
        //REQUIRES: todolist and int. It requires tasks to have been added.
        //MODIFY: todolist
        //EFFECTS; item removed from todolist
        public String taskRemoved(String a) throws InvalidSelectionException {
                if(todos.containsKey(a)) {
                        operand="removed from";
                        todos.remove(a);
                        return operationsCompleted(listItem, operand, listType);
                }
                else{
                        throw new InvalidSelectionException(notFound);
                }
        }

        //user has chosen to mark an item as completed in the to do list.
        //REQUIRES: todolist and int. Requires tasks to have been added.
        //MODIFY: todolist
        //EFFECTS: Adds exclamation marks around user input that matches item..
        public String taskChecked(String a) throws InvalidSelectionException {
                if(todos.containsKey(a)) {
                        todos.replace(a, COMPLETE);
                        operand="checked from";
                        return operationsCompleted(listItem, operand, listType);
                } else {
                        throw new InvalidSelectionException(notFound);
                }
        }

        public void renameFile(String a) {
                name = a;
        }

}
