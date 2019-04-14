package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author david
 */
public class SpaceAction extends AbstractAction
{

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //TODO: check if there is an instance already running
        //      don't add shield if yes
        Main.gameData.addShooterShield();
    }
       
}
