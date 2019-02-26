package controller.actions.gameactions;

import controller.AudioPlayer;
import controller.GameState;
import controller.Main;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Carries out an action based off of the pause screen
 * selection.
 * @author Drew
 */
public class PauseEnterAction extends AbstractAction
{
    private AudioPlayer player;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the game is paused the enter action will
        // carry out an action based off of the selection
        // property from the pauseScreen object.
        if(GameState.isPaused())
        {
            // resume is selected.
            if (Main.gameData.pauseScreen.getSelection())
            {
                player = new AudioPlayer("src/view/resources/Audio/pausesound.wav", 3);
                player.play();
                GameState.setPaused();
            }
            // Quit is selected.
            else
            {
                System.exit(-1);
            }
        }
    }

}