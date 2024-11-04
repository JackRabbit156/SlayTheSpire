package models.game_settings;

import models.GameContext;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;

public class GameSettings {
    private static GameMode gameMode = GameMode.NORMAL;
    private static DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

    public static String lastSession = "";

    private static GameCounter time = new GameCounter();

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void setGameMode(GameMode mode) {
        gameMode = mode;
    }

    public static DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public static void setDifficultyLevel(DifficultyLevel difLevel) {
        difficultyLevel = difLevel;
    }

    public static void startTimer(){
        time = new GameCounter();
        time.start();
    }

    public static void restartTimer(){
        time.stopTimer();
        time = new GameCounter();
        time.start();
    }

    public static void stopTimer(){
        time.stopTimer();
    }

    public static int getTimerSeconds(){
        return time.getSeconds();
    }

    public static int getTimerMinutes(){
        return time.getMinutes();
    }

    public static int getTimerHours(){
        return time.getHours();
    }

    public static void setTimerSeconds(int seconds){
        time.setSeconds(seconds);
    }

    public static void setTimerMinutes(int minutes){
        time.setMinutes(minutes);
    }

    public static void setTimerHours(int hours){
        time.setHours(hours);
    }

}
