package models.game_settings;

import models.GameContext;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;

public class GameSettings {
    private static GameMode gameMode = GameMode.NORMAL;
    private static DifficultyLevel difficultyLevel = DifficultyLevel.EASY;

    public static String lastSession = "";

    public static GameCounter time = new GameCounter();

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

}
