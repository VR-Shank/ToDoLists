package ui;

import Model.ReadFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static Model.Exceptions.*;
import static java.nio.file.Paths.get;

public class SavedLists implements ReadFile {
    private static final Map<String, String> savedListHolder= new HashMap<>();
    public static String srcOutput1 = "src/InputOutput/Output1.txt";
    public static String srcOutput2 = "src/InputOutput/Output2.txt";
    public static String srcOutput3 = "src/InputOutput/Output3.txt";
    public static HashMap < String, String> srcs;
    public static ArrayList <String> src;
    public static ArrayList <String> keys;
    private static String name;
    private static String sourceFile;
    private static String type;

    public SavedLists() throws IOException, InvalidSelectionException, loadException {
        keys=new ArrayList<>();
        src=new ArrayList<>();
        srcs = new HashMap<>();
        addSrcToSrcList();

        for (String x : src) {
            loadLists(x);
        }
        //addKeysToKeylist();
    }

    private void addSrcToSrcList(){
        src.add(srcOutput1);
        src.add(srcOutput2);
        src.add(srcOutput3);
    }
    /**public void addKeysToKeylist(){
        keys.addAll(savedListHolder.keySet());
    }**/
    public boolean ContainsThis(String name){
        return savedListHolder.containsKey(name);
    }

    public int getSize(){
        return savedListHolder.size();
    }
    public String getListType(String key) {
        if(savedListHolder.get(key)!=null) {
            if (savedListHolder.get(key).contains("Shopping List")){
                return "Shopping List";
            } else return "Task List";
        } else return null;
    }
    public Map<String, String> getValue()
    {
        return savedListHolder;
    }

    public void saveToHolder(String a, String b){
        savedListHolder.put(a, b);
    }


    public String getSrcFromSrcMap (String name) throws InvalidSelectionException {
        if(srcs.containsKey(name)){
            return srcs.get(name);
        } else {
            System.out.println(srcs.entrySet());
            throw new InvalidSelectionException("Big problems in getSrcFromSrcMap()!");
        }
    }

    private void loadLists(String source) throws IOException, loadException, InvalidSelectionException {
        List<String> lines = Files.readAllLines(get(source));
        if(!lines.isEmpty()) {
            for (String x : lines) {
                if(x.startsWith("Shopping List") || x.startsWith("Task List")) {
                    if (x.startsWith("Shopping List")) {
                        type = loadListType(source);
                        name = x.substring(15);
                    } else if (x.startsWith("Task List")) {
                        type = loadListType(source);
                        name = x.substring(11);
                    }
                    savedListHolder.put(name, type);
                    keys.add(name);
                    srcs.put(name, source);
                }
                break;
            }
        }

    }
    private String loadListType(String src) throws IOException, loadException, InvalidSelectionException {
        String firstLine;
        System.out.println(src);
        String toDoType;
        try (BufferedReader br = new BufferedReader(new FileReader(src))) {
            toDoType = "";
            if ((firstLine = br.readLine()) != null) {
                if (firstLine.startsWith("Shopping List") || firstLine.startsWith("Task List")) {
                    return firstLine;
                }
            } else {
               return "Empty";
            }
        }
        return toDoType;
    }

    public String savedListsNames() {
        int i = 0;
        return String.join(", ","\"" + savedListHolder.keySet() + "\"");
    }

    /**public String replaceFile(String abc) throws Exceptions.InvalidSelectionException {
        if (shoppingListHolder.containsKey(abc)) {
            shoppingListHolder.remove(abc);
            if(shoppingListHolder.get(abc).listType == "Shopping List" ){

            }
        } else throw new Exceptions.InvalidSelectionException("Sorry there is no To Do saved with that name!");
    }**/


    public void saveShoppingList(String toDoName,String toDoType) {
        savedListHolder.put(toDoName, toDoType);
    }
    public void deleteSavedList(String toDoName) {
        savedListHolder.remove(toDoName);
    }


}

