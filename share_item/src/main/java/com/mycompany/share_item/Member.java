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
public class Member {
    private String name;
    private String address;
    private String email;
    private final int donatedQty;
    private  ArrayList<Item> borrowing = new ArrayList<>();
    private  ArrayList<Item> donatedItems = new ArrayList<>();
    
    public Member (String name,String address,String email, int donatedQty){
        
        this.name = name;
        this.address = address;
        this.donatedQty = 0;
    }
    
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setName(String name){
        this.name = name; 
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public int getDonatedQty (){
        return donatedQty;
    }
    
    public ArrayList<Item> getDonatedItems(){
        return donatedItems;
    }
    public int borrowingQty(){
        return borrowing.size();
    }
    public ArrayList<Item> getLoanItems(){
        return borrowing;
    }
    
    public void lend(Item item){
        // lend max of 5 items 
    }
    
    public void addDonation(Item item){
        // tbc
    }
    
    public void returnItem(Item item){
        //tbc
    }
    
    @Override
    public String toString(){
        return null;
    }
    
    
}
