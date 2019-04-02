

//import java.io.ByteArrayInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import recipedatabase.UserInterface;


public class RecipeDatabaseTest {
    private String input;
    
    public RecipeDatabaseTest() {
        this.input = "1\n" + "Pasta\n" + "90\n" + "pasta\n" +"salt\n" +"oil\n" +"water\n" 
                + "x\n" +"italian\n" +"vegetarian\n" +"dinner\n" +"x\n" + "2";
    }
//    @Rule
//    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void listWorks() {
        UserInterface userinterface = new UserInterface();
        ByteArrayInputStream in = new ByteArrayInputStream(this.input.getBytes());
        System.setIn(in);        
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 
        System.setOut(new PrintStream(outContent));
        userinterface.start(new Scanner(System.in));
        String wanted = "All recipes:\n" +
            "Pasta, 1 h 30 min\n" +
            "Ingredients:\n" +
            "pasta\n" +
            "salt\n" +
            "oil\n" +
            "water\n" +
            "Categories: italian, vegetarian, dinner";
        
             assertEquals(wanted, outContent.toString());
        }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
