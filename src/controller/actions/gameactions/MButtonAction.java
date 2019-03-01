package controller.actions.gameactions;

import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author david
 * Generates an instance of the MonsterEnemy upon M hot key press.
 */
public class MButtonAction extends AbstractAction
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Main.gameData.enemyFigures.clear();
        Main.gameData.addMonsterEnemy();
    }
    
}
