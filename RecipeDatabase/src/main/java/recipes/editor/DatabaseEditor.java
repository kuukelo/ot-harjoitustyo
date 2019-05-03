

package recipes.editor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.Recipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import recipes.dao.CategoryDao;
import recipes.dao.CategoryRecipeDao;
import recipes.dao.IngredientDao;
import recipes.dao.IngredientRecipeDao;
import recipes.dao.RecipeDao;
import recipes.domain.CategoryRecipe;
import recipes.domain.IngredientRecipe;

@Component
public class DatabaseEditor {
    
    @Autowired
    RecipeDao rDao;
    @Autowired
    IngredientDao iDao;
    @Autowired
    CategoryDao cDao;
    @Autowired
    IngredientRecipeDao irDao;
    @Autowired
    CategoryRecipeDao crDao;
    
    public DatabaseEditor()  {
        rDao = new RecipeDao();
        iDao = new IngredientDao();
        cDao = new CategoryDao();
        irDao = new IngredientRecipeDao();
        crDao = new CategoryRecipeDao();
    }
    
    /* 
    * Listaa käyttäjälle kaikki tiedostossa olevat reseptit. Metodi ohjaa toiseen metodiin, joka hakee tiedot tiedostosta. 
    * Tämä metodi, muuttaa ne tulostettavaan muotoon. 
    * 
    */
    public static void setUpDatabase() {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "")) {
            conn.prepareStatement("DROP TABLE Recipe IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Recipe ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(60), "
                    + "time INTEGER,"
                    + "instructions LONGTEXT);").executeUpdate();
            conn.prepareStatement("DROP TABLE Ingredient IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Ingredient ("
                    + "id SERIAL PRIMARY KEY, "
                    + "ingredient VARCHAR(60));").executeUpdate();
            conn.prepareStatement("DROP TABLE Category IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Category ("
                    + "id SERIAL PRIMARY KEY, "
                    + "category VARCHAR(60));").executeUpdate();
            conn.prepareStatement("DROP TABLE CategoryRecipe IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE CategoryRecipe ("
                    + "category_id INTEGER, "
                    + "recipe_id INTEGER, "
                    + "FOREIGN KEY (recipe_id) REFERENCES Recipe(id), "
                    + "FOREIGN KEY (category_id) REFERENCES Category(id));").executeUpdate();
            conn.prepareStatement("DROP TABLE IngredientRecipe IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE IngredientRecipe ("
                    + "ingredient_id INTEGER, "
                    + "recipe_id INTEGER, "
                    + "FOREIGN KEY (recipe_id) REFERENCES Recipe(id), "
                    + "FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));").executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String listAll() throws SQLException {
        List<Recipe> recipes = rDao.list();
        if (recipes.isEmpty()) {
            return "There are no recipes in the database.";
        }
        
        String returnable = "\nAll recipes:\n";
        for (Recipe r: recipes) {
            returnable += r + "\n";
        }
        return returnable;
    }

    /* 
    * Metodi hakee reseptejä ainesosien perusteella tiedostosta.
    * 
    *
    */
    
    public List<Recipe> findRecipesBasedOnIngredients(List<Ingredient> ingredients) throws SQLException {
        List<Recipe> recipes = rDao.list();
        List<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: recipes) {
            if (r.getIngredients().containsAll(ingredients)) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }

    /* 
    * Metodi hakee reseptejä kategorioiden perusteella.
    * 
    *
    */
    
    public List<Recipe> findRecipesBasedOnCategory(List<Category> categories) throws SQLException {
        List<Recipe> recipes = rDao.list();
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: recipes) {
            if (r.getCategories().containsAll(categories)) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }
    
    /* 
    * Hakee reseptejä aikaan perustuen.
    * 
    *
    */

    public List<Recipe> findRecipesBasedOnTime(int time) throws SQLException {
        List<Recipe> recipes = rDao.list();
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: recipes) {
            if (r.getTime() <= time) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }

    /* 
    * Tulostaa käyttäjälle halutun reseptin. Hakee ensin kaikki, etsii oikean reseptin ja tulostaa sen tiedot. 
    * 
    *
    */
    
    public Recipe getRecipe(String name) throws SQLException {
        List<Recipe> recipes = rDao.list();
        for (Recipe r: recipes) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    public void editTime(String recipeName, int time) throws SQLException {
        List<Recipe> recipes = rDao.list();
        for (Recipe r: recipes) {
            if (r.getName().equals(recipeName)) {
                r.setTime(Integer.valueOf(time));
                rDao.update(r);
                break;
            }
        }
    }
    
    public void editName(String recipeName, String newName) throws SQLException {
        List<Recipe> recipes = rDao.list();
        for (Recipe r: recipes) {
            if (r.getName().equals(recipeName)) {
                r.setName(newName);
                rDao.update(r);
                break;
            }
        }
    }
    
    public Integer getTime(String hoursS, String minutesS) {
        int hours = 0;
        int minutes = 0;
        if (!hoursS.equals("")) {
            hours = Integer.valueOf(hoursS);
        }
        if (!minutesS.equals("")) {
            minutes = Integer.valueOf(minutesS);
        }
        return 60 * hours + minutes;
    }

    public List<Ingredient> getIngredientList(String text) {
        String s[] = text.split("\\r?\\n");
        List<String> strings = new ArrayList<>(Arrays.asList(s));
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String string: strings) {
            Ingredient i = new Ingredient(string);
            ingredientList.add(i);
        }
        return ingredientList;
    }

    public List<Category> getCategoryList(String text) {
        String s[] = text.split("\\r?\\n");
        List<String> strings = new ArrayList<>(Arrays.asList(s));
        List<Category> categoryList = new ArrayList<>();
        for (String string: strings) {
            Category c = new Category(string);
            categoryList.add(c);
        }
        return categoryList;
    }

    public void addNewRecipe(String name, String hours, String minutes, String ingredients, String categories, String instructionsText) throws SQLException {
        String recipeName = name;
        int recipeTime = getTime(hours, minutes);
        List<Ingredient> ingredientList = getIngredientList(ingredients);
        List<Category> categoryList = getCategoryList(categories);
        String instructions = instructionsText;        
        
        Recipe recipe = new Recipe(recipeName, recipeTime, ingredientList, categoryList, instructions);
        
        rDao.create(recipe);
        addIngredientsToDatabase(recipe);
        addCategoriesToDatabase(recipe);
    }

    public boolean databaseContainsIngredient(Ingredient ingredient) throws SQLException {
        List<Ingredient> allIngredients = iDao.list();
        for (Ingredient i: allIngredients) {
            if (i.equals(ingredient)) {
                ingredient.setId(i.getId());
                return true;
            }
        }    
        return false;
    }
    
    public boolean databaseContainsCategory(Category category) throws SQLException {
        List<Category> allCategories = cDao.list();
        for (Category c: allCategories) {
            if (c.equals(category)) {
                category.setId(c.getId());
                return true;
            }
        }
        return false;
    }

    public boolean nameIsUnique(String name) throws SQLException {
        List<Recipe> recipes = rDao.list();
        for (Recipe r: recipes) {
            if (r.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public void addIngredientsToDatabase(Recipe recipe) throws SQLException {
        for (Ingredient i: recipe.getIngredients()) {
            IngredientRecipe ir = new IngredientRecipe(i, recipe);
            if (!databaseContainsIngredient(i)) {
                iDao.create(i);  
            } else {
                i.setId(iDao.getGeneratedId(i.getIngredient()));
            }
            irDao.create(ir);
        }  
    }

    public void addCategoriesToDatabase(Recipe recipe) throws SQLException {
        for (Category c: recipe.getCategories()) {
            CategoryRecipe cr = new CategoryRecipe(c, recipe);
            if (!databaseContainsCategory(c)) {
                cDao.create(c);
            } else {
                c.setId(cDao.getGeneratedId(c.getCategory()));
            }
            crDao.create(cr);
        }
    }
}
