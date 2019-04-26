
package recipes.ui;


import java.io.File;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import recipes.userinterface.UserInterface;


public class RecipeDatabase {

    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to recipe database");
        UserInterface userinterface = new UserInterface(new File("recipes.txt"));
//        File file = this.className.class.getResourceAsStream("recipes.txt");
//        UserInterface userinterface = new UserInterface(file);
        alustaTietokanta();
        Scanner reader = new Scanner(System.in);
        userinterface.start(reader);
        
    }
    
    private static void alustaTietokanta() {

//        try (Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "")) {
//            conn.prepareStatement("DROP TABLE Recipe IF EXISTS;").executeUpdate();
//            conn.prepareStatement("CREATE TABLE Recipe ("
//                    + "id SERIAL PRIMARY KEY, "
//                    + "name VARCHAR(30), "
//                    + "time INTEGER);").executeUpdate();
//            conn.prepareStatement("DROP TABLE Ingredient IF EXISTS;").executeUpdate();
//            conn.prepareStatement("CREATE TABLE Ingredient ("
//                    + "id SERIAL PRIMARY KEY, "
//                    + "ingredient VARCHAR(30));").executeUpdate();
//            conn.prepareStatement("DROP TABLE Category IF EXISTS;").executeUpdate();
//            conn.prepareStatement("CREATE TABLE Category ("
//                    + "id SERIAL PRIMARY KEY, "
//                    + "category VARCHAR(30));").executeUpdate();
//            conn.prepareStatement("DROP TABLE CategoryRecipe IF EXISTS;").executeUpdate();
//            conn.prepareStatement("CREATE TABLE CategoryRecipe ("
//                    + "id SERIAL PRIMARY KEY, "
//                    + "recipe_id INTEGER, "
//                    + "category_id INTEGER, "
//                    + "FOREIGN KEY (recipe_id) REFERENCES Recipe(id), "
//                    + "FOREIGN KEY (category_id) REFERENCES Category(id));").executeUpdate();
//            conn.prepareStatement("DROP TABLE IngredientRecipe IF EXISTS;").executeUpdate();
//            conn.prepareStatement("CREATE TABLE IngredientRecipe ("
//                    + "id SERIAL PRIMARY KEY, "
//                    + "recipe_id INTEGER, "
//                    + "ingredient_id INTEGER, "
//                    + "FOREIGN KEY (recipe_id) REFERENCES Recipe(id), "
//                    + "FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));").executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    

    
}
