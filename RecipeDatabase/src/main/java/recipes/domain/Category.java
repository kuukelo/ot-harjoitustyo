
package recipes.domain;

import java.util.Objects;

/**
 *
 * @author Elina
 */
public class Category {
    private Integer id;
    private String category;
    
    /**
     *
     * @param category
     */
    public Category(String category) {
        this.category = category;
    }

    /**
     *
     * @param id
     * @param category
     */
    public Category(Integer id, String category) {
        this.id = id;
        this.category = category;
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
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return this.category;
    }

    /**
     *
     * @param newCategory
     */
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
