package controller;

import java.util.concurrent.TimeUnit;
import model.GameFigure;
import model.GameFigureState;
import model.Missile;

public class SplashAnimator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;

    @Override
    public void run() {

        Main.game.splashLayout();

        while (running) {
            long startTime = System.currentTimeMillis();

            Main.splashPanel.gameRender();
            Main.splashPanel.printScreen();

            if(!GameState.isPaused())
            {
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