/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.share_item;

import java.util.*;

/**
 *
 * @author ayadm
 */
public class Collection {
    private  ArrayList<Item> borrowing = new ArrayList<>(); 
    
    
    public Collection(){
    }
    
    public void addBook(String title, String author, Member donator, 
            String language, String isbn){
        
    }
    
    public void addDVD(String title, String author, Member donator, 
            String language, String isbn ){
        
    }
    
    public ArrayList<Item> searchItems(String searchItem){
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("hello","english", new Member("test", "test", "test", 0)));
        return items;
    }
    
    public Item getItem (String title){
        return null;
    }
    
    public void removeItem(Item item){
        
    }
}
