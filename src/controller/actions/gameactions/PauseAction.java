/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.actions.gameactions;

import controller.AudioPlayer;
import controller.GameState;
import controller.Main;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Creates a new pause screen object.
 * @author Drew
 */
public class PauseAction extends AbstractAction
{
    private AudioPlayer player;
    @Override
    public void actionPerformed(ActionEvent e)
    {
        player = new AudioPlayer("src/view/resources/Audio/pausesound.wav", 3);
        player.play();
        GameState.setPaused();
    }

}

