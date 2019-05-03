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
public class CategoryRecipe {
    private Category category;
    private Recipe recipe;
    
    public CategoryRecipe(Category c, Recipe r) {
        this.category = c;
        this.recipe = r;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public Category getCategory() {
        return category;
    }
    public String toString() {
        return category.getCategory() + category.getId() + recipe.getName() + recipe.getId();
    }
}
