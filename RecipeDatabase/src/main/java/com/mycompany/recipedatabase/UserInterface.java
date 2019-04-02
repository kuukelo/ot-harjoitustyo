

package com.mycompany.recipedatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class UserInterface {
    private List<Recipe> recipes;
    private List<Recipe> wantedRecipes;
    
    public UserInterface() {
        this.recipes = new ArrayList<>();
    }
    
    public void start(Scanner reader) {
        while (true) {
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("1 - Add a recipe");
            System.out.println("2 - List all recipes");
            System.out.println("3 - Find recipes");
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
//                System.out.println(listAll());
                
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

    private void findRecipes(Scanner reader) {
        this.wantedRecipes = new ArrayList<>();
        System.out.println("How do you want to filter the recipes?");
        System.out.println("1 - Based on time");
//        System.out.println("2 - Based on ingredients");
//        System.out.println("3 - Based on categories");
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
        System.out.println("Suitable recipes:\n");
        for (Recipe r: wantedRecipes) {
            System.out.println(r);
            }
//        if (order.equals("2")) {
//            System.out.println("What ingredient do you want the recipe to have?");
//            String ingredient = reader.nextLine();
//        }
    }

    
    
}
