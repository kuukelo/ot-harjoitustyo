/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elina
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println(list().get(0));
    }
    public static List<String> list() {
        List<String> strings = new ArrayList<>();
        strings.add("nn");
        strings.add("mm");
        
        return strings; 
    }
    
}
