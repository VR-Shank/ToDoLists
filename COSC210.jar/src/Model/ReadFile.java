package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static Model.ToDo.*;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

public interface ReadFile {
    //Checks whether to load default or saved list
    default void load(String src) throws IOException, Exceptions.InvalidSelectionException {
        ArrayList<String> lines = (ArrayList<String>) readAllLines(get(src));
        loadHelper(lines);
    }
    default void loadHelper(ArrayList<String> stringLines){
        for (String x : stringLines) {
            if (x.startsWith("Shopping List")) {
                name = x.substring(15);
            } else if (x.startsWith("Task List")){
                name= x.substring(11);
            }

            if (x.startsWith(prefixIncomplete)) {
                y = x.substring(4);
                todos.put(y, "Incomplete");
            }
            if (x.startsWith(prefixComplete)) {
                y = x.substring(4);
                todos.put(y, "Complete");
            }
        }
    }
    static ToDo loadSaves(ArrayList<String> lines) {
        String firstLine = lines.get(0);
                if (firstLine.contains("Shopping List")) {
                    ShoppingList s= new ShoppingList();
                    s.loadHelper(lines);
                    return s;
                } else if(firstLine.contains("Task List")) {
                    TaskList t= new TaskList();
                    t.loadHelper(lines);
                    return t;
                } else {
                    return null;
                }
        }

    static ToDo ReadWebFile(String theURL) throws IOException {
        BufferedReader br = null;

        StringBuilder sb;
        ArrayList <String> reader;
        try {
            //String theURL = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            reader=new ArrayList<>();
            String line;

            sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (line.contains("Task List:") || line.contains("Shopping List:")) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    reader.add(line);
                } else if (line.startsWith("GLI") || line.startsWith("GLC") || line.startsWith("TLI") || line.startsWith("TLC")) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    reader.add(line);
                }
            }

            WriteFile.webWriter(sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                br.close();

            }
        }
        return loadSaves(reader);
    }
}

