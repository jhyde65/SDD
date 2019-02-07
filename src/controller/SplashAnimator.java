package controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;

public class SplashAnimator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;
    private AudioPlayer theme;
    private AudioPlayer accept;

    // Animation for the Splash Screen.
    @Override
    public void run() {

        Main.game.splashLayout();
        theme = new AudioPlayer("src/view/resources/Audio/theme.mp3", 0.2);
        accept = new AudioPlayer("src/view/resources/Audio/start.wav", 1.5);

        while (running) {
            long startTime = System.currentTimeMillis();

            Main.splashPanel.gameRender();
            Main.splashPanel.printScreen();

            // Checks to see if song is still playing.
            if (!theme.poll())
            {
                theme.play();
            }

            // Checks for enter key pressed to
            // start game. If the enter key is
            // pressed the theme song is stopped
            // and the accept tone is played.
            // Then splash screen animation thread
            // is killed.
            if(!GameState.isPaused())
            {
                theme.stop();
                accept.play();
                break;
            }

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}