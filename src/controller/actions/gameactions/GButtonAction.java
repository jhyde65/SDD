package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author david
 */
public class GButtonAction extends AbstractAction
{

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Main.gameData.addGoblinEnemy();
    }
       
}
