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
import recipes.domain.IngredientRecipe;

/**
 *
 * @author Elina
 */
@Component
public class IngredientRecipeDao {
    

    public void create(IngredientRecipe ir) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO IngredientRecipe (ingredient_id, recipe_id) VALUES (?, ?)");
        stmt.setInt(1, ir.getIngredient().getId());
        stmt.setInt(2, ir.getRecipe().getId());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }

    public IngredientRecipe read(Integer key) throws SQLException {
        RecipeDao rDao = new RecipeDao();
        IngredientDao iDao = new IngredientDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM IngredientRecipe WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        
        IngredientRecipe ir = null;
        
        if (rs.next()) {
            ir = new IngredientRecipe(
                iDao.read(rs.getInt("ingredient_id")),
                rDao.read(rs.getInt("recipe_id"))); 
        }
        stmt.close();
        rs.close();
        conn.close();

        return ir;
    }

    public List<IngredientRecipe> list() throws SQLException {
        RecipeDao rDao = new RecipeDao();
        IngredientDao iDao = new IngredientDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM IngredientRecipe");
        ResultSet rs = stmt.executeQuery();  
        
        List<IngredientRecipe> irList = new ArrayList<>();
        IngredientRecipe ir = null;
        
        while (rs.next()) {
            ir = new IngredientRecipe(iDao.read(rs.getInt("ingredient_id")),
            rDao.read(rs.getInt("recipe_id")));
            irList.add(ir);
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return irList;
        
    }
    
    public List<Ingredient> listByRecipe(Integer key) throws SQLException {
        IngredientDao iDao = new IngredientDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM IngredientRecipe WHERE recipe_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        
        List<Ingredient> iList = new ArrayList<>();
        Ingredient i = null;
        
        while (rs.next()) {
            i = new Ingredient(rs.getInt("ingredient_id"),
            iDao.read(rs.getInt("ingredient_id")).getIngredient());
            iList.add(i);
        }
        stmt.close();
        rs.close();
        conn.close();

        return iList;
    }
    
}
