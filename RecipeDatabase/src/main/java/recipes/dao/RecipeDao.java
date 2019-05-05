/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipes.dao;


import recipes.domain.Recipe;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;


/**
 * This dao is designated for the Recipe table in database.
 * @author Elina
 */

public class RecipeDao  {

    /**
     * Creates new Recipe line to database
     * @param recipe
     * @throws SQLException
     */
    public void create(Recipe recipe) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Recipe (name, time, instructions) VALUES (?, ?, ?)");
        stmt.setString(1, recipe.getName());
        stmt.setInt(2, recipe.getTime());
        stmt.setString(3, recipe.getInstructions());
        stmt.executeUpdate();        
        
        stmt.close();
        conn.close();
        
        int id = getGeneratedId(recipe.getName());
        recipe.setId(id);
    }

    /**
     * Gets a line from database based on id and returns the info
     * @param key
     * @return
     * @throws SQLException
     */
    public Recipe read(Integer key) throws SQLException {
        IngredientRecipeDao irDao = new IngredientRecipeDao();
        CategoryRecipeDao crDao = new CategoryRecipeDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Recipe WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        
        Recipe recipe = null;
        
        if (rs.next()) {
            recipe = new Recipe(key, rs.getString("name"), rs.getInt("time"),
                    irDao.listByRecipe(rs.getInt("id")), crDao.listByRecipe(rs.getInt("id")),
                    rs.getString("instructions"));
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return recipe;
    }

    /**
     * Updates a line in database based on an id
     * @param recipe
     * @return
     * @throws SQLException
     */
    public Recipe update(Recipe recipe) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("UPDATE Recipe SET name = ?, time = ?, instructions = ? WHERE id = ?");
        stmt.setString(1, recipe.getName());
        stmt.setInt(2, recipe.getTime());
        stmt.setString(3, recipe.getInstructions());
        stmt.setInt(4, recipe.getId());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
        
        return recipe;
    }

    /**
     * Deletes a recipe from database based on an id.
     * @param key
     * @throws SQLException
     */
    public void delete(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("DELETE * FROM Recipe WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
    }

    /**
     * Creates a list of the lines in database and returns it. 
     * @return
     * @throws SQLException
     */
    public List<Recipe> list() throws SQLException {
        IngredientRecipeDao irDao = new IngredientRecipeDao();
        CategoryRecipeDao crDao = new CategoryRecipeDao();
        
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Recipe");
        ResultSet rs = stmt.executeQuery();
        
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = null;
        
        while (rs.next()) {
            recipe = new Recipe(rs.getInt("id"), rs.getString("name"), rs.getInt("time"),
                    irDao.listByRecipe(rs.getInt("id")), crDao.listByRecipe(rs.getInt("id")),
                    rs.getString("instructions"));
            recipes.add(recipe);
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return recipes;
    }
    
    /**
     * Returns the just created id
     * @param name
     * @return
     * @throws SQLException
     */
    public Integer getGeneratedId(String name) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Recipe WHERE name = ?");
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
