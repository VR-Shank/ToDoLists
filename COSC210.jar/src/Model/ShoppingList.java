package Model;

import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingList extends ToDo implements WriteFile {
        public ShoppingList() {
            setUpToDo();
        }

        @Override
        void setUpToDo() {
            todos = new HashMap<>();
            prefixIncomplete = "GLI";
            prefixComplete = "GLC";
            notFound = "Sorry, item was not found";
            finished = "bought";
            listItem= "Item";
            listType= "Shopping List";


        }
}


