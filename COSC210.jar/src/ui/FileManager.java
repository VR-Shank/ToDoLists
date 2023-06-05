package ui;

import Model.Exceptions;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

import static Model.WriteFile.writeFile;
import static java.lang.System.*;
import static java.lang.System.in;

public class FileManager {
    Scanner scanner=new Scanner(in);
    String prompt1;
    String prompt2;
    static String origin;
    static String destination;
    private JButton deleteAFileButton;
    private JButton deleteAllFilesButton;
    private JButton exitButton;
    private JButton renameAFileButton;

    FileManager(SavedLists o) throws Exceptions.loadException, Exceptions.InvalidSelectionException, IOException {
        if (!o.getValue().isEmpty()) {
            out.println("Here are the saved list files: ");
            for (String x : o.keys) {
                out.println("Name: " + x + ", Type: " + o.getListType(x));
            }
        } else throw new Exceptions.loadException();

        out.println("What would you like to do?: a) Delete a file, b) Delete all files, c) Download a Printed List," +
                "  or d) Exit");
        prompt1= scanner.nextLine();
        try {
           manager(o, prompt1);
        } catch (Exceptions.InvalidSelectionException e){
            out.println(e);
            manager(o, prompt1);
        }
    }
    private void manager(SavedLists o, String select) throws Exceptions.InvalidSelectionException, IOException {
        switch (select) {
            case "a": {
                deleteAFile(o);
                break;
            }
            case "b": {
                try {
                    deleteAllFiles(o);
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "c": {
                //Export file
            }
            case "d":{
                break;
            }
            default:
                throw new Exceptions.InvalidSelectionException("Please enter \"a\", \"b\", of \"c\" or \"d\"!");
        }
    }
    private void deleteAFile(SavedLists o) throws IOException, Exceptions.InvalidSelectionException {
        out.println("Which file would you like to delete:" + o.savedListsNames() + " or e) Exit");
        prompt2 = scanner.nextLine();
        try{
        switch(prompt2) {
            case "e": {
                out.println("Exiting to Operator!");
                break;
            }
            default: {
                if(o.getSrcFromSrcMap(prompt2)=="src/InputOutput/Output3.txt") {
                    fileDeleter(o, prompt2);
                    break;
                } else if(o.getSrcFromSrcMap(prompt2)=="src/InputOutput/Output2.txt") {
                    fileDeleter(o, prompt2);
                    fileMover(1);
                }else if(o.getSrcFromSrcMap(prompt2)=="src/InputOutput/Output1.txt") {
                    fileDeleter(o, prompt2);
                    fileMover(1);
                    fileMover(2);
                }
            }
        }
        } catch (Exceptions.InvalidSelectionException e){
            out.println(e);
            deleteAFile(o);
        }
    }
    
    private void fileDeleter(SavedLists o, String delete) throws Exceptions.InvalidSelectionException, IOException {
            if (o.ContainsThis(delete)) {
                writeFile(null, o.getSrcFromSrcMap(delete), true);
                o.deleteSavedList(delete);
            } else throw new Exceptions.InvalidSelectionException("The file selected was not found!" +
                    " Please check the spelling and try again!");
    }
    private void fileMover (int i){

        FileReader fr = null;
        PrintWriter fw = null;
        PrintWriter clear = null;
        try {
            switch(i){
                case 1:{
                    origin="src/InputOutput/Output3.txt";
                    destination="src/InputOutput/Output2.txt";
                    break;
                }
                case 2:{
                    origin="src/InputOutput/Output2.txt";
                    destination="src/InputOutput/Output1.txt";
                    break;
                }
            }
            fr=new FileReader(origin);
            fw = new PrintWriter(destination);
            int c = fr.read();
            while (c != -1) {
                fw.write(c);
                c = fr.read();
                }

            clear = new PrintWriter(origin);
            clear.write("");

        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(fr);
            fw.close();
            clear.close();
        }
    }
    private void close(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch(IOException e) {
           throw new RuntimeException(e);
        }
    }
    private void deleteAllFiles(SavedLists o) throws Exceptions.InvalidSelectionException, IOException {
        for(String x: o.keys){
            o.deleteSavedList(x);
            writeFile(null, o.getSrcFromSrcMap(x), true);
        }

    }

    private void ExportFile(SavedLists o) {

    }
}
