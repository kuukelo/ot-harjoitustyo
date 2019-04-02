
package com.mycompany.recipedatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RecipeDatabase {

    public static void main(String[] args) {;
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to recipe database");// TODO code application logic here
        UserInterface userinterface = new UserInterface();
        userinterface.start(reader);
    }

    

    
}
