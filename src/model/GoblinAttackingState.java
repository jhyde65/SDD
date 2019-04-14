package model;

/**
 *
 * @author david
 */
public class GoblinAttackingState implements GameFigureState
{
    float distanceX;
    float distanceY;
    
    public GoblinAttackingState()
    {
           
    }

    @Override
    public void goNext(GameFigure context)
    {
        GoblinEnemy goblin = (GoblinEnemy) context;
        distanceX = Math.abs(GameData.shooter.x - goblin.x);
        distanceY = Math.abs(GameData.shooter.y - goblin.y);
        if (goblin.getHealth() >= 4)
        {
            if (distanceX >= 50 && distanceY >= 50)
            {
                goblin.setState(new GoblinWalkingState());
            }
        } else {
            if (distanceX >= 50 && distanceY >= 50)
            {
                goblin.setState(new GoblinDyingState());
            }
        }
    }
    
}
