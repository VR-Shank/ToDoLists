package Model;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static Model.ToDo.*;

public interface WriteFile {
    static void writeFile(ToDo abc, boolean test) throws IOException, Exceptions.loadException, Exceptions.InvalidSelectionException {
        PrintWriter writer;
        PrintWriter writerTest = new PrintWriter(srcTestOutput, StandardCharsets.UTF_8);

        if (!test) {
            writer = setWriter();
        } else {
            writer = writerTest;
        }
        if (writer != null) {
            writer.println(listType + ": " + name);
            for (String x : todos.keySet()) {
                if (todos.get(x) == "Incomplete") {
                    writer.println(prefixIncomplete + " " + x);
                } else if (todos.get(x) == "Complete") {
                    writer.println(prefixComplete + " " + x);
                }
            }
            writer.close(); //note -- if you miss this, the file will not be written at all.
        } else {
            throw new Exceptions.InvalidSelectionException("Not sure why but there is an error being thrown"+
                    " - the list has been saved, please quit without saving");
        }
    }


    static void writeFile(ToDo abc, String src, Boolean delete) throws IOException {
        PrintWriter writer = new PrintWriter(src, StandardCharsets.UTF_8);

        if (writer != null && !delete) {
            writer.println(abc.listType + ": " + abc.name);
            for (String x : abc.todos.keySet()) {
                if (abc.todos.get(x) == "Incomplete") {
                    writer.println(prefixIncomplete + " " + x);
                } else if (abc.todos.get(x) == "Complete") {
                    writer.println(prefixComplete + " " + x);
                }
            }
            writer.close(); //note -- if you miss this, the file will not be written at all.
        } else if(delete) {
            writer.print("");
            writer.close();
        } else throw new RuntimeException("Failed Write!");

    }

    static void webWriter(StringBuilder builtString) throws IOException {
        PrintWriter writer = new PrintWriter("src/InputOutput/WebInput.txt", StandardCharsets.UTF_8);
        try {
            writer.println(builtString);
        }
        finally {
            writer.close();
        }
    }
    static PrintWriter setWriter() throws IOException, Exceptions.InvalidSelectionException {
        BufferedReader br1 = new BufferedReader(new FileReader(srcOutput1));
        BufferedReader br2 = new BufferedReader(new FileReader(srcOutput2));
        BufferedReader br3 = new BufferedReader(new FileReader(srcOutput3));


        if (br1.readLine() == null) {
            return new PrintWriter(srcOutput1, StandardCharsets.UTF_8);
        } else if (br2.readLine() == null) {
            return new PrintWriter(srcOutput2, StandardCharsets.UTF_8);
        } else if (br3.readLine() == null) {
            return new PrintWriter(srcOutput3, StandardCharsets.UTF_8);
        } else throw new Exceptions.InvalidSelectionException("Problems in SetWriter()");
    }
}

