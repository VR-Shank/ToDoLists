package Model;
public class Exceptions {
    public static String emptyError = "Sorry! There are currently no item's that can be removed.";

    public static class ToDoEmptyException extends Exception {
        public ToDoEmptyException(){
            super(emptyError);
        }
    }
    public static class InvalidSelectionException extends Exception{
        public InvalidSelectionException(String message){
            super(message);
        }
    }
    public static class InputException extends Exception{
        public InputException (String message){
            super(message);
        }
    }
    public static class loadException extends Exception {
        public loadException(){
            super("There are no saved files!");
        }
    }
}
