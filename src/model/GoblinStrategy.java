package model;

/**
 *
 * @author david
 */
public class GoblinStrategy implements Strategy
{
    private final float UNIT_TRAVEL_DISTANCE = 3.5f;

    @Override
    public void move(float x, float y, GameFigure context)
    {
        GoblinEnemy goblin = (GoblinEnemy) context;
        float shx = GameData.shooter.x;
        float shy = GameData.shooter.y;
        determineDirection(goblin, shx, shy);
        
        switch (goblin.getDirection())
        {
            case NORTH:
                if (Math.abs(shy - goblin.y) < 50)
                {
                    goblin.setAnimation(goblin.animation, goblin.stabUp);
                    goblin.setState(new GoblinAttackingState());
                } else {
                    goblin.setAnimation(goblin.animation, goblin.up);
                    goblin.setPosition(goblin.x, goblin.y - UNIT_TRAVEL_DISTANCE);
                }
                break;
            case SOUTH:
                if (Math.abs(shy - goblin.y) < 50)
                {
                    goblin.setAnimation(goblin.animation, goblin.stabDown);
                    goblin.setState(new GoblinAttackingState());
                } else {
                    goblin.setAnimation(goblin.animation, goblin.down);
                    goblin.setPosition(goblin.x, goblin.y + UNIT_TRAVEL_DISTANCE);
                }
                break;
            case WEST:
                if (Math.abs(shx - goblin.x) < 50)
                {
                    goblin.setAnimation(goblin.animation, goblin.stabLeft);
                    goblin.setState(new GoblinAttackingState());
                } else {
                    goblin.setAnimation(goblin.animation, goblin.left);
                    goblin.setPosition(goblin.x - UNIT_TRAVEL_DISTANCE, goblin.y);
                }
                break;
            case EAST:
                if (Math.abs(shx - goblin.x) < 50)
                {
                    goblin.setAnimation(goblin.animation, goblin.stabRight);
                    goblin.setState(new GoblinAttackingState());
                } else {
                    goblin.setAnimation(goblin.animation, goblin.right);
                    goblin.setPosition(goblin.x + UNIT_TRAVEL_DISTANCE, goblin.y);
                }
                break;
            default:
                break;
        }
    }
    
    private void determineDirection(GoblinEnemy goblin, float shx, float shy)
    {
        if (isNorthOfGoblin(goblin, shx, shy))
        {
            goblin.setDirection(Direction.NORTH);
        }
        else if (isSouthOfGoblin(goblin, shx, shy))
        {
            goblin.setDirection(Direction.SOUTH);
        }
        else if (isWestOfGoblin(goblin, shx, shy))
        {
            goblin.setDirection(Direction.WEST);
        }
        else if (isEastOfGoblin(goblin, shx, shy))
        {
            goblin.setDirection(Direction.EAST);
        }
    }
    
    private boolean isNorthOfGoblin(GoblinEnemy goblin, float shx, float shy)
    {
        return (goblin.y > shy && Math.abs(shy - goblin.y) > Math.abs(shx - goblin.x));
    }
    
    private boolean isSouthOfGoblin(GoblinEnemy goblin, float shx, float shy)
    {
        return (goblin.y < shy && Math.abs(shy - goblin.y) > Math.abs(shx - goblin.x));
    }
    
    private boolean isWestOfGoblin(GoblinEnemy goblin, float shx, float shy)
    {
        return (goblin.x > shx && Math.abs(shx - goblin.x) > Math.abs(shy - goblin.y));
    }
    
    private boolean isEastOfGoblin(GoblinEnemy goblin, float shx, float shy)
    {
        return (goblin.x < shx && Math.abs(shx - goblin.x) > Math.abs(shy - goblin.y));
    }
    
}
