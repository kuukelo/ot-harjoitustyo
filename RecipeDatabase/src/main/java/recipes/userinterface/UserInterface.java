

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
                System.out.println("Suitable recipes:\n");
                for (Recipe r: wantedRecipes) {
                    System.out.println(r);
                }                
            }
            if (order.equals("4")) {
                editRecipe(reader);
                System.out.println("Suitable recipes:\n");
                for (Recipe r: wantedRecipes) {
                    System.out.println(r);
                }                
            }
            if (order.equals("x")) {
                break;
            }
            
        }
    }

    public Recipe addRecipe(Scanner reader) throws FileNotFoundException, IOException {
//        Scanner reader = new Scanner(System.in);
        System.out.println("What is the recipe name?");
        String name = reader.nextLine();
        System.out.println("How long does it take to make?");
        System.out.print("Hours: ");
        Integer hours = Integer.parseInt(reader.nextLine());
        System.out.print("Minutes: ");
        Integer minutes = Integer.parseInt(reader.nextLine());
        Integer time = hours * 60 + minutes;
        System.out.println("What ingredients and how much does it need? "
                + "Press enter to add an ingredient. x quits.");
        List<Ingredient> ingredients = new ArrayList<>();
        while (true) {
            String ingredient = reader.nextLine();
            if (ingredient.equals("x")) {
                break;
            }
            ingredients.add(new Ingredient(ingredient));
        }
        System.out.println("What food categories does the recipe belong to? "
                + "Press enter to add a category. x quits.");
        List<Category> categories = new ArrayList<>();
        while (true) {
            String category = reader.nextLine();
            if (category.equals("x")) {
                break;
            }
            categories.add(new Category(category));
        }
        Recipe recipe = new Recipe(name, time, ingredients, categories);
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
            int time = Integer.parseInt(reader.nextLine());
            
            for (Recipe r: recipes) {
                if (r.getTime() < time) {
                    this.wantedRecipes.add(r);
                }
            }
        }

        if (order.equals("2")) {
            System.out.println("What ingredient do you want the recipe to have?");
            String ingredient = reader.nextLine();
            for (Recipe r: recipes) {
                for (Ingredient i: r.getIngredients()) {
                    if (i.getIngredient().equals(ingredient)) {
                        this.wantedRecipes.add(r);
                    }
                }
            }
            
        } 
        
        if (order.equals("3")) {
            System.out.println("What category do you want the recipe to have?");
            String category = reader.nextLine();
            for (Recipe r: recipes) {
                for (Category c: r.getCategories()) {
                    if (c.getCategory().equals(category)) {
                        this.wantedRecipes.add(r);
                    }
                }
            }
        }
        return wantedRecipes;
    } 

    private void editRecipe(Scanner reader) {
        System.out.println("What recipe would you like to edit?");
        String name = reader.nextLine();
        System.out.println("Would you like to change name (1) or time (2)?");
        String order = reader.nextLine();
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
        
    }

    private ArrayList<Recipe> getAll() {
        ArrayList<Recipe> recipes = new ArrayList<>();


        try (Scanner fileReader = new Scanner(file)) {

            while (fileReader.hasNextLine()) {
                
                String name = fileReader.nextLine();
                int time = Integer.valueOf(fileReader.nextLine());
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ArrayList<Category> categories = new ArrayList<>();
                
                while (fileReader.hasNextLine()) {
                    String ingredient = fileReader.nextLine();
                    if(ingredient.equals("Categories:")) {
                        break;
                    }
                    ingredients.add(new Ingredient(ingredient));
                }
                
                while (fileReader.hasNextLine()) {
                    String category = fileReader.nextLine();
                    if(category.equals("..")) {
                        break;
                    }
                    categories.add(new Category(category));
                }
            recipes.add(new Recipe(name, time, ingredients, categories));
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
        
//        pw.println("Method:");
//        int i = 1;
//        for (Method m: recipe.getMethods()) {
//            pw.println(i + ". " + m);
//            i++;
//        }
        pw.println("..");
        pw.close();
    }
}
