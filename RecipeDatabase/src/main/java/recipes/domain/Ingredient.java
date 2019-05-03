
package recipes.domain;

import java.util.Objects;

public class Ingredient {
    private Integer id;
    private String ingredient;
    
    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public Ingredient(Integer id, String ingredient) {
        this.id = id;
        this.ingredient = ingredient;
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
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
