
package com.mycompany.recipedatabase;

public class Category {
    
    private String category;
    
    public Category(String category) {
        this.category = category;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String newCategory) {
        this.category = newCategory;
    }
    public String toString() {
        return this.category;
    }
}
