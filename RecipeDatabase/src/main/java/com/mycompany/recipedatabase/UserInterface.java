

package com.mycompany.recipedatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class UserInterface {
    private List<Recipe> recipes;
    
    public UserInterface() {
        this.recipes = new ArrayList<>();
    }
    
    public void start(Scanner reader) {
        while (true) {
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("1 - Add a recipe");
            System.out.println("2 - List all recipes");
            System.out.println("x - Quit");
            String order = reader.nextLine();
            System.out.println("");
            
            if (order.equals("1")) {
                recipes.add(addRecipe(reader));
            }
            if (order.equals("2")) {
                System.out.println(listAll());
                
            }
            if (order.equals("x")) {
                break;
            }
            
        }
    }

    public Recipe addRecipe(Scanner reader) {
//        Scanner reader = new Scanner(System.in);
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
        System.out.println("Recipe " + name.toLowerCase() + " has been added");
        return recipe;
    }

    public String listAll() {
        String returnable = "All recipes:\n";
        for (Recipe r: recipes) {
            returnable += r + "\n";
        }
        return returnable;
    }

    
    
}
