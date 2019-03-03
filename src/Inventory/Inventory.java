/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import controller.Animator;
import controller.MouseController;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.CopyOnWriteArrayList;
import model.GameData;
import model.GameFigure;

public class Inventory {

    public boolean isOpen = true;
    public boolean hasBeenPressed = true;
    private int x,y;
    private int a,b;
    private int width,height;
    
    private int numCols = 6;
    private int numRows = 4;
    
    public static CopyOnWriteArrayList<ItemSlot> itemSlots;
    
    private ItemSlot currSelectedSlot;

    public Inventory(int x, int y)
    {
        this.x = x;
        this.y = y;
        itemSlots = new CopyOnWriteArrayList<ItemSlot>();
        
        for(int i = 0; i < numCols; i++){
            for(int j = 0; j < numRows; j++){
                if(j == (numRows - 1)){
                    y += 35;
                }
                
                itemSlots.add(new ItemSlot(x+(i*(ItemSlot.SLOTSIZE + 10)),y+(j*(ItemSlot.SLOTSIZE + 10)),null));
                
                if(j == (numRows - 1)){
                    y-=35;
                }
                    
            }
        }
        width = numCols *(ItemSlot.SLOTSIZE + 10);
        height = numRows * (ItemSlot.SLOTSIZE + 10) + 35;
        for(int i = 0; i<Animator.counter;i++){
            itemSlots.get(i).addItem(new Potion() ,1);
        }
    }
    
    public void mouseClicked(MouseEvent me) { 
          a = me.getX();
          b = me.getY();
        }
    
    public void update(){
        
        if(isOpen){
            Rectangle temp = new Rectangle(a,b,1,1);
            for(ItemSlot is : itemSlots){
                is.update();
                
                Rectangle temp2;
                temp2 = new Rectangle(is.getX(), is.getY(), ItemSlot.SLOTSIZE, ItemSlot.SLOTSIZE);
                if(!hasBeenPressed){    
                if(temp2.contains(temp)){
                    if(is.getItemStack() != null){
                        if(currSelectedSlot == null){
                            currSelectedSlot = is;
                            
                            is.setItem(null);  
                        }
                    }else{
                        if(currSelectedSlot != null){
                            if(is.addItem(currSelectedSlot.getItemStack().getItem(), currSelectedSlot.getItemStack().getAmount())){
                                
                            }else{
                                is.setItem(currSelectedSlot.getItemStack());
                            }
                            
                            currSelectedSlot = null;
                        }
                    }
                }
            }
            }
            if(hasBeenPressed){
                hasBeenPressed = false;
            }
        }
    }
    
    public void render(Graphics2D g){
        if(isOpen){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x-17,y-17,width+30,height+30);
        g.setColor(Color.BLACK);
        g.drawRect(x-17,y-17,width+30,height+30);
        for(ItemSlot is : itemSlots){
            is.render(g);
        }
        
        if(currSelectedSlot != null){
            g.drawImage(currSelectedSlot.getItemStack().getItem().texture, a,b,null);
        }
    }
    }
    public static void addItem(int i) {
        if(i==1)
        Inventory.itemSlots.get(0).addItem(new Potion() ,1);
    }
}
