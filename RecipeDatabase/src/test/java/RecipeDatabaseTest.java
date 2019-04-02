

//import java.io.ByteArrayInputStream;
import com.mycompany.recipedatabase.Recipe;
import com.mycompany.recipedatabase.UserInterface;
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


public class RecipeDatabaseTest {
    private String input;
    private String input2;
    
    public RecipeDatabaseTest() {
        this.input = "Pasta\n" + "90\n" + "pasta\n" +"salt\n" +"oil\n" +"water\n" 
                + "x\n" +"italian\n" +"vegetarian\n" +"dinner\n" +"x\n";
        this.input2 = "Soup\n" + "30\n" + "cream\n" +"chicken\n" +"3 onions\n" +"salt\n" 
                + "x\n" +"easy\n" + "dinner\n" +"x\n";
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
    public void addingRecipeWorks() {
        UserInterface userinterface = new UserInterface();
        ByteArrayInputStream in = new ByteArrayInputStream(this.input.getBytes());
        System.setIn(in);        
        String wanted = "Pasta, 1 h 30 min\n" +
            "Ingredients:\n" +
            "pasta\n" +
            "salt\n" +
            "oil\n" +
            "water\n" +
            "Categories: italian, vegetarian, dinner\n";
        Recipe recipe = userinterface.addRecipe(new Scanner(System.in));
        assertEquals(wanted, recipe.toString());
    }
    
//    @Test
//    public void listWorks() {
//        Scanner reader = new Scanner(System.in);
//        UserInterface userinterface = new UserInterface();
//        ByteArrayInputStream in = new ByteArrayInputStream(this.input.getBytes());
//        System.setIn(in);   
//        userinterface.addRecipe(reader);
//        in = new ByteArrayInputStream(this.input2.getBytes());
//        System.setIn(in);   
//        userinterface.addRecipe(reader);
//        
//        String wanted = "All recipes:\n" +
//            "Pasta, 1 h 30 min\n" +
//            "Ingredients:\n" +
//            "pasta\n" +
//            "salt\n" +
//            "oil\n" +
//            "water\n" +
//            "Categories: italian, vegetarian, dinner\n\n" +
//            "Soup, 30 min\n" +
//            "Ingredients:\n" +
//            "cream\n" +
//            "chicken\n" +
//            "3 onions\n" +
//            "salt\n" +
//            "Categories: easy, dinner";
//            String output = userinterface.listAll();
//            assertEquals(wanted, output);
//        }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
