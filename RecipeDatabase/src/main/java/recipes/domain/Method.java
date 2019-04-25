

package recipes.domain;


public class Method {
    
    private String method;
    
    public Method(String method) {
        this.method = method;
    }
    public String getCategory() {
        return this.method;
    }
    public void setCategory(String newCategory) {
        this.method = newCategory;
    }
    public String toString() {
        return this.method;
    }
}
