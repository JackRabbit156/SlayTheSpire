package de.bundeswehr.auf.slaythespire.model.settings;

import de.bundeswehr.auf.slaythespire.model.settings.structure.DifficultyLevel;
import de.bundeswehr.auf.slaythespire.model.settings.structure.GameMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Löschner
 * @author Warawa
 * @author Keil
 */
public class GameSettings {

    public static final boolean DEBUG_MODE = true;
    public static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    public static String lastSession = "";

    private static DifficultyLevel difficultyLevel;
    private static int distributedDamageStats = 0;
    private static int energySpentStats = 0;
    private static GameMode gameMode = GameMode.NORMAL;
    private static int receivedDamageStats = 0;
    private static int receivedGoldStats = 0;
    private static GameCounter time = new GameCounter();

    public static DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public static void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        GameSettings.difficultyLevel = difficultyLevel;
    }

    public static int getDistributedDamageStats() {
        return distributedDamageStats;
    }

    public static int getEnergySpentStats() {
        return energySpentStats;
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void setGameMode(GameMode mode) {
        gameMode = mode;
    }

    public static int getReceivedDamageStats() {
        return receivedDamageStats;
    }

    public static int getReceivedGoldStats() {
        return receivedGoldStats;
    }

    public static int getTimerHours() {
        return time.getHours();
    }

    public static void setTimerHours(int hours) {
        time.setHours(hours);
    }

    public static int getTimerMinutes() {
        return time.getMinutes();
    }

    public static void setTimerMinutes(int minutes) {
        time.setMinutes(minutes);
    }

    public static int getTimerSeconds() {
        return time.getSeconds();
    }

    public static void setTimerSeconds(int seconds) {
        time.setSeconds(seconds);
    }

    public static void increaseDistributedDamageStats(int damage) {
        distributedDamageStats += damage;
    }

    public static void increaseEnergySpentStats(int energy) {
        energySpentStats += energy;
    }

    public static void increaseGoldStats(int gold) {
        receivedGoldStats += gold;
    }

    public static void increaseReceivedDamageStats(int damage) {
        receivedDamageStats += damage;
    }

    public static void resetStats() {
        distributedDamageStats = 0;
        receivedDamageStats = 0;
        receivedGoldStats = 0;
        energySpentStats = 0;
    }

    public static void restartTimer() {
        time.stopTimer();
        time.startTimer();
    }

    public static void setStats(int newReceivedGoldStats, int newReceivedDamageStats, int newDistributedDamageStats, int newEnergySpentStats) {
        receivedGoldStats = newReceivedGoldStats;
        receivedDamageStats = newReceivedDamageStats;
        distributedDamageStats = newDistributedDamageStats;
        energySpentStats = newEnergySpentStats;
    }

    public static void startTimer() {
        time = new GameCounter();
        time.startTimer();
    }

    public static void stop() {
        time.stopTimer();
        executorService.shutdownNow();
    }

}
