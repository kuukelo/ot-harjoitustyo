

package recipes.editor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.Recipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import recipes.dao.CategoryDao;
import recipes.dao.CategoryRecipeDao;
import recipes.dao.IngredientDao;
import recipes.dao.IngredientRecipeDao;
import recipes.dao.RecipeDao;
import recipes.domain.CategoryRecipe;
import recipes.domain.IngredientRecipe;


/**
 *This is editor, which gets information from different daos and processes it.  
 * The methods send info back to daos to save or to ui to show to the user.
 * @author Elina
 */

public class DatabaseEditor {
    

    RecipeDao rDao;
    IngredientDao iDao;
    CategoryDao cDao;
    IngredientRecipeDao irDao;
    CategoryRecipeDao crDao;
    
    /**
     *
     */
    public DatabaseEditor()  {
        rDao = new RecipeDao();
        iDao = new IngredientDao();
        cDao = new CategoryDao();
        irDao = new IngredientRecipeDao();
        crDao = new CategoryRecipeDao();
    }

    /**
     * This method can be used to clear and set up the database if needed.
     * At the moment it isn't used in other methods. But it was left here for convinience.
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
    
    /** 
     *  This is simple method that gets all recipes from database and returns them as a list.
     *  
     * @return
     * @throws SQLException
     */

   
    public List<Recipe> getAll() throws SQLException {
        return rDao.list();
    }
    
    /**
     *
     * @param recipes
     * @return
     */
    public String recipesAsList(List<Recipe> recipes) {
        String returnable = "";
        for (Recipe r: recipes) {
            returnable += r.getName() + ", " + r.getTimeInHours() + "\n" + r.getCategoriesAsString() + "\n";
        }
        return returnable;
        
    }

    /**
     * This method gets all recipes that use wanted ingredients and returns them as a list.
     * @param ingredients
     * @return
     * @throws SQLException
     */

    
    public List<Recipe> findRecipesBasedOnIngredients(List<Ingredient> ingredients) throws SQLException {
        List<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: rDao.list()) {
            if (r.getIngredients().containsAll(ingredients)) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }


    /**
     * This method gets all recipes that belong to wanted categories and returns them as a list.
     * @param categories
     * @return
     * @throws SQLException
     */

    
    public List<Recipe> findRecipesBasedOnCategory(List<Category> categories) throws SQLException {
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: rDao.list()) {
            if (r.getCategories().containsAll(categories)) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }
    

    /**
     * This method gets all recipes that have preparing time lower than wanted time and returns them as a list.
     * @param time
     * @return
     * @throws SQLException
     */


    public List<Recipe> findRecipesBasedOnTime(int time) throws SQLException {
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: rDao.list()) {
            if (r.getTime() <= time) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }


    /**
     * This method finds the wanted recipe based on given recipe name and returns it.
     * @param name
     * @return
     * @throws SQLException
     */

    
    public Recipe getRecipe(String name) throws SQLException {
        for (Recipe r: rDao.list()) {
            if (r.getName().toLowerCase().equals(name.toLowerCase())) {
                return r;
            }
        }
        return null;
    }

    /**
     * This method edits the time of the wanted recipe and saves it to database.
     * @param recipeName
     * @param time
     * @throws SQLException
     */


    public void editTime(String recipeName, int time) throws SQLException {
        for (Recipe r: rDao.list()) {
            if (r.getName().equals(recipeName)) {
                r.setTime(Integer.valueOf(time));
                rDao.update(r);
                break;
            }
        }
    }
    

    /**
     * This method edits the name of the wanted recipe and saves it to database.
     * @param recipeName
     * @param newName
     * @throws SQLException
     */

    
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


    /**
     * This method gets a recipe that has been updated and updates the info to database. 
     * @param r
     * @throws SQLException
     */

    
    public void updateRecipe(Recipe r) throws SQLException {
        rDao.update(r);
    }
    

    /**
     * This method gets info in strings and processes it to time in minutes and returns it.
     * @param hoursS
     * @param minutesS
     * @return
     */

    
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
    

    /**
     * This method gets info in strings and processes it to list of ingredients. 
     * @param text
     * @return
     */


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


    /**
     * This method gets info in strings and processes it to list of categories. 
     * @param text
     * @return
     */

    
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

    /**
     * This method gets info in strings and processes it to info making a new recipe need. Then it creates a new recipe and saves it to database. 
     * @param name
     * @param hours
     * @param minutes
     * @param ingredients
     * @param categories
     * @param instructionsText
     * @throws SQLException
     */

    
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


    /**
     * This method checks if database contains the wanted ingredients.  
     * @param ingredient
     * @return
     * @throws SQLException
     */

    
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
    

    /**
     * This method checks if database contains the wanted category.  
     * @param category
     * @return
     * @throws SQLException
     */

    
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


    /**
     * This method checks that database doesn't already have a recipe by the wanted name.
     * @param name
     * @return
     * @throws SQLException
     */

    
    public boolean nameIsUnique(String name) throws SQLException {
        List<Recipe> recipes = rDao.list();
        for (Recipe r: recipes) {
            if (r.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method gets the newly created recipe and adds it's ingredients to database if they don't exist already.  
     * Then it adds them to IngredientRecipe table so that we can match the recipe and ingredients.
     * @param recipe
     * @throws SQLException
     */

    
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

    /* 
    * This method gets the newly created recipe and adds it's categories to database if they doen't exist already.  
    * Then it adds them to CategoryRecipe table so that we can match the recipe and categories.
    *
    */

    /**
     * This method gets the newly created recipe and adds it's categories to database if they doen't exist already.  
     * Then it adds them to CategoryRecipe table so that we can match the recipe and categories.
     * @param recipe
     * @throws SQLException
     */

    
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

    /**
     * This method sorts all recipes based on time (3 categories), puts them in lists and returns the lists. 
     * @return
     * @throws SQLException
     */


    public List<List> getRecipesSortedByTime() throws SQLException {
        List<List> recipeLists = new ArrayList<>();
        List<Recipe> fast = new ArrayList<>();
        List<Recipe> medium = new ArrayList<>(); 
        List<Recipe> slow = new ArrayList<>();
        
        for (Recipe r: getAll()) {
            if (r.getTime() <= 30) {
                fast.add(r);
            } else if (r.getTime() <= 75) {
                medium.add(r);
            } else {
                slow.add(r);
            }
        }
        recipeLists.add(fast);
        recipeLists.add(medium);
        recipeLists.add(slow);
        
        return recipeLists;
       
    }


    /**
     * This method sorts all recipes based on categories, 
     * puts them in lists and puts the list with the category as a key to a hasmap and returns it. 
     * @return
     * @throws SQLException
     */

    
    public Map<Category, List> getRecipesSortedByCategories() throws SQLException {
        List<Category> categories = cDao.list();
        Map<Category, List> listOfCategoryLists = new HashMap<>();
        List<Recipe> recipes = getAll();
        for (Category c: categories) {
            List<Recipe> categoryList = new ArrayList<>();
            for (Recipe r: recipes) {
                if (r.getCategories().contains(c)) {
                    categoryList.add(r);
                }
            }
            listOfCategoryLists.put(c, categoryList);
        }
        return listOfCategoryLists;
    }    
       
}
