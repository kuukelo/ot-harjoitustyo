
package recipes.domain;

public class Ingredient {
    
    private String ingredient;
    
    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public String getIngredient() {
        return this.ingredient;
    }
    public void setIngredient(String newIngredient) {
        this.ingredient = newIngredient;
    }
    public String toString() {
        return this.ingredient;
    }
}
