/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.CopyOnWriteArrayList;
import model.GameFigure;

public class Inventory {
    public boolean isOpen = false;
    private int x,y;
    private int width,height;
    
    private int numCols = 6;
    private int numRows = 4;
    
    private CopyOnWriteArrayList<ItemSlot> itemSlots;

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
    }
    
    public void update(){
        //for(ItemSlot is : itemSlots){
        //    is.update();
        //}
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x-17,y-17,width+30,height+30);
        g.setColor(Color.BLACK);
        g.drawRect(x-17,y-17,width+30,height+30);
        for(ItemSlot is : itemSlots){
            is.render(g);
        }
    }

}
