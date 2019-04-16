

package RecipeDatabase.domain;

import java.util.List;

public class Recipe {
    private String name;
    private int time;
    private List<Ingredient> ingredients;
    private List<Category> categories;
    
    public Recipe(String name, Integer time, List<Ingredient> ingredients, List<Category> categories) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
        this.categories = categories;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public String getTimeInHours() {
        if (this.time < 60) {
            return this.time + " min";
        } else {
            int hours = this.time / 60;
            int minutes = this.time % 60;
            if (minutes == 0) {
                return hours + " h";
            } else {
                return hours + " h " + minutes + " min";
    
            }
        }
    }
    public String toString() {
        String returnable = name + ", " + getTimeInHours() + 
                "\nIngredients:";
        for (Ingredient i: ingredients) {
            returnable += "\n" + i;
        }
        returnable += "\nCategories: ";
        for (Category c: categories) {
            returnable += c + ", ";
        }
        returnable = returnable.substring(0, returnable.length() - 2) + "\n";
        return returnable;
    }
    
}
