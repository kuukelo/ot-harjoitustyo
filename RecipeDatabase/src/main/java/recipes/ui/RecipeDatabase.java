
package recipes.ui;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import recipes.dao.IngredientDao;
import recipes.dao.IngredientRecipeDao;
import recipes.dao.RecipeDao;
import recipes.domain.Category;
import recipes.domain.Ingredient;
import recipes.domain.IngredientRecipe;
import recipes.domain.Recipe;
import recipes.editor.DatabaseEditor;



public class RecipeDatabase extends Application {
    private final DatabaseEditor dbEditor;
    private Scene view;
    private BorderPane layout;
    private Button add;
    private Button seeAll;
    private Button find;
    private Button edit;
    private Button getFull;
    private HBox buttons;
    private HBox buttons2;
    private Button findTime;
    private Button findIngredient;
    private Button findCategory;
    private Button addRecipe;
    private Button time;
    private Button ingredient;
    private Button category;
    private Button getRecipe;
    
    public RecipeDatabase() throws IOException {
        this.dbEditor = new DatabaseEditor();
        this.layout = new BorderPane();
        this.layout.setPrefSize(500, 500);
        
        this.edit = new Button("Edit recipe");
        this.add = new Button("Add recipe");
        this.seeAll = new Button("See all recipes");
        this.find = new Button("Find a recipe");
        this.getFull = new Button("Get full recipe");
        this.findTime = new Button("Find");
        this.findIngredient = new Button("Find");
        this.findCategory = new Button("Find");
        this.time = new Button("Time");
        this.ingredient = new Button("Ingredients");
        this.category = new Button("Categories");
        this.addRecipe = new Button("Add");
        this.getRecipe = new Button("Get full recipe");
        
        this.buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(add, seeAll, find, edit, getFull);
        
        this.buttons2 = new HBox();
        buttons2.setSpacing(10);
        buttons2.getChildren().addAll(time, ingredient, category);
        
        edit.setOnAction((event) -> {
            layout.setCenter(getContentEdit());
        });
        add.setOnAction((event) -> {
            layout.setCenter(getContentAdd());
        });
        seeAll.setOnAction((event) -> {
            try {
                layout.setCenter(getContentSeeAll());
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        find.setOnAction((event) -> {
            layout.setCenter(getContentFind());
        });
        getFull.setOnAction((event) -> {
            layout.setCenter(getContentGetFull());
        });
        time.setOnAction((event) -> {
            layout.setCenter(getTimeContent());
        });
        ingredient.setOnAction((event) -> {
            layout.setCenter(getIngredientContent());
        });
        category.setOnAction((event) -> {
            layout.setCenter(getCategoryContent());
        });
    }

    public void start(Stage window) throws SQLException {
        
//        List<Ingredient> is = new ArrayList<>();
//            String i = "plums";
//            is.add(new Ingredient(i));
//            i = "soda";
//            is.add(new Ingredient(i));
//            i = "lemonjuice";
//            is.add(new Ingredient(i));
//            System.out.println(is);
//            
//            List<Category> cs = new ArrayList<>();
//            String c = "dinner";
//            cs.add(new Category(c));
//            c = "drink";
//            cs.add(new Category(c));
//            
//            Recipe r = new Recipe("Plumdrink", 15, is, cs, "1. Pour all in a glass.");
//            userinterface.addRecipeToDatabase(r);
//            System.out.println("Jou");
        window.setTitle("Recipe database");
        window.show(); 
        
        BorderPane contentStart = new BorderPane();
        Label startText = new Label("Welcome to recipedatabase, what would you like to do?");
        contentStart.setCenter(startText);

        layout.setCenter(contentStart);
        layout.setBottom(buttons);
        
        view = new Scene(layout); 
        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) throws SQLException {
//        DatabaseEditor.setUpDatabase();
//            IngredientDao iDao = new IngredientDao();
//            IngredientRecipeDao irDao = new IngredientRecipeDao();
//            for (Ingredient i: iDao.list()) {
//                System.out.println(i.getId() + " " + i);
//            }
//            System.out.println("Tässä on lista ir: " + irDao.list());
//            RecipeDao rDao = new RecipeDao();
//            for (Recipe r: rDao.list()) {
//                System.out.println(r + "\n" + r.getInstructions());
//                System.out.println(irDao.listByRecipe(r.getId()));
//            }
            
//            rDao.delete(5);
            
            


        launch(RecipeDatabase.class);
    }




    private Node getContentAdd() {
        GridPane contentAdd = new GridPane();
        Label label = new Label("Here you can add a recipe\n");
        Label nameLabel = new Label("What is the recipe name?");
        Label time = new Label("How long does it take to make?");
        Label hoursLabel = new Label("Hours");
        Label minutesLabel = new Label("Minutes");
        Label ingredientsLabel = new Label("What ingredient does the recipe need? Write all to their own row.");
        Label categoriesLabel = new Label("What categories does the recipe belong to? Write all to their own row.");
        Label instructionsLabel = new Label("Write instructions.");
        Label editDone = new Label("\n");
        TextField name = new TextField();
        TextField hours = new TextField();
        TextField minutes = new TextField();
        TextArea ingredients = new TextArea();
        TextArea categories = new TextArea();
        TextArea instructionsText = new TextArea();
        
        contentAdd.addColumn(0, label, nameLabel, name, time, hoursLabel, hours, minutesLabel, minutes, ingredientsLabel, ingredients, categoriesLabel, categories, instructionsLabel, instructionsText, addRecipe, editDone);
        addRecipe.setOnAction((event) -> {
            
            try {
                if (dbEditor.nameIsUnique(name.getText())) {
                    dbEditor.addNewRecipe(name.getText(), hours.getText(), minutes.getText(), ingredients.getText(), categories.getText(), instructionsText.getText());
                    editDone.setText("Recipe " + name.getText().toLowerCase() + " has been added");
                } else {
                    editDone.setText("There already is a recipe named " + name.getText().toLowerCase() + ".\nChange the name and try again, or edit the existing recipe.");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        return contentAdd;
    }

    private Node getContentSeeAll() throws SQLException {
        GridPane contentSeeAll = new GridPane();
        Label seeAllText = new Label(dbEditor.listAll());
        contentSeeAll.add(seeAllText, 1, 1);
        return contentSeeAll;
    }

    private Node getContentEdit() {
        GridPane content = new GridPane();
        Label label = new Label("Here you can edit a recipe.");
        Label nameLabel = new Label("What is the name of the recipe you want to edit? ");
        TextField name = new TextField();
        Label nameOrTime = new Label("Do you want to edit the name or the time?");
        
        Button nameEdit = new Button("Name");
        Button timeEdit = new Button("Time");
        HBox buttons3 = new HBox();
        buttons3.setSpacing(10);
        buttons3.getChildren().addAll(nameEdit, timeEdit);
        
        Button editRecipe = new Button("Edit");
        content.addColumn(0, label, nameLabel, nameOrTime, buttons3);
        content.add(name, 1, 1);
        
        nameEdit.setOnAction((event) -> {
            layout.setCenter(getEditNameContent(name.getText()));
        });
        
        timeEdit.setOnAction((event) -> {
            layout.setCenter(getEditTimeContent(name.getText()));;
        });
       
        return content;
    }

    private Node getContentFind() {
        GridPane contentFind = new GridPane();
        Label text = new Label("How do you want to filter the recipes? ");
        contentFind.add(text, 0, 0);
        contentFind.add(buttons2, 0, 1);
        
        return contentFind;
        
    }

    private GridPane getTimeContent() {
        BorderPane top = new BorderPane();
        GridPane contentTime = new GridPane();
        Label text = new Label("What is the max time in minutes you want? ");
        TextField time = new TextField();
        top.setTop(text);
        top.setCenter(time);
        top.setBottom(findTime);
        Label label = new Label("");
        
        contentTime.add(getContentFind(), 0, 0);
        contentTime.add(top, 0, 1);
        contentTime.add(label, 0, 2);
        
        findTime.setOnAction((event) -> {
            List<Recipe> recipes = null;
            try {
                recipes = dbEditor.findRecipesBasedOnTime(Integer.valueOf(time.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            contentTime.getChildren().remove(label);
            label.setText(getSuitables(recipes));
            contentTime.add(label, 0, 3);
        });
        return contentTime;
    }

    private GridPane getIngredientContent() {
        BorderPane top = new BorderPane();
        GridPane content = new GridPane();
        Label text = new Label("What ingredient do you want the recipe to have? Put every ingredient on different row.");
        TextArea ingredients = new TextArea();
        top.setTop(text);
        top.setCenter(ingredients);
        top.setBottom(findIngredient);
        Label label = new Label("");
        
        content.addColumn(0, getContentFind(), top, label);
        
        findIngredient.setOnAction((event) -> {            
            List<Ingredient> ingredientList = getIngredientList(ingredients.getText());            
            List<Recipe> recipes = null;
            try {
                recipes = dbEditor.findRecipesBasedOnIngredients(ingredientList);
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            content.getChildren().remove(label);
            label.setText(getSuitables(recipes));
            content.add(label, 0, 3);
        });
        return content;
    }
    private Node getCategoryContent() {
        BorderPane top = new BorderPane();
        GridPane content = new GridPane();
        Label text = new Label("What categories do you want the recipe to have? Put every ingredient on different row.");
        TextArea categories = new TextArea();
        top.setTop(text);
        top.setCenter(categories);
        top.setBottom(findCategory);
        Label label = new Label("");
        content.addColumn(0, getContentFind(), top, label);
       
        findCategory.setOnAction((event) -> {
            List<Category> categoryList = getCategoryList(categories.getText());            
            List<Recipe> recipes = null;
            try {
                recipes = dbEditor.findRecipesBasedOnCategory(categoryList);
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            content.getChildren().remove(label);
            label.setText(getSuitables(recipes));
            content.add(label, 0, 3);
        });
        return content;
    }

    private Node getContentGetFull() {
        GridPane content = new GridPane();
        Label label = new Label("Here you can get full recipe.");
        Label nameLabel = new Label("What is the name of the recipe you want? ");
        TextField name = new TextField();
        Button getFull = new Button("Get the recipe");
        Label recipeLabel = new Label("");
        content.addColumn(0, label, nameLabel, getFull, recipeLabel);
        content.add(name, 1, 1);
        
        
        getFull.setOnAction((event) -> {
            Recipe recipe = null;            
            try {
                recipe = dbEditor.getRecipe(name.getText());
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            recipeLabel.setText(recipe.getFullRecipe());
        });
        
        return content;
    }

    private String getSuitables(List<Recipe> recipes) {
        if (recipes.isEmpty()) {
            return "\nNo suitable recipies found.";
        }
        String suitable = "\nSuitable recipes:\n\n";
        for (Recipe r: recipes) {
            suitable += r.getName() + "\n";
        }
        return suitable;
    }

    private List<Ingredient> getIngredientList(String text) {
        String s[] = text.split("\\r?\\n");
        List<String> strings = new ArrayList<>(Arrays.asList(s));
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String i: strings) {
            ingredientList.add(new Ingredient(i));
        }
        return ingredientList;
    }

    private List<Category> getCategoryList(String text) {
        String s[] = text.split("\\r?\\n");
        List<String> strings = new ArrayList<>(Arrays.asList(s));
        List<Category> categoryList = new ArrayList<>();
        for (String i: strings) {
            categoryList.add(new Category(i));
        }
        return categoryList;
    }

    private Node getEditTimeContent(String recipeName) {
        GridPane content = new GridPane();
        Label label = new Label("Editing recipe " + recipeName.toLowerCase());
        Label newTimeLabel = new Label("What is the new time in minutes?");
        TextField newTime = new TextField();
        Button editRecipe = new Button("Edit");
        Label edited = new Label("Recipe " + recipeName.toLowerCase() + " has been edited.");
        
        content.addColumn(0, label, newTimeLabel, editRecipe);
        content.add(newTime, 1, 1);
        
        editRecipe.setOnAction((event) -> {
            
            try {
                dbEditor.editTime(recipeName, Integer.valueOf(newTime.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            content.add(edited, 0, 4);
        });
        return content;
    }

    private Node getEditNameContent(String recipeName) {
        GridPane content = new GridPane();
        Label label = new Label("Editing recipe " + recipeName.toLowerCase());
        Label newNameLabel = new Label("What is the new name?");
        TextField newName = new TextField();
        Button editRecipe = new Button("Edit");
        Label edited = new Label("Recipe " + recipeName.toLowerCase() + " has been edited.");
        
        content.addColumn(0, label, newNameLabel, editRecipe);
        content.add(newName, 1, 1);
        
        editRecipe.setOnAction((event) -> {
        
            try {
                dbEditor.editName(recipeName, newName.getText());
            } catch (SQLException ex) {
                Logger.getLogger(RecipeDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
       
            content.add(edited, 0, 4);
        });
        return content;
    }
    
}
