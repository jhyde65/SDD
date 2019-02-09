package controller;

public class GameState
{
    private static boolean isSplash;

    private static boolean isFading = false;

    public static boolean PAUSED = true;

    public static void setPaused()
    {
        GameState.PAUSED = !PAUSED;
    }

    public static boolean isPaused()
    {
        return PAUSED;
    }

    public static boolean isFading() {
        return isFading;
    }

    public static void setIsFading(boolean isFading) {
        GameState.isFading = isFading;
    }
}
