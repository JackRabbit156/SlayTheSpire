package models.game_settings;

import controller.cli.menus.GameMenuViewController;
import models.game_settings.structure.DifficultyLevel;
import models.game_settings.structure.GameMode;
import models.player.player_structure.Player;

/**
 * @author Löschner
 * @author Warawa
 * @author Keil
 */
public class GameSettings {
    private static GameMode gameMode = GameMode.NORMAL;
    private static DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
    private static GameMenuViewController gameMenu = new GameMenuViewController();
    // Statics
    private static int distributedDamageStats = 0;
    private static int receivedDamageStats = 0;
    private static int receivedGoldStats = 0;
    private static int energySpentStats = 0;

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

    public static int openGameMenu(Player player) {
        return gameMenu.display(player);
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
