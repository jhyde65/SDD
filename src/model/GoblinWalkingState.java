package model;

/**
 *
 * @author david
 */
public class GoblinWalkingState implements GameFigureState
{
    
    public GoblinWalkingState()
    {
        
    }

    @Override
    public void goNext(GameFigure context) 
    {
        GoblinEnemy goblin = (GoblinEnemy) context;
        if (goblin.getHealth() >= 4)
        {
            goblin.decreaseHealth();
        } else {
            goblin.setState(new GoblinDyingState());
        }
    }
    
}
