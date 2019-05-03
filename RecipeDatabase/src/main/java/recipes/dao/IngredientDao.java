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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.Recipe;

/**
 *
 * @author Elina
 */
@Component
public class IngredientDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
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

    public Ingredient update(Ingredient ingredient) throws SQLException {
        jdbcTemplate.update("UPDATE Ingredient SET ingredient = ? WHERE id = ?",
            ingredient.getIngredient(), ingredient.getId());        
        return ingredient;
    }

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
