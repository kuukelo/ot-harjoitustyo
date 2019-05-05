

//import java.io.ByteArrayInputStream;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import recipes.dao.CategoryDao;
import recipes.dao.CategoryRecipeDao;
import recipes.dao.IngredientDao;
import recipes.dao.IngredientRecipeDao;
import recipes.dao.RecipeDao;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.Recipe;
import recipes.editor.DatabaseEditor;
import javafx.scene.control.Hyperlink;
import org.junit.runner.RunWith;
import recipes.ui.RecipeDatabase;


public class RecipeDatabaseTest {
    private DatabaseEditor dbEditor;
    private List<Recipe> recipes;
    private RecipeDao rDao;
    private IngredientDao iDao;
    private CategoryDao cDao;
    private IngredientRecipeDao irDao;
    private CategoryRecipeDao crDao;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;

    
    public RecipeDatabaseTest() {
        this.dbEditor = new DatabaseEditor();
        this.recipes = new ArrayList<>();
        this.rDao = new RecipeDao();
        this.iDao = new IngredientDao();
        this.irDao = new IngredientRecipeDao();
        this.cDao = new CategoryDao();
        this.crDao = new CategoryRecipeDao();
        this.r1 = getRecipe1();
        this.r2 = getRecipe2();
        this.r3 = getRecipe3();
    }
    
//    @Rule
//    public TemporaryFolder testFolder = new TemporaryFolder();
//    
//    @Rule
//    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
//    
//    @BeforeClass
//    public void static setUpClass() {
//        
//    }
//    
//    @AfterClass
//    public void static tearDownClass() {
//        
//    }
    
    @Before
    public void setUp() throws SQLException {
        this.recipes = rDao.list();
        dbEditor.setUpDatabase();
        
        addRecipeToDatabase(r1);
        addRecipeToDatabase(r2);

    }
    
    @After
    public void tearDown() throws SQLException {
        dbEditor.setUpDatabase();
        for (Recipe r: recipes) {
            rDao.create(r);
            dbEditor.addIngredientsToDatabase(r);
            dbEditor.addCategoriesToDatabase(r);
        }
    }
    
    @Test
    public void findRecipesBasedOnIngredientsWorks() throws SQLException {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("apples"));
        addRecipeToDatabase(r3);
        assertTrue(dbEditor.findRecipesBasedOnIngredients(ingredients).size() == 2);
    }
    @Test
    public void findRecipesBasedOnIngredientsWorks2() throws SQLException {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("apples"));
        assertTrue(dbEditor.findRecipesBasedOnIngredients(ingredients).contains(r2));
    }

    @Test
    public void findRecipesBasedOnCategoryWorks() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("easy"));
        addRecipeToDatabase(r3);
        assertTrue(dbEditor.findRecipesBasedOnCategory(categories).size() == 2);
    }
    @Test
    public void findRecipesBasedOnCategoriesWorks2() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("dinner"));
        assertTrue(dbEditor.findRecipesBasedOnCategory(categories).contains(r1));
    }
    
    @Test
    public void findRecipesBasedOnTimeWorks1() throws SQLException {
        int time = 60;
        assertTrue(dbEditor.findRecipesBasedOnTime(time).contains(r1));
    }
    @Test
    public void findRecipesBasedOnTimeWorks2() throws SQLException {
        int time = 60;
        addRecipeToDatabase(r3);
        assertTrue(dbEditor.findRecipesBasedOnTime(time).size() == 2);
    }
    
    @Test
    public void getRecipeWorks() throws SQLException {
        assertTrue(dbEditor.getRecipe(r1.getName()).equals(r1));
    }
    @Test
    public void getRecipeWorks2() throws SQLException {
        assertTrue(dbEditor.getRecipe("WrongRecipe") == null);
    }
    
    @Test
    public void editTimeWorks1() throws SQLException {
        dbEditor.editTime(r1.getName(), 45);
        assertTrue(dbEditor.getRecipe(r1.getName()).getTime() == 45);
    }
    @Test
    public void editNameWorks1() throws SQLException {
        dbEditor.editName(r1.getName(), "New name");
        int i = r1.getId();
        assertTrue(rDao.read(i).getName().equals("New name"));
    }
    @Test
    public void getTimeWorks1() {
        assertTrue(dbEditor.getTime("3", "") == 180);
    }
    @Test
    public void getTimeWorks2() {
        assertTrue(dbEditor.getTime("", "45") == 45);
    }
    @Test
    public void getTimeWorks3() {
        assertTrue(dbEditor.getTime("1", "30") == 90);
    }

    @Test
    public void getIngredientListWorks() {
        String s = "apples\nbananas\nmushrooms";
        assertTrue(dbEditor.getIngredientList(s).size() == 3);
    }
    @Test
    public void getIngredientListWorks2() {
        String s = "apples\nbananas\nmushrooms";
        assertTrue(dbEditor.getIngredientList(s).contains(new Ingredient("bananas")));
    }
    
    @Test
    public void getCategoryListWorks() throws SQLException {
        String s = "dinner\nchinese\neasy";
        assertTrue(dbEditor.getCategoryList(s).size() == 3);
    }
    @Test
    public void getCategoryListWorks2() throws SQLException {
        String s = "dinner\nchinese\neasy";
        assertTrue(dbEditor.getCategoryList(s).contains(new Category("easy")));
    }

    @Test
    public void addNewRecipeWorks() throws SQLException {
        dbEditor.addNewRecipe(r3.getName(), "0", "30", "apples\nwater\nsoda", "dinner\neasy", r3.getInstructions());
        assertTrue(rDao.list().contains(r3));
    }
    
    @Test
    public void databaseContainsIngredientWorks1() throws SQLException {
        iDao.create(new Ingredient("olives"));
        assertTrue(dbEditor.databaseContainsIngredient(new Ingredient("olives")));
    }
    @Test
    public void databaseContainsIngredientWorks2() throws SQLException {
        assertFalse(dbEditor.databaseContainsIngredient(new Ingredient("cotton candy")));
    }

    @Test
    public void databaseContainsCategoryWorks1() throws SQLException {
        cDao.create(new Category("fast"));
        assertTrue(dbEditor.databaseContainsCategory(new Category("fast")));
    }
    @Test
    public void databaseContainsCategoryWorks2() throws SQLException {
        assertFalse(dbEditor.databaseContainsCategory(new Category("healthy")));
    }
    
    @Test
    public void nameIsUniqueWorks1() throws SQLException {
        assertFalse(dbEditor.nameIsUnique("Onionsoup"));
    }
    @Test
    public void nameIsUniqueWorks2() throws SQLException {
        assertTrue(dbEditor.nameIsUnique("Bouillabaise"));
    }

    @Test
    public void addIngredientToDatabaseWorks1() throws SQLException {
        rDao.create(r3);
        dbEditor.addIngredientsToDatabase(r3);
        assertTrue(iDao.list().contains(r3.getIngredients().get(2)));
    }
