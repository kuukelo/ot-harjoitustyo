
package recipes.domain;

import java.util.Objects;

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
        final Category other = (Category) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
    
}
