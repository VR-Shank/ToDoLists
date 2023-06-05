package Tests;

import Model.Exceptions;
import Model.ToDo;
import Model.WriteFile;
import Model.ShoppingList;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static Model.Exceptions.emptyError;
import static Model.ToDo.*;
import static Model.ToDo.finished;
import static Model.Exceptions.*;
import static java.nio.file.Paths.get;
import static org.junit.Assert.*;

public class ShoppingListTest {
    static ShoppingList testList;
    static String testHolder;
    static String error;
    static String test= "Test";
    static String test1= "Test1";
    static String test2= "Test2";
    static String test_List="Test List";

    @Before
    public void setUp() {
        testList = new ShoppingList();
    }

    @Test
    public void addTaskTest() throws InvalidSelectionException {
        testList.taskAdded(test);
        assertTrue(todos.containsKey(test));
    }
    @Test
    public void checkTaskTest() throws InvalidSelectionException {
        testList.taskAdded(test);
        assertNotSame(COMPLETE, todos.get(test));
        testList.taskChecked(test);
        assertSame(COMPLETE, todos.get(test));
    }
    @Test
    public void removeTaskTest() throws InvalidSelectionException {
        testList.taskAdded(test);
        assertTrue(todos.containsKey(test));
        testList.taskRemoved(test);
        assertFalse(todos.containsKey(test));
    }
    @Test
    public void renameTaskListTest() throws InvalidSelectionException {
        assertEquals(null, testList.name);
        testList.renameFile(test_List);
        assertEquals(test_List, testList.name);
    }
    @Test
    public void writeFileTest() throws IOException, InvalidSelectionException, loadException {
        String line;
        List<String> lines = Files.readAllLines(get("src/InputOutput/TestOutput.txt"));

        //Perform add test to testList2 then writeFile().
        testList.taskAdded(test);
        WriteFile.writeFile(testList, true);

        line = lines.get(2); // Individual test fails unless you get line 1.
        // When all operations are performing there is a test1 added.
        assertEquals("GLI " + test, line);

    }

    @Test
    public void writeNullFiles() throws InvalidSelectionException, IOException, loadException {
        String line;
        String line2;
        testList.taskAdded(test1);
        WriteFile.writeFile(testList, true);
        List<String> lines = Files.readAllLines(get("src/InputOutput/TestOutput.txt"));
        //Perform add test to testList2 then writeFile().

        line = lines.get(1); // Individual test fails unless you get line 1.
        // When all operations are performing there is a test1 added.
        assertEquals("GLI " + test1, line);//Major error

        WriteFile.writeFile(null, "src/InputOutput/TestOutput.txt", true);
        BufferedReader br= new BufferedReader(new FileReader("src/InputOutput/TestOutput.txt"));

        assertEquals(null, br.readLine());
    }
    @Test
    public void loadDefaultFileTest(){
        try {
            testList.load(srcTestInput);
            assertTrue(todos.containsKey(test));
            assertSame(COMPLETE, todos.get(test1));
        } catch (InvalidSelectionException | IOException e){
            System.out.println(e);
        }
    }
    @Test
    public void loadSavedFileTest() throws InvalidSelectionException {
        testList.taskAdded(test);
        testList.taskAdded(test1);
        testList.taskAdded(test2);
        testList.taskChecked(test2);
        try {
            WriteFile.writeFile(testList, true);
            testList.load(srcTestOutput);
            assertTrue(todos.containsKey(test) && todos.containsKey(test1));
            assertTrue(todos.containsKey(test2));
            assertSame(COMPLETE, todos.get(test2));
        } catch (InvalidSelectionException | IOException | loadException e){
            System.out.println(e);
        }
    }
    @Test
    public void InvalidSelectionErrorTest(){
        String error;
        try{
            throw new InvalidSelectionException(test2);
        } catch (InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(test2));
    }
    @Test
    public void InputErrorTest(){
        String error;
        try{
            throw new InputException(test2);
        } catch (InputException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(test2));
    }
    @Test
    public void ToDoEmptyExceptionTest() {
        String error;
        try{
            throw new ToDoEmptyException();
        } catch (Exceptions.ToDoEmptyException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(emptyError));
    }
    @Test
    public void StringGetterTest(){
        testHolder= incompleteTitle(listItem, listType, finished);
        assertEquals("Here are the Items from your Shopping List that haven't been bought:", testHolder);
        testHolder= completedTitle(listItem, listType, finished);
        assertEquals("Here are the Items from your Shopping List that have been bought:", testHolder);

    }
    @Test
    public void DuplicateException1Test() {

        try{
            testList.taskAdded(test);
            testList.taskAdded(test);
        } catch ( InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(duplicationError1()));
    }
    @Test
    public void taskRemoved_NotFoundExceptionTest() {

        try{
            testList.taskRemoved(test);
            testList.taskRemoved(test);
        } catch ( InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(notFound));
    }
    @Test
    public void taskChecked_NotFoundExceptionTest() {

        try{
            testList.taskChecked(test);
        } catch ( InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(notFound));
    }

}
