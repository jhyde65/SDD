/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Brandy
 */
public class KButtonAction extends AbstractAction{
    public void actionPerformed(ActionEvent e) 
    {
        Main.gameData.enemyFigures.clear();
        Main.gameData.addSpikeyEnemy();
    }
}
