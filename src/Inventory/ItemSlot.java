/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.Color;

import java.awt.Graphics2D;

/**
 *
 * @author matthew
 */
public class ItemSlot {
    
    public static final int SLOTSIZE = 100;
    private int x;
    private int y;
    
    private ItemStack itemStack;
    
    public ItemSlot(int x, int y, ItemStack itemstack){
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
    }
    
    public void update(){
        
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y,SLOTSIZE,SLOTSIZE);
        
        g.setColor(Color.BLACK);
        g.drawRect(x,y,SLOTSIZE,SLOTSIZE);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    
    public boolean addItem(Item item, int amount){
        if(itemStack != null){
            if(item.getItemType() == itemStack.getItem().getItemType())
            {
                this.itemStack.setAmount(this.itemStack.getAmount() + amount);
                return true;  
            }
            else
            {
                return false;
            }
        } 
        else{
            
            this.itemStack = new ItemStack(item,amount);
            return true;
        }
    }
}