package controller.actions.gameactions;

import controller.AudioPlayer;
import controller.GameState;
import controller.Main;
import model.Shooter;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpArrowAction extends AbstractAction
{
    private AudioPlayer player;
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!GameState.isPaused())
        {
            Shooter sh = Main.gameData.shooter;
            sh.translate(0, -10);
            sh.setAnimation(sh.animation, sh.moveUp);
            sh.animation.update();
        }
        else
        {
            player = new AudioPlayer("src/view/resources/Audio/menuclick.wav", 3);
            player.play();
            Main.gameData.pauseScreen.toggleSelection();
        }
    }
}
