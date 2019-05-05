/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.domain;

/**
 *
 * @author Elina
 */
public class IngredientRecipe {
    private Ingredient ingredient;
    private Recipe recipe;
    
    /**
     *
     * @param i
     * @param r
     */
    public IngredientRecipe(Ingredient i, Recipe r) {
        this.ingredient = i;
        this.recipe = r;
    }

    /**
     *
     * @return
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     *
     * @return
     */
    public Ingredient getIngredient() {
        return ingredient;
    }
    public String toString() {
        return ingredient.getIngredient() + ingredient.getId() + recipe.getName() + recipe.getId();
    }
    
}
