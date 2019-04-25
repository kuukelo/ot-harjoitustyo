

package recipes.userinterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import recipes.domain.Method;



public class UserInterface {
    private List<Recipe> recipes;
    private List<Recipe> wantedRecipes;
    private File file;
//    private FileWriter fileWriter;

    
    public UserInterface() throws IOException {
        this.recipes = new ArrayList<>();
        this.file = new File("recipes.txt");
//        this.fileWriter = new FileWriter(file);
    }
    
    public void start(Scanner reader) throws FileNotFoundException, IOException {
        while (true) {
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("1 - Add a recipe");
            System.out.println("2 - List all recipes");
            System.out.println("3 - Find recipes");
            System.out.println("4 - Edit recipe");
            System.out.println("x - Quit");
            String order = reader.nextLine();
            System.out.println("");            
            if (order.equals("1")) {
                recipes.add(addRecipe(reader));
            }
            if (order.equals("2")) {
                System.out.println(listAll());
            }
            if (order.equals("3")) {
                findRecipes(reader);
                System.out.println("");
                System.out.println("Suitable recipes:\n");
                for (Recipe r: wantedRecipes) {
                    System.out.println(r);
                }                
            }
            if (order.equals("4")) {
                editRecipe(reader);                
            }
            if (order.equals("x")) {
                break;
            }
        }
    }

    public Recipe addRecipe(Scanner reader) throws FileNotFoundException, IOException {
        System.out.println("What is the recipe name?");
        String name = reader.nextLine();
        System.out.println("How long does it take to make?");
        System.out.print("Hours: ");
        Integer hours = Integer.parseInt(reader.nextLine());
        System.out.print("Minutes: ");
        Integer minutes = Integer.parseInt(reader.nextLine());
        Integer time = hours * 60 + minutes;

        System.out.println("What ingredients and how much does it need? Press enter to add an ingredient. x quits.");
        List<Ingredient> ingredients = getIngredients(reader);
        
        System.out.println("What food categories does the recipe belong to? Press enter to add a category. x quits.");
        List<Category> categories = getCategories(reader);

        System.out.println("Write the method one line at a time. Press enter to add a category. x quits.");
        List<Method> methods = getMethods(reader);

        Recipe recipe = new Recipe(name, time, ingredients, categories, methods);
        addRecipeToFile(recipe);
        System.out.println("Recipe " + name.toLowerCase() + " has been added");
        return recipe;
    }

    public String listAll() {
        ArrayList<Recipe> recipes = getAll();
        
        String returnable = "All recipes:\n";
        for (Recipe r: recipes) {
            returnable += r + "\n";
        }
        return returnable;
    }

    public List<Recipe> findRecipes(Scanner reader) {
        this.wantedRecipes = new ArrayList<>();
        System.out.println("How do you want to filter the recipes?");
        System.out.println("1 - Based on time");
        System.out.println("2 - Based on ingredients");
        System.out.println("3 - Based on categories");
        String order = reader.nextLine();
        
        if (order.equals("1")) {
            System.out.println("What is max time in minutes you want?");
            this.wantedRecipes = findRecipesBasedOnTime(Integer.parseInt(reader.nextLine()));
        }
        
        if (order.equals("2")) {
            System.out.println("What ingredients do you want the recipe to have? Press enter to add ingredient. x quits");
            this.wantedRecipes = findRecipesBasedOnIngredients(getIngredients(reader)); 
        } 
        
        if (order.equals("3")) {
            System.out.println("What categories do you want the recipe to have? Press enter to add category. x quits");
            this.wantedRecipes = findRecipesBasedOnCategory(getCategories(reader));
        }
        
        return wantedRecipes;
    } 

    private void editRecipe(Scanner reader) throws IOException {
        System.out.println("What recipe would you like to edit?");
        String name = reader.nextLine();
        System.out.println("Would you like to change the name (1) or the time (2)?");
        String order = reader.nextLine();
        List<Recipe> recipes = getAll();
        if (order.equals("1")) {
            System.out.println("What is the new name?");
            String newName = reader.nextLine();
            for (Recipe r: recipes) {
                if (r.getName().equals(name)) {
                    r.setName(newName);
                }
            }
        }
        if (order.equals("2")) {
            System.out.println("What is the new time?");
            int newTime = Integer.parseInt(reader.nextLine());
            for (Recipe r: recipes) {
                if (r.getName().equals(name)) {
                    r.setTime(newTime);
                }
            }
        }
        System.out.println("Recipe " + name + " has been edited.");
        writeOverFile(recipes);
        
    }

