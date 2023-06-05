package Tests;

import Model.Exceptions;
import Model.WriteFile;
import org.junit.Before;
import org.junit.Test;
import Model.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static Model.Exceptions.emptyError;
import static Model.ToDo.*;
import static Model.Exceptions.*;
import static java.nio.file.Paths.get;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TaskListTest {
    static TaskList testList2;
    static String test= "Test";
    static String test1= "Test1";
    static String test2= "Test2";
    static String testHolder;
    static String error;
    static String test_List="Test List";

    @Before
    public void setUp() {
        testList2 = new TaskList();
    }

    @Test
    public void addTaskTest() throws InvalidSelectionException {
        testList2.taskAdded(test);
        assertTrue(todos.containsKey(test));
    }
    @Test
    public void checkTaskTest() throws InvalidSelectionException {
        testList2.taskAdded(test);
        assertNotSame(COMPLETE, todos.get(test));
        testList2.taskChecked(test);
        assertEquals(COMPLETE, testList2.todos.get(test));
    }
    @Test
    public void removeTaskTest() throws InvalidSelectionException {
        testList2.taskAdded(test);
        assertTrue(todos.containsKey(test));
        testList2.taskRemoved(test);
        assertFalse(todos.containsKey(test));
    }
    @Test
    public void renameTaskListTest() throws InvalidSelectionException {
        testList2.renameFile(null); // Carrying over from ShoppingListTest
        assertEquals(null, testList2.name);
        testList2.renameFile(test_List);
        assertEquals(test_List, testList2.name);
    }
    @Test
    public void writeFileTest() throws IOException, InvalidSelectionException, loadException {
        String line;
        List<String> lines = Files.readAllLines(get("src/InputOutput/TestOutput.txt"));

        //Perform add test to testList2 then writeFile().
        testList2.taskAdded(test);
        WriteFile.writeFile(testList2, true);

        line = lines.get(2); // Individual test fails unless you get line 1.
        // When all operations are performing there is a test1 added.
        assertEquals("TLI " + test, line);

    }

    @Test
    public void loadDefaultFileTest(){
        try {
            testList2.load(srcTestInput);
            assertTrue(todos.containsKey(test));
            assertSame(COMPLETE, todos.get(test1));
        } catch (InvalidSelectionException | IOException e){
            System.out.println(e);
        }
    }
    @Test
    public void loadSavedFileTest() throws InvalidSelectionException, loadException {
        testList2.taskAdded(test);
        testList2.taskAdded(test1);
        testList2.taskAdded(test2);
        testList2.taskChecked(test2);
        try {
            WriteFile.writeFile(testList2, true);
            testList2.load(srcTestOutput);
            assertTrue(todos.containsKey(test) && todos.containsKey(test1));
            assertTrue(todos.containsKey(test2));
            assertSame(COMPLETE, todos.get(test2));
        } catch (InvalidSelectionException | IOException e){
            System.out.println(e);
        }
    }
    @Test
    public void writeNullFiles() throws InvalidSelectionException, IOException, loadException {
        String line;
        List<String> lines = Files.readAllLines(get("src/InputOutput/TestOutput.txt"));
        //Perform add test to testList2 then writeFile().
        testList2.taskAdded(test1);
        WriteFile.writeFile(testList2, true);

        line = lines.get(1); // Individual test fails unless you get line 1.
        // When all operations are performing there is a test1 added.
        assertEquals("GLI " + test, line); //Major issue

        testList2.taskAdded(test);
        WriteFile.writeFile(testList2, true);
        // When all operations are performing there is a test1 added.
        assertEquals("GLI " + test, line); //Major error
        WriteFile.writeFile(null, "src/InputOutput/TestOutput.txt", true);
        BufferedReader br= new BufferedReader(new FileReader("src/InputOutput/TestOutput.txt"));

        assertNull(br.readLine());
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
        try{
            throw new ToDoEmptyException();
        } catch (Exceptions.ToDoEmptyException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(emptyError));
    }
    @Test
    public void DuplicateException1Test() {

        try{
            testList2.taskAdded(test);
            testList2.taskAdded(test);
        } catch ( InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(duplicationError1()));
    }

    @Test
    public void taskRemoved_NotFoundExceptionTest() {

        try{
            testList2.taskRemoved(test);
            testList2.taskRemoved(test);
        } catch ( InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(notFound));
    }
    @Test
    public void taskChecked_NotFoundExceptionTest() {

        try{
            testList2.taskChecked(test);
        } catch ( InvalidSelectionException e) {
            error = e.toString();
        }
        assertTrue(error.endsWith(notFound));
    }
    @Test
    public void StringGetterTest(){
        testHolder= incompleteTitle(listItem, listType, finished);
        assertEquals("Here are the Tasks from your Task List that haven't been completed:", testHolder);
        testHolder= completedTitle(listItem, listType, finished);
        assertEquals("Here are the Tasks from your Task List that have been completed:", testHolder);
    }

}
