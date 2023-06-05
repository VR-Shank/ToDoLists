package ui;

import Model.*;

import java.io.IOException;

import static Model.ToDo.*;
import static Model.WriteFile.writeFile;
import static ui.Operator.*;

public class SavedListsOperations {
    public static void replaceSavedToDo(ToDo abc, Boolean full) throws Exceptions.InvalidSelectionException, Exceptions.loadException, IOException {
        String removed="";
        if(full) {
            System.out.println(saved + s.savedListsNames() + ", or \"EXIT\" (Don't Save & Quit).");
            prompt = scanner.nextLine();
            removed= prompt + " has been removed, from your " + listType + " and" + name + " has been saved to your files instead!";
        } else {
            prompt= name;
            removed=prompt+ " has been overwritten!";
        }
        try{
            if(s.ContainsThis(prompt)){
                s.deleteSavedList(prompt);
                s.saveShoppingList(prompt, listType);
                writeFile(abc, s.getSrcFromSrcMap(prompt), false);
                System.out.println(removed);
            }else if(prompt== "EXIT"){
                System.exit(0);
            }else throw new Exceptions.InvalidSelectionException("Sorry there is no To Do saved with that name!");

        } catch (Exceptions.InvalidSelectionException e) {
            System.out.println(e);
            replaceSavedToDo(abc, full);
        }
    }

    public static String load(){
        int i=1;
        String load = "";
        for(String x: holderKeys)
            if (i == 1) {
                load += i + ". " + x;
                i++;
            } else load += " or " + ". " + x;
        return load;

    }
}

