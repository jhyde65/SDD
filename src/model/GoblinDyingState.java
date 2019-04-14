package model;

/**
 *
 * @author david
 */
public class GoblinDyingState implements GameFigureState
{
    
    public GoblinDyingState()
    {
        
    }

    @Override
    public void goNext(GameFigure context)
    {
        GoblinEnemy goblin = (GoblinEnemy) context;
        if (goblin.getHealth() == 0)
        {
            goblin.setState(new DoneFigureState());
        }
    }
    
}
