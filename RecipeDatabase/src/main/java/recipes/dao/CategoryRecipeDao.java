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
import recipes.domain.Category;
import recipes.domain.CategoryRecipe;

/**
 *
 * @author Elina
 */

public class CategoryRecipeDao {
    
    public void create(CategoryRecipe cr) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO CategoryRecipe (category_id, recipe_id) VALUES (?, ?)");
        stmt.setInt(1, cr.getCategory().getId());
        stmt.setInt(2, cr.getRecipe().getId());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }

    public CategoryRecipe read(Integer key) throws SQLException {
        RecipeDao rDao = new RecipeDao();
        CategoryDao cDao = new CategoryDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CategoryRecipe WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        
        CategoryRecipe cr = null;
        
        if (rs.next()) {
            cr = new CategoryRecipe(
                    cDao.read(rs.getInt("category_id")),
                    rDao.read(rs.getInt("recipe_id"))); 
        }
        stmt.close();
        rs.close();
        conn.close();

        return cr;
    }

    public List<CategoryRecipe> list() throws SQLException {
        RecipeDao rDao = new RecipeDao();
        CategoryDao cDao = new CategoryDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM IngredientRecipe");
        ResultSet rs = stmt.executeQuery();  
        
        List<CategoryRecipe> crList = new ArrayList<>();
        CategoryRecipe cr = null;
        
        while (rs.next()) {
            cr = new CategoryRecipe(cDao.read(rs.getInt("category_id")),
            rDao.read(rs.getInt("recipe_id")));
            crList.add(cr);
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return crList;    
    }
    
    public List<Category> listByRecipe(Integer key) throws SQLException {
        CategoryDao cDao = new CategoryDao();
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CategoryRecipe WHERE recipe_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();

        List<Category> cList = new ArrayList<>();
        Category c = null;
        
        while (rs.next()) {
            c = new Category(rs.getInt("category_id"),
            cDao.read(rs.getInt("category_id")).getCategory());
            cList.add(c);
        }
        stmt.close();
        rs.close();
        conn.close();

        return cList;
    }
}