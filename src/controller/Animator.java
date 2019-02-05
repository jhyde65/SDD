package controller;

import java.util.concurrent.TimeUnit;
import model.GameFigure;
import model.GameFigureState;
import model.Missile;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 40;

    @Override
    public void run() {

        while (running) {
            long startTime = System.currentTimeMillis();

            // If the game is in a paused state
            // we don't call update methods.
            if (!GameState.isPaused()) {
                processCollisions();

                Main.gameData.update();
                Main.gamePanel.gameRender();
                Main.gamePanel.printScreen();
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
        System.exit(0);
    }
    
    private void processCollisions() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
        
        for(GameFigure friend : Main.gameData.friendFigures)
        {
            for(GameFigure enemy : Main.gameData.enemyFigures)
            {
                if(enemy.getCollisionBox().intersects(friend.getCollisionBox()) && enemy.state == 1)
                {
                    enemy.state = GameFigureState.STATE_DYING;
                    if(friend instanceof Missile)
                    {
                        friend.state = GameFigureState.STATE_DONE;
                    }
                }
            }
        }
    }

}
