package models.game_settings;

import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;

public class GameSettings {
    private static GameMode gameMode = GameMode.NORMAL;
    private static DifficultyLevel difficultyLevel = DifficultyLevel.EASY;


    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void setGameMode(GameMode gameMode) {
        gameMode = gameMode;
    }

    public static DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public static void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        difficultyLevel = difficultyLevel;
    }
}