//    @Test
//    public void addIngredientToDatabaseWorks2() throws SQLException {
//        rDao.create(r3);
//        dbEditor.addIngredientsToDatabase(r3);
//        assertTrue(irDao.list().get(6).getIngredient().equals(new Ingredient("soda")));
//    }
    
    @Test
    public void addCategoryToDatabaseWorks1() throws SQLException {
        rDao.create(r3);
        dbEditor.addCategoriesToDatabase(r3);
        assertTrue(cDao.list().contains(r3.getCategories().get(0)));
    }
    
//    @Test
//    public void addCategoryToDatabaseWorks2() throws SQLException {
//        rDao.create(r3);
//        dbEditor.addCategoriesToDatabase(r3);
//        assertTrue(crDao.list().get(4).getCategory().equals(new Category("drink")));
//        
//    }

    private Recipe getRecipe1() {
        String name = "Onionsoup";
        int time = 60;
        String instructions = "1. Add all ingredient to the pot.\n2. Boil for 1 hour.";
        
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("onions"));
        ingredients.add(new Ingredient("salt"));
        ingredients.add(new Ingredient("water"));
        
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("easy"));
        categories.add(new Category("dinner"));
        
        return new Recipe(name, time, ingredients, categories, instructions);
    }

    private Recipe getRecipe2() {
        String name = "Applepie";
        int time = 90;
        String instructions = "1. Put crust in the pan.\n2. Put apple slizes on top.\n3. Pour sugar on top.";
        
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("apples"));
        ingredients.add(new Ingredient("crust"));
        ingredients.add(new Ingredient("sugar"));
        
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("dessert"));
        
        return new Recipe(name, time, ingredients, categories, instructions);
    }

    private Recipe getRecipe3() {
        String name = "Appledrink";
        int time = 30;
        String instructions = "1. Add all ingredients together.";
        
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("apples"));
        ingredients.add(new Ingredient("water"));
        ingredients.add(new Ingredient("soda"));
        
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("drink"));
        categories.add(new Category("dessert"));
        categories.add(new Category("easy"));

        
        return new Recipe(name, time, ingredients, categories, instructions);
    }

    private void addRecipeToDatabase(Recipe r) throws SQLException {
        rDao.create(r);
        dbEditor.addIngredientsToDatabase(r);
        dbEditor.addCategoriesToDatabase(r);
    }
}
