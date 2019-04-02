

package recipedatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class UserInterface {

    
    public void start(Scanner reader) {
        List<Recipe> recipes = new ArrayList<>();
        while (true) {
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("1 - Add a recipe");
            System.out.println("2 - List all recipes");
            System.out.println("x - Quit");
            String order = reader.nextLine();
            System.out.println("");
            
            if (order.equals("1")) {
                addRecipe(reader, recipes);
            }
            if (order.equals("2")) {
                listAll(recipes);
                
            }
            if (order.equals("x")) {
                break;
            }
            
        }
    }

    public void addRecipe(Scanner reader, List recipes) {
        System.out.println("What is the recipe name?");
        String name = reader.nextLine();
        System.out.println("How lond does it take to make in minutes?");
        Integer time = Integer.parseInt(reader.nextLine());
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
        recipes.add(recipe);
        System.out.println("Recipe " + name.toLowerCase() + " has been added");
        
    }

    private void listAll(List<Recipe> recipes) {
        System.out.println("All recipes:");
        for (Recipe r: recipes) {
            System.out.println(r);
        }
    }

    
    
}
