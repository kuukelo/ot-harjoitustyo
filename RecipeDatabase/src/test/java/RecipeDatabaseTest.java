

//import java.io.ByteArrayInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.Method;
import recipes.domain.Recipe;
import recipes.userinterface.UserInterface;


public class RecipeDatabaseTest {
    private String input;
    private String input2;
    private UserInterface userinterface;
    private Recipe recipe;
    private File file;
    
    public RecipeDatabaseTest() throws IOException {
        this.userinterface = new UserInterface(new File("recipes_test.txt"));
        this.file = new File("recipes.txt");
        this.input = "Pasta\n" + "1\n" + "30\n" + "pasta\n" +"salt\n" +"oil\n" +"water\n" 
                + "x\n" +"italian\n" +"vegetarian\n" +"dinner\n" +"x\n" + "Boil for 10 min.\n" + "x";
        this.input2 = "Soup\n" + "0\n" + "30\n" + "cream\n" +"chicken\n" +"3 onions\n" +"salt\n" 
                + "x\n" +"easy\n" + "dinner\n" +"x\n";
    }
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    File userFile; 
    
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
        ByteArrayInputStream in = new ByteArrayInputStream(this.input.getBytes());
        System.setIn(in);    
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void addingRecipeWorks() throws IOException {    
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
    @Test
    public void addingRecipeToTheFileWorks() throws IOException {
        userinterface = new UserInterface(new File("recipes_test.txt"));
        recipe = new Recipe("Pasta", 90, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userinterface.addRecipeToFile(recipe);
        assertTrue(userinterface.getAll().contains(recipe));
    }
    @Test
    public void findRecipesBasedOnTimeWorks() throws IOException {
        recipe = new Recipe("Sandwich", 10, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userinterface.addRecipeToFile(recipe);
        assertTrue(userinterface.findRecipesBasedOnTime(15).contains(recipe));
    }
    @Test
    public void findRecipesBasedOnIngredientWorks() throws IOException {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("tomatoes"));
        ingredients.add(new Ingredient("water"));
        ingredients.add(new Ingredient("salt"));
        recipe = new Recipe("Tomatosoup", 60, ingredients, new ArrayList<>(), new ArrayList<>());
        userinterface.addRecipeToFile(recipe);
        ingredients.remove("water");
        ingredients.remove("salt");
        assertTrue(userinterface.findRecipesBasedOnIngredients(ingredients).contains(recipe));
    }
    @Test
    public void findRecipesBasedOnCategoryWorks() throws IOException {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("spanish"));
        categories.add(new Category("dinner"));
        categories.add(new Category("seafood"));
        recipe = new Recipe("Paella", 60, new ArrayList<>(), categories, new ArrayList<>());
        userinterface.addRecipeToFile(recipe);
        categories.remove("dinner");
        categories.remove("seafood");
        assertTrue(userinterface.findRecipesBasedOnCategory(categories).contains(recipe));
    }
    @Test
    public void findRecipesBasedOnTimeWorks2() throws IOException {
        recipe = new Recipe("Boiled egg", 10, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        userinterface.addRecipeToFile(recipe);
        assertFalse(userinterface.findRecipesBasedOnTime(5).contains(recipe));
    }
    @Test
    public void writeOverFileWorks() throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe2 = new Recipe("Boiled egg", 10, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Recipe recipe3 = new Recipe("Omelette", 30, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        recipes.add(recipe2);
        recipes.add(recipe3);
        userinterface.writeOverFile(recipes);
        assertTrue(userinterface.getAll().size() == 2 && userinterface.getAll().contains(recipe2) && userinterface.getAll().contains(recipe3));
    }
    @Test
    public void listAllWorks() throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe2 = new Recipe("Boiled egg", 10, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Recipe recipe3 = new Recipe("Omelette", 30, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        recipes.add(recipe2);
        recipes.add(recipe3);
        userinterface.writeOverFile(recipes);
        String wanted = "All recipes:\n" + recipe2 + "\n" + recipe3 + "\n"; 
        assertTrue(wanted.equals(userinterface.listAll()));
    }
//    @Test
//    public void getFullRecipeWorks() throws IOException {
//        recipe = new Recipe("Pesto", 15, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//        userinterface.addRecipeToFile(recipe);
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//
//        System.setOut(new PrintStream(outContent));
//        userinterface.getFullRecipe(recipe.getName());
//        String wanted = "\nFull recipe:\n" + recipe;     
//        assertTrue(wanted.equals(outContent.toString()));
//    }
    
//    @Test
//    public void findRecipesBasedOnIngredientWorks2() throws IOException {
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient("tomatoes"));
//        ingredients.add(new Ingredient("water"));
//        ingredients.add(new Ingredient("salt"));
//        recipe = new Recipe("Tomatosoup", 60, ingredients, new ArrayList<>(), new ArrayList<>());
//        userinterface.addRecipeToFile(recipe);
//        ingredients.remove("tomatoes");
//        ingredients.remove("water");
//        ingredients.remove("salt");
//        ingredients.add(new Ingredient("flour"));
//        assertFalse(userinterface.findRecipesBasedOnIngredients(ingredients).contains(recipe));
//    }
//    @Test
//    public void findRecipesBasedOnCategoryWorks2() throws IOException {
//        List<Category> categories = new ArrayList<>();
//        categories.add(new Category("spanish"));
//        categories.add(new Category("dinner"));
//        categories.add(new Category("seafood"));
//        recipe = new Recipe("Paella", 60, new ArrayList<>(), categories, new ArrayList<>());
//        userinterface.addRecipeToFile(recipe);
//        categories.remove("spanish");
//        categories.remove("dinner");
//        categories.remove("seafood");
//        categories.add(new Category("italian"));
//        assertTrue(!userinterface.findRecipesBasedOnCategory(categories).contains(recipe));
//    }
    
//    @Test
//    public void getFullRecipeWorks() throws IOException {
//        userinterface = new UserInterface(new File("recipes_test.txt"));
//        recipe = new Recipe("Pasta", 90, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//        userinterface.addRecipeToFile(recipe);
//        assertTrue(userinterface.getAll().contains(recipe));
//    }
//    @Test
//    public void getAllWorks() throws IOException {
//        userinterface = new UserInterface(new File("recipes_test.txt"));
//        assertTrue(userinterface.getAll().contains(recipe));
//    }
    

//    @Test
//    public void listAllWorks() throws IOException {
//        
//        assertTrue(userinterface.getAll().contains(recipe));
//    }
    
//    @Test
//    public void listWorks() {        
//        userinterface.addRecipe(new Scanner(System.in));
////        in = new ByteArrayInputStream(this.input2.getBytes());
////        System.setIn(in);   
////        userinterface.addRecipe(reader);
//        
//        String wanted = "All recipes:\n" +
//            "Pasta, 1 h 30 min\n" +
//            "Ingredients:\n" +
//            "pasta\n" +
//            "salt\n" +
//            "oil\n" +
//            "water\n" +
//            "Categories: italian, vegetarian, dinner\n\n";
////            "Soup, 30 min\n" +
////            "Ingredients:\n" +
////            "cream\n" +
////            "chicken\n" +
////            "3 onions\n" +
////            "salt\n" +
////            "Categories: easy, dinner";
//            String output = userinterface.listAll();
//            assertEquals(wanted, output);
//    }
//    @Test
//    public void compareTimeWorks() {
//        Recipe recipe = userinterface.addRecipe(new Scanner(System.in));
//        
//        String input3 = "1\n" + "100\n";
//        ByteArrayInputStream in2 = new ByteArrayInputStream(input3.getBytes());
//        System.setIn(in2);
//        assertEquals(recipe.toString(), userinterface.findRecipes(new Scanner(System.in)).get(0).toString());
//        
//    }    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
//    public void testaa(List<String> tiedostonSisalto, List<String> komentolista, List<String> odotetutTulosteet, List<String> eiOdotetutTulosteet) {
//        String tiedosto = luoTestitiedosto(tiedostonSisalto);
//
//        String komennot = tiedosto + "\n";
//        for (String komento : komentolista) {
//            komennot += komento + "\n";
//        }
//        komennot += "lopeta\n";
//
//        io.setSysIn(komennot);
//        Reseptihaku.main(new String[]{});
//
//        String tulostus = io.getSysOut();
//        for (String odotettu : odotetutTulosteet) {
//            if (odotettu.trim().isEmpty()) {
//                continue;
//            }
//
//            assertTrue("Odotettiin, että tulostuksessa olisi merkkijono " + odotettu + ".\nKun tiedoston sisältö on:\n" + riveittain(tiedostonSisalto) + "\nKokeile ohjelmaa komennoilla:\n" + komennot + ".", tulostus.contains(odotettu));
//        }
//
//        for (String eiOdotettu : eiOdotetutTulosteet) {
//            if (eiOdotettu.trim().isEmpty()) {
//                continue;
//            }
//
//            assertFalse("Odotettiin, että tulostuksessa ei olisi merkkijonoa " + eiOdotettu + ".\nKun tiedoston sisältö on:\n" + riveittain(tiedostonSisalto) + "\nKokeile ohjelmaa komennoilla:\n" + komennot + ".", tulostus.contains(eiOdotettu));
//        }
//
//        try {
//            new File(tiedosto).delete();
//        } catch (Throwable t) {
//            System.out.println("Testitiedoston " + tiedosto + " poistaminen epäonnistui.");
//        }
//    }
//
//    private String luoTestitiedosto(List<String> tiedostonSisalto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
