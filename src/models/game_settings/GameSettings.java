package models.game_settings;

import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;

public class GameSettings {
    private static GameMode gameMode = GameMode.NORMAL;
    private static DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
    // Statics
    private static int distributedDamageStats = 0;
    private static int receivedDamageStats = 0;
    private static int receivedGoldStats = 0;
    private static int energySpentStats = 0;

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

    public static void increaseGoldStats(int gold) {
        receivedGoldStats += gold;
    }

    public static void increaseReceivedDamageStats(int damage) {
        receivedDamageStats += damage;
    }

    public static void increaseEnergySpentStats(int energy) {
        energySpentStats += energy;
    }

    public static void increaseDistributedDamageStats(int damage) {
        distributedDamageStats += damage;
    }


    public static int getEnergySpentStats() {
        return energySpentStats;
    }

    public static int getDistributedDamageStats() {
        return distributedDamageStats;
    }

    public static int getReceivedDamageStats() {
        return receivedDamageStats;
    }

    public static int getReceivedGoldStats() {
        return receivedGoldStats;
    }
}
