/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import recipes.domain.Ingredient;

/**
 * This dao is designated for the Ingredient table in database.
 * @author Elina
 */
@Component
public class IngredientDao {

    
    /**
     * Creates new Ingredient line to database
     * @param ingredient
     * @throws SQLException
     */
    public void create(Ingredient ingredient) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Ingredient (ingredient) VALUES (?)");
        stmt.setString(1, ingredient.getIngredient());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
        
        int id = getGeneratedId(ingredient.getIngredient());
        ingredient.setId(id);
    }

    /**
     * Gets a line from database based on id and returns the info
     * @param key
     * @return
     * @throws SQLException
     */
    public Ingredient read(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ingredient WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        
        Ingredient ingredient = null;
        
        if (rs.next()) {
            ingredient = new Ingredient(key,
                    rs.getString("ingredient"));
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return ingredient;
    }

    /**
     * Creates a list of the lines in database and returns it. 
     * @return
     * @throws SQLException
     */
    public List<Ingredient> list() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ingredient");
        ResultSet rs = stmt.executeQuery();
        
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = null;
        
        while (rs.next()) {
            ingredient = new Ingredient(rs.getInt("id"),
                    rs.getString("ingredient"));
            ingredients.add(ingredient);
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return ingredients;
    }

    /**
     * Returns the just created id
     * @param name
     * @return
     * @throws SQLException
     */
    public Integer getGeneratedId(String name) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Ingredient WHERE ingredient = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        int id = 0;
        
        if (rs.next()) {
            id = rs.getInt("id");
        }
        
        stmt.close();
        rs.close();
        conn.close();
        
        return id;
        
    }
    
}
