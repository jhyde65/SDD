/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import controller.GameState;
import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Shortcut to GolemBoss, removes all enemies adds Golem, used for dev of GolemBoss
 * @author j
 */
public class inventoryAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        GameState.setInventory();
        if(GameState.isInventory()){
        Main.gameData.addInventory();
        }
        else
        {
            Main.gameData.removeInventory();
        }
    }
    
}

