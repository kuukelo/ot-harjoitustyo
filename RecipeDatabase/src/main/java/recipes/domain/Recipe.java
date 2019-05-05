

package recipes.domain;


import java.util.Objects;
import java.util.List;

/**
 *
 * @author Elina
 */
public class Recipe {
    private String name;
    private int time;
    private List<Ingredient> ingredients;
    private List<Category> categories;
    private Integer id;
    private String instructions;
    
    /**
     *
     * @param name
     * @param time
     * @param ingredients
     * @param categories
     * @param instructions
     */
    public Recipe(String name, Integer time, List<Ingredient> ingredients, List<Category> categories, String instructions) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
        this.categories = categories;
        this.instructions = instructions;
    }

    /**
     *
     * @param id
     * @param name
     * @param time
     * @param ingredients
     * @param categories
     * @param instructions
     */
    public Recipe(Integer id, String name, Integer time, List<Ingredient> ingredients, List<Category> categories, String instructions) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
        this.categories = categories;
        this.instructions = instructions;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @return
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     *
     * @return
     */
    public String getInstructions() {
        return this.instructions;
    }

    /**
     *
     * @param instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     *
     * @param categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     *
     * @return
     */
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
        String returnable = "\n" + name + ", " + getTimeInHours();
        returnable += "\n" + getIngredientsAsString();
        returnable += "\n" + getCategoriesAsString();
        returnable += "\nInstructions: \n" + this.instructions;

        return returnable;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public String getCategoriesAsString() {
        String returnable = "Categories: ";
        for (Category c: categories) {
            returnable += c + ", ";
        }
        returnable = returnable.substring(0, returnable.length() - 2) + "\n";
        return returnable;
    }

    /**
     *
     * @return
     */
    public String getIngredientsAsString() {
        String returnable = "Ingredients: ";
        for (Ingredient i: ingredients) {
            returnable += "\n" + i;
        }
        return returnable + "\n";
    }
    
    
    
}