    public ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try (Scanner fileReader = new Scanner(file)) {

            while (fileReader.hasNextLine()) {
                
                String name = fileReader.nextLine();
                int time = Integer.valueOf(fileReader.nextLine());
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ArrayList<Category> categories = new ArrayList<>();
                ArrayList<Method> methods = new ArrayList<>();
                
                while (fileReader.hasNextLine()) {
                    String ingredient = fileReader.nextLine();
                    if (ingredient.equals("Categories:")) {
                        break;
                    }
                    ingredients.add(new Ingredient(ingredient));
                }
                
                while (fileReader.hasNextLine()) {
                    String category = fileReader.nextLine();
                    if (category.equals("Method:")) {
                        break;
                    }
                    categories.add(new Category(category));
                }
                while (fileReader.hasNextLine()) {
                    String method = fileReader.nextLine();
                    if (method.equals("..")) {
                        break;
                    }
                    methods.add(new Method(method));
                }
                recipes.add(new Recipe(name, time, ingredients, categories, methods));
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        return recipes;
    }

    private void addRecipeToFile(Recipe recipe) throws FileNotFoundException, IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        

        pw.println(recipe.getName());
        pw.println(String.valueOf(recipe.getTime()));
        for (Ingredient i: recipe.getIngredients()) {
            pw.println(i.getIngredient());
        }

        pw.println("Categories:");
        for (Category c: recipe.getCategories()) {
            pw.println(c.getCategory());
        }
        
        pw.println("Method:");
        int i = 1;
        for (Method m: recipe.getMethods()) {
            pw.println(i + ". " + m);
            i++;
        }
        pw.println("..");
        pw.close();
    }

    private List<Ingredient> getIngredients(Scanner reader) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (true) {
            String ingredient = reader.nextLine();
            if (ingredient.equals("x")) {
                break;
            }
            ingredients.add(new Ingredient(ingredient));
        }
        return ingredients;
    }

    private List<Category> getCategories(Scanner reader) {
        List<Category> categories = new ArrayList<>();
        while (true) {
            String category = reader.nextLine();
            if (category.equals("x")) {
                break;
            }
            categories.add(new Category(category));
        }
        return categories;
    }

    private List<Method> getMethods(Scanner reader) {
        List<Method> methods = new ArrayList<>();
        while (true) {
            String method = reader.nextLine();
            if (method.equals("x")) {
                break;
            }
            methods.add(new Method(method));
        }
        return methods;
    }

    private List<Recipe> findRecipesBasedOnIngredients(List<Ingredient> ingredients) {
        ArrayList<Recipe> recipes = getAll();
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: recipes) {
            boolean found = false;
            boolean match = true;
            for (Ingredient i: ingredients) {
                for (Ingredient i2: r.getIngredients()) {
                    if (i.equals(i2)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    match = false;
                    break;
                }  
            }
            if (match) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }

    private List<Recipe> findRecipesBasedOnCategory(List<Category> categories) {
        ArrayList<Recipe> recipes = getAll();
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: recipes) {
            boolean found = false;
            boolean match = true;
            for (Category c: categories) {
                for (Category c2: r.getCategories()) {
                    if (c.equals(c2)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    match = false;
                    break;
                }  
            }
            if (match) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }

    private List<Recipe> findRecipesBasedOnTime(int time) {
        ArrayList<Recipe> recipes = getAll();
        ArrayList<Recipe> wantedrecipes = new ArrayList<>();
        
        for (Recipe r: recipes) {
            if (r.getTime() <= time) {
                wantedrecipes.add(r);
            }
        }
        return wantedrecipes;
    }

    private void writeOverFile(List<Recipe> recipes) throws IOException {
        PrintWriter pw = new PrintWriter(file);

        for (Recipe r: recipes) {
            pw.println(r.getName());
            pw.println(String.valueOf(r.getTime()));
            
            for (Ingredient i: r.getIngredients()) {
                pw.println(i.getIngredient());
            }

            pw.println("Categories:");
            for (Category c: r.getCategories()) {
                pw.println(c.getCategory());
            }

            pw.println("Method:");
            int i = 1;
            for (Method m: r.getMethods()) {
                pw.println(i + ". " + m);
                i++;
            }
            pw.println("..");
        }
        pw.close();
    }

}
