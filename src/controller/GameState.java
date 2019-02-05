package controller;

public class GameState
{
    private static boolean isSplash;

    public static boolean PAUSED = true;

    public static void setPaused()
    {
        GameState.PAUSED = !PAUSED;
    }

    public static boolean isPaused()
    {
        return PAUSED;
    }
}
