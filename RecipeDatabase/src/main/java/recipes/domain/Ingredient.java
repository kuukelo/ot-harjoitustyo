
package recipes.domain;

import java.util.Objects;

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
        final Ingredient other = (Ingredient) obj;
        if (!Objects.equals(this.ingredient, other.ingredient)) {
            return false;
        }
        return true;
    }
    
}
