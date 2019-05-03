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

/**
 *
 * @author Elina
 */

public class CategoryDao {
   

    public void create(Category category) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Category (category) VALUES (?)");
        stmt.setString(1, category.getCategory());
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
        
        int id = getGeneratedId(category.getCategory());
        category.setId(id);
    }


    public Category read(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        
        Category category = null;
        
        if (rs.next()) {
            category = new Category(key, rs.getString("category"));
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return category;
    }

    public List<Category> list() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category");
        ResultSet rs = stmt.executeQuery();
        
        List<Category> categories = new ArrayList<>();
        Category category = null;
        
        while (rs.next()) {
            category = new Category(rs.getInt("id"), rs.getString("category"));
            categories.add(category);
        }
        stmt.close();
        rs.close();
        conn.close();
        
        return categories;
    }

    public int getGeneratedId(String category) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./recipedatabase", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category WHERE category = ?");
        stmt.setString(1, category);
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